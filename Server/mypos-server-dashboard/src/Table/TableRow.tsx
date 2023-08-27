import { TrackHTMLAttributes } from "react";
import { getObjectKeys } from "./tableUtil";

type TableRowProps<T extends Record<string, any>> = {
    data: T,
    rowClickFunction: ((rowData: T) => void) | null;
}

function TableRow<T extends Record<string, any>>(props: TableRowProps<T>) {
    return (
        <tr onClick={() => {
            if (props.rowClickFunction) {
                props.rowClickFunction(props.data)
            } else {
                console.log("No function declared");
            }
        }}>
            {
                getObjectKeys(props.data).map((prop) => {
                    return(<td>{props.data[prop]}</td>);
                })
            }
        </tr>
    )
}

export default TableRow
