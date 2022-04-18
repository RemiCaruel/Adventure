import React from "react";

import { ActionButton } from "../component/Button";
import ScriptViewer from "../component/ScriptViewer";
import {drawMap} from "../component/MapViewer"

let simulationRunner;

function startSimulation(){
    simulationRunner = window.setInterval(stepSimulation, 500);
}

function stepSimulation(){
    var myHeaders = new Headers();
    myHeaders.append("Origin", "localhost:3000");
    fetch('http://localhost:8080/api/step?id=' + localStorage.getItem("currentAdvId"),
        {
            method:"GET",
            mode: "cors",
            headers:myHeaders,
        }
    )
    .then(response => response.json())
    .then(data => {
        drawMap(data);
    });
}

function stopSimulation(){
    clearInterval(simulationRunner);
}

function reloadSimulation(){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Origin", "localhost:3000");
    fetch('http://localhost:8080/api/init',
        {
            method:"POST",
            mode: "cors",
            headers:myHeaders,
            body:JSON.stringify({"name":"CustomAdv", "commands":document.getElementsByClassName("textArea")[0].innerHTML.replace(/<\/div><div>/g, ";").replace(/<[\/]?[ a-zA-Z\"=]*>/g, ""), "previous_id": localStorage.getItem("currentAdvId")})
        }
    )
    .then(response => response.json())
    .then(data => {
        document.getElementById("MapViewerContainer").innerHTML = "";
        localStorage.setItem("currentAdvId", data)
    });
}

class OverView extends React.Component {

    constructor(props) {
        super(props);
    }

    render () {
        return <div className="overview">
        <div className="container left box" id="MapViewerContainer">
        </div>
        <div className="container right">
            <ScriptViewer />
            <div className="holder actionButton">
                <ActionButton action={startSimulation} value="Play"/>
                <ActionButton action={stepSimulation} value="Step"/>
                <ActionButton action={stopSimulation} value="Stop"/>
                <ActionButton action={reloadSimulation} value="reload"/>
            </div>
        </div>
    </div>
    }
}

export default OverView;