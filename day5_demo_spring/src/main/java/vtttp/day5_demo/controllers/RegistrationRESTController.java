package vtttp.day5_demo.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vtttp.day5_demo.models.Registration;
import vtttp.day5_demo.models.Response;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRESTController {

    Registration reg;
    Response resp;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Logger logger = Logger.getLogger(RegistrationRESTController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postRegistation(@RequestBody String payload){

        logger.info("payload: %s".formatted(payload));
        String id = UUID.randomUUID().toString().substring(0,8);

        try {
            reg = Registration.create(payload);
            reg.setId(id);
        } catch (Exception e){
            resp = new Response();
            resp.setResponseCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(resp));
        }

        //save to database
        resp = new Response();
        resp.setResponseCode(201);
        resp.setMessage(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(resp));
    }
}
