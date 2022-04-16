package com.adventurer.logic;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class AdventureController {
    public AdventureController() {
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test")
    public String test() {
        return "This is shown thanks to java back-end !";
    }
}
