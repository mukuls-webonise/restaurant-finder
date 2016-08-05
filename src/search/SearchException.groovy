package search

/**
 * Created by mukuls-webonise on 5/8/16.
 */
class SearchException extends Exception {
    SearchException() {
        super("No restaurant found for your order")
    }
}
