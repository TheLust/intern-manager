package md.ceiti.internmanager.enums;

public enum Stage {
    INTERN,
    JUNIOR,
    INTERMEDIATE,
    SENIOR;

    public String getDisplayName() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
