package nl.jellejurre.biomesampler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.jellejurre.biomesampler.minecraft.Biome;
import nl.jellejurre.biomesampler.minecraft.BiomeKey;
import nl.jellejurre.biomesampler.minecraft.BiomeRegistry;
import nl.jellejurre.biomesampler.minecraft.MultiNoiseUtil;
import nl.jellejurre.biomesampler.minecraft.VanillaBiomeParameters;
import nl.kallestruik.noisesampler.minecraft.util.Pair;

public class BiomeEntries {
    static public HashMap<Dimension, MultiNoiseUtil.Entries<Biome>> lists = new HashMap<>();
    static {
        ArrayList<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> list = new ArrayList<>();
        new VanillaBiomeParameters().writeVanillaBiomeParameters(pair  -> list.add(pair));
        lists.put(Dimension.OVERWORLD, new MultiNoiseUtil.Entries(list));
        lists.put(Dimension.NETHER, new MultiNoiseUtil.Entries(List.of(
            Pair.of(MultiNoiseUtil.createNoiseHypercube(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                BiomeRegistry.get(BiomeKey.NETHER_WASTES)),
            Pair.of(MultiNoiseUtil.createNoiseHypercube(0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                BiomeRegistry.get(BiomeKey.SOUL_SAND_VALLEY)),
            Pair.of(MultiNoiseUtil.createNoiseHypercube(0.4f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                BiomeRegistry.get(BiomeKey.CRIMSON_FOREST)),
            Pair.of(MultiNoiseUtil.createNoiseHypercube(0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.375f),
                BiomeRegistry.get(BiomeKey.WARPED_FOREST)),
            Pair.of(MultiNoiseUtil.createNoiseHypercube(-0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.175f),
                BiomeRegistry.get(BiomeKey.BASALT_DELTAS)))));
    }

}
