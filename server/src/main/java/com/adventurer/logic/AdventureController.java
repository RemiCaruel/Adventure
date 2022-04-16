package com.adventurer.logic;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class AdventureController {

    DynamicStorage storage;

    @GetMapping("/init")
    public Long init(@RequestBody String name, @RequestBody String commands) {
        Adventure newAdventure = new Adventure(name, commands);
        storage.add(newAdventure);
        return newAdventure.getId();
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getCode")
    public String[] getCode(@RequestBody String name) throws IOException {
        String fullPath = "predefined_adventures/" + name + ".txt";
        String cmds = readFromInputStream(fullPath);
        return (cmds + "\n" + init(name, cmds)).split("\n");
    }

    @GetMapping("/step")
    public String step(@RequestBody Long id) {
        Adventure cAdventure = storage.getAdventure(id);
        cAdventure.step();
        return cAdventure.getState();
    }

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
