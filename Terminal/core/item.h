#include <iostream>
#include <string>

#ifndef ITEM_H
#define ITEM_H
    class Item {
        private:
            std::string sku;
            int quantity;
            
        public:
            Item(std::string sku, int quantity);
            const std::string getSku();
            const int getQuantity();
            bool operator==(Item& otherItem);
            bool operator!=(Item& otherItem);

    };
#endif
