package org.example.model.description;

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

public class Descript implements IDescript {

    @Inject
    private IDataBaseWork DataBaseWork;

    @Override
    public Response putDescript(String descriptions){
        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();
        Map<String, String> Data = new HashMap<>();
        Data = (Map<String, String>) jsonb.fromJson(descriptions, Data.getClass());

        String id = Data.getOrDefault("id", "");
        String userid = Data.getOrDefault("userid", "");
        String titleid = Data.getOrDefault("titleid", "");
        String name = Data.getOrDefault("name", "");
        String text = Data.getOrDefault("text", "");
        String color = Data.getOrDefault("color", "");
        String title = Data.getOrDefault("title", "");

        ArrayList<String> otherName = new ArrayList<>();
        otherName = jsonb.fromJson(Data.getOrDefault("otherName", ""), otherName.getClass());

        ArrayList<String> images = new ArrayList<>();
        images = jsonb.fromJson(Data.getOrDefault("images", ""), images.getClass());

        DDescription description = new DDescription(id, name, String.join(",", otherName), String.join(",", images), text, color, userid, titleid);

        try {
            if (!DataBaseWork.ping()) {
                Result.put("Msg", "Not connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }


            DDescription dDesc = DataBaseWork.put_description(description);
            Result.put("Msg", dDesc.getMsg());
            return Response.ok(jsonb.toJson(Result)).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }

    };

    @Override
    public Response addDescript(String descriptions) {
        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();
        Map<String, String> Data = new HashMap<>();
        Data = (Map<String, String>) jsonb.fromJson(descriptions, Data.getClass());

        String id = Data.getOrDefault("id", "");
        String userid = Data.getOrDefault("userid", "");
        String titleid = Data.getOrDefault("titleid", "");
        String name = Data.getOrDefault("name", "");
        String text = Data.getOrDefault("text", "");
        String color = Data.getOrDefault("color", "");
        String title = Data.getOrDefault("title", "");

        ArrayList<String> otherName = new ArrayList<>();
        otherName = jsonb.fromJson(Data.getOrDefault("otherName", ""), otherName.getClass());

        ArrayList<String> images = new ArrayList<>();
        images = jsonb.fromJson(Data.getOrDefault("images", ""), images.getClass());

        DDescription description = new DDescription(id, name, String.join(",", otherName), String.join(",", images), text, color, userid, titleid);

        try {
            if (!DataBaseWork.ping()) {
                Result.put("Msg", "Not connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }


            DTitle dTitle = DataBaseWork.add_title(description.getUser_ID(), title);

            DDescription dDesc = new DDescription();
            if (dTitle.getMsg() == null)
                dDesc = DataBaseWork.add_description(description);


            Result.put("Title msg", dTitle.getMsg());
            Result.put("Description msg", dDesc.getMsg());
            return Response.ok(jsonb.toJson(Result)).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }


    @Override
    public Response getAllDescript(String userID, String titleID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();
        try {
            if (!DataBaseWork.ping()) {
                Result.put("Msg", "Not connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }


            StringBuilder msg = new StringBuilder();

            ArrayList<DDescription> dDesc = DataBaseWork.get_description(userID,titleID,msg);


            Result.put("msg", msg.toString());
            Result.put("Description", dDesc.toString());
            return Response.ok(jsonb.toJson(Result)).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

}
