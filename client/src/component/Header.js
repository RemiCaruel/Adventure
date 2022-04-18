import React from "react";
import { LinkButton } from "../component/Button";

const Header = () => {
    return <div className="header">
         <LinkButton className="headerLogo" to="/" value="Adventurer"/>
    </div>
}

export default Header;