import React, { Component } from "react";
import { drawMap } from "./MapViewer";

let isWaitingForHighlightRefresh = false;

function getCode() {
    
    const params = new URLSearchParams(window.location.search)
    //call to back-end
    if (params.get("scn") == undefined) return {"code":[""]};
    fetch('http://localhost:8080/api/getCode?name=' + params.get("scn"),
        {
            method:"GET",
            mode: "cors",
        }
    )
    .then(response => response.json())
    .then(data => {
        localStorage.setItem("currentAdvId", data["id"])
        document.getElementsByTagName("textarea")[0].value = data["cmds"].join("\n");
        textareaChanged();
    });
}

function textareaChanged() {
    document.getElementById("rowsIds").innerHTML = ""
    for (let i = 0; i < document.getElementsByTagName("textarea")[0].value.split("\n").length; i++) {
        document.getElementById("rowsIds").innerHTML += "<span>" + (i+1) + "</span>";
    }
}

function highlight() {
    
}

class ScriptViewer extends Component {

    componentDidMount(){
        getCode()
    }

    render(){
        return <div className="box codeContainer code">
            <div className="currentRow">{">"}</div>
            <div className="rowsIds code" id="rowsIds"></div>
            <textarea onChange={textareaChanged} onLoad={textareaChanged}>
            </textarea>
        </div>;
    }
}

export default ScriptViewer;