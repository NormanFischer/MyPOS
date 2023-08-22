#include <nlohmann/json.hpp>
#include <core/transaction.h>

using json = nlohmann::json;

#ifndef TOJSON_H
#define TOJSON_H

class ToJson
{
public:
    ToJson();
    static json fromItem(Item &item);
    static json fromItemVector(std::vector<Item> &items);
    static json toPostTransactionDTO(Transaction &transaction);
};

#endif // TOJSON_H
