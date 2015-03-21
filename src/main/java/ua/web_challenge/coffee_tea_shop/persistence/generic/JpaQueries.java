package ua.web_challenge.coffee_tea_shop.persistence.generic;

/**
 * Created on 21.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public final class JpaQueries {
    private JpaQueries() {}

    public static final String ENTITY_NAME_TEMPLATE = "${entityName}";
    public static final String FIND_ALL = "SELECT o FROM " + ENTITY_NAME_TEMPLATE + " o";
    public static final String FIND_BY_NAME = "SELECT o FROM " + ENTITY_NAME_TEMPLATE + " o WHERE o.name = :name";

    public static String findAllQuery(final String entityName) {
        return FIND_ALL.replace(ENTITY_NAME_TEMPLATE, entityName);
    }

    public static String findByNameQuery(String entityName) {
        return FIND_BY_NAME.replace(ENTITY_NAME_TEMPLATE, entityName);
    }
}
