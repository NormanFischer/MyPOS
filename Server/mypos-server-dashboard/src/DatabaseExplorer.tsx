import { useEffect, useState } from "react";
import DatabaseTable from "./Table/DatabaseTable";

function DatabaseExplorer<T extends Record<string, any>>(props: { dataType: new () => T, apiEndpoint: string}) {
    const [query, setQuery] = useState("");
    const [validFilters, setValidFilters] = useState<string[]>([]);
    //TODO: Change this!
    const [selectedFilter, setCurrentFilter] = useState("SKU");
    const [objs, setObjs] = useState<T[]>([]);
    
    useEffect(() => {
        setValidFilters(getValidFilters());
    }, []);

    function getValidFilters(): string[] {
        //TODO: More filter support
        console.log("Get valid filters called")
        let ans = [];
        const instance: T = new props.dataType;
        for(const property in instance) {
            if(typeof instance[property] === "string") {
                ans.push(property.toString());
            }
        }
        return ans;
    }

    function applyFilter() {
        const url = `${props.apiEndpoint}/${selectedFilter}/${query}`;
        fetch(url)
        .then(res => res.json())
        .then(json => {
            setObjs(json);
        });
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
                {
                    validFilters.map((opt) => {
                        return <option value={opt.toUpperCase()}>{opt}</option>
                    })
                }
            </select>

            <button onClick={async() => { applyFilter(); }}>Apply Filter</button> 
        <DatabaseTable dataType={props.dataType} objs={objs}/>
        </>
    );
}

export default DatabaseExplorer;