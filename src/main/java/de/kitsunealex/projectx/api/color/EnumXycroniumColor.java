package de.kitsunealex.projectx.api.color;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;

import java.util.Arrays;

public enum EnumXycroniumColor {

    BLUE    (0, "blue", new ColourRGBA(0, 100, 255, 255)),
    GREEN   (1, "green", new ColourRGBA(16711935)),
    RED     (2, "red", new ColourRGBA(-16776961)),
    DARK    (3, "dark", new ColourRGBA(30, 30, 30, 255)),
    LIGHT   (4, "light", new ColourRGBA(-1));

    public static final EnumXycroniumColor[] VALUES = {BLUE, GREEN, RED, DARK, LIGHT};
    private int index;
    private String name;
    private Colour color;

    EnumXycroniumColor(int index, String name, Colour color) {
        this.index = index;
        this.name = name;
        this.color = color;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public Colour getColor() {
        return color;
    }

    public static String[] toStringArray() {
        return Arrays.stream(VALUES).map(EnumXycroniumColor::getName).toArray(String[]::new);
    }

}
