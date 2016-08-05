package search

/**
 * Created by mukuls-webonise on 5/8/16.
 */
class SearchResult {
    float price
    int restId

    SearchResult(float price, int restId) {
        this.price = price
        this.restId = restId
    }


    @Override
    public String toString() {
        return "SearchResult{" +
                "price=" + price +
                ", restId=" + restId +
                '}';
    }
}
