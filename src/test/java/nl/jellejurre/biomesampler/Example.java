package nl.jellejurre.biomesampler;

import static org.junit.jupiter.api.Assertions.assertEquals;


import nl.jellejurre.biomesampler.minecraft.Biome;
import nl.kallestruik.noisesampler.minecraft.Dimension;
import org.junit.jupiter.api.Test;

public class Example {
    @Test
    public void Example(){
        BiomeSampler overworldSampler = new BiomeSampler(1L, Dimension.OVERWORLD);
        Biome overworldBiome = overworldSampler.getBiomeFromBlockPos(1162, 66, -575);
        
        BiomeSampler netherSampler = new BiomeSampler(1L, Dimension.NETHER);
        Biome netherBiome = netherSampler.getBiomeFromBlockPos(1162, 66, -575);

        BiomeSampler endSampler = new BiomeSampler(1L, Dimension.THEEND);
        Biome endBiome = endSampler.getBiomeFromBlockPos(1162, 66, -575);

        assertEquals(Biome.DARK_FOREST, overworldBiome);
        assertEquals(Biome.NETHER_WASTES, netherBiome);
        assertEquals(Biome.END_BARRENS, endBiome);
    }
}
