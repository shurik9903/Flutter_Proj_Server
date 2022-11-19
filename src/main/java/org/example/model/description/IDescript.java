package org.example.model.description;

import jakarta.json.JsonObject;
import jakarta.ws.rs.core.Response;

public interface IDescript {

    Response inputDescript(String userID, String descriptions);

}
