package com.rev.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "excuses")
public class Excuse {

    @Id
    @Column(columnDefinition = "serial primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;

    @Column(nullable = false)
    private String excuse_type;

    public Excuse(){}

    public Excuse(String excuse_type) {
        this.excuse_type = excuse_type;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getExcuse_type() {
        return excuse_type;
    }

    public void setExcuse_type(String excuse_type) {
        this.excuse_type = excuse_type;
    }

    @Override
    public String toString() {
        return "Excuse{" +
                "eid=" + eid +
                ", excuse_type='" + excuse_type + '\'' +
                '}';
    }
}
