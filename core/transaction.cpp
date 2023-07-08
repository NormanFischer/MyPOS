#include <iostream>
#include "transaction.h"

Transaction::Transaction() {
    this->items = {};
}

std::vector<Item>& Transaction::getItems() {
    return this->items;
}

void Transaction::add_item(Item i) {
    Transaction::getItems().insert(Transaction::getItems().begin(), i);    
}