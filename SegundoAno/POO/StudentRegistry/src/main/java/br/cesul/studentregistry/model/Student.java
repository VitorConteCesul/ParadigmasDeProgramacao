package br.cesul.studentregistry.model;

import java.util.UUID;

public class Student {
    private String id;
    private String name;
    private String email;
    private double grade;
    private boolean active;

    public Student() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.email = "";
        this.grade = 0.0;
        this.active = true;
    }

    public Student(String name, String email, double grade, boolean active) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
