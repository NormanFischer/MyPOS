#include "tojson.h"

ToJson::ToJson()
{}


json ToJson::fromItem(Item &item)
{
    json itemData;
    itemData["sku"] = item.getSku();
    itemData["costPer"] = item.getCostPer();
    itemData["quantity"] = item.getQuantity();
    return itemData;
}

json ToJson::fromItemVector(std::vector<Item> &items)
{
    json itemsData;
    for(Item item: items) {
        itemsData.push_back(ToJson::fromItem(item));
    }
    return itemsData;
}

json ToJson::toPostTransactionDTO(Transaction &transaction)
{
    json transactionData;
    transactionData["items"] = ToJson::fromItemVector(transaction.getItems());
    transactionData["total"] = transaction.getTotal();
    return transactionData;
}
