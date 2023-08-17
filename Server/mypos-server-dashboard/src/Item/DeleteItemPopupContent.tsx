import { useState } from "react";

function DeleteItemPopupContent(props: {handleDeleteItemButtonClick: Function}) {
    const [itemSku, setItemSku] = useState("");

    function deleteItem() {
        const req = {
            method: "DELETE",
        };
        fetch(`/items/deleteItem?sku=${itemSku}`, req)
        .then(res => res.json())
        .then(json => console.log(json));
    }

    function handleSkuChange(e: React.ChangeEvent<HTMLInputElement>) {
        setItemSku(e.target.value);
    }

    return (
        <>
        <h3>Delete Item</h3>
        Enter SKU:<input type="text" value={itemSku} onChange={handleSkuChange} />
        <button onClick={deleteItem}>Delete</button>
        <button onClick={() => {
            setItemSku("");
            props.handleDeleteItemButtonClick();
        }}>Close</button>
        </>
    )
}

export default DeleteItemPopupContent;