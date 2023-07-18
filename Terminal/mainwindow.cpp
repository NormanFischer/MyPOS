#include "mainwindow.h"
#include "./ui_mainwindow.h"
#include <iostream>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
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
    QString skuText = skuEntry->text();
    transactionTable->insertRow(transactionTable->rowCount());
    transactionTable->setItem(transactionTable->rowCount() - 1, 0, new QTableWidgetItem("Item added"));
}

