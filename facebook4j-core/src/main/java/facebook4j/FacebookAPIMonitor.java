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
package facebook4j;

import facebook4j.internal.logging.Logger;
import facebook4j.management.APIStatistics;
import facebook4j.management.APIStatisticsMBean;
import facebook4j.management.APIStatisticsOpenMBean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Singleton instance of all Twitter API monitoring. Handles URL parsing and "wire off" logic.
 * We could avoid using a singleton here if Twitter objects were instantiated
 * from a factory.
 *
 * @author Nick Dellamaggiore (nick.dellamaggiore <at> gmail.com)
 * @since Twitter4J 2.2.1
 *
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 *   <li>Changed for Facebook API</li>
 * </ul>
 */
public class FacebookAPIMonitor {
    private static final Logger logger = Logger.getLogger(FacebookAPIMonitor.class);

    private static final FacebookAPIMonitor SINGLETON = new FacebookAPIMonitor();

    private static final APIStatistics STATISTICS = new APIStatistics(100);


    static {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName oName = new ObjectName("facebook4j.mbean:type=APIStatisticsOpenMBean");
            APIStatisticsOpenMBean openMBean = new APIStatisticsOpenMBean(STATISTICS);
            mbs.registerMBean(openMBean, oName);
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * Constructor
     */
    private FacebookAPIMonitor() {
    }

    public static FacebookAPIMonitor getInstance() {
        return SINGLETON;
    }

    public APIStatisticsMBean getStatistics() {
        return STATISTICS;
    }

    void methodCalled(String facebookUrl, long elapsedTime, boolean success) {
        try {
            URL url = new URL(facebookUrl);
            String method = url.getPath();
            STATISTICS.methodCalled(method, elapsedTime, success);
        } catch (MalformedURLException ignore) {}
    }

}
