package uz.developers.paypal.config;

public enum UserPermission {


    CARD_READ_ALL("card:readAll"),
    CARD_READ_ONE("card:readOne"),
    CARD_ADD("card:add"),
    CARD_EDIT("card:edit"),
    CARD_DELETE("card:delete"),

    INCOME_READ_ALL("income:readAll"),
    INCOME_READ_ONE("income:readOne"),
    INCOME_ADD("income:add"),
    INCOME_EDIT("income:edit"),
    INCOME_DELETE("income:delete"),

    OUTCOME_READ_ALL("outcome:readAll"),
    OUTCOME_READ_ONE("outcome:readOne"),
    OUTCOME_ADD("outcome:add"),
    OUTCOME_EDIT("outcome:edit"),
    OUTCOME_DELETE("outcome:delete"),

    USER_READ_ALL("user:readAll"),
    USER_READ_ONE("user:readOne"),
    USER_ADD("user:add"),
    USER_EDIT("user:edit"),
    USER_DELETE("user:delete");



    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
