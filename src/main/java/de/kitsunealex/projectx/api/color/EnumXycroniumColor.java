package de.kitsunealex.projectx.api.color;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;

import java.util.Arrays;

//TODO: add colors
public enum EnumXycroniumColor {

    BLUE    ("blue", new ColourRGBA(0, 100, 255, 255)),
    GREEN   ("green", new ColourRGBA(16711935)),
    RED     ("red", new ColourRGBA(-16776961)),
    DARK    ("dark", new ColourRGBA(30, 30, 30, 255)),
    LIGHT   ("light", new ColourRGBA(-1));

    private String name;
    private Colour color;

    EnumXycroniumColor(String name, Colour color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Colour getColor() {
        return color;
    }

    public int getColorRGBA() {
        return color.rgba();
    }

    public int getColorARGB() {
        return color.argb();
    }

    @Override
    public String toString() {
        return getName();
    }

    public static String[] toStringArray() {
        return Arrays.stream(values()).map(EnumXycroniumColor::getName).toArray(String[]::new);
    }

}
