package search

import models.Order
import models.Restaurant

/**
 * Created by mukuls-webonise on 5/8/16.
 */
interface SearchStrategy {
    SearchResult searchRestaurantsForOrder(List<Restaurant> restaurants, Order order)
}