#include "mainwindow.h"
#include "./ui_mainwindow.h"
#include "core/item.h"
#include <iostream>
#include <curl/curl.h>
#include <nlohmann/json.hpp>

using json = nlohmann::json;

MainWindow::MainWindow(HttpClient *httpClient, QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow), httpClient(httpClient)
{
    QWidget *main = new QWidget(this);
    setCentralWidget(main);
    initializeChildren();
    initializeConnects();
    initializeAndSetupLayout();
    main->setLayout(layout);
    //ui->setupUi(this);
}

//Startup helpers

void MainWindow::initializeConnects()
{
    connect(skuEntryController, &SkuEntryController::itemTableRowCreated, transactionTableWidget, &TransactionTableWidget::onItemReceived);
}

void MainWindow::initializeChildren()
{
    transactionTableWidget = new TransactionTableWidget(this);
    skuEntryController = new SkuEntryController(httpClient, this);
}

void MainWindow::initializeAndSetupLayout()
{
    layout = new QVBoxLayout(this);
    layout->addWidget(transactionTableWidget);
    layout->addWidget(skuEntryController);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::handleEnterSku()
{

}

