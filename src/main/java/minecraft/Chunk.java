/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 *
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package minecraft;


import java.util.Map;
import minecraft.palette.IndexedIterable;
import minecraft.palette.PalettedContainer;
import nl.kallestruik.noisesampler.NoiseSampler;

public class Chunk {
    protected final Vec3i pos;
    protected NoiseSampler chunkNoiseSampler;
    protected final ChunkSection[] sectionArray;

    public Chunk(Vec3i pos, PalettedContainer<Biome>  biome) {
        this.pos = pos;
        this.sectionArray = new ChunkSection[24];
        Chunk.fillSectionArray(this.sectionArray);
    }

    private static void fillSectionArray(ChunkSection[] sectionArray) {
        for (int i = 0; i < sectionArray.length; ++i) {
            if (sectionArray[i] != null) continue;
            sectionArray[i] = new ChunkSection(i - 4);
        }
    }

    public ChunkSection getHighestNonEmptySection() {
        ChunkSection[] lvs = this.getSectionArray();
        for (int i = lvs.length - 1; i >= 0; --i) {
            ChunkSection lv = lvs[i];
            if (lv.isEmpty()) continue;
            return lv;
        }
        return null;
    }

    public int getHighestNonEmptySectionYOffset() {
        ChunkSection lv = this.getHighestNonEmptySection();
        return lv == null ? this.getBottomY() : lv.getYOffset();
    }

    public ChunkSection[] getSectionArray() {
        return this.sectionArray;
    }

    public ChunkSection getSection(int yIndex) {
        return this.getSectionArray()[yIndex];
    }

    public Vec3i getPos() {
        return this.pos;
    }

    public boolean areSectionsEmptyBetween(int lowerHeight, int upperHeight) {
        if (lowerHeight < this.getBottomY()) {
            lowerHeight = this.getBottomY();
        }
        if (upperHeight >= this.getTopY()) {
            upperHeight = this.getTopY() - 1;
        }
        for (int k = lowerHeight; k <= upperHeight; k += 16) {
            if (this.getSection(this.getSectionIndex(k)).isEmpty()) continue;
            return false;
        }
        return true;
    }

    public int getBottomY() {
        return -64;
    }

    public int getTopY() {
        return  320;
    }

    public int getHeight() {
        return 384;
    }

    public int getSectionIndex(int y) {
        return (y >> 4) + 4;
    }


    public NoiseSampler getOrCreateChunkNoiseSampler(NoiseSampler noiseColumnSampler) {
        return this.chunkNoiseSampler;
    }

    public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
        int l = this.getBottomY()>>2;
        int m = l + this.getHeight()>>2 - 1;
        int n = MathHelper.clamp(biomeY, l, m);
        int o = this.getSectionIndex(n<<2);
        return this.sectionArray[o].getBiome(biomeX & 3, n & 3, biomeZ & 3);
    }

    public void populateBiomes(BiomeSupplier biomeSupplier, MultiNoiseSampler sampler) {
        Vec3i lv = this.getPos();
        int i = lv.getX()<<4>>2;
        int j = lv.getZ()<<4>>2;
        for (int k = -4; k < 20; ++k) {
            ChunkSection lv3 = this.getSection(k + 4);
            lv3.populateBiomes(biomeSupplier, sampler, i, j);
        }
    }
}

