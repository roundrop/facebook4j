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

import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class TargetingParameter implements java.io.Serializable {
    private Set<String> countries;
    private Set<TargetingGeoLocation> cities;
    private Set<TargetingGeoLocation> regions;
    private Set<String> locales;

    public class TargetingGeoLocation {
    	private String key;
    	
    	public TargetingGeoLocation(String key) {
    		this.key = key;
    	}
        public String key() {
        	return key;
        }
    }
    
    public TargetingParameter() {
    }

    public void setCountries(Set<String> countries) {
        this.countries = countries;
    }

    public void setCities(Set<TargetingGeoLocation> cities) {
        this.cities = cities;
    }

    public void setRegions(Set<TargetingGeoLocation> regions) {
        this.regions = regions;
    }

    public void setLocales(Set<Locale> locales) {
        this.locales = new LinkedHashSet<String>();
        for (Locale locale : locales) {
            this.locales.add(locale.toString());
        }
    }

    public TargetingParameter countries(Collection<String> countries) {
        if (this.countries == null) {
            this.countries = new LinkedHashSet<String>();
        }
        this.countries.addAll(countries);
        return this;
    }

    public TargetingParameter country(String country) {
        if (countries == null) {
            countries = new LinkedHashSet<String>();
        }
        countries.add(country);
        return this;
    }

    public TargetingParameter cities(Collection<TargetingGeoLocation> cities) {
        if (this.cities == null) {
            this.cities = new LinkedHashSet<TargetingGeoLocation>();
        }
        this.cities.addAll(cities);
        return this;
    }

    public TargetingParameter city(TargetingGeoLocation city) {
        if (cities == null) {
            cities = new LinkedHashSet<TargetingGeoLocation>();
        }
        cities.add(city);
        return this;
    }

    public TargetingParameter regions(Collection<TargetingGeoLocation> regions) {
        if (this.regions == null) {
            this.regions = new LinkedHashSet<TargetingGeoLocation>();
        }
        this.regions.addAll(regions);
        return this;
    }

    public TargetingParameter region(TargetingGeoLocation region) {
        if (regions == null) {
            regions = new LinkedHashSet<TargetingGeoLocation>();
        }
        regions.add(region);
        return this;
    }

    public TargetingParameter locales(Collection<Locale> locales) {
        if (this.locales == null) {
            this.locales = new LinkedHashSet<String>();
        }
        for (Locale locale : locales) {
            this.locales.add(locale.toString());
        }
        return this;
    }

    public TargetingParameter locale(Locale locale) {
        if (locales == null) {
            locales = new LinkedHashSet<String>();
        }
        locales.add(locale.toString());
        return this;
    }

    public Set<String> getCountries() {
        return countries;
    }

    public Set<TargetingGeoLocation> getCities() {
        return cities;
    }

    public Set<TargetingGeoLocation> getRegions() {
        return regions;
    }

    public Set<String> getLocales() {
        return locales;
    }

    private JSONObject json = null;

    public JSONObject asJSONObject() throws JSONException {
        if (json == null) {
            json = new JSONObject().put("geo_locations", new JSONObject(this));
        }
        return json;
    }

    public String asJSONString() throws JSONException {
        return asJSONObject().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TargetingParameter)) return false;

        TargetingParameter targetingParameter = (TargetingParameter) o;

        if (cities != null ? !cities.equals(targetingParameter.cities) : targetingParameter.cities != null) return false;
        if (countries != null ? !countries.equals(targetingParameter.countries) : targetingParameter.countries != null) return false;
        if (locales != null ? !locales.equals(targetingParameter.locales) : targetingParameter.locales != null) return false;
        if (regions != null ? !regions.equals(targetingParameter.regions) : targetingParameter.regions != null) return false;

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
        return "TargetingParameter{" +
                "countries=" + countries +
                ", cities=" + cities +
                ", regions=" + regions +
                ", locales=" + locales +
                '}';
    }
}
