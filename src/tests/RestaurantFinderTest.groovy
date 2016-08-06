package tests

import data.reader.RestaurantsParser
import models.Item
import models.Order
import models.Restaurant
import search.RestaurantFinder

/**
 * Created by mukuls-webonise on 6/8/16.
 */
class RestaurantFinderTest extends GroovyTestCase {


    RestaurantFinder finder;

    void setUp() {
        super.setUp()
        List<Restaurant> restaurants = new RestaurantsParser().parse()
        finder = new RestaurantFinder(restaurants)
    }

//    Menu directly available(1 item or all items).
//
//    2. Menu available but distributed over multiple items.
//
//    3. Menu need not be present in all restaurants listed.
//
//    4. Menu not available at all.

    void testSingleAvailable() {
        executeSearch(new Order(new Item("burger")))
    }

    void testComboAvailable() {
        List<Item> searchItems = new ArrayList<>()
        searchItems.add(new Item("extra_salsa"))
        searchItems.add(new Item(("extreme_fajita")))
        searchItems.add(new Item(("jalapeno_poppers")))

        executeSearch(new Order(searchItems))
    }

    void testDistributedAvailable() {
        List<Item> searchItems = new ArrayList<>()
        searchItems.add(new Item("fancy_european_water"))
        searchItems.add(new Item(("extreme_fajita")))
        executeSearch(new Order(searchItems))
    }

    void testSomePresentButNotAll() {
        List<Item> searchItems = new ArrayList<>()
        searchItems.add(new Item("lassi"))
        searchItems.add(new Item(("extreme_fajita")))
        executeSearch(new Order(searchItems))
    }

    void testNotAvailable() {
        List<Item> searchItems = new ArrayList<>()
        searchItems.add(new Item("dal_makhani"))
        executeSearch(new Order(searchItems))
    }

    void executeSearch(Order order) {
        print finder.searchRestaurant(order)
    }
}
