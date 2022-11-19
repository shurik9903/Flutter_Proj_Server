package org.example.model.registration;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.example.model.database.IDataBaseWork;

import java.util.HashMap;
import java.util.Map;

public class Registration implements IRegistration{

    @Inject
    private IDataBaseWork DataBaseWork;

    public Response RegistrFunc(String RegData){
        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<String, String>();
        Map<String, String> Data = new HashMap<>();
        Data = (Map<String,String>) jsonb.fromJson(RegData, Data.getClass());

        String result = "";

        String email  = Data.getOrDefault("email", "");
        String login  = Data.getOrDefault("username", "");
        String password = Data.getOrDefault("password", "");
        String password2 = Data.getOrDefault("password2", "");


        try {

            if (!email.isEmpty() &&  !login.isEmpty() && !password.isEmpty() && !password2.isEmpty()){
                String CheckingResult = RegistrationChecking(email, login, password, password2);
                if (CheckingResult.isEmpty()){
                    if (DataBaseWork.ping()) {
                        String regres = DataBaseWork.add_user(email,login, password);
                        if (regres.equals("")) {
                            result = "You have successfully registered";
                        } else result = regres;
                    }else result = "Failed to connect to server";
                }else result = CheckingResult;
            } else result = "Fill in all the fields";

            Result.put("Msg", result);
            return Response.ok(jsonb.toJson(Result)).build();

        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }
    }

    private String RegistrationChecking(String Mail, String UserName, String Password, String Password2) {

        if ((Mail.trim().length() <= 0) || (UserName.trim().length() <= 0) ||
                (Password.trim().length() <= 0) || (Password2.trim().length() <= 0))
            return "You have not filled in all the fields";

        if (Mail.length() > 50) return "Mail must not be longer than 50 characters";

        if (Mail.contains(" ")) return "Mail should not contain spaces";

        if (UserName.length() > 25) return "Login must not be longer than 25 characters";

        if (UserName.contains(" ")) return "Login should not contain spaces";

        if (!UserName.matches("[a-zA-Z0-9\\d\\s\\p{Punct}]+"))
            return "Login can only consist of English letters," +
                    " numbers and punctuation marks";

        if (Password.length() > 50) return "Password must not be longer than 50 characters";

        if ((!Password.equals(Password2))) return "Password mismatch";

        return "";
    }

}
