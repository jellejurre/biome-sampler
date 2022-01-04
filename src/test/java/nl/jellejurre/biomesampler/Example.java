package nl.jellejurre.biomesampler;

import static org.junit.jupiter.api.Assertions.assertEquals;


import nl.jellejurre.biomesampler.minecraft.Biome;
import nl.jellejurre.biomesampler.minecraft.BiomeKey;
import nl.jellejurre.biomesampler.minecraft.BiomeRegistry;
import org.junit.jupiter.api.Test;

public class Example {
    @Test
    public void Example(){
        BiomeSampler sampler = new BiomeSampler(1L);
        //To turn real world coords into biome coords, divide by 4
        Biome overworldBiome = sampler.getBiome(1162/4, 66, -575/4, Dimension.OVERWORLD);

        Biome netherBiome = sampler.getBiome(1162/4, 66, -575/4, Dimension.NETHER);

        Biome endBiome = sampler.getBiome(1162/4, 66, -575/4, Dimension.THEEND);
        assertEquals(BiomeRegistry.get(BiomeKey.DARK_FOREST), overworldBiome);
        assertEquals(BiomeRegistry.get(BiomeKey.NETHER_WASTES), netherBiome);
        assertEquals(BiomeRegistry.get(BiomeKey.END_BARRENS), endBiome);
    }
}
