import React from "react";

const Cell = (props) => (
    <div className="cell">
        {props.type ? <img className="icon" src={"img/" + props.imgName}></img>:""}
        {props.quantity ? <div className="info">{props.quantity}</div>:""}
        {props.name ? <div className="info infoName">{props.name}</div>:""}
    </div>
)

export default Cell;