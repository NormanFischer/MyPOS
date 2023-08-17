import DatabaseExplorer from "../DatabaseExplorer";
import { ItemConstructor } from "./Item";

function ItemExplorer() {
    return (
        <DatabaseExplorer dataType={ItemConstructor} apiEndpoint={"/items/getItems"} />
    )
}

export default ItemExplorer;