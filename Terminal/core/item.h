#include <iostream>
#include <string>

#ifndef ITEM_H
#define ITEM_H
    class Item {
        private:
            std::string sku;
            int costPer;
            int quantity;
            
        public:
            Item(const std::string& sku, const int quantity, const int costPer);
            const std::string getSku();
            const int getQuantity();
            const int getCostPer();
            bool operator==(Item& otherItem);
            bool operator!=(Item& otherItem);
    };
#endif
