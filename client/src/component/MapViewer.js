import React from "react";
import ReactDOMServer from 'react-dom/server';
import Cell from "./Cell";
import newId from "./IdManager";

var regExMap      = /C[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)/;
var regExMountain = /M[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)/;
var regExTreasure = /T[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)/;
var regExPlayer   = /A[ ]*-[ ]*([a-zA-Z]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([NOES])[ ]*-[ ]*([0-9]+)/;

function drawMap(cmds) {
    document.getElementById("MapViewerContainer").innerHTML = ReactDOMServer.renderToString(CreateMap(cmds[0]));
    for (let i = 1; i < cmds.length; i++) {
        if (regExMountain.test(cmds[i])) {
            let elementInfo = regExMountain.exec(cmds[i]);
            document.getElementsByClassName("col")[elementInfo[1]].childNodes[parseInt(elementInfo[2]) + 1].innerHTML = 
                ReactDOMServer.renderToString(<Cell key={newId()} type="M" imgName="mountain.png"/>);
            continue;
        }
        if (regExTreasure.test(cmds[i])) {
            let elementInfo = regExTreasure.exec(cmds[i]);
            document.getElementsByClassName("col")[elementInfo[1]].childNodes[parseInt(elementInfo[2]) + 1].innerHTML = 
                ReactDOMServer.renderToString(<Cell key={newId()} type="T" imgName="treasure.png" quantity={elementInfo[3]}/>);
            continue;
        }
        if (regExPlayer.test(cmds[i])) {
            let elementInfo = regExPlayer.exec(cmds[i]);
            console.log(elementInfo);
            document.getElementsByClassName("col")[elementInfo[2]].childNodes[parseInt(elementInfo[3]) + 1].innerHTML = 
                ReactDOMServer.renderToString(<Cell key={newId()} type="A" imgName="avatar.png" name={elementInfo[1]}/>);
            continue;
        }
    }
}

function CreateMap(cmd) {
    return Array.apply(null, Array(parseInt(regExMap.exec(cmd)[1]))).map((idx) => {
        return <Row amount={parseInt(regExMap.exec(cmd)[2])} />;
    })
}

const Row = (props) => {
    return <div className="col" key={newId()}> {Array.apply(null,Array(props.amount)).map(function () {
        return <NewCell/>;
    })} </div>
}

const NewCell = (props) => {
    return <div><Cell key={newId()}></Cell></div>
}

export { drawMap };