#include <iostream>
#include <vector>
#include "item.h"
#include <nlohmann/json.hpp>

using json = nlohmann::json;

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
            std::string toJsonStr();
    };
#endif
