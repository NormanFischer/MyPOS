#include "mainwindow.h"
#include "./ui_mainwindow.h"
#include "core/HttpClient.h"
#include "core/item.h"
#include <iostream>
#include <curl/curl.h>
#include <nlohmann/json.hpp>

using json = nlohmann::json;

MainWindow::MainWindow(HttpClient *client, QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow), client(client)
{

    QWidget *main = new QWidget(this);
    setCentralWidget(main);

    layout = new QVBoxLayout(this);

    enterSku = new QPushButton("Enter sku");
    skuEntry = new QLineEdit();

    connect(enterSku, &QPushButton::released, this, &MainWindow::handleEnterSku);
    connect(skuEntry, &QLineEdit::returnPressed, this, &MainWindow::handleEnterSku);

    transactionTable = new QTableWidget();
    transactionTable->setShowGrid(false);
    transactionTable->setRowCount(0);
    transactionTable->setColumnCount(5);
    transactionTable->setHorizontalHeaderItem(0, new QTableWidgetItem("ITEM NAME"));
    transactionTable->setHorizontalHeaderItem(1, new QTableWidgetItem("SKU #"));
    transactionTable->setHorizontalHeaderItem(2, new QTableWidgetItem("QUANTITY"));
    transactionTable->setHorizontalHeaderItem(3, new QTableWidgetItem("COST PER"));
    transactionTable->setHorizontalHeaderItem(4, new QTableWidgetItem("SUBTOTAL"));
    transactionRow = 0;

    layout->addWidget(transactionTable);
    layout->addWidget(skuEntry);
    layout->addWidget(enterSku);
    main->setLayout(layout);
    //ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::handleEnterSku() {
    std::string skuToReq = skuEntry->text().toStdString();
    std::string url = "/items/getItemBySku/" + skuToReq;

    HttpResponse fetchResponse = client->fetch(url, HttpClient::GET);
    std::string jsonStr = fetchResponse.body;

    json j = json::parse(jsonStr);
    QString itemSku = QString::fromStdString(j["sku"]);
    QString itemName = QString::fromStdString(j["itemName"]);
    int quantity = j["quantity"];
    int cost = j["cost"];
    QString quantityStr = QString::fromStdString(std::to_string(quantity));
    QString costStr = QString::fromStdString(std::to_string(cost));

    skuEntry->setText("");
    transactionTable->insertRow(transactionTable->rowCount());
    transactionTable->setItem(transactionTable->rowCount() - 1, 0, new QTableWidgetItem(itemSku));
    transactionTable->setItem(transactionTable->rowCount() - 1, 1, new QTableWidgetItem(itemName));
    transactionTable->setItem(transactionTable->rowCount() - 1, 2, new QTableWidgetItem(quantityStr));
    transactionTable->setItem(transactionTable->rowCount() - 1, 3, new QTableWidgetItem(costStr));
}

