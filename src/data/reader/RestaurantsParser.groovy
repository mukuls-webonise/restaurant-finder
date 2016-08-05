package data.reader

import au.com.bytecode.opencsv.CSVParser

/**
*  Created by mukuls-webonise on 5/8/16.
*/

import com.xlson.groovycsv.CsvParser
import models.Item
import models.Meal
import models.Restaurant

import java.nio.file.Paths

class RestaurantsParser {

    static String defaultPath = "sample_data.csv"

    def map = [:]

    def List<Restaurant> parse() {
        readRestaurants()
    }

    List<Restaurant> readRestaurants(String path = defaultPath) {
        def csv = readFile(path)

        def data = new CsvParser().parse(csv, readFirstLine:true,
                columnNames:['id', 'price', 'items'])

        for (line in data) {
            readRestaurant(line)

        }
        return map.values().asList()
    }

    static String readFile(String path) {
        new File(path).text
    }


    def readRestaurant(line) {
        int id = "$line.id".toInteger()

        float price = "$line.price".toFloat()
        def items = "$line.items"
        def meal = readMeal(price, items)

        if (hasRestaurantForId(id)) {
            def rest = map.get(id)
            rest.meals.add(meal)
        } else {
            def meals = [meal] as ArrayList<Meal>
            map.put(id, createRestaurant(id, meals))
        }
    }

    static Meal readMeal(price, items) {
        List<Item> mealItems = [] as ArrayList
        if (items.lastIndexOf(' ') > 1) {
            String[] split = items.split('\\s')
            for (item in split) {
                item = item.trim()
                mealItems.add(new Item(item))
            }
        } else {
            items = items.trim()
            mealItems.add(new Item(items))
        }
        new Meal(price, mealItems)
    }

    boolean hasRestaurantForId(id) {
        map.containsKey(id)
    }
     static Restaurant createRestaurant(def id, List<Meal> meal) {
       new Restaurant(id, meal)
    }
}