package search

import models.Order
import models.Restaurant

/**
 * Created by mukuls-webonise on 5/8/16.
 */
class RestaurantFinder {

    private final MultiSearchStrategy multi = new MultiSearchStrategy()
    private final SingleSearchStrategy single = new SingleSearchStrategy()

    SearchStrategy strategy;
    List<Restaurant> restaurants;

    RestaurantFinder(List<Restaurant> restaurants) {
        this.restaurants = restaurants
    }

    SearchResult searchRestaurant(Order order) {
        decideStrategyForOrder(order)
        strategy.searchRestaurantsForOrder(restaurants, order)
    }

    def decideStrategyForOrder(Order order) {
        strategy = order.items.size() > 1 ? multi : single
    }

}
