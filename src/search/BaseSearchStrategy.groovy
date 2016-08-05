package search

/**
 * Created by mukuls-webonise on 5/8/16.
 */
abstract class BaseSearchStrategy implements SearchStrategy {

    List<SearchResult> hits = new ArrayList<>()

    def addSearchResult(SearchResult result) {
        hits.add(result)
    }

    SearchResult getCheapestRestaurantResult() {
//        TODO: test this is in asc order
        hits.sort { it.price}
        if (hits.size() > 0){
           return hits.get(0)
        } else {
            throw new SearchException()
        }
    }
}
