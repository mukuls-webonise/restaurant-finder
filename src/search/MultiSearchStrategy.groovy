package search

import models.Item
import models.Meal
import models.Order
import models.Restaurant

/**
 * Created by mukuls-webonise on 5/8/16.
 */
class MultiSearchStrategy extends BaseSearchStrategy {

    @Override
    SearchResult searchRestaurantsForOrder(List<Restaurant> restaurants, Order order) {
        print "Searching for : ${order.items}\n"
        for (rest in restaurants) {

            HashMap<Meal, List<Item>> foundInRestWithMeal = new HashMap<>()
            for (meal in rest.meals) {
                if(meal.items.containsAll(order.items)) {
                    addSearchResult(new SearchResult(meal.price, rest.id))
                } else {
                    def found = mealContainsWhichOrderItems(meal, order)
                    foundInRestWithMeal.put(meal, found)
                }
            }
            def allFound = []
            for (found in foundInRestWithMeal.values()) {
                allFound.addAll(found)
            }
            if (allFound.containsAll(order.items)) {
                float totalPrice = 0.0
               for (mealsThatInclude in foundInRestWithMeal.keySet()) {
                   totalPrice += mealsThatInclude.price
               }
                addSearchResult(new SearchResult(totalPrice, rest.id))
            }
        }
        return getCheapestRestaurantResult()
    }


    static List<Item> mealContainsWhichOrderItems(Meal meal, Order order) {
        def found = []
        for (item in meal.items) {
            for (orderItem in order.items) {
                if (item == orderItem) {
                    found.add(item)
                }
            }
        }
        return found
    }
}
