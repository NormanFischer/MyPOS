import { useState } from "react";
import PopUp from "../PopUp";
import UserExplorer from "./UserExplorer";
import AddUserPopupContent from "./AddUserPopupContent";

function Users() {
    const [addUserIsOn, toggleAddItemIsOn] = useState(false);
    const [deleteUserIsOn, toggleDeleteItemIsOn] = useState(false);

    function handleAddUserButtonClick() {
        toggleAddItemIsOn(!addUserIsOn);
    }

    function handleDeleteUserButtonClick() {
        toggleDeleteItemIsOn(!deleteUserIsOn);
    }

    return (
        <>
            <h1>Users</h1>
            <UserExplorer/>
            <button onClick={handleAddUserButtonClick}>Add Item</button>
            <button onClick={handleDeleteUserButtonClick}>Delete Item</button>
            <PopUp isOn={addUserIsOn}>
                <AddUserPopupContent handleAddUserButtonClick={handleAddUserButtonClick}/>
            </PopUp>
            <PopUp isOn={deleteUserIsOn}>
                {/* <DeleteUserPopupContent handleDeleteUserButtonClick={handleDeleteUserButtonClick}/> */}
            </PopUp>
        </>
    )
}

export default Users;