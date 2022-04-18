package com.adventurer.logic;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Map;

@RestController
public class AdventureController {

    DynamicStorage storage = new DynamicStorage();

    /**
     * Initialize the adventure with its commands
     * @param name name of the adventure
     * @param commands adventure's commands list
     * @return the id of the adventure to the client
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/init")
    public Long init(@RequestBody Map<String, Object> json) {
        storage.remove(Long.valueOf((String)json.get("previous_id")));
        Adventure newAdventure = new Adventure((String)json.get("name"), ((String)json.get("commands")));
        storage.add(newAdventure);
        return newAdventure.getId();
    }

    /**
     * Initialize a predefined adventure
     * @param name name of the adventure
     * @return the adventure predefined commands
     * @throws IOException In case the file cannot be read
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/getCode")
    public String getCode(@RequestParam String name) throws IOException {
        String fullPath = "predefined_adventures/" + name + ".txt";
        String cmds = readFromInputStream(fullPath);
        Adventure newAdventure = new Adventure(name, cmds.replace("\n", ";"));
        storage.add(newAdventure);
        return "{\"cmds\":" + toJson(cmds.split("\n")) + ",\"id\":\"" + newAdventure.getId() + "\"}";
    }

    /**
     * Perform a step to the adventure
     * @param id Adventure's id
     * @return the current adventure state according to client specs
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/step")
    public String[] step(@RequestParam Long id) {
        Adventure cAdventure = storage.getAdventure(id);
        cAdventure.step();
        return cAdventure.getState().split("\n");
    }

    /**
     * Read a file and returns its content
     * @param path path of the file
     * @return content of the file
     * @throws IOException in case of file reading error
     */
    private String readFromInputStream(String path)
        throws IOException {
            InputStream inputStream = (new ClassPathResource(path)).getInputStream();
            StringBuilder resultStringBuilder = new StringBuilder();
            try (
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
        return resultStringBuilder.toString();
    }

    private String toJson(String[] rows) {
        String ret = "[";
        for (int i = 0; i < rows.length - 1; i++) {
            ret += "\"" + rows[i] + "\",";
        }
        ret += "\"" + rows[rows.length-1] + "\"]";
        return ret;
    }
}
