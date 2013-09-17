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

package facebook4j.internal.json;

import facebook4j.FacebookException;
import facebook4j.Targeting;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class TargetingJSONImpl implements Targeting, java.io.Serializable {
    private static final long serialVersionUID = 87174149234341549L;

    private List<String> countries;
    private List<String> cities;
    private List<String> regions;
    private List<String> locales;

    /*package*/TargetingJSONImpl(JSONObject json) throws FacebookException {
        try {
            if (!json.isNull("countries")) {
                JSONArray countriesJSONArray = json.getJSONArray("countries");
                countries = new ArrayList<String>(countriesJSONArray.length());
                for (int i = 0; i < countriesJSONArray.length(); i++) {
                    countries.add((String) countriesJSONArray.get(i));
                }
            } else {
                countries = new ArrayList<String>(0);
            }
            if (!json.isNull("cities")) {
                JSONArray citiesJSONArray = json.getJSONArray("cities");
                cities = new ArrayList<String>(citiesJSONArray.length());
                for (int i = 0; i < citiesJSONArray.length(); i++) {
                    cities.add((String) citiesJSONArray.get(i));
                }
            } else {
                cities = new ArrayList<String>(0);
            }
            if (!json.isNull("regions")) {
                JSONArray regionsJSONArray = json.getJSONArray("regions");
                regions = new ArrayList<String>(regionsJSONArray.length());
                for (int i = 0; i < regionsJSONArray.length(); i++) {
                    regions.add((String) regionsJSONArray.get(i));
                }
            } else {
                regions = new ArrayList<String>(0);
            }
            if (!json.isNull("locales")) {
                JSONArray localesJSONArray = json.getJSONArray("locales");
                locales = new ArrayList<String>(localesJSONArray.length());
                for (int i = 0; i < localesJSONArray.length(); i++) {
                    locales.add((String) localesJSONArray.get(i));
                }
            } else {
                locales = new ArrayList<String>(0);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getCities() {
        return cities;
    }

    public List<String> getRegions() {
        return regions;
    }

    public List<String> getLocales() {
        return locales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TargetingJSONImpl)) return false;

        TargetingJSONImpl that = (TargetingJSONImpl) o;

        if (cities != null ? !cities.equals(that.cities) : that.cities != null) return false;
        if (countries != null ? !countries.equals(that.countries) : that.countries != null) return false;
        if (locales != null ? !locales.equals(that.locales) : that.locales != null) return false;
        if (regions != null ? !regions.equals(that.regions) : that.regions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countries != null ? countries.hashCode() : 0;
        result = 31 * result + (cities != null ? cities.hashCode() : 0);
        result = 31 * result + (regions != null ? regions.hashCode() : 0);
        result = 31 * result + (locales != null ? locales.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TargetingJSONImpl{" +
                "countries=" + countries +
                ", cities=" + cities +
                ", regions=" + regions +
                ", locales=" + locales +
                '}';
    }
}
