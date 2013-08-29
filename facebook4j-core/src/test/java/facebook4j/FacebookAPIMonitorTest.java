package facebook4j;

import facebook4j.management.APIStatisticsMBean;
import facebook4j.management.InvocationStatistics;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.fail;

public class FacebookAPIMonitorTest {
    private static final String BASE_URL = "https://graph.facebook.com";
    private FacebookAPIMonitor apiMonitor;

    @Before
    public void setUp() throws Exception {
        apiMonitor = FacebookAPIMonitor.getInstance();
    }

    @Test
    public void methodCalled_get() throws Exception {
        final String path = "/me/posts?access_token=access_token";
        apiMonitor.methodCalled(BASE_URL + path, 1000l, true);
        checkMethodStats("/me/posts");
    }

    @Test
    public void methodCalled_reading() throws Exception {
        final String path = "/me/posts?limit=10&access_token=access_token";
        apiMonitor.methodCalled(BASE_URL + path, 1000l, true);
        checkMethodStats("/me/posts");
    }

    @Test
    public void methodCalled_fql() throws Exception {
        final String path = "/fql?q=SELECT+uid2+FROM+friend+WHERE+uid1=me()&access_token=access_token";
        apiMonitor.methodCalled(BASE_URL + path, 1000l, true);
        checkMethodStats("/fql");
    }

    @Test
    public void methodCalled_search() throws Exception {
        final String path = "/search?q=watermelon&access_token=access_token&type=post";
        apiMonitor.methodCalled(BASE_URL + path, 1000l, true);
        checkMethodStats("/search");
    }

    // Helper methods
    private void checkMethodStats(String path) {
        APIStatisticsMBean statistics = apiMonitor.getStatistics();
        Iterator<? extends InvocationStatistics> itr = statistics.getInvocationStatistics().iterator();
        InvocationStatistics methodStats = null;
        while (itr.hasNext()) {
            InvocationStatistics s = itr.next();

            if (s.getName().equals(path)) {
                methodStats = s;
                break;
            }
        }

        if (methodStats == null) {
            fail("No stats available for method with name '" + path + "'");
        }
    }
}
