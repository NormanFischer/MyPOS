import { getObjectKeys } from "./tableUtil";

function TableHeaderRow<T extends Record<string, any>>({ dataType }: { dataType: new () => T }) {
    const instance: T = new dataType;
    const keys = getObjectKeys(instance);

    return(
        <tr>
        {
            keys.map((key) => {
                return <th>{key.toString()}</th>
            })
        }
        </tr>
    )
}

export default TableHeaderRow