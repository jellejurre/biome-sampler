/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 *
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package nl.jellejurre.biomesampler.minecraft;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class Biome {
    private final String name;
    private final Category category;

    Biome(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public final String getName() {
        return name;
    }

    public final Category getCategory() {
        return this.category;
    }

    public static enum Category
    {
        NONE("none"),
        TAIGA("taiga"),
        EXTREME_HILLS("extreme_hills"),
        JUNGLE("jungle"),
        MESA("mesa"),
        PLAINS("plains"),
        SAVANNA("savanna"),
        ICY("icy"),
        THEEND("the_end"),
        BEACH("beach"),
        FOREST("forest"),
        OCEAN("ocean"),
        DESERT("desert"),
        RIVER("river"),
        SWAMP("swamp"),
        MUSHROOM("mushroom"),
        NETHER("nether"),
        UNDERGROUND("underground"),
        MOUNTAIN("mountain");

        private static final Map<String, Category> BY_NAME;
        private final String name;

        private Category(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static Category byName(String name) {
            return BY_NAME.get(name);
        }

        public String asString() {
            return this.name;
        }

        static {
            BY_NAME = Arrays.stream(Category.values()).collect(Collectors.toMap(Category::getName, category -> category));
        }
    }
}

