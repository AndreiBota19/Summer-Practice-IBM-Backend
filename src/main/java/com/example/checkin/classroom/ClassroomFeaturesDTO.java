package com.example.checkin.classroom;

import java.util.HashSet;
import java.util.Set;

public class ClassroomFeaturesDTO {
    private Long id;
    private Set<Long> features = new HashSet<>();

    public ClassroomFeaturesDTO() {
    }

    public ClassroomFeaturesDTO(Long id, Set<Long> features) {
        this.id = id;
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Long> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "ClassroomFeaturesDTO{" +
                "id=" + id +
                ", features=" + features +
                '}';
    }
}
