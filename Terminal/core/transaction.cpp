#include <iostream>
#include "tojson.h"

Transaction::Transaction()
{
    items = {};
}

std::vector<Item>& Transaction::getItems()
{
    return items;
}

void Transaction::add_item(Item i)
{
    getItems().push_back(i);
}

std::string Transaction::toJsonStr()
{
    std::string jsonStr;
    jsonStr.append(ToJson::toPostTransactionDTO(*this).dump());
    std::cout << "Json dump: " << jsonStr << std::endl;
    return jsonStr;
}
