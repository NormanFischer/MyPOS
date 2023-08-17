import { getObjectKeys } from "./tableUtil";

function TableRow<T extends Record<string, any>>(props: {data: T}) {
    return (
        <tr>
            {
                getObjectKeys(props.data).map((prop) => {
                    return(<td>{props.data[prop]}</td>);
                })
            }
            
        </tr>
    )
}

export default TableRow
