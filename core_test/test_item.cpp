#include "cassert"
#include "test_item.h"

void test() {
    //Getters and setters
    Item i("test", 100);
    assert(i.getItemName() == "test");
    assert(i.getPrice() == 100);
    
    //Item equality
    Item i2("test", 100);
    assert(i == i2);

    //Item inequality
    Item i3("fake", 100);
    assert(i != i3);

}

int main() {
    test();
}