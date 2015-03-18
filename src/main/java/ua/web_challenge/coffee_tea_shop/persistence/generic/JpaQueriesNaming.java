package ua.web_challenge.coffee_tea_shop.persistence.generic;

/**
 * Created on 18.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public final class JpaQueriesNaming {
    private JpaQueriesNaming() {}

    public static final String RANGE_QUERY = "findRange";

    public static String rangeQuery(String className) {
        return className + "." + RANGE_QUERY;
    }
}
