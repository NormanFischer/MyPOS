import { useState } from "react";
import DatabaseExplorer from "./DatabaseExplorer";
import AddItemPopup from "./AddItemPopup";

function Items() {
    const [isOn, toggleIsOn] = useState(false);

    function handleAddItemButtonClick() {
        toggleIsOn(!isOn);
    }

    return (
        <>
            <h1>Items</h1>
            <DatabaseExplorer />
            <button onClick={handleAddItemButtonClick}>Add Item</button>
            <AddItemPopup isOn={isOn} handleAddItemButtonClick={handleAddItemButtonClick}/>
        </>
    )
}

export default Items;