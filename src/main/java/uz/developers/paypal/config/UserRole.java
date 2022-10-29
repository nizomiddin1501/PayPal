package uz.developers.paypal.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static uz.developers.paypal.config.UserPermission.*;

public enum UserRole {


    USER(Sets.newHashSet(CARD_READ_ALL,CARD_READ_ONE,CARD_ADD,
            INCOME_READ_ALL,INCOME_READ_ONE,INCOME_ADD,
            OUTCOME_READ_ALL,OUTCOME_READ_ONE,OUTCOME_ADD,
            USER_READ_ALL,USER_READ_ONE,USER_ADD)),


    ADMIN(Sets.newHashSet(CARD_READ_ALL,CARD_READ_ONE,CARD_ADD,CARD_EDIT,
            INCOME_READ_ALL,INCOME_READ_ONE,INCOME_ADD,INCOME_EDIT,
            OUTCOME_READ_ALL,OUTCOME_READ_ONE,OUTCOME_ADD,OUTCOME_EDIT,
            USER_READ_ALL,USER_READ_ONE,USER_ADD,USER_EDIT)),


    SUPER_ADMIN(Sets.newHashSet(CARD_READ_ALL,CARD_READ_ONE,CARD_ADD,CARD_EDIT,CARD_DELETE,
            INCOME_READ_ALL,INCOME_READ_ONE,INCOME_ADD,INCOME_EDIT,INCOME_DELETE,
            OUTCOME_READ_ALL,OUTCOME_READ_ONE,OUTCOME_ADD,OUTCOME_EDIT,OUTCOME_DELETE,
            USER_READ_ALL,USER_READ_ONE,USER_ADD,USER_EDIT,USER_DELETE));


    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

}
