package org.example.data.mdata;

import org.example.data.entity.EDescription;

import java.util.Arrays;

public class DDescription {
    private String desc_ID;
    private String name;
    private String otherName;
    private String images;
    private String text;
    private String color;
    private String user_ID;
    private String title_ID;
    private String Msg;

    public DDescription(){};

    public DDescription(String name, String otherName, String images, String text, String color) {
        this.name = name;
        this.otherName = otherName;
        this.images = images;
        this.text = text;
        this.color = color;
    }

    public DDescription(String desc_ID, String name, String otherName, String images, String text, String color, String user_ID, String title_ID) {
        this.desc_ID = desc_ID;
        this.name = name;
        this.otherName = otherName;
        this.images = images;
        this.text = text;
        this.color = color;
        this.user_ID = user_ID;
        this.title_ID = title_ID;
    }

    public DDescription(EDescription desc){
        this.desc_ID = desc.getDesc_ID().toString();
        this.name = desc.getName();
        this.otherName = desc.getOtherName();
        this.images = desc.getImages();
        this.text = desc.getText();
        this.color = desc.getColor();
        this.user_ID = desc.getUser_ID().toString();
        this.title_ID = desc.getTitle_ID().toString();
    }

    public String getDesc_ID() {
        return desc_ID;
    }

    public void setDesc_ID(String desc_ID) {
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

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getTitle_ID() {
        return title_ID;
    }

    public void setTitle_ID(String title_ID) {
        this.title_ID = title_ID;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    @Override
    public String toString(){
        return "{" +
                " \"id\":" + "\"" + desc_ID + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"otherName\":" + Arrays.toString(Arrays.stream(otherName.split(",")).map(s -> ("\"" + s + "\"")).toArray()) +
                ", \"images\":" + Arrays.toString(Arrays.stream(images.split(",")).map(s -> ("\"" + s + "\"")).toArray()) +
                ", \"text\":" + "\"" + text + "\"" +
                ", \"color\":" + "\"" + color + "\"" +
                ", \"titleid\":" + "\"" + title_ID + "\"" +
                ", \"userid\":" + "\"" + title_ID + "\"" +
                ", \"msg\":" +  Msg +
                "}";
    }
}
