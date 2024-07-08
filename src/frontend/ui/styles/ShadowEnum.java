package frontend.ui.styles;

public enum ShadowEnum {
    NONE("Ninguna"),
    SIMPLE("Simple"),
    COLORED("Coloreada"),
    INVERSE_SIMPLE("Simple Inversa"),
    INVERSE_COLORED("Coloreada Inversa");

    private final String name;

    ShadowEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isSimple() {
        return this == SIMPLE || this == INVERSE_SIMPLE;
    }

    public boolean isInversed() {
        return this == INVERSE_SIMPLE || this == INVERSE_COLORED;
    }
}
