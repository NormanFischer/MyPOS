#include "item.h"

Item::Item(std::string itemName, long int price) {
    this->itemName = itemName;
    this->price = price;
}

std::string Item::getItemName() {
    return this->itemName;
}

long int Item::getPrice() {
    return this->price;
}

bool Item::operator==(Item& otherItem) {
    return this->getItemName() == otherItem.getItemName() && this->getPrice() == otherItem.getPrice();
}

bool Item::operator!=(Item& otherItem) {
    return this->getItemName() != otherItem.getItemName() || this->getPrice() != otherItem.getPrice();
}

