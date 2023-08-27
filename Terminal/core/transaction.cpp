#include <iostream>
#include "tojson.h"

Transaction::Transaction()
{
    items = {};
    total = 0;
}

std::vector<Item>& Transaction::getItems()
{
    return items;
}

void Transaction::add_item(const Item &i)
{
    getItems().push_back(i);
}

void Transaction::setTotal(int newTotal)
{
    total = newTotal;
}

int Transaction::getTotal()
{
    return total;
}
