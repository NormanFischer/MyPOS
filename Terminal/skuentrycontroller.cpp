#include "skuentrycontroller.h"
#include "core/HttpClient.h"
#include "QIntValidator"
#include <nlohmann/json.hpp>
#include <string>

using json = nlohmann::json;

SkuEntryController::SkuEntryController(HttpClient *httpClient, QWidget *parent)
    :QWidget(parent), httpClient(httpClient)
{
    enterSkuButton = new QPushButton("Enter sku", this);
    completeTransaction = new QPushButton("Complete Transaction", this);
    skuEntry = new QLineEdit(this);
    quantityEntry = new QLineEdit(this);
    layout = new QHBoxLayout(this);

    QIntValidator *validQuantity = new QIntValidator();
    validQuantity->setBottom(1);
    quantityEntry->setValidator(validQuantity);

    layout->addWidget(skuEntry);
    layout->addWidget(quantityEntry);
    layout->addWidget(enterSkuButton);
    layout->addWidget(completeTransaction);

    connect(enterSkuButton, &QPushButton::released, this, &SkuEntryController::handleEnterSkuButtonReleased);
    connect(skuEntry, &QLineEdit::returnPressed, this, &SkuEntryController::handleEnterSkuButtonReleased);
    connect(completeTransaction, &QPushButton::released, this, &SkuEntryController::hanldeCompleteTransactionButtonReleased);
}

void SkuEntryController::handleEnterSkuButtonReleased()
{
    //Create item table row
    std::string skuToReq = skuEntry->text().toStdString();
    std::string url = "/items/getItemBySku/" + skuToReq;

    HttpResponse fetchResponse = httpClient->fetch(url, HttpClient::GET);
    std::string jsonStr = fetchResponse.body;

    json j = json::parse(jsonStr);
    std::string itemSku = j["sku"];
    std::string itemName = j["itemName"];
    int cost = j["cost"];

    emit itemTableRowCreated(ItemTableRow {itemSku, itemName, 1, cost});
}

void SkuEntryController::hanldeCompleteTransactionButtonReleased()
{
    emit completeTransactionRequested();
}
