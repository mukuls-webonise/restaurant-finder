package models
/**
 * Created by webonise on 5/8/16.
 */

import groovy.transform.EqualsAndHashCode
@EqualsAndHashCode
class Item {
    String name

    Item(name) {
        this.name = name
    }


    @Override
    public String toString() {
        return "Item{" +
                "name=" + name +
                '}';
    }

}
