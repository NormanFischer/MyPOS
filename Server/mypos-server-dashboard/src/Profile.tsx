import { useEffect, useState } from "react";

type ProfileDTO = {
    username: string
}

function Profile() {
    const [userData, setUserData] = useState<ProfileDTO | null>(null);
    const [requestCompleted, setRequestCompleted] = useState(false);
    const [userRequestFailed, setUserRequestFailed] = useState(false);


    useEffect(() => {
        fetch(`/users/getUserProfile/`)
        .then(res => res.json())
        .then(json => setUserData({username: json.username}))
        .catch(_err => {
            console.log("Request failed");
            setUserRequestFailed(true);
        });
        setRequestCompleted(true);
    }, []);


    if (requestCompleted && !userRequestFailed) return (
        <>
        <h1>Profile</h1>
        <h2>Username: ${userData?.username}</h2>
        </>
    ); else if (userRequestFailed) return (<h1>Request for user profile failed.</h1>)
    else return (<h1>Loading...</h1>);
}

export default Profile