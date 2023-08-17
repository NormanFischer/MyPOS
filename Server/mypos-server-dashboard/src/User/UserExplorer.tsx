import DatabaseExplorer from "../DatabaseExplorer";
import { UserConstructor } from "./User";

function UserExplorer() {
    return (
        <DatabaseExplorer dataType={UserConstructor} apiEndpoint={"/users/getUser"} />
    )
}

export default UserExplorer;