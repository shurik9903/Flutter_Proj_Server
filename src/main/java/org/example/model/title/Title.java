package org.example.model.title;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.example.data.mdata.DDescription;
import org.example.data.mdata.DTitle;
import org.example.model.database.IDataBaseWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Title implements ITitle {

    @Inject
    private IDataBaseWork DataBaseWork;

    @Override
    public Response inputTitle(String userID, String title) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();
        Map<String, String> Data = new HashMap<>();
        Data = (Map<String, String>) jsonb.fromJson(title, Data.getClass());

        String name = Data.getOrDefault("name", "");

        try {
            if (!DataBaseWork.ping()) {
                Result.put("Msg", "Not connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            DTitle dTitle = DataBaseWork.add_title(userID, title);

            Result.put("Msg", dTitle.getMsg());
            Result.put("Title", dTitle.getTitle_name());
            Result.put("TitleID", dTitle.getTitle_ID().toString());
            return Response.ok(jsonb.toJson(Result)).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @Override
    public Response getTitle(String userID, String title) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {
            if (!DataBaseWork.ping()) {
                Result.put("Msg", "Not connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }


            DTitle dTitle = DataBaseWork.add_title(userID, title);

            Result.put("Msg", dTitle.getMsg());
            Result.put("Title", dTitle.getTitle_name());
            Result.put("TitleID", dTitle.getTitle_ID().toString());

            return Response.ok(jsonb.toJson(Result)).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }
}
