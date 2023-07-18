#include <iostream>
#include <string>

#ifndef ITEM_H
#define ITEM_H
    class Item {
        private:
            std::string itemName;
            long int price;
            
        public:
            Item(std::string itemName, long int price);
            std::string getItemName();
            long int getPrice();
            bool operator==(Item& otherItem);
            bool operator!=(Item& otherItem);
    };
#endif