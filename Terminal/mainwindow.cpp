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

    main->setLayout(layout);

    //ui->setupUi(this);
}

void MainWindow::toggleLoginWindow()
{
    //Prompt user login...
    if (loginWindow->isHidden()) {
        loginWindow->setModal(true);
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
    connect(loginWindow, &LoginWindow::userRequestedLogin, this, &MainWindow::handleLoginRequest);
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
    std::cout << "Attempting to log in" << std::endl;
    bool loggedIn = httpClient->login(userName, password);
    //Login will redirect
    if (loggedIn)
    {
        //Update display
        emit gotNewUserName(httpClient->getUserName());
        toggleLoginWindow();
    } else {
        std::cout << "Could not log in..." << std::endl;
    }
}

//Slots

void MainWindow::handleItemAdded(ItemTableRow itemTableRow)
{
    int newTotal = currentTransaction.getTotal() + (itemTableRow.costPer * itemTableRow.quantity);
    currentTransaction.add_item(Item(itemTableRow.itemSKU, itemTableRow.quantity));
    currentTransaction.setTotal(newTotal);
    QString newTotalStr = QString::fromStdString(std::to_string(newTotal));
    transactionTableWidget->setTotal(newTotalStr);
}

void MainWindow::processRequestedTransaction()
{
    json transactionBody = ToJson::toPostTransactionDTO(currentTransaction);
    std::string url = "/transaction/postTransaction";
    HttpResponse response = httpClient->fetch(url, HttpClient::POST, transactionBody);
    std::cout << "Response:" << response.body.dump() << std::endl;
}



