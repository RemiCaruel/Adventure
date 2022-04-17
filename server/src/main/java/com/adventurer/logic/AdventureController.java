package com.adventurer.logic;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class AdventureController {

    DynamicStorage storage;

    /**
     * Initialize the adventure with its commands
     * @param name name of the adventure
     * @param commands adventure's commands list
     * @return the id of the adventure to the client
     */
    @GetMapping("/init")
    public Long init(@RequestBody String name, @RequestBody String commands) {
        Adventure newAdventure = new Adventure(name, commands);
        storage.add(newAdventure);
        return newAdventure.getId();
    }

    /**
     * Initialize a predefined adventure
     * @param name name of the adventure
     * @return the adventure predefined commands
     * @throws IOException In case the file cannot be read
     */
    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getCode")
    public String[] getCode(@RequestBody String name) throws IOException {
        String fullPath = "predefined_adventures/" + name + ".txt";
        String cmds = readFromInputStream(fullPath);
        return (cmds + "\n" + init(name, cmds)).split("\n");
    }

    /**
     * Perform a step to the adventure
     * @param id Adventure's id
     * @return the current adventure state according to client specs
     */
    @GetMapping("/step")
    public String step(@RequestBody Long id) {
        Adventure cAdventure = storage.getAdventure(id);
        cAdventure.step();
        return cAdventure.getState();
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
}
