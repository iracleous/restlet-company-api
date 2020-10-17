package gr.codehub.restapi.security;

public enum CustomRole {
    ROLE_NA("n/a"),
    ROLE_ADMIN("admin"),
    ROLE_USER("user"),
    ROLE_OWNER("owner");

    private final String roleName;

    CustomRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static CustomRole getRoleValue(String roleParameter) {
        for (CustomRole role : CustomRole.values()) {
            if (roleParameter.equals(role.getRoleName()))
                return role;
        }
        return ROLE_NA;
    }
}