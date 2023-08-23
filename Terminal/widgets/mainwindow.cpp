#include "mainwindow.h"
#include "./ui_mainwindow.h"
#include "core/item.h"
#include <iostream>
#include <curl/curl.h>
#include "QScreen"
#include "QDesktopWidget"

MainWindow::~MainWindow()
{
    delete ui;
}

MainWindow::MainWindow(HttpClient *httpClient, QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow), httpClient(httpClient)
{
    QWidget *main = new QWidget(this);

    setCentralWidget(main);
    initializeChildren();
    initializeConnects();
    initializeAndSetupLayout();
    //Get username
    emit gotNewUserName(httpClient->getUserName());

    main->setLayout(layout);



    //ui->setupUi(this);
}

void MainWindow::toggleLoginWindow()
{
    //Prompt user login...
    if (loginWindow->isHidden()) {
        loginWindow->show();
    } else {
        loginWindow->hide();
    }
}


//Startup helpers

void MainWindow::initializeConnects()
{
    connect(skuEntryController, &SkuEntryController::itemTableRowCreated, transactionTableWidget, &TransactionTableWidget::onItemReceived);
    connect(skuEntryController, &SkuEntryController::itemTableRowCreated, this, &MainWindow::handleItemAdded);
    connect(skuEntryController, &SkuEntryController::completeTransactionRequested, this, &MainWindow::processRequestedTransaction);
    connect(this, &MainWindow::gotNewUserName, headerContainerWidget, &HeaderContainerWidget::handleUserNameChangeRequest);
    connect(loginWindow, &LoginWindow::handleLoginButtonReleased, this, &MainWindow::handleLoginRequest);
}

void MainWindow::initializeChildren()
{
    headerContainerWidget = new HeaderContainerWidget(httpClient, this);
    transactionTableWidget = new TransactionTableWidget(this);
    skuEntryController = new SkuEntryController(httpClient, this);
    loginWindow = new LoginWindow(this);
}

void MainWindow::initializeAndSetupLayout()
{
    layout = new QVBoxLayout(this);
    layout->addWidget(headerContainerWidget);
    layout->addWidget(transactionTableWidget);
    layout->addWidget(skuEntryController);
}

//Signals
void MainWindow::handleLoginRequest(const std::string &userName, const std::string &password)
{

}

//Slots

void MainWindow::handleItemAdded(ItemTableRow itemTableRow)
{
    std::cout << "Handling item added" << std::endl;
    std::cout << itemTableRow.itemSKU << std::endl;
    std::cout << std::to_string(itemTableRow.quantity) << std::endl;
    currentTransaction.add_item(Item(itemTableRow.itemSKU, itemTableRow.quantity));
}

void MainWindow::processRequestedTransaction()
{
    json transactionBody = ToJson::toPostTransactionDTO(currentTransaction);
    std::string url = "/transaction/postTransaction";
    HttpResponse response = httpClient->fetch(url, HttpClient::POST, transactionBody);
    std::cout << "Response:" << response.body.dump() << std::endl;
}



