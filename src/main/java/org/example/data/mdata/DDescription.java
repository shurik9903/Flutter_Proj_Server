package org.example.data.mdata;

import org.example.data.entity.EDescription;

public class DDescription {
    private Integer desc_ID;
    private String name;
    private String otherName;
    private String images;
    private String text;
    private String color;
    private Integer user_ID;
    private Integer title_ID;

    public DDescription(){};

    public DDescription(String name, String otherName, String images, String text, String color) {
        this.name = name;
        this.otherName = otherName;
        this.images = images;
        this.text = text;
        this.color = color;
    }

    public DDescription(Integer desc_ID, String name, String otherName, String images, String text, String color, Integer user_ID, Integer title_ID) {
        this.desc_ID = desc_ID;
        this.name = name;
        this.otherName = otherName;
        this.images = images;
        this.text = text;
        this.color = color;
        this.user_ID = user_ID;
        this.title_ID = title_ID;
    }

    DDescription(EDescription desc){
        this.desc_ID = desc.getDesc_ID();
        this.name = desc.getName();
        this.otherName = desc.getOtherName();
        this.images = desc.getImages();
        this.text = desc.getText();
        this.color = desc.getColor();
        this.user_ID = desc.getUser_ID();
        this.title_ID = desc.getTitle_ID();
    }

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
