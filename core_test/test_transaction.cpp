#include "cassert"
#include "vector"
#include "algorithm"
#include "test_transaction.h"

void test() {
    Transaction t;
    Item i("testItem", 123123);

    t.add_item(i);

    std::vector<Item> tVec = t.getItems();
    std::vector<Item> ans = { Item("testItem", 123123)  };

    std::equal(ans.begin(), ans.end(), tVec.begin());
}

int main() {
    test();
}
