#include <iostream>
#include <vector>
#include "item.h"

#ifndef TRANSACTION_H
#define TRANSACTION
    class Transaction {
        private:
            int userId;
            std::vector<Item> items;
        public:
            Transaction();
            std::vector<Item>& getItems();
            void add_item(Item i);
            void delete_item();
            long int calculateTotal();
            int getUserId;
    };
#endif
