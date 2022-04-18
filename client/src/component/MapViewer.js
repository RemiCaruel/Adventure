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
    setCoefficientSize(1);
    AdaptMapSize(regExMap.exec(cmds[0])[1], regExMap.exec(cmds[0])[2]);
    for (let i = 1; i < cmds.length; i++) {
        if (regExMountain.test(cmds[i])) {
            let elementInfo = regExMountain.exec(cmds[i]);
            document.getElementsByClassName("col")[elementInfo[1]].childNodes[elementInfo[2]].innerHTML = 
                ReactDOMServer.renderToString(<Cell key={newId()} type="M" imgName="mountain.png"/>);
            continue;
        }
        if (regExTreasure.test(cmds[i])) {
            let elementInfo = regExTreasure.exec(cmds[i]);
            document.getElementsByClassName("col")[elementInfo[1]].childNodes[elementInfo[2]].innerHTML = 
                ReactDOMServer.renderToString(<Cell key={newId()} type="T" imgName="treasure.png" quantity={elementInfo[3]}/>);
            continue;
        }
        if (regExPlayer.test(cmds[i])) {
            let elementInfo = regExPlayer.exec(cmds[i]);
            document.getElementsByClassName("col")[elementInfo[2]].childNodes[elementInfo[3]].innerHTML = 
                ReactDOMServer.renderToString(<Cell key={newId()} type="A" imgName="avatar.png" name={elementInfo[1]}/>);
            continue;
        }
    }
}

function AdaptMapSize(x, y) {
    let mapHolderRef = document.getElementsByClassName("MapHolder")[0];
    let mapViewerContainerRef = document.getElementById("MapViewerContainer");
    if (mapHolderRef.clientWidth < mapViewerContainerRef.clientWidth
        &&
        mapHolderRef.clientHeight < mapViewerContainerRef.clientHeight) return;
    
    let coeff = Math.max(
        (mapHolderRef.clientWidth + x*2) / (mapViewerContainerRef.clientWidth),
        (mapHolderRef.clientHeight + y*2) / (mapViewerContainerRef.clientHeight)
    )
    setCoefficientSize(coeff);
}

function setCoefficientSize(coeff) {
    document.documentElement.style.setProperty('--coefficient', coeff);
}

function CreateMap(cmd) {
    return <div className="MapHolder">{Array.apply(null, Array(parseInt(regExMap.exec(cmd)[1]))).map((idx) => {
        return <Row amount={parseInt(regExMap.exec(cmd)[2])} />;
    })}</div>;
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