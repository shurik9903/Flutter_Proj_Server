package org.example.model.database;

import org.example.data.mdata.DDescription;
import org.example.data.mdata.DTitle;

public interface IDataBaseWork {
    Object login(String login, String Password);
    String add_user(String Mail, String Login, String Password);
    String add_title(String userID, String title, StringBuilder Msg);
    String add_description(String UserID, DDescription description, StringBuilder Msg, String titleID);

    boolean ping();

}
