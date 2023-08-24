import { useState } from "react";
import DatabaseExplorer from "../DatabaseExplorer";
import { TransactionConstructor } from "./Transaction";

function TransactionExplorer() {
    const [viewReportsIsOn, toggleViewReportsIsOn] = useState(false);

    function handleViewReportButtonClick() {
        toggleViewReportsIsOn(!viewReportsIsOn);
    }

    return (
        <>
        <DatabaseExplorer dataType={TransactionConstructor} apiEndpoint={"/transactions/getTransactions"}/>
        <button onClick={handleViewReportButtonClick}>Generate Reports</button>
        </>
    )
}

export default TransactionExplorer;