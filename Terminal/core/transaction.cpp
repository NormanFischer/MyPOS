#include <iostream>
#include "transaction.h"

Transaction::Transaction() {
    items = {};
}

std::vector<Item>& Transaction::getItems() {
    return items;
}

void Transaction::add_item(Item i) {
    getItems().push_back(i);
}

std::string Transaction::toJsonStr() {
    std::string jsonStr = "{[";
    for (Item &item : items) {
        json itemData;
        itemData["sku"] = item.getSku();
        itemData["quantity"] = item.getQuantity();
        jsonStr.append(itemData.dump());
    }
    std::cout << "Json dump: " << jsonStr << "}" << std::endl;
    return jsonStr.append("]}");
}
