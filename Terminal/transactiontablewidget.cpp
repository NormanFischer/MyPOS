#include "transactiontablewidget.h"
#include <iostream>

TransactionTableWidget::TransactionTableWidget(QWidget *parent)
    :QWidget(parent)
{
    layout = new QHBoxLayout();
    transactionTable = new QTableWidget();
    transactionTable->setShowGrid(false);
    transactionTable->setRowCount(0);
    transactionTable->setColumnCount(5);
    transactionTable->setHorizontalHeaderItem(0, new QTableWidgetItem("ITEM NAME"));
    transactionTable->setHorizontalHeaderItem(1, new QTableWidgetItem("SKU #"));
    transactionTable->setHorizontalHeaderItem(2, new QTableWidgetItem("QUANTITY"));
    transactionTable->setHorizontalHeaderItem(3, new QTableWidgetItem("COST PER"));
    transactionTable->setHorizontalHeaderItem(4, new QTableWidgetItem("SUBTOTAL"));
    layout->addWidget(transactionTable);
    setLayout(layout);
}

void TransactionTableWidget::populateTableRow(ItemTableRow itemTableRow)
{
    std::cout << "Populate row" << std::endl;
    transactionTable->insertRow(transactionTable->rowCount());
    transactionTable->setItem(transactionTable->rowCount() - 1, 0, new QTableWidgetItem(QString::fromStdString(itemTableRow.itemSKU)));
    transactionTable->setItem(transactionTable->rowCount() - 1, 1, new QTableWidgetItem(QString::fromStdString(itemTableRow.itemName)));
    transactionTable->setItem(transactionTable->rowCount() - 1, 2, new QTableWidgetItem(QString::fromStdString(std::to_string(itemTableRow.quantity))));
    transactionTable->setItem(transactionTable->rowCount() - 1, 3, new QTableWidgetItem(QString::fromStdString(std::to_string(itemTableRow.costPer))));
}

//Slots
void TransactionTableWidget::onItemReceived(ItemTableRow itemTableRow)
{
    this->populateTableRow(itemTableRow);
}


