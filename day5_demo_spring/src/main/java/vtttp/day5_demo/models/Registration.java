package vtttp.day5_demo.models;

import com.google.gson.Gson;
import lombok.Data;


@Data
public class Registration {
    private String id;
    private String name;
    private String email;

    public static Registration create (String json){
        Gson gson = new Gson();
        Registration reg = gson.fromJson(json, Registration.class);

        return reg;
    }
}
