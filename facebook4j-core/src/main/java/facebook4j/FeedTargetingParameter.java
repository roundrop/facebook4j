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

import java.util.Set;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class FeedTargetingParameter extends TargetingParameter implements java.io.Serializable {
    private static final long serialVersionUID = -4978831942434930510L;

    private Gender genders;
    private Integer ageMax;
    private Integer ageMin;
    private Set<Integer> relationshipStatuses;
    private Set<Integer> interestedIn;
    private Set<Integer> educationStatuses;
    private Set<IdNameEntity> workNetworks;
    private Set<IdNameEntity> collegeNetworks;
    private Set<String> collegeMajors;
    private Set<Integer> collegeYears;

    public FeedTargetingParameter() {
    }

    public void setGenders(Gender genders) {
        this.genders = genders;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public void setRelationshipStatuses(Set<Integer> relationshipStatuses) {
        this.relationshipStatuses = relationshipStatuses;
    }

    public void setInterestedIn(Set<Integer> interestedIn) {
        this.interestedIn = interestedIn;
    }

    public void setEducationStatuses(Set<Integer> educationStatuses) {
        this.educationStatuses = educationStatuses;
    }

    public void setWorkNetworks(Set<IdNameEntity> workNetworks) {
        this.workNetworks = workNetworks;
    }

    public void setCollegeNetworks(Set<IdNameEntity> collegeNetworks) {
        this.collegeNetworks = collegeNetworks;
    }

    public void setCollegeMajors(Set<String> collegeMajors) {
        this.collegeMajors = collegeMajors;
    }

    public void setCollegeYears(Set<Integer> collegeYears) {
        this.collegeYears = collegeYears;
    }

    public FeedTargetingParameter genders(Gender genders) {
        setGenders(genders);
        return this;
    }

    public FeedTargetingParameter ageMax(Integer ageMax) {
        setAgeMax(ageMax);
        return this;
    }

    public FeedTargetingParameter ageMin(Integer ageMin) {
        setAgeMin(ageMin);
        return this;
    }

    public FeedTargetingParameter relationshipStatuses(Set<Integer> relationshipStatuses) {
        setRelationshipStatuses(relationshipStatuses);
        return this;
    }

    public FeedTargetingParameter interestedIn(Set<Integer> interestedIn) {
        setInterestedIn(interestedIn);
        return this;
    }

    public FeedTargetingParameter educationStatuses(Set<Integer> educationStatuses) {
        setEducationStatuses(educationStatuses);
        return this;
    }

    public FeedTargetingParameter workNetworks(Set<IdNameEntity> workNetworks) {
        setWorkNetworks(workNetworks);
        return this;
    }

    public FeedTargetingParameter collegeNetworks(Set<IdNameEntity> collegeNetworks) {
        setCollegeNetworks(collegeNetworks);
        return this;
    }

    public FeedTargetingParameter collegeMajors(Set<String> collegeMajors) {
        setCollegeMajors(collegeMajors);
        return this;
    }

    public FeedTargetingParameter collegeYears(Set<Integer> collegeYears) {
        setCollegeYears(collegeYears);
        return this;
    }

    /* getters for asJSONObject() */

    public Gender getGenders() {
        return genders;
    }

    public Integer getAge_max() {
        return ageMax;
    }

    public Integer getAge_min() {
        return ageMin;
    }

    public Set<Integer> getRelationship_statuses() {
        return relationshipStatuses;
    }

    public Set<Integer> getInterested_in() {
        return interestedIn;
    }

    public Set<Integer> getEducation_statuses() {
        return educationStatuses;
    }

    public Set<IdNameEntity> getWork_networks() {
        return workNetworks;
    }

    public Set<IdNameEntity> getCollege_networks() {
        return collegeNetworks;
    }

    public Set<String> getCollege_majors() {
        return collegeMajors;
    }

    public Set<Integer> getCollege_years() {
        return collegeYears;
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
        if (!(o instanceof FeedTargetingParameter)) return false;
        if (!super.equals(o)) return false;

        FeedTargetingParameter that = (FeedTargetingParameter) o;

        if (ageMax != null ? !ageMax.equals(that.ageMax) : that.ageMax != null) return false;
        if (ageMin != null ? !ageMin.equals(that.ageMin) : that.ageMin != null) return false;
        if (collegeMajors != null ? !collegeMajors.equals(that.collegeMajors) : that.collegeMajors != null)
            return false;
        if (collegeNetworks != null ? !collegeNetworks.equals(that.collegeNetworks) : that.collegeNetworks != null)
            return false;
        if (collegeYears != null ? !collegeYears.equals(that.collegeYears) : that.collegeYears != null) return false;
        if (educationStatuses != null ? !educationStatuses.equals(that.educationStatuses) : that.educationStatuses != null)
            return false;
        if (genders != that.genders) return false;
        if (interestedIn != null ? !interestedIn.equals(that.interestedIn) : that.interestedIn != null) return false;
        if (relationshipStatuses != null ? !relationshipStatuses.equals(that.relationshipStatuses) : that.relationshipStatuses != null)
            return false;
        if (workNetworks != null ? !workNetworks.equals(that.workNetworks) : that.workNetworks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (genders != null ? genders.hashCode() : 0);
        result = 31 * result + (ageMax != null ? ageMax.hashCode() : 0);
        result = 31 * result + (ageMin != null ? ageMin.hashCode() : 0);
        result = 31 * result + (relationshipStatuses != null ? relationshipStatuses.hashCode() : 0);
        result = 31 * result + (interestedIn != null ? interestedIn.hashCode() : 0);
        result = 31 * result + (educationStatuses != null ? educationStatuses.hashCode() : 0);
        result = 31 * result + (workNetworks != null ? workNetworks.hashCode() : 0);
        result = 31 * result + (collegeNetworks != null ? collegeNetworks.hashCode() : 0);
        result = 31 * result + (collegeMajors != null ? collegeMajors.hashCode() : 0);
        result = 31 * result + (collegeYears != null ? collegeYears.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FeedTargetingParameter{" +
                "genders=" + genders +
                ", ageMax=" + ageMax +
                ", ageMin=" + ageMin +
                ", relationshipStatuses=" + relationshipStatuses +
                ", interestedIn=" + interestedIn +
                ", educationStatuses=" + educationStatuses +
                ", workNetworks=" + workNetworks +
                ", collegeNetworks=" + collegeNetworks +
                ", collegeMajors=" + collegeMajors +
                ", collegeYears=" + collegeYears +
                '}';
    }


    /**
     * @since Facebook4J 2.0.0
     */
    public static enum Gender {
        Male(1),
        Female(2),
        ;

        private int value;

        private Gender(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
