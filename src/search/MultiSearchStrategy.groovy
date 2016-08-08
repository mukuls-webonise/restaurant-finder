package search

import models.Item
import models.Meal
import models.Order
import models.Restaurant

/**
 * Created by mukuls-webonise on 5/8/16.
 */
class MultiSearchStrategy extends BaseSearchStrategy {

    private final static float INFINITY = -1.0

    @Override
    SearchResult searchRestaurantsForOrder(List<Restaurant> restaurants, Order order) {
        print "Searching for : ${order.items}\n"
        for (rest in restaurants) {

            //find meals that serve
            //if meal has all the items add to search result
            //else if meal has partial items find meals that serve missing items
            //if any item is missing in else case throw exception
            def servingMealsAndMissing = findServingMealsAndMissingItems(rest.meals, order)
            if (servingMealsAndMissing.isEmpty()) {
                continue
            }

            servingMealsAndMissing.each {meal, missing ->
                if (missing.isEmpty()) {
                    addSearchResult(new SearchResult(meal.price, rest.id))
                } else {
                    float price = meal.price
                    float priceForMissing = priceForMissingItems(rest, missing)
                    if (priceForMissing != INFINITY) {
                        float total = price + priceForMissing
                        addSearchResult(new SearchResult(total, rest.id))
                    }
                }
            }


//            HashMap<Meal, List<Item>> foundInRestWithMeal = new HashMap<>()
//            for (meal in rest.meals) {
//                if(meal.items.containsAll(order.items)) {
//                    addSearchResult(new SearchResult(meal.price, rest.id))
//                } else {
//                    def found = mealContainsWhichOrderItems(meal, order)
//                    foundInRestWithMeal.put(meal, found)
//                }
//            }
//            def allFound = []
//            for (found in foundInRestWithMeal.values()) {
//                allFound.addAll(found)
//            }
//            if (allFound.containsAll(order.items)) {
//                float totalPrice = 0.0
//               for (mealsThatInclude in foundInRestWithMeal.keySet()) {
//                   totalPrice += mealsThatInclude.price
//               }
//                addSearchResult(new SearchResult(totalPrice, rest.id))
//            }


        }
        return getCheapestRestaurantResult()
    }

    static HashMap<Meal, Set<Item>> findServingMealsAndMissingItems(List<Meal> meals, Order order) {
        def map = [:]
        for (meal in meals) {
            def orderItemsPending = new HashSet(order.items)
            orderItemsPending.removeAll(meal.items)
            if (orderItemsPending.size() != order.items.size()) {
                map.put(meal, orderItemsPending)
            }
        }
        return map
    }

    static float priceForMissingItems(Restaurant restaurant, Set<Item> missing) {
        try {
            def multiSearch = new MultiSearchStrategy()
            def rests = [restaurant] as ArrayList
            def result = multiSearch.searchRestaurantsForOrder(rests, new Order(missing))
            return result.price
        } catch (SearchException e) {
            print "Menu not available completely in ${restaurant.id}\n"
            return INFINITY
        }
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
