package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

public class Style {
    public static final Style.Type defaultType = Style.Type.CROP;
    public static final Style.Align defaultAlign = Style.Align.LEFT;
    public static final Style.LabelStyle defaultLabelStyle = Style.LabelStyle.WRAP;

    @SerializedName("bg")
    private Background background;
    private Padding padding;
    private Label label;
    private Font font;

    public Background getBackground() {
        return background;
    }

    public Padding getPadding() {
        return padding;
    }

    public Label getLabel() {
        return label;
    }

    public Font getFont() {
        return font;
    }

    public static class Background {
        @SerializedName("image")
        private String imageId;
        private int color;
        private Type type = defaultType;

        public String getImageId() {
            return imageId;
        }

        public int getColor() {
            return color;
        }

        public Type getType() {
            return type;
        }

    }

    public static class Padding {
        private int h;
        private int v;

        public int getHorizontalPadding() {
            return h;
        }

        public int getVerticalPadding() {
            return v;
        }
    }

    public static class Label {
        @SerializedName("bgImg")
        private String backgroundImageId;
        @SerializedName("bgColor")
        private int backgroundColor;
        private int color;
        private Align align = defaultAlign;
        private LabelStyle style = defaultLabelStyle;
        private int borderColor;
        private int borderSize;

        public String getBackgroundImageId() {
            return backgroundImageId;
        }

        public int getBackgroundColor() {
            return backgroundColor;
        }

        public int getColor() {
            return color;
        }

        public Align getAlign() {
            return align;
        }

        public LabelStyle getStyle() {
            return style;
        }

        public int getBorderColor() {
            return borderColor;
        }

        public int getBorderSize() {
            return borderSize;
        }
    }

    public static class Font {
        private String name;
        private int size;
        private int color;
        private Align align = defaultAlign;

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }

        public int getColor() {
            return color;
        }

        public Align getAlign() {
            return align;
        }
    }

    public enum Type {
        WIDE, CROP
    }

    public enum Align {
        LEFT, CENTER, RIGHT
    }

    public enum LabelStyle {
        WRAP, WIDTH, HEIGHT
    }
}