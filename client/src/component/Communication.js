const message = (carte, command) => ({
    "carte"   : carte,
    "command" : command
});

const carte = (width, height, elements, players) => ({
    "width"    : width,
    "height"   : height,
    "elements" : elements,
    "players"  : players
});

const element = (type, x, y, quantity) => ({
    "element": {
        "type"    : type,
        "position": {
            "x" : x,
            "y" : y
        },
        "quantity": quantity
    }
});

const player = (nom, x, y, orientation) => ({
    "player": {
        "nom"        : nom,
        "position"   : {
            "x" : x,
            "y" : y
        },
        "orientation": orientation,
    }
})

export {message, carte, element, player};