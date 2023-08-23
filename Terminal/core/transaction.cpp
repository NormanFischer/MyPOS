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
