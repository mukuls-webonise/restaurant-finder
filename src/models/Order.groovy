package models

import com.sun.javaws.exceptions.InvalidArgumentException

/**
 * Created by mukuls-webonise on 5/8/16.
 */
class Order {
    List<Item> items

    Order(List<Item> items) {
        this.items = items
        checkOrderSize()
    }

    Order(Item item) {
        this.items = new ArrayList<>()
        items.add(item)
        checkOrderSize()
    }

    def checkOrderSize() {
        if (items == null || items.empty) {
            throw new InvalidArgumentException("Order can't be empty")
        }
    }
}
