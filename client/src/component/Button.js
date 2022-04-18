import React from "react";
import { Link } from "react-router-dom";

const ActionButton = (props) => {
    return <div className={"button action-button " + props.className} onClick={props.action}>
        {props.value}
    </div>;
}

const LinkButton = (props) => {
    return <Link to={props.to}>
        <div className={"button " + props.className} id={props.id ? props.id:""}>
        {props.value}
        </div>
    </Link>;
}

export {ActionButton, LinkButton};