package com.rev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(columnDefinition = "serial primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;

    private String rname;

    @JsonIgnore
    @OneToOne(mappedBy = "homeRoom")
    private Teacher teacher;

    public Room() { }

    public Room(String rname) {
        this.rname = rname;
    }

    //region Getters and Setters
    public int getId() {
        return rid;
    }

    public void setId(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return rname;
    }

    public void setName(String rname) {
        this.rname = rname;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    //endregion

    @Override
    public String toString() {
        return "Room{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", teacher=" + teacher.getId() +
                '}';
    }
}
