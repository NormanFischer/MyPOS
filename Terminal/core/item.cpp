#include <core/item.h>

Item::Item(const std::string sku, int quantity) {
    this->sku = sku;
    this->quantity = quantity;
}

std::string Item::getSku() {
    return sku;
}

int Item::getQuantity() {
    return quantity;
}

bool Item::operator==(Item& otherItem) {
    return getSku() == otherItem.getSku() && getQuantity() == otherItem.getQuantity();
}

bool Item::operator!=(Item& otherItem) {
    return getSku() != otherItem.getSku() || getQuantity() != otherItem.getQuantity();
}

