import TableHeaderRow from "./TableHeaderRow";
import TableRow from "./TableRow";

type DatabaseTableProps<T> = {
    dataType: new() => T;
    objs: T[];
    rowClickFunction: ((rowData: T) => void) | null;
}

function DatabaseTable<T extends Record<string, any>>(props: DatabaseTableProps<T>) {
    
    return (
        <div className="tableContainer">
            <table id="dbTable">
                <thead>
                    <TableHeaderRow<T> dataType={props.dataType} />
                </thead>
                <tbody>
                    {props.objs.map((data, index) => (
                        <TableRow<T> key={index} data={data} rowClickFunction={props.rowClickFunction}/>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default DatabaseTable;