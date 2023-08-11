import { ReactNode } from "react";

function PopUp(props: {isOn: boolean, children: ReactNode}) {

    if (props.isOn)
    return (
    <div id="popUp">
        <div id="popUpWindow">
            {props.children}
        </div>
    </div>
    )
    else return null;
}

export default PopUp;