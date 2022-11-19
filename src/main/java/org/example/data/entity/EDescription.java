package org.example.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "\"description\"")
public class EDescription implements Serializable {

    @Id
    @Column(name = "\"id\"")
    private Integer desc_ID;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"othername\"")
    private String otherName;

    @Column(name = "\"images\"")
    private String images;

    @Column(name = "\"text\"")
    private String text;

    @Column(name = "\"color\"")
    private String color;

    @Column(name = "\"userid\"")
    private Integer user_ID;

    @Column(name = "\"titleid\"")
    private Integer title_ID;

    public EDescription(Integer desc_ID, String name, String otherName, String images, String text, String color, Integer user_ID, Integer title_ID) {
        this.desc_ID = desc_ID;
        this.name = name;
        this.otherName = otherName;
        this.images = images;
        this.text = text;
        this.color = color;
        this.user_ID = user_ID;
        this.title_ID = title_ID;
    }

    public EDescription(){}

    public Integer getDesc_ID() {
        return desc_ID;
    }

    public void setDesc_ID(Integer desc_ID) {
        this.desc_ID = desc_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Integer user_ID) {
        this.user_ID = user_ID;
    }

    public Integer getTitle_ID() {
        return title_ID;
    }

    public void setTitle_ID(Integer title_ID) {
        this.title_ID = title_ID;
    }

}
