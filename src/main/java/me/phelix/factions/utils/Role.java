package me.phelix.factions.utils;

public enum  Role {

    LEADER(5, "ROLE_LEADER"),
    COLEADER(4, "ROLE_COLEADER"),
    MODERATOR(3, "ROLE_MODERATOR"),
    NORMAL(2, "ROLE_NORMAL"),
    RECRUIT(1, "ROLE_RECRUIT"),
    NONE(0, "ROLE_NONE");

    public String name;
    public int value;

    Role(int value, String role) {
        this.value = value;
        this.name = role;
    }

    public Role getByValue(int value) {
        switch (value) {
            case 0:
                return NONE;
            case 1:
                return RECRUIT;
            case 2:
                return NORMAL;
            case 3:
                return MODERATOR;
            case 4:
                return COLEADER;
            case 5:
                return LEADER;
        }
        return null;
    }

    public Role getRole(String check) {
        switch (check.toLowerCase()) {
            case "leader":
            case "admin":
                return LEADER;
            case "coleader":
                return COLEADER;
            case "mod":
            case "moderator":
                return MODERATOR;
            case "normal":
            case "member":
                return NORMAL;
            case "recruit":
            case "rec":
                return RECRUIT;
            case "none":
                return NONE;
        }
        return null;
    }

    public boolean isAtleast(Role role) {
        return this.value >= role.value;
    }

    public boolean isAtMost(Role role) {
        return this.value <= role.value;
    }

    public String getPrefix() {
        switch (this) {
            case LEADER:
                return "Leader";
            case COLEADER:
                return "CO-Leader";
            case MODERATOR:
                return "Moderator";
            case NORMAL:
                return "Member";
            case RECRUIT:
                return "Recruit";
            case NONE:
                return "None";
        }
        return "";
    }

}
