import { useState } from "react";
import DatabaseTable from "./Table/TableRow";
import DatabaseExplorer from "./DatabaseExplorer";
import { ItemConstructor } from "./Item";

function ItemExplorer() {
    return (
        <DatabaseExplorer dataType={ItemConstructor} apiEndpoint={"/items/getItems"} />
    )
}

// function ItemExplorer() {
//     const [selectedFilter, setCurrentFilter] = useState("SKU");
//     const [query, setQuery] = useState("");
//     const [items, setItems] = useState<Item[]>([]);

//     async function applyFilter() {
//         const url = `/items/getItems/${selectedFilter}/${query}`;
//         console.log(url);
//         const res = await fetch(url);
//         const json = await res.json();
//         let resultList: Item[] = [];

//         for (const obj of json) {
//             console.log(obj);
//             const toAdd: Item = {sku: obj.sku, itemName: obj.itemName, cost: obj.cost, quantity: obj.quantity};
//             console.log(toAdd);
//             resultList.push(toAdd);
//         }
//         setItems(resultList);
//     }

//     function handleFilterChange(e: React.ChangeEvent<HTMLSelectElement>) {
//         setCurrentFilter(e.target.value);
//     }

//     function handleSearchChange(e: React.ChangeEvent<HTMLInputElement>) {
//         setQuery(e.target.value);
//     }

//     return(
//         <>
//             <input value={query} onChange={handleSearchChange} placeholder="Enter search term..."></input>
//             <select value={selectedFilter} onChange={handleFilterChange}>
//                 <option value="SKU">SKU</option>
//                 <option value="NAME">Item Name</option>
//             </select>

//             <button onClick={async() => { await applyFilter(); }}>Apply Filter</button> 

//             <DatabaseTable dataType={ItemConstructor} />

//             {/* <div className="tableContainer">
//             <table id="dbTable">
//                 <thead>
//                     <tr>
//                         <th>SKU #</th>
//                         <th>Item Name</th>
//                         <th>Item Cost</th>
//                     </tr>
//                 </thead>
                
//                 <tbody>
//                     {
//                         items.map(row => {
//                             return <TableRow sku={row.sku} itemName={row.itemName} cost={row.cost} quantity={row.quantity} />
//                         })
//                     }
//                 </tbody>
                
//             </table>
//             </div> */}
//         </>
//     )
// }

export default ItemExplorer;