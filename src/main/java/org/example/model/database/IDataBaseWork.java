package org.example.model.database;

import org.example.data.mdata.DDescription;
import org.example.data.mdata.DTitle;

import java.util.ArrayList;

public interface IDataBaseWork {
    Object login(String login, String Password);
    String add_user(String Mail, String Login, String Password);
    DTitle add_title(String userID, String title);

    DTitle get_title(String userID, String titleName);

    DDescription put_description(DDescription description);

    DDescription add_description( DDescription description);

    ArrayList<DDescription> get_description(String UserID, String TitleID, StringBuilder msg);

    boolean ping();

}
