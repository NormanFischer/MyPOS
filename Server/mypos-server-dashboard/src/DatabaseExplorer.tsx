import { useState } from "react";

function TableRow(props: Item) {
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
    const [items, setItems] = useState<Item[]>([]);

    async function applyFilter() {
        const url = `/items/getItems/${selectedFilter}/${query}`;
        console.log(url);
        const res = await fetch(url);
        const json = await res.json();
        let resultList: Item[] = [];

        for (const obj of json) {
            console.log(obj);
            const toAdd: Item = {sku: obj.sku, itemName: obj.itemName, cost: obj.cost, quantity: obj.quantity};
            console.log(toAdd);
            resultList.push(toAdd);
        }
        setItems(resultList);
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
                        items.map(row => {
                            return <TableRow sku={row.sku} itemName={row.itemName} cost={row.cost} quantity={row.quantity} />
                        })
                    }
                </tbody>
            </table>
        </>
    )
}

export default DatabaseExplorer;