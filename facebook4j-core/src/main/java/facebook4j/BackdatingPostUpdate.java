/*
 * Copyright 2012 Ryuji Yamashita
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

import facebook4j.internal.http.HttpParameter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class BackdatingPostUpdate extends PostUpdate {
    private static final long serialVersionUID = -3273911742995980650L;

    private Integer backdatedTime;
    private BackdatedTimeGranularity backdatedTimeGranularity;

    public BackdatingPostUpdate(String message) {
        super(message);
    }

    public BackdatingPostUpdate(URL link) {
        super(link);
    }

    public Integer getBackdatedTime() {
        return backdatedTime;
    }

    public void setBackdatedTime(Integer backdatedTime) {
        this.backdatedTime = backdatedTime;
    }

    public void setBackdatedTime(Date backdatedTime) {
        long time = backdatedTime.getTime() / 1000L;
        setBackdatedTime(Long.valueOf(time).intValue());
    }

    public BackdatingPostUpdate backdatedTime(Integer backdatedTime) {
        setBackdatedTime(backdatedTime);
        return this;
    }

    public BackdatingPostUpdate backdatedTime(Date backdatedTime) {
        long time = backdatedTime.getTime() / 1000L;
        return backdatedTime(Long.valueOf(time).intValue());
    }

    public BackdatedTimeGranularity getBackdatedTimeGranularity() {
        return backdatedTimeGranularity;
    }

    public void setBackdatedTimeGranularity(BackdatedTimeGranularity backdatedTimeGranularity) {
        this.backdatedTimeGranularity = backdatedTimeGranularity;
    }

    public BackdatingPostUpdate backdatedTimeGranularity(BackdatedTimeGranularity backdatedTimeGranularity) {
        setBackdatedTimeGranularity(backdatedTimeGranularity);
        return this;
    }

    @Override
    HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>(Arrays.asList(super.asHttpParameterArray()));
        if (backdatedTime != null) {
            params.add(new HttpParameter("backdated_time", backdatedTime));
        }
        String _backdatedTimeGranularity;
        if (backdatedTimeGranularity != null) {
            _backdatedTimeGranularity = backdatedTimeGranularity.toString();
        } else {
            _backdatedTimeGranularity = BackdatedTimeGranularity.none.toString();
        }
        params.add(new HttpParameter("backdated_time_granularity", _backdatedTimeGranularity));
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BackdatingPostUpdate)) return false;
        if (!super.equals(o)) return false;

        BackdatingPostUpdate that = (BackdatingPostUpdate) o;

        if (backdatedTime != null ? !backdatedTime.equals(that.backdatedTime) : that.backdatedTime != null)
            return false;
        if (backdatedTimeGranularity != that.backdatedTimeGranularity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (backdatedTime != null ? backdatedTime.hashCode() : 0);
        result = 31 * result + (backdatedTimeGranularity != null ? backdatedTimeGranularity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BackdatingPostUpdate{" +
                "backdatedTime=" + backdatedTime +
                ", backdatedTimeGranularity=" + backdatedTimeGranularity +
                "} extends " + super.toString();
    }
}
