package com.example.demo.model;

public class UserDTO {

    private final String name;

    private final int age;
    private final int studentid;

    public UserDTO(String name, int age,int studentid) {
        this.name = name;
        this.age = age;
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getStudentid() {
        return studentid;
    }
}
