package com.rev.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @Column(columnDefinition = "serial primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String lname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "rid", nullable = false)
    private Room homeRoom;

    @OneToMany(mappedBy = "homeRoomTeacher")
    private Set<Student> homeRoomStudents;

    public Teacher(String fname, String lname, Room homeRoom) {
        this.fname = fname;
        this.lname = lname;
        this.homeRoom = homeRoom;
    }

    //region Getters and Setters
    public int getId() {
        return tid;
    }

    public void setId(int tid) {
        this.tid = tid;
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

    public Room getHomeRoom() {
        return homeRoom;
    }

    public void setHomeRoom(Room homeRoom) {
        this.homeRoom = homeRoom;
    }

    public Set<Student> getStudents() {
        return homeRoomStudents;
    }

    public void setStudents(Set<Student> homeRoomStudents) {
        this.homeRoomStudents = homeRoomStudents;
    }
    //endregion

    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", homeRoom=" + homeRoom +
                '}';
    }
}
