package vtttp.day5_demo.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.Data;

@Data
public class Response {
    private String message = "";
    private int responseCode;

    private String data = "{}";

    //Using jakarta json method to build json obj
    public JsonObject toJson(){
        return Json.createObjectBuilder()
                .add("message", message)
                .add("code", responseCode)
                .add("data", data)
                .build();
    }

}
