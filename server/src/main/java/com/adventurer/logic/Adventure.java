package com.adventurer.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Adventure {

    static final Pattern regExComment  = Pattern.compile("(^#.*)");
    static final Pattern regExMap      = Pattern.compile("^(?!(#)+)(C[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*)$");
    static final Pattern regExMountain = Pattern.compile("^(?!(#)+)(M[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*)$");
    static final Pattern regExTreasure = Pattern.compile("^(?!(#)+)(T[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*)$");
    static final Pattern regExPlayer   = Pattern.compile("^(?!(#)+)(A[ ]*-[ ]*([a-zA-Z]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([SOEN])[ ]*-[ ]*([AGD]+)[ ]*)$");
    static final Pattern regExPlayerCreation = Pattern.compile("^(?!(#)+)(A-([a-zA-Z]+)-([0-9]+)-([0-9]+)-([SOEN])[ ]*)$");
    static final Pattern regExPlayerMoving   = Pattern.compile("^(?!(#)+)(A-([a-zA-Z]+)-([AGD])[ ]*)$");
    
    private Long id;
    private String name;
    private String[] commands;
    private int currentcmd = 0;
    private Map map;

    public Adventure(String name, String commands) {
        this.commands = getCommandListDevelopped(commands);
        this.name = name;
    }

    public void step() {
        Matcher matcher;

        if (currentcmd >= this.commands.length) return;

        String cCmd = this.commands[currentcmd];

        if (regExMap.matcher(cCmd).matches()) {
            matcher = regExMap.matcher(cCmd);
            matcher.find();
            this.map = new Map(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }

        if (regExMountain.matcher(cCmd).matches()) {
            matcher = regExMountain.matcher(cCmd);
            matcher.find();
            this.map.AddElementAt("M", new int[]{Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))}, false, 2);
        }

        if (regExTreasure.matcher(cCmd).matches()) {
            matcher = regExTreasure.matcher(cCmd);
            matcher.find();
            this.map.AddTreasureAt(new int[]{Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))}, Integer.parseInt(matcher.group(5)));
        }

        if (regExPlayerCreation.matcher(cCmd).matches()) {
            matcher = regExPlayerCreation.matcher(cCmd);
            matcher.find();
            this.map.AddPlayerAt(new int[]{Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5))}, matcher.group(3), matcher.group(6));
        }

        if (regExPlayerMoving.matcher(cCmd).matches()) {
            matcher = regExPlayerMoving.matcher(cCmd);
            matcher.find();
            this.map.MovePlayer(matcher.group(3), matcher.group(4));
        }
        
        currentcmd += 1;
    }

    public String getState() {
        return map.getState();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String[] getCommandListDevelopped(String cmd) {
        String ret = "";
        for (String command : cmd.split("\n")) {
            if (regExComment.matcher(command).matches()) continue;
            if (regExMap.matcher(command).matches() || regExMountain.matcher(command).matches() || regExTreasure.matcher(command).matches())  ret += command + "\n";
            if (regExPlayer.matcher(command).matches()) {
                Matcher mplayer = regExPlayer.matcher(command);
                mplayer.find();
                ret += "A-" + mplayer.group(3) + "-" + mplayer.group(4)+ "-" + mplayer.group(5)+ "-" + mplayer.group(6) + "\n";
                for (int i = 0; i < mplayer.group(7).length(); i++) {
                    ret += "A-" + mplayer.group(3) + "-" + mplayer.group(7).charAt(i) + "\n";
                }
            }
        }
        return ret.split("\n");
    }

    public Map getMap(){
        return this.map;
    }
}
