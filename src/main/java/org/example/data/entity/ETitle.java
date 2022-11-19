package org.example.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "\"user_title\"")
public class ETitle implements Serializable {

    @Id
    @Column(name = "\"id\"")
    private Integer title_ID;

    @Column(name = "\"title\"")
    private String title_name;

    @Column(name = "\"userid\"")
    private Integer user_ID;

    public ETitle(){}

    public ETitle(String title_name, Integer user_ID){

        this.title_name = title_name;
        this.user_ID = user_ID;
    }

    public ETitle(int ID, String title_name, Integer user_ID){

        this.title_ID = ID;
        this.title_name = title_name;
        this.user_ID = user_ID;
    }

    public Integer getTitle_ID() {
        return title_ID;
    }

    public void setTitle_ID(Integer title_ID) {
        this.title_ID = title_ID;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public Integer getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Integer user_ID) {
        this.user_ID = user_ID;
    }

}
