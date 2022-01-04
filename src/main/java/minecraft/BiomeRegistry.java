package minecraft;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class BiomeRegistry {
    public static Map<BiomeKey, Biome> biomeMap = new HashMap<>();
    private static void register(BiomeKey key, Biome biome) {
        biomeMap.put(key, biome);
    }

    public static Biome get(BiomeKey biomeKey){
        return biomeMap.get(biomeKey);
    }

    static {
        BiomeRegistry.register(BiomeKey.SUNFLOWER_PLAINS, new Biome("sunFlowerPlains", Biome.Category.PLAINS));
        BiomeRegistry.register(BiomeKey.SNOWY_PLAINS, new Biome("snowyPlains", Biome.Category.ICY));
        BiomeRegistry.register(BiomeKey.ICE_SPIKES, new Biome("iceSpikes", Biome.Category.ICY));
        BiomeRegistry.register(BiomeKey.DESERT, new Biome("desert", Biome.Category.DESERT));
        BiomeRegistry.register(BiomeKey.SWAMP, new Biome("swamp", Biome.Category.SWAMP));
        BiomeRegistry.register(BiomeKey.FOREST, new Biome("forest", Biome.Category.FOREST));
        BiomeRegistry.register(BiomeKey.FLOWER_FOREST, new Biome("flowerForest", Biome.Category.FOREST));
        BiomeRegistry.register(BiomeKey.BIRCH_FOREST, new Biome("birchForest", Biome.Category.FOREST));
        BiomeRegistry.register(BiomeKey.DARK_FOREST, new Biome("darkForest", Biome.Category.FOREST));
        BiomeRegistry.register(BiomeKey.OLD_GROWTH_BIRCH_FOREST, new Biome("oldGrowthBirchForest", Biome.Category.FOREST));
        BiomeRegistry.register(BiomeKey.OLD_GROWTH_PINE_TAIGA, new Biome("oldGrowthPineTaiga", Biome.Category.TAIGA));
        BiomeRegistry.register(BiomeKey.OLD_GROWTH_SPRUCE_TAIGA, new Biome("oldGrowthSpruceTaiga", Biome.Category.TAIGA));
        BiomeRegistry.register(BiomeKey.TAIGA, new Biome("taiga", Biome.Category.TAIGA));
        BiomeRegistry.register(BiomeKey.SNOWY_TAIGA, new Biome("taiga", Biome.Category.TAIGA));
        BiomeRegistry.register(BiomeKey.SAVANNA, new Biome("savanna", Biome.Category.SAVANNA));
        BiomeRegistry.register(BiomeKey.SAVANNA_PLATEAU, new Biome("savannaPlateau", Biome.Category.SAVANNA));
        BiomeRegistry.register(BiomeKey.WINDSWEPT_HILLS, new Biome("windsweptHills", Biome.Category.EXTREME_HILLS));
        BiomeRegistry.register(BiomeKey.WINDSWEPT_GRAVELLY_HILLS, new Biome("windsweptGravellyHills", Biome.Category.EXTREME_HILLS));
        BiomeRegistry.register(BiomeKey.WINDSWEPT_FOREST, new Biome("windsweptForest", Biome.Category.EXTREME_HILLS));
        BiomeRegistry.register(BiomeKey.WINDSWEPT_SAVANNA, new Biome("windsweptSavanna", Biome.Category.SAVANNA));
        BiomeRegistry.register(BiomeKey.JUNGLE, new Biome("jungle", Biome.Category.JUNGLE));
        BiomeRegistry.register(BiomeKey.SPARSE_JUNGLE, new Biome("sparseJungle", Biome.Category.JUNGLE));
        BiomeRegistry.register(BiomeKey.BAMBOO_JUNGLE, new Biome("bambooJungle", Biome.Category.JUNGLE));
        BiomeRegistry.register(BiomeKey.BADLANDS, new Biome("mesa", Biome.Category.MESA));
        BiomeRegistry.register(BiomeKey.ERODED_BADLANDS, new Biome("erodedBadlands", Biome.Category.MESA));
        BiomeRegistry.register(BiomeKey.WOODED_BADLANDS, new Biome("woodedBadlands", Biome.Category.MESA));
        BiomeRegistry.register(BiomeKey.MEADOW, new Biome("meadow", Biome.Category.MOUNTAIN));
        BiomeRegistry.register(BiomeKey.GROVE, new Biome("grove", Biome.Category.FOREST));
        BiomeRegistry.register(BiomeKey.SNOWY_SLOPES, new Biome("snowySlopes", Biome.Category.MOUNTAIN));
        BiomeRegistry.register(BiomeKey.FROZEN_PEAKS, new Biome("frozenPeaks", Biome.Category.MOUNTAIN));
        BiomeRegistry.register(BiomeKey.JAGGED_PEAKS, new Biome("jaggedPeaks", Biome.Category.MOUNTAIN));
        BiomeRegistry.register(BiomeKey.STONY_PEAKS, new Biome("stonyPeaks", Biome.Category.MOUNTAIN));
        BiomeRegistry.register(BiomeKey.RIVER, new Biome("river", Biome.Category.RIVER));
        BiomeRegistry.register(BiomeKey.FROZEN_RIVER, new Biome("frozenRiver", Biome.Category.RIVER));
        BiomeRegistry.register(BiomeKey.BEACH, new Biome("beach", Biome.Category.BEACH));
        BiomeRegistry.register(BiomeKey.SNOWY_BEACH, new Biome("snowyBeach", Biome.Category.BEACH));
        BiomeRegistry.register(BiomeKey.STONY_SHORE, new Biome("stonyShore", Biome.Category.BEACH));
        BiomeRegistry.register(BiomeKey.WARM_OCEAN, new Biome("warmOcean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.LUKEWARM_OCEAN, new Biome("lukewarmOcean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.DEEP_LUKEWARM_OCEAN, new Biome("deepLukewarmOcean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.OCEAN, new Biome("ocean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.DEEP_OCEAN, new Biome("deepOcean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.COLD_OCEAN, new Biome("coldOcean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.DEEP_COLD_OCEAN, new Biome("deepColdOcean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.FROZEN_OCEAN, new Biome("frozenOcean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.DEEP_FROZEN_OCEAN, new Biome("deepFrozenOcean", Biome.Category.OCEAN));
        BiomeRegistry.register(BiomeKey.MUSHROOM_FIELDS, new Biome("mushroomFields", Biome.Category.MUSHROOM));
        BiomeRegistry.register(BiomeKey.DRIPSTONE_CAVES, new Biome("dripstoneCaves", Biome.Category.UNDERGROUND));
        BiomeRegistry.register(BiomeKey.LUSH_CAVES, new Biome("lushCaves", Biome.Category.UNDERGROUND));
        BiomeRegistry.register(BiomeKey.NETHER_WASTES, new Biome("netherWastes", Biome.Category.NETHER));
        BiomeRegistry.register(BiomeKey.WARPED_FOREST, new Biome("warpedForest", Biome.Category.NETHER));
        BiomeRegistry.register(BiomeKey.CRIMSON_FOREST, new Biome("crimsonForest", Biome.Category.NETHER));
        BiomeRegistry.register(BiomeKey.SOUL_SAND_VALLEY, new Biome("soulSandValley", Biome.Category.NETHER));
        BiomeRegistry.register(BiomeKey.BASALT_DELTAS, new Biome("basaltDeltas", Biome.Category.NETHER));
        BiomeRegistry.register(BiomeKey.THE_END, new Biome("theEnd", Biome.Category.THEEND));
        BiomeRegistry.register(BiomeKey.END_HIGHLANDS, new Biome("endHighlands", Biome.Category.THEEND));
        BiomeRegistry.register(BiomeKey.END_MIDLANDS, new Biome("endMidlands", Biome.Category.THEEND));
        BiomeRegistry.register(BiomeKey.SMALL_END_ISLANDS, new Biome("smallEndIslands", Biome.Category.THEEND));
        BiomeRegistry.register(BiomeKey.END_BARRENS, new Biome("endBarrens", Biome.Category.THEEND));
    }
}


