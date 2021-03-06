package nl.jellejurre.biomesampler;

import nl.jellejurre.biomesampler.minecraft.Biome;
import nl.jellejurre.biomesampler.minecraft.MathHelper;
import nl.kallestruik.noisesampler.NoiseSampler;
import nl.kallestruik.noisesampler.minecraft.Dimension;
import nl.kallestruik.noisesampler.minecraft.NoiseColumnSampler;
import nl.kallestruik.noisesampler.minecraft.util.NoiseValuePoint;

public class BiomeSampler {
    long seed;
    NoiseColumnSampler noiseColumnSampler;
    Dimension dimension;

    public BiomeSampler(long seed) {
        this(seed, Dimension.OVERWORLD);
    }

    public BiomeSampler(long seed, Dimension dimension) {
        this.seed = seed;
        this.dimension = dimension;
        this.noiseColumnSampler = new NoiseSampler(seed, dimension).getNoiseColumnSampler();
    }

    public Biome getBiomeFromBlockPos(int x, int y, int z){
        return getBiomeFromBiomePos(x>>2, y, z>>2);
    }

    public Biome getBiomeFromBiomePos(int x, int y, int z){
        switch (dimension){
            case OVERWORLD:
                return getBiomeAtPoint(noiseColumnSampler.sample(x, y, z), dimension);
            case NETHER:
                return getBiomeAtPoint(noiseColumnSampler.sample(x, y, z), dimension);
            case THEEND:
                return getEndBiome(x, y, z);
        }
        return null;
    }

    public Biome getBiomeAtPoint(NoiseValuePoint point, Dimension dimension) {
        return BiomeEntries.lists.get(dimension).method_39527(point);
    }

    private float getNoiseAt(int i, int j) {
        int k = i / 2;
        int l = j / 2;
        int m = i % 2;
        int n = j % 2;
        float f = 100.0f - MathHelper.sqrt(i * i + j * j) * 8.0f;
        f = MathHelper.clamp(f, -100.0f, 80.0f);
        for (int o = -12; o <= 12; ++o) {
            for (int p = -12; p <= 12; ++p) {
                long q = k + o;
                long r = l + p;
                if (q * q + r * r <= 4096L || !(noiseColumnSampler.islandNoise.sample(q, r) < (double)-0.9f)) continue;
                float g = (MathHelper.abs(q) * 3439.0f + MathHelper.abs(r) * 147.0f) % 13.0f + 9.0f;
                float h = m - o * 2;
                float s = n - p * 2;
                float t = 100.0f - MathHelper.sqrt(h * h + s * s) * g;
                t = MathHelper.clamp(t, -100.0f, 80.0f);
                f = Math.max(f, t);
            }
        }
        return f;
    }

    private Biome getEndBiome(int x, int y, int z) {
        int i = x >> 2;
        int j = z >> 2;
        if ((long)i * (long)i + (long)j * (long)j <= 4096L) {
            return Biome.THE_END;
        } else {
            float f = getNoiseAt(i * 2 + 1, j * 2 + 1);
            if (f > 40.0F) {
                return Biome.END_HIGHLANDS;
            } else if (f >= 0.0F) {
                return Biome.END_MIDLANDS;
            } else {
                return f < -20.0F ? Biome.SMALL_END_ISLANDS : Biome.END_BARRENS;
            }
        }
    }
}
