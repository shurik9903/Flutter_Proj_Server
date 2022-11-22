package org.example.model.description;

import jakarta.json.JsonObject;
import jakarta.ws.rs.core.Response;

public interface IDescript {

//    Response updateDescript(String descriptions);

    Response putDescript(String descriptions);

    Response addDescript(String descriptions);

    Response getAllDescript(String userID, String titleID);
}
