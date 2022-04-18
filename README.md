# Adventure
 
    Carbon IT project

## How to use it

### Home

    On the home page, there is 3 buttons.
        top : "Adventurer" => return to the main page
        middle : "New adventure" => Play a new adventure, code it from scratch
                 "Load adventure" => Load some predefined adventures

    On "Load adventure" click, it appears:
        - A list menu containing the predefined adventures
        - A button ">" to start it

### Overview

    This page shows the adventure.
    The left Panel represent the adventure.
    The right Panel shows the adventure's code

    On the code side, here are the listed commands available:
        => C - X - Y => Create a map of size X (columns) Y (rows)
        => M - X - Y => Create a mountain located at X, Y. (indices are starting at 0)
        => T - X - Y - Q => Create a treasure located at X, Y (indices are starting at 0) with a quantity Q of treasures to take
        => A - Name - X - Y - Orientation - MovingSequence => Create a player named "Name" (can be any letter capitals or not)
                                                                              located at X, Y (indices are starting at 0)
                                                                              Oriented at "Orientation" (Can be S (South), N (North), O (West), E (East) but only one capital letter)
                                                                              With a moving sequence "MovingSequence" (Can be Multiple letter as A (Forward), G (turn left), D (turn right))

    In case the command is misstyped, it will be underlined in red.

    If you edited the code, you need to click on the "load" button.
    Then you can press the "Step" button that will execute the next command of the code.
    You can also click on Play to read the code continuously. 

    /!\ There is no automatic stop /!\