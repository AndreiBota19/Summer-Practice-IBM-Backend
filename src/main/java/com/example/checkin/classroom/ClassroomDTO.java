package com.example.checkin.classroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassroomDTO {

    private Long id;
    private String name;

    public ClassroomDTO() {
    }

    public ClassroomDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ClassroomDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
