package nl.jellejurre.biomesampler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.jellejurre.biomesampler.minecraft.Biome;
import nl.jellejurre.biomesampler.minecraft.MultiNoiseUtil;
import nl.jellejurre.biomesampler.minecraft.VanillaBiomeParameters;
import nl.kallestruik.noisesampler.minecraft.Dimension;
import nl.kallestruik.noisesampler.minecraft.util.Pair;

public class BiomeEntries {
    static public HashMap<Dimension, MultiNoiseUtil.Entries<Biome>> lists = new HashMap<>();
    static {
        ArrayList<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> list = new ArrayList<>();
        new VanillaBiomeParameters().writeVanillaBiomeParameters(pair  -> list.add(pair));
        lists.put(Dimension.OVERWORLD, new MultiNoiseUtil.Entries(list));
        lists.put(Dimension.NETHER, new MultiNoiseUtil.Entries(List.of(
            Pair.of(MultiNoiseUtil.createNoiseHypercube(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                Biome.NETHER_WASTES),
            Pair.of(MultiNoiseUtil.createNoiseHypercube(0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                Biome.SOUL_SAND_VALLEY),
            Pair.of(MultiNoiseUtil.createNoiseHypercube(0.4f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                Biome.CRIMSON_FOREST),
            Pair.of(MultiNoiseUtil.createNoiseHypercube(0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.375f),
                Biome.WARPED_FOREST),
            Pair.of(MultiNoiseUtil.createNoiseHypercube(-0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.175f),
                Biome.BASALT_DELTAS))));
    }

}
