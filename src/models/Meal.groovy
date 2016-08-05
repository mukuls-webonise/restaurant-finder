package models

import groovy.transform.EqualsAndHashCode

/**
 * Created by webonise on 5/8/16.
 */
@EqualsAndHashCode
class Meal {
    float price
    List<Item> items

    Meal(def price, def List<Item> items) {
        this.price = price
        this.items = items
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", items=" + items +
                '}';
    }
}
