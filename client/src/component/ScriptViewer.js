import React, { Component } from "react";
import { drawMap } from "./MapViewer";
import ReactDOMServer from 'react-dom/server';

let timeOutHolder;

let regExComment  = /(^#.*)/;
let regExMap      = /^(?!(#)+)(C[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*)$/;
let regExMountain = /^(?!(#)+)(M[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*)$/;
let regExTreasure = /^(?!(#)+)(T[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*)$/;
let regExPlayer   = /^(?!(#)+)(A[ ]*-[ ]*([a-zA-Z]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([0-9]+)[ ]*-[ ]*([SOEN])[ ]*-[ ]*([AGD]+)[ ]*)$/;

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
        document.getElementsByClassName("textArea")[0].innerHTML = ReactDOMServer.renderToString(<pre>{data["cmds"].join("\n")}</pre>);
        textareaChanged();
    });
}

function textareaChanged() {
    try {clearTimeout(timeOutHolder);console.log("TimeOutCleared!")}catch{console.log("No Timeout to clear")}

    document.getElementById("rowsIds").innerHTML = ""
    for (let i = 1; i <= document.getElementsByTagName("pre")[0].innerHTML.replace(/(<br>)$/, "").replace(/<br>/g, "\n").split("\n").length; i++) {
        document.getElementById("rowsIds").innerHTML += "<span>" + (i) + "</span>";
    }

    timeOutHolder = setTimeout(highlight, 2000);
}

function highlight() {
    let content = document.getElementsByTagName("pre")[0].innerHTML.replace(/<br>/g, "\n").replace(/<[\/]?[ a-zA-Z\"=]*>/g, "").split("\n");
    let ret = [];
    for (let i = 0; i < content.length; i++) {
        if (regExComment.test(content[i])){
            ret.push("<span class=\"codeComment\">" + content[i] + "</span>");
            continue;
        }
        if (regExMap.test(content[i]) || regExMountain.test(content[i]) || regExTreasure.test(content[i])){
            ret.push(content[i].replace(/([0-9]+)/g, "<span class=\"codeNumber\">$1</span>"));
            continue;
        }
        if (regExPlayer.test(content[i])){
            let splitted = content[i].split("-");
            ret.push(
                splitted[0] + "-" +
                "<span class=\"codeName\">" + splitted[1] + "</span>" + "-" +
                "<span class=\"codeNumber\">" + splitted[2] + "</span>" + "-" +
                "<span class=\"codeNumber\">" + splitted[3] + "</span>" + "-" +
                "<span class=\"codeOrientation\">" + splitted[4] + "</span>" + "-" +
                "<span class=\"codeMovements\">" + splitted[5] + "</span>"
            );
            continue;
        }
        ret.push("<span class=\"codeError\">" + content[i] + "</span>")
    }
    document.getElementsByTagName("pre")[0].innerHTML = ret.join("<br>");

}

class ScriptViewer extends Component {

    componentDidMount(){
        console.log("I am mounted!")
        getCode();
        document.getElementsByClassName("textArea")[0].addEventListener("keyup", inputEvt => {
            textareaChanged()
        }, false);
    }

    render(){
        return <div className="box codeContainer code">
            <div className="rowsIds code" id="rowsIds"></div>
            <div className="textArea" contentEditable="true" onChange={textareaChanged} onLoad={textareaChanged}></div>
        </div>;
    }
}



export default ScriptViewer;