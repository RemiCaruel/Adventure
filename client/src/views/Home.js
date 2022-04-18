import React from "react";
import { LinkButton, ActionButton} from "../component/Button";

function getScenarioList() {
    //call to back-end
    return [
        "Arabia",
        "France",
        "Arena",
        "Error"
    ]
}

function showExistingAventures() {
    Array.prototype.filter.call(document.getElementsByClassName("hiden"),
        (e) => {e.style.display = e.style.display !== "flex" ? "flex" : "none";}
    );
}

function LoadScenario() {
    window.location.href += "overview?scn=" + document.getElementById('scenario').value;
}

const Home = (props) => {
    return <div className="home">
        <div className="holder">
            <LinkButton to="/overview" value="New adventure"/>
            <ActionButton action={showExistingAventures} value="Load adventure"/>
        </div>
        <div className="holder">
            <select className="selector hiden" name="scenario" id="scenario">
                {getScenarioList().map((element) =>
                    <option value={element} key={element}>{element}</option>
                )}
            </select>
            <ActionButton className="LoadButton hiden" action={LoadScenario} value=">"/>
        </div>
    </div>
}

export default Home;