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

import facebook4j.internal.org.json.JSONObject;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class Targeting implements java.io.Serializable {
    private Set<String> countries;
    private Set<String> cities;
    private Set<String> regions;
    private Set<String> locales;

    public Targeting() {
    }

    public void setCountries(Set<String> countries) {
        this.countries = countries;
    }

    public void setCities(Set<String> cities) {
        this.cities = cities;
    }

    public void setRegions(Set<String> regions) {
        this.regions = regions;
    }

    public void setLocales(Set<Locale> locales) {
        this.locales = new LinkedHashSet<String>();
        for (Locale locale : locales) {
            this.locales.add(locale.toString());
        }
    }

    public Targeting countries(Collection<String> countries) {
        if (this.countries == null) {
            this.countries = new LinkedHashSet<String>();
        }
        this.countries.addAll(countries);
        return this;
    }

    public Targeting country(String country) {
        if (countries == null) {
            countries = new LinkedHashSet<String>();
        }
        countries.add(country);
        return this;
    }

    public Targeting cities(Collection<String> cities) {
        if (this.cities == null) {
            this.cities = new LinkedHashSet<String>();
        }
        this.cities.addAll(cities);
        return this;
    }

    public Targeting city(String city) {
        if (cities == null) {
            cities = new LinkedHashSet<String>();
        }
        cities.add(city);
        return this;
    }

    public Targeting regions(Collection<String> regions) {
        if (this.regions == null) {
            this.regions = new LinkedHashSet<String>();
        }
        this.regions.addAll(regions);
        return this;
    }

    public Targeting region(String region) {
        if (regions == null) {
            regions = new LinkedHashSet<String>();
        }
        regions.add(region);
        return this;
    }

    public Targeting locales(Collection<Locale> locales) {
        if (this.locales == null) {
            this.locales = new LinkedHashSet<String>();
        }
        for (Locale locale : locales) {
            this.locales.add(locale.toString());
        }
        return this;
    }

    public Targeting locale(Locale locale) {
        if (locales == null) {
            locales = new LinkedHashSet<String>();
        }
        locales.add(locale.toString());
        return this;
    }

    public Set<String> getCountries() {
        return countries;
    }

    public Set<String> getCities() {
        return cities;
    }

    public Set<String> getRegions() {
        return regions;
    }

    public Set<String> getLocales() {
        return locales;
    }


    private JSONObject json = null;

    public JSONObject asJSONObject() {
        if (json == null) {
            json = new JSONObject(this);
        }
        return json;
    }

    public String asJSONString() {
        return asJSONObject().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Targeting)) return false;

        Targeting targeting = (Targeting) o;

        if (cities != null ? !cities.equals(targeting.cities) : targeting.cities != null) return false;
        if (countries != null ? !countries.equals(targeting.countries) : targeting.countries != null) return false;
        if (locales != null ? !locales.equals(targeting.locales) : targeting.locales != null) return false;
        if (regions != null ? !regions.equals(targeting.regions) : targeting.regions != null) return false;

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
        return "Targeting{" +
                "countries=" + countries +
                ", cities=" + cities +
                ", regions=" + regions +
                ", locales=" + locales +
                '}';
    }
}
