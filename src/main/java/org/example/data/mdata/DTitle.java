package org.example.data.mdata;

import org.example.data.entity.ETitle;

public class DTitle {

    private Integer title_ID;
    private String title_name;
    private Integer user_ID;
    private String Msg;

    public DTitle(){}

    public DTitle(ETitle descript){
        this.user_ID = descript.getTitle_ID();
        this.title_name = descript.getTitle_name();
        this.user_ID = descript.getUser_ID();
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

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

}
