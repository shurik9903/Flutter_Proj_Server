package org.example.model.title;

import jakarta.ws.rs.core.Response;

public interface ITitle{

    Response inputTitle(String userID, String title);

    Response getTitle(String userID, String title);
}
