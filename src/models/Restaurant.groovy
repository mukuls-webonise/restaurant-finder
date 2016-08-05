package models
/**
 * Created by webonise on 5/8/16.
 */

class Restaurant {
    int id
    List<Meal> meals

    Restaurant(id, List<Meal> meals) {
        this.id = id
        this.meals = meals
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", meals=" + meals +
                '}\n';
    }
}