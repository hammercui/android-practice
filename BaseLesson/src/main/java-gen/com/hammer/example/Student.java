package com.hammer.example;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "STUDENT".
 */
public class Student {

    private Long id;
    /** Not-null value. */
    private String studentId;
    private String name;
    private Integer sex;
    private String address;
    private String classes;

    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String studentId, String name, Integer sex, String address, String classes) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.classes = classes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getStudentId() {
        return studentId;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

}
