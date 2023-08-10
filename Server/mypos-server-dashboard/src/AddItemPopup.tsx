
function AddItemPopup(props: {isOn: boolean, handleAddItemButtonClick: Function}) {
    if (props.isOn)
    return (
        <div id="popUp">
            <div id="popUpWindow">
                <h3>Adding Item</h3>
                Item SKU #<input type="text"></input>
                Item Name<input type="text"></input>
                Item Cost ($)<input type="text"></input>
                <button>Add Item</button>
                <button onClick={() => props.handleAddItemButtonClick()}>Close</button>
            </div>
        </div>  
    ); else return null
}

export default AddItemPopup