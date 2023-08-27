#include <core/item.h>

Item::Item(const std::string &sku, const int quantity, const int costPer)
{
    this->sku = sku;
    this->quantity = quantity;
    this->costPer = costPer;
}

const std::string Item::getSku()
{
    return sku;
}

const int Item::getQuantity()
{
    return quantity;
}

const int Item::getCostPer()
{
    return costPer;
}

bool Item::operator==(Item& otherItem)
{
    return getSku() == otherItem.getSku() && getQuantity() == otherItem.getQuantity();
}

bool Item::operator!=(Item& otherItem)
{
    return getSku() != otherItem.getSku() || getQuantity() != otherItem.getQuantity();
}

