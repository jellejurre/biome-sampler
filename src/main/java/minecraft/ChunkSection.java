/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package minecraft;


import java.util.Map;
import minecraft.palette.IndexedIterable;
import minecraft.palette.PalettedContainer;

public class ChunkSection {
    private final int yOffset;
    private short nonEmptyBlockCount;
    private short randomTickableBlockCount;
    private short nonEmptyFluidCount;
    private final PalettedContainer<Biome> biomeContainer;
//    private static final PalettedContainer.PaletteProvider BIOME = new PalettedContainer.PaletteProvider(2){
//
//        @Override
//        public <A> PalettedContainer.DataProvider<A> createDataProvider(IndexedIterable<A> idList, int bits) {
//            return switch (bits) {
//                case 0 -> new PalettedContainer.DataProvider(SINGULAR, bits);
//                case 1, 2, 3 -> new PalettedContainer.DataProvider(ARRAY, bits);
//                default -> new PalettedContainer.DataProvider(ID_LIST, MathHelper.ceilLog2(idList.size()));
//            };
//        }
//    };

    public ChunkSection(int chunkPos) {
        this.yOffset = ChunkSection.blockCoordFromChunkCoord(chunkPos);
        this.biomeContainer = new PalettedContainer<Biome>(null, BiomeRegistry.biomeMap.get(BiomeKey.PLAINS), PalettedContainer.PaletteProvider.BIOME);
        this.calculateCounts();
    }

    public static int blockCoordFromChunkCoord(int chunkPos) {
        return chunkPos << 4;
    }

    public boolean isEmpty() {
        return this.nonEmptyBlockCount == 0;
    }

    public boolean hasRandomTicks() {
        return this.hasRandomBlockTicks() || this.hasRandomFluidTicks();
    }

    public boolean hasRandomBlockTicks() {
        return this.randomTickableBlockCount > 0;
    }

    public boolean hasRandomFluidTicks() {
        return this.nonEmptyFluidCount > 0;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    public void calculateCounts() {
        this.nonEmptyBlockCount = 0;
        this.randomTickableBlockCount = 0;
        this.nonEmptyFluidCount = 0;
    }

    public PalettedContainer<Biome> getBiomeContainer() {
        return this.biomeContainer;
    }

    public Biome getBiome(int x, int y, int z) {
        return this.biomeContainer.get(x, y, z);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void populateBiomes(BiomeSupplier biomeSupplier, MultiNoiseSampler sampler, int x, int z) {
        PalettedContainer<Biome> lv = this.getBiomeContainer();
        int k = this.getYOffset()>>2;
        int l = 4;
        for (int m = 0; m < 4; ++m) {
            for (int n = 0; n < 4; ++n) {
                for (int o = 0; o < 4; ++o) {
                    lv.swapUnsafe(m, n, o, biomeSupplier.getBiome(x + m, k + n, z + o, sampler));
                }
            }
        }
    }
}

