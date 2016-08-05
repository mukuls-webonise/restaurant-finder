import data.reader.RestaurantsParser
import models.Item
import models.Order
import models.Restaurant
import search.RestaurantFinder
import search.SearchResult

/**
 * Created by webonise on 5/8/16.
 */
class Main {

    public static void main(String[] args) {
        List<Restaurant> rests = new RestaurantsParser().parse()
        def finder = new RestaurantFinder(rests)
        List<Item> searchItems = new ArrayList<>()
//        searchItems.add(new Item("jalapeno_poppers"))
//        searchItems.add(new Item("fancy_european_water"))
//        searchItems.add(new Item(("extreme_fajita")))
        def order = new Order(searchItems)
        SearchResult result = finder.searchRestaurant(order)
        print result
    }
}
