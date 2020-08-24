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

    public Excuse(String excuse_type) {
        this.excuse_type = excuse_type;
    }

    //region Getters and Setters
    public int getId() {
        return eid;
    }

    public void setId(int eid) {
        this.eid = eid;
    }

    public String getExcuseType() {
        return excuse_type;
    }

    public void setExcuseType(String excuse_type) {
        this.excuse_type = excuse_type;
    }
    //endregion

    @Override
    public String toString() {
        return "Excuse{" +
                "eid=" + eid +
                ", excuse_type='" + excuse_type + '\'' +
                '}';
    }
}
