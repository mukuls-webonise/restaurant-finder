package models

/**
 * Created by mukuls-webonise on 5/8/16.
 */
class Order {
    List<Item> items

    Order(List<Item> items) {
        this.items = items
    }

    Order(Item item) {
        this.items = new ArrayList<>()
        items.add(item)
    }
}
