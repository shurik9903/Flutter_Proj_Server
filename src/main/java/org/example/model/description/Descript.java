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
    public Response inputDescript(String userID, String descriptions) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();
        Map<String, String> Data = new HashMap<>();
        Data = (Map<String, String>) jsonb.fromJson(descriptions, Data.getClass());

        String name = Data.getOrDefault("name", "");
        String text = Data.getOrDefault("text", "");
        String color = Data.getOrDefault("color", "");
        String title = Data.getOrDefault("title", "");

        ArrayList<String> otherName = new ArrayList<>();
        otherName = jsonb.fromJson(Data.getOrDefault("otherName", ""), otherName.getClass());

        ArrayList<String> images = new ArrayList<>();
        images = jsonb.fromJson(Data.getOrDefault("images", ""), images.getClass());

        DDescription description = new DDescription(name, otherName.toString(), images.toString(), text, color);

        try {
            if (!DataBaseWork.ping()) {
                Result.put("Msg", "Not connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder title_msg = new StringBuilder();
            StringBuilder desc_msg = new StringBuilder();
//            desc_msg.append(DataBaseWork.add_title(userID, title, title_msg));

            String title_id = DataBaseWork.add_title(userID, title, title_msg);
            DataBaseWork.add_description(userID, description, desc_msg, title_id);

            Result.put("Title msg", title_msg.toString());
            Result.put("description msg", desc_msg.toString());
            return Response.ok(jsonb.toJson(Result)).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }

//        return Response.ok(JsonbBuilder.create().toJson(new HashMap<String, String>(){{put("msg", "OK");}})).build();
    }

}
