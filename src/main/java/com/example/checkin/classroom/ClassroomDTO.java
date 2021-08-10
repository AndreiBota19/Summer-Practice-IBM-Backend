package com.example.checkin.classroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassroomDTO {

    private Long id;
    private String name;
    private Integer capacity;

    public ClassroomDTO() {
    }

    public ClassroomDTO(Long id, String name, Integer capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "ClassroomDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
