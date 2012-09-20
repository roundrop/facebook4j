package facebook4j.internal.util;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.4
 * 
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 * <li>Gave up JDK1.4 compatibility</li>
 * <li>Added {@link z_F4JInternalStringUtil#join(String[], String)}</li>
 * <li>Added {@link z_F4JInternalStringUtil#formatFacebookDatetime(Date)}</li>
 * </ul>
 */
public class z_F4JInternalStringUtil {
    private z_F4JInternalStringUtil() {
        throw new AssertionError();
    }

    public static String maskString(String str) {
        StringBuilder buf = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            buf.append("*");
        }
        return buf.toString();
    }

    public static String join(int[] follows) {
        StringBuilder buf = new StringBuilder(11 * follows.length);
        for (int follow : follows) {
            if (0 != buf.length()) {
                buf.append(",");
            }
            buf.append(follow);
        }
        return buf.toString();
    }

    public static String join(long[] follows) {
        StringBuilder buf = new StringBuilder(11 * follows.length);
        for (long follow : follows) {
            if (0 != buf.length()) {
                buf.append(",");
            }
            buf.append(follow);
        }
        return buf.toString();
    }

    public static String join(String[] track) {
        StringBuilder buf = new StringBuilder(11 * track.length);
        for (String str : track) {
            if (0 != buf.length()) {
                buf.append(",");
            }
            buf.append(str);
        }
        return buf.toString();
    }

    public static String join(String[] track, String separator) {
        StringBuilder buf = new StringBuilder(11 * track.length);
        for (String str : track) {
            if (0 != buf.length()) {
                buf.append(separator);
            }
            buf.append(str);
        }
        return buf.toString();
    }
    
    public static String formatISO8601Datetime(Calendar cal) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        df.setTimeZone(cal.getTimeZone());
        return df.format(cal.getTime());
    }
}
