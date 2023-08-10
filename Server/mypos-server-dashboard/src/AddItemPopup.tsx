import { useState } from "react";

function AddItemPopup(props: {isOn: boolean, handleAddItemButtonClick: Function}) {
    
    async function addNewItem() {
    }

    const [itemSku, setItemSku] = useState("");
    const [itemName, setItemName] = useState("");
    const [itemCost, setItemCost] = useState("");
    const [initialQuantity, setInitialQuantity] = useState("");

    function handleSkuChange(e: React.ChangeEvent<HTMLInputElement>) {
        setItemSku(e.target.value);
    }

    function handleItemNameChange(e: React.ChangeEvent<HTMLInputElement>) {
        setItemName(e.target.value);
    }

    function handleItemCostChange(e: React.ChangeEvent<HTMLInputElement>) {
        setItemCost(e.target.value);
    }

    function handleIntialQuantityChange(e: React.ChangeEvent<HTMLInputElement>) {
        setInitialQuantity(e.target.value);
    }

    if (props.isOn)
    return (
        <div id="popUp">
            <div id="popUpWindow">
                <h3>Adding Item</h3>
                Item SKU #<input type="text" value={itemSku} onChange={handleSkuChange}/>
                Item Name<input type="text"  value={itemName} onChange={handleItemNameChange}/>
                Item Cost ($)<input type="text" value={itemCost} onChange={handleItemCostChange}/>
                Initial Quantity<input type="number" value={initialQuantity} onChange={handleIntialQuantityChange}/>
                <button onClick={async () =>  { await addNewItem(); }}>Add Item</button>
                <button onClick={() => props.handleAddItemButtonClick()}>Close</button>
            </div>
        </div>  
    ); else return null
}

export default AddItemPopup