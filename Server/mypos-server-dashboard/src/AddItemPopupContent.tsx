import { useState } from "react";
import { currencyStringToNum, numToCurrencyString } from "./util";

function AddItemPopupContent(props: {handleAddItemButtonClick: Function}) {
    
    async function addNewItem() {
        const ItemToAdd = {sku: itemSku, itemName: itemName, cost: Number(currencyStringToNum(itemCost)), quantity: initialQuantity};
        const req = {
            method: "POST",
            headers: { "Content-Type": "application/json"},
            body: JSON.stringify(ItemToAdd),
        };
        fetch("/items/createItem", req)
        .then(res => res.text)
        .then(text => console.log(text));
    }

    const [itemSku, setItemSku] = useState("");
    const [itemName, setItemName] = useState("");
    const [itemCost, setItemCost] = useState("");
    const [initialQuantity, setInitialQuantity] = useState(0);

    function handleSkuChange(e: React.ChangeEvent<HTMLInputElement>) {
        setItemSku(e.target.value);
    }

    function handleItemNameChange(e: React.ChangeEvent<HTMLInputElement>) {
        setItemName(e.target.value);
    }

    function handleItemCostChange(e: React.ChangeEvent<HTMLInputElement>) {
        //We change the display to represent the number as a formatted currency
        const newValue = currencyStringToNum(e.target.value);
        console.log(newValue);
        const newFormatted = numToCurrencyString(newValue);
        setItemCost(newFormatted);
    }

    function handleIntialQuantityChange(e: React.ChangeEvent<HTMLInputElement>) {
        try {
            setInitialQuantity(Number(e.target.value));
        } catch(e) {
            console.log(e);
        }
    }

    return (
        <>
        <h3>Adding Item</h3>
        Item SKU #<input type="text" value={itemSku} onChange={handleSkuChange}/>
        Item Name<input type="text"  value={itemName} onChange={handleItemNameChange}/>
        Item Cost ($)<input type="text" value={itemCost} onChange={handleItemCostChange}/>
        Initial Quantity<input type="number" value={initialQuantity} onChange={handleIntialQuantityChange}/>
        <button onClick={async () =>  { await addNewItem(); }}>Add Item</button>
        <button onClick={() =>  {
            setItemSku("");
            setItemName("");
            setItemCost("");
            setInitialQuantity(0);
            props.handleAddItemButtonClick();
        }}>Close</button>
        </>
    )
}

export default AddItemPopupContent