package com.rev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(columnDefinition = "serial primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String lname;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="teacher_id", referencedColumnName="tid", columnDefinition="int4")
    private Teacher homeRoomTeacher;

    @Lob
    @Column(columnDefinition="oid")
    private byte[] picture;

    @Column(nullable = false)
    private int grade;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Absence> absences;

    public Student() { }

    public Student(String fname, String lname, Teacher homeRoomTeacher, int grade) {
        this.fname = fname;
        this.lname = lname;
        this.homeRoomTeacher = homeRoomTeacher;
        this.grade = grade;
    }

    public Student(String fname, String lname, Teacher homeRoomTeacher, byte[] picture, int grade) {
        this.fname = fname;
        this.lname = lname;
        this.homeRoomTeacher = homeRoomTeacher;
        this.picture = picture;
        this.grade = grade;
    }

    //region Getters and Setters
    public int getId() {
        return sid;
    }

    public void setId(int sid) {
        this.sid = sid;
    }

    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

    public String getLName() {
        return lname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }

    public Teacher getHomeRoomTeacher() {
        return homeRoomTeacher;
    }

    public void setHomeRoomTeacher(Teacher homeRoomTeacher) {
        this.homeRoomTeacher = homeRoomTeacher;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @JsonIgnore
    public Set<Absence> getAbsences() {
        return absences;
    }

    @JsonIgnore
    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }
    //endregion

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", homeRoomTeacher=" + homeRoomTeacher +
                ", grade=" + grade +
                '}';
    }
}
