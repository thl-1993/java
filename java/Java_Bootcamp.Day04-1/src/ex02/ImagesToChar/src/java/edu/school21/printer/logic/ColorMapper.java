package edu.school21.printer.logic;

import com.diogonunes.jcolor.Attribute;

import java.util.HashMap;
import java.util.Map;

public class ColorMapper {
    private static final Map<String, Attribute> map = new HashMap<>();

    static {
        map.put("RED", Attribute.RED_BACK());
        map.put("GREEN", Attribute.GREEN_BACK());
        map.put("YELLOW", Attribute.YELLOW_BACK());
        map.put("BLUE", Attribute.BLUE_BACK());
        map.put("MAGENTA", Attribute.MAGENTA_BACK());
        map.put("CYAN", Attribute.CYAN_BACK());
        map.put("WHITE", Attribute.WHITE_BACK());
        map.put("BLACK", Attribute.BLACK_BACK());
    }

    public static Attribute getAttribute(String color) {
        if (map.containsKey(color)) {
            return map.get(color);
        }
        throw new IllegalArgumentException("Unknown color: " + color.toUpperCase());
    }
}
