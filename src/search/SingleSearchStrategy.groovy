package search

import models.Item
import models.Meal
import models.Order
import models.Restaurant

/**
 * Created by mukuls-webonise on 5/8/16.
 */
class SingleSearchStrategy extends BaseSearchStrategy {

    @Override
    SearchResult searchRestaurantsForOrder(List<Restaurant> restaurants, Order order) {
        Item search = order.items.get(0)
        print "Searching for : ${search.name}\n"
        for (rest in restaurants) {
            for (meal in rest.meals) {
                if(meal.items.contains(search)) {
                    addSearchResult(new SearchResult(meal.price, rest.id))
                }
            }
        }
        return getCheapestRestaurantResult()
    }
}
