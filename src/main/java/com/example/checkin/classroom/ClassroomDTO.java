package com.example.checkin.classroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassroomDTO {

    private Long id;
    private String name;
    private String location;
    private Integer capacity;
    private Set<Long> features = new HashSet<>();

    public ClassroomDTO() {
    }

    public ClassroomDTO(Long id, String name, String location, Integer capacity, Set<Long> features) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<Long> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Long> f_ids) {
        this.features = f_ids;
    }

    @Override
    public String toString() {
        return "ClassroomDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", features=" + features +
                '}';
    }
}
