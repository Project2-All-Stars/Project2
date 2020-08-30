package com.rev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "absences")
public class Absence {

    @Id
    @Column(columnDefinition = "serial primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="student_id", referencedColumnName="sid", columnDefinition="int4", nullable = false)
    private Student student;

    @Column(nullable = false)
    private Date date;

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="excuse_id", referencedColumnName="eid", columnDefinition="int4")
    private Excuse excuse;

    public Absence() { }

    public Absence(Student student, Date date){
        this.student = student;
        this.date = date;
    }

    public Absence(Student student, Date date, Excuse excuse){
        this.student = student;
        this.date = date;
        this.excuse = excuse;
    }

    //region Getters and Setters
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Excuse getExcuse() {
        return excuse;
    }

    public void setExcuse(Excuse excuse) {
        this.excuse = excuse;
    }
    //endregion

    @Override
    public String toString() {
        return "AbsenceModel{" +
                "aid=" + aid +
                ", student_id=" + student.getId() +
                ", date=" + date +
                ", excuse_id=" + excuse.getId() +
                '}';
    }
}
