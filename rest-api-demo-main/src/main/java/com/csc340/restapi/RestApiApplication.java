package com.csc340.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        getJoke(); 
        System.exit(0);
    }

    /**
     * Get a random JOKE and print to console
     */
    public static void getJoke() {
        try {
            String url = "https://official-joke-api.appspot.com/random_joke";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

        
            String jsonJoke = restTemplate.getForObject(url, String.class);
           
            JsonNode root = mapper.readTree(jsonJoke);

            String setup = root.path("setup").asText();
            String punchline = root.path("punchline").asText();
            int id = root.path("id").asInt(); // Assuming you might be interested in the ID as well

           
            System.out.println("**********JOKE ID: " + id + " **********");
            System.out.println("Setup: " + setup);
            System.out.println("Punchline: " + punchline);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in getJoke");
        }
    }
    
    
}
