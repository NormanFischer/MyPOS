import { useState } from "react";

function AddUserPopupContent(props: {handleAddUserButtonClick: Function}) {
    
    function addNewUser() {
        const userDTO = {username: userName, password: password, auths: Array.from(authorities) as string[]};
        console.log(JSON.stringify(userDTO));
        
        const req = {
            method: "POST",
            headers: { "Content-Type": "application/json"},
            body: JSON.stringify(userDTO),
        };
        fetch("/users/createUser", req)
        .then(res => res.text)
        .then(text => console.log(text));
    }

    const [userName, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [authorities, setAuthorities] = useState<Set<string>>(new Set<string>());

    function handleUsernameChange(e: React.ChangeEvent<HTMLInputElement>) {
        setUsername(e.target.value);
    }

    function handlePasswordChange(e: React.ChangeEvent<HTMLInputElement>) {
        setPassword(e.target.value);
    }

    function handleAuthorityChange(e: React.ChangeEvent<HTMLInputElement>) {
        if(e.target.checked) {
            setAuthorities(authorities.add(e.target.value));
            console.log("AUTHORITIES: " + [...authorities].join(' '))
        } else if(authorities.has(e.target.value) && authorities.delete(e.target.value)) {
            setAuthorities(authorities);
            console.log("AUTHORITIES: " + [...authorities].join(' '))
        } 
    }

    return (
        <>
        {
            authorities.forEach(auth => {
                return(<h2>{auth}</h2>)
            })
        }
        <h3>Adding User</h3>
        Username<input type="text" value={userName} onChange={handleUsernameChange}/>
        Password<input type="text"  value={password} onChange={handlePasswordChange}/>
        <input type="checkbox" name="user" value="USER" onChange={handleAuthorityChange}/>
        <label htmlFor={"user"}>User</label>

        <input type="checkbox" name="admin" value="ADMIN" onChange={handleAuthorityChange}/>
        <label htmlFor="vehicle2">Admin</label>

        <button onClick={() =>  { addNewUser() }}>Add Item</button>
        <button onClick={() =>  {
            setUsername("");
            setPassword("");
            setAuthorities(new Set<string>());
            props.handleAddUserButtonClick();
        }}>Close</button>
        </>
    )
}

export default AddUserPopupContent;