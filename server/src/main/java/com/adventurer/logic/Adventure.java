package com.adventurer.logic;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Adventure {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String commands;

    public Adventure(String name, String commands) {
        this.commands = commands;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\"adventure\":{" + 
               "\"id\":" + this.id +
               "\"name\":" + this.name +
               "\"commands\":[" + String.join(",", this.commands.split("\n")) + "]}}";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getcommands() {
        return commands;
    }
}
