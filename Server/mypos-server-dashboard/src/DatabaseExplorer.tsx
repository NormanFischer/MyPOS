import { useState } from "react";

const item1: ItemEntry = {sku: "123", itemName: "Burger", cost: "$2.99"};
const item2: ItemEntry = {sku: "999", itemName: "HotDog", cost: "$.99"};
const item3: ItemEntry = {sku: "321", itemName: "Pizza", cost: "$5.99"};
const testData: ItemEntry[] = [item1, item2, item3];

interface ItemEntry {
    sku: string,
    itemName: string,
    cost: string
}

function TableRow(props: ItemEntry) {
    const {sku, itemName, cost} = props;
    return(
        <tr>
            <td>{sku}</td>
            <td>{itemName}</td>
            <td>{cost}</td>
        </tr>
    );
}

function DatabaseExplorer() {

    const [selectedFilter, setCurrentFilter] = useState("SKU");
    const [query, setQuery] = useState("");

    async function applyFilter() {
        //TODO: Change item server port
        // const url = `localhost:8080/items/getItems/${selectedFilter}/${query}`;
        const url = "http://localhost:8080/items/getAll"
        console.log(url);
        const res = await fetch(url);
        console.log(res);
        //const json = await res.json();
        //console.log(json);
    }

    function handleFilterChange(e: React.ChangeEvent<HTMLSelectElement>) {
        setCurrentFilter(e.target.value);
    }

    function handleSearchChange(e: React.ChangeEvent<HTMLInputElement>) {
        setQuery(e.target.value);
    }

    return(
        <>
            <input value={query} onChange={handleSearchChange} placeholder="Enter search term..."></input>
            <select value={selectedFilter} onChange={handleFilterChange}>
                <option value="SKU">SKU</option>
                <option value="NAME">Item Name</option>
            </select>

            <button onClick={async() => { await applyFilter(); }}>Apply Filter</button>

            <table>
                <thead>
                    <tr>
                        <th>SKU #</th>
                        <th>Item Name</th>
                        <th>Item Cost</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        testData.map(row => {
                            return <TableRow sku={row.sku} itemName={row.itemName} cost={row.cost} />
                        })
                    }
                </tbody>
            </table>
        </>
    )
}

export default DatabaseExplorer;