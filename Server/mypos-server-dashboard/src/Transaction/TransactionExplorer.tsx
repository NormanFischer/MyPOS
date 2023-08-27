import { useState } from "react";
import DatabaseExplorer from "../DatabaseExplorer";
import { TransactionConstructor, Transaction } from "./Transaction";

function TransactionExplorer() {
    const [viewTransactionSummaryIsOn, setViewTransactionSummary] = useState(false);
    const [viewReportsIsOn, toggleViewReportsIsOn] = useState(false);

    function handleViewReportButtonClick() {
        toggleViewReportsIsOn(!viewReportsIsOn);
    }

    function handleRowClick(transactionData: Transaction) {
        console.log(transactionData.user);
        console.log(transactionData.datetime);
        console.log(transactionData.total);
    }

    return (
        <>
        <DatabaseExplorer dataType={TransactionConstructor} apiEndpoint={"/transactions/getTransactions"} rowClickFunction={handleRowClick}/>
        <button onClick={handleViewReportButtonClick}>Generate Reports</button>
        </>
    )
}

export default TransactionExplorer;