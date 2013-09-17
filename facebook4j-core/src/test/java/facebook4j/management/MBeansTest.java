/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package facebook4j.management;

import org.junit.Test;

import javax.management.AttributeList;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Unit tests for APIStatistics, MBeans
 *
 * @author Nick Dellamaggiore (nick.dellamaggiore <at> gmail.com)
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 *   <li>Updates to JUnit4</li>
 * </ul>
 */
public class MBeansTest {
    /**
     * Tests statistics calculation for a single method
     */
    @Test
    public void testInvocationStatisticsCalculator() throws Exception {
        InvocationStatisticsCalculator calc = new InvocationStatisticsCalculator("foo", 5);

        assertThat(calc.getName(), is("foo"));
        checkCalculator(calc, 0, 0, 0, 0);

        calc.increment(100, true);
        checkCalculator(calc, 1, 0, 100, 100);

        calc.increment(100, true);
        checkCalculator(calc, 2, 0, 200, 100);

        calc.increment(400, false);
        checkCalculator(calc, 3, 1, 600, 200);

        // test rollover for average
        calc.increment(200, true);
        calc.increment(200, true);
        calc.increment(200, true);
        checkCalculator(calc, 6, 1, 1200, 220);

        for (int i = 0; i < 1000; i++) {
            calc.increment(i, true);
        }
        checkCalculator(calc, 1006, 1, 500700, 997);

        // test reset, sure it still works after resetting
        calc.reset();
        checkCalculator(calc, 0, 0, 0, 0);

        calc.increment(100, true);
        checkCalculator(calc, 1, 0, 100, 100);
    }

    /**
     * Tests statistics calculation/aggregation for an entire API
     */
    @Test
    public void testAPIStatistics() throws Exception {
        APIStatistics stats = new APIStatistics(5);

        checkCalculator(stats, 0, 0, 0, 0);
        assertFalse(stats.getInvocationStatistics().iterator().hasNext());

        stats.methodCalled("foo", 100, true);
        checkCalculator(stats, 1, 0, 100, 100);
        checkMethodStats(stats, "foo", 1, 0, 100, 100);

        stats.methodCalled("bar", 100, true);
        checkCalculator(stats, 2, 0, 200, 100);
        checkMethodStats(stats, "foo", 1, 0, 100, 100);
        checkMethodStats(stats, "bar", 1, 0, 100, 100);

        stats.methodCalled("foo", 400, false);
        checkCalculator(stats, 3, 1, 600, 200);
        checkMethodStats(stats, "foo", 2, 1, 500, 250);
        checkMethodStats(stats, "bar", 1, 0, 100, 100);

        // test rollover for average
        stats.methodCalled("foo", 200, true);
        stats.methodCalled("bar", 200, true);
        stats.methodCalled("baz", 200, true);
        checkCalculator(stats, 6, 1, 1200, 220);
        checkMethodStats(stats, "foo", 3, 1, 700, 233);
        checkMethodStats(stats, "bar", 2, 0, 300, 150);
        checkMethodStats(stats, "baz", 1, 0, 200, 200);

        // test reset, sure it still works after resetting
        stats.reset();
        checkCalculator(stats, 0, 0, 0, 0);
        assertFalse(stats.getInvocationStatistics().iterator().hasNext());

        stats.methodCalled("foo", 100, true);
        checkCalculator(stats, 1, 0, 100, 100);
        checkMethodStats(stats, "foo", 1, 0, 100, 100);
    }

