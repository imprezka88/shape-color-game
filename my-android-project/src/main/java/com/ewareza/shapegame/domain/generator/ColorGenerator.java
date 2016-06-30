package com.ewareza.shapegame.domain.generator;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorGenerator {
    private final static List<Integer> colors = new ArrayList<>();

    static {
        initColors();
    }

    public static int generateColor() {
        Random random = new Random();
        return colors.get(random.nextInt(colors.size()));
    }

    static void initColors() {
        colors.add(Color.BLUE);
//        colors.add(Color.WHITE);
        colors.add(Color.GREEN);
//        colors.add(Color.MAGENTA);
//        colors.add(Color.YELLOW);
        colors.add(Color.RED);
//        colors.add(Color.GRAY);
    }

    public enum ColorWithIndex {
        BLUE("blue", Color.BLUE),
        RED("red", Color.RED),
        GREEN("green", Color.GREEN),
        MAGENTA("magenta", Color.MAGENTA);

        private final String colorName;
        private final int colorIndex;

        ColorWithIndex(String colorName, int colorIndex) {
            this.colorName = colorName;
            this.colorIndex = colorIndex;
        }

        public static String asString(int colorIndex) {
            for (ColorWithIndex colorWithIndex : ColorWithIndex.values()) {
                if (colorWithIndex.getColorIndex() == colorIndex)
                    return colorWithIndex.getColorName();
            }

            throw new IllegalArgumentException(String.format("Shape color with index: %s not supported", colorIndex));
        }

        public String getColorName() {
            return colorName;
        }

        public int getColorIndex() {
            return colorIndex;
        }
    }
}
