import { useState } from "react";
import ItemExplorer from "./ItemExplorer";
import PopUp from "../PopUp";
import AddItemPopupContent from "./AddItemPopupContent";
import DeleteItemPopupContent from "./DeleteItemPopupContent";
import DatabaseExplorer from "../DatabaseExplorer";

function Items() {
    const [addItemIsOn, toggleAddItemIsOn] = useState(false);
    const [deleteItemIsOn, toggleDeleteItemIsOn] = useState(false);

    function handleAddItemButtonClick() {
        toggleAddItemIsOn(!addItemIsOn);
    }

    function handleDeleteItemButtonClick() {
        toggleDeleteItemIsOn(!deleteItemIsOn);
    }

    return (
        <>
            <h1>Items</h1>
            <ItemExplorer/>
            <button onClick={handleAddItemButtonClick}>Add Item</button>
            <button onClick={handleDeleteItemButtonClick}>Delete Item</button>
            <PopUp isOn={addItemIsOn}>
                <AddItemPopupContent handleAddItemButtonClick={handleAddItemButtonClick}/>
            </PopUp>
            <PopUp isOn={deleteItemIsOn}>
                <DeleteItemPopupContent handleDeleteItemButtonClick={handleDeleteItemButtonClick}/>
            </PopUp>
        </>
    )
}

export default Items;