    /**
     * Tests exposure of API statistics via a dynamic MBean
     */
    @Test
    public void testAPIStatisticsOpenMBean() throws Exception {
        APIStatistics stats = new APIStatistics(5);
        APIStatisticsOpenMBean openMBean = new APIStatisticsOpenMBean(stats);

        // sanity check to ensure metadata accurately describes dynamic attributes
        MBeanInfo info = openMBean.getMBeanInfo();
        assertThat(info.getAttributes().length, is(5));
        assertThat(info.getOperations().length, is(1));

        List<String> attrNames = new ArrayList<String>();
        for (MBeanAttributeInfo attr : info.getAttributes()) {
            assertNotNull(openMBean.getAttribute(attr.getName()));
            attrNames.add(attr.getName());
        }
        AttributeList attrList = openMBean.getAttributes(attrNames.toArray(new String[attrNames.size()]));
        assertNotNull(attrList);
        assertThat(attrList.size(), is(5));

        // check stats (empty case)
        Long callCount = (Long) openMBean.getAttribute("callCount");
        assertThat(callCount.longValue(), is(0l));
        Long errorCount = (Long) openMBean.getAttribute("errorCount");
        assertThat(callCount.longValue(), is(0l));
        Long totalTime = (Long) openMBean.getAttribute("totalTime");
        assertThat(totalTime.longValue(), is(0l));
        Long averageTime = (Long) openMBean.getAttribute("averageTime");
        assertThat(averageTime.longValue(), is(0l));

        // check table (empty case)
        TabularData table = (TabularData) openMBean.getAttribute("statisticsTable");
        assertTrue(table.isEmpty());

        stats.methodCalled("foo", 100, true);

        // check stats (populated case)
        callCount = (Long) openMBean.getAttribute("callCount");
        assertThat(callCount.longValue(), is(1l));
        errorCount = (Long) openMBean.getAttribute("errorCount");
        assertThat(errorCount.longValue(), is(0l));
        totalTime = (Long) openMBean.getAttribute("totalTime");
        assertThat(totalTime.longValue(), is(100l));
        averageTime = (Long) openMBean.getAttribute("averageTime");
        assertThat(averageTime.longValue(), is(100l));

        // check table (populated  case)
        table = (TabularData) openMBean.getAttribute("statisticsTable");
        assertFalse(table.isEmpty());
        assertThat(table.keySet().size(), is(1));

        CompositeData data = table.get(new Object[]{"foo"});
        assertNotNull(data);
        String[] columnNames = new String[]{"methodName", "callCount", "totalTime", "avgTime"};
        Object[] columnValues = data.getAll(columnNames);
        assertThat(columnValues.length, is(columnNames.length));
        assertThat(columnValues[0], instanceOf(String.class));
        assertThat(columnValues[0].toString(), is("foo"));
        assertThat(((Long) columnValues[1]).longValue(), is(1L));
        assertThat(((Long) columnValues[2]).longValue(), is(100L));
        assertThat(((Long) columnValues[3]).longValue(), is(100L));

        // check reset
        openMBean.invoke("reset", new Object[0], new String[0]);
        checkCalculator(stats, 0, 0, 0, 0);
        assertFalse(stats.getInvocationStatistics().iterator().hasNext());
    }

    // *****************
    // Helper methods
    // *****************

    private void checkMethodStats(APIStatistics apiStats,
                                  String methodName,
                                  long callCount,
                                  long errorCount,
                                  long totalTime,
                                  long avgTime) {
        InvocationStatistics methodStats = null;

        Iterator<? extends InvocationStatistics> itr = apiStats.getInvocationStatistics().iterator();
        while (itr.hasNext()) {
            InvocationStatistics s = itr.next();

            if (s.getName().equals(methodName)) {
                methodStats = s;
                break;
            }
        }

        if (methodStats != null) {
            checkCalculator(methodStats, callCount, errorCount, totalTime, avgTime);
        } else {
            fail("No stats available for method with name '" + methodName + "'");
        }
    }

    private void checkCalculator(InvocationStatistics calc,
                                 long callCount,
                                 long errorCount,
                                 long totalTime,
                                 long avgTime) {
        assertThat(calc.getCallCount(), is(callCount));
        assertThat(calc.getErrorCount(), is(errorCount));
        assertThat(calc.getTotalTime(), is(totalTime));
        assertThat(calc.getAverageTime(), is(avgTime));
    }
}
