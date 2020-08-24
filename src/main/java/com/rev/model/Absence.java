package com.rev.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "absences")
public class Absence {

    @Id
    @Column(columnDefinition = "serial primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;

    private int student_id;
    private Date date;
    private int excuse_id;

    public Absence(){}

    public Absence(int student_id, Date date){
        this.student_id = student_id;
        this.date = date;
    }

    public Absence(int student_id, Date date, int excuse_id){
        this.student_id = student_id;
        this.date = date;
        this.excuse_id = excuse_id;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getExcuse_id() {
        return excuse_id;
    }

    public void setExcuse_id(int excuse_id) {
        this.excuse_id = excuse_id;
    }

    @Override
    public String toString() {
        return "AbsenceModel{" +
                "aid=" + aid +
                ", student_id=" + student_id +
                ", date=" + date +
                ", excuse_id=" + excuse_id +
                '}';
    }
}
