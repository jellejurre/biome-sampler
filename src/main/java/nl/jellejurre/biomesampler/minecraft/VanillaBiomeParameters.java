/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package nl.jellejurre.biomesampler.minecraft;

import java.util.List;
import java.util.function.Consumer;
import nl.kallestruik.noisesampler.minecraft.VanillaTerrainParameters;
import nl.kallestruik.noisesampler.minecraft.util.Pair;

public final class VanillaBiomeParameters {
    private static final float field_34500 = 0.05f;
    private static final float field_35047 = 0.26666668f;
    public static final float field_35041 = 0.4f;
    private static final float field_35048 = 0.93333334f;
    private static final float field_34501 = 0.1f;
    public static final float field_34502 = 0.56666666f;
    private static final float field_34503 = 0.7666667f;
    public static final float field_35042 = -0.11f;
    public static final float field_35043 = 0.03f;
    public static final float field_35044 = 0.3f;
    public static final float field_35045 = -0.78f;
    public static final float field_35046 = -0.375f;
    private final MultiNoiseUtil.ParameterRange DEFAULT_PARAMETER = MultiNoiseUtil.ParameterRange.of(-1.0f, 1.0f);
    private final MultiNoiseUtil.ParameterRange[] TEMPERATURE_PARAMETERS = new MultiNoiseUtil.ParameterRange[]{MultiNoiseUtil.ParameterRange.of(-1.0f, -0.45f), MultiNoiseUtil.ParameterRange.of(-0.45f, -0.15f), MultiNoiseUtil.ParameterRange.of(-0.15f, 0.2f), MultiNoiseUtil.ParameterRange.of(0.2f, 0.55f), MultiNoiseUtil.ParameterRange.of(0.55f, 1.0f)};
    private final MultiNoiseUtil.ParameterRange[] HUMIDITY_PARAMETERS = new MultiNoiseUtil.ParameterRange[]{MultiNoiseUtil.ParameterRange.of(-1.0f, -0.35f), MultiNoiseUtil.ParameterRange.of(-0.35f, -0.1f), MultiNoiseUtil.ParameterRange.of(-0.1f, 0.1f), MultiNoiseUtil.ParameterRange.of(0.1f, 0.3f), MultiNoiseUtil.ParameterRange.of(0.3f, 1.0f)};
    private final MultiNoiseUtil.ParameterRange[] EROSION_PARAMETERS = new MultiNoiseUtil.ParameterRange[]{MultiNoiseUtil.ParameterRange.of(-1.0f, -0.78f), MultiNoiseUtil.ParameterRange.of(-0.78f, -0.375f), MultiNoiseUtil.ParameterRange.of(-0.375f, -0.2225f), MultiNoiseUtil.ParameterRange.of(-0.2225f, 0.05f), MultiNoiseUtil.ParameterRange.of(0.05f, 0.45f), MultiNoiseUtil.ParameterRange.of(0.45f, 0.55f), MultiNoiseUtil.ParameterRange.of(0.55f, 1.0f)};
    private final MultiNoiseUtil.ParameterRange FROZEN_TEMPERATURE = this.TEMPERATURE_PARAMETERS[0];
    private final MultiNoiseUtil.ParameterRange NON_FROZEN_TEMPERATURE_PARAMETERS = MultiNoiseUtil.ParameterRange.combine(this.TEMPERATURE_PARAMETERS[1], this.TEMPERATURE_PARAMETERS[4]);
    private final MultiNoiseUtil.ParameterRange MUSHROOM_FIELDS_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(-1.2f, -1.05f);
    private final MultiNoiseUtil.ParameterRange DEEP_OCEAN_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(-1.05f, -0.455f);
    private final MultiNoiseUtil.ParameterRange OCEAN_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(-0.455f, -0.19f);
    private final MultiNoiseUtil.ParameterRange SHORE_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(-0.19f, -0.11f);
    private final MultiNoiseUtil.ParameterRange RIVER_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(-0.11f, 0.55f);
    private final MultiNoiseUtil.ParameterRange NEAR_INLAND_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(-0.11f, 0.03f);
    private final MultiNoiseUtil.ParameterRange MID_INLAND_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(0.03f, 0.3f);
    private final MultiNoiseUtil.ParameterRange FAR_INLAND_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(0.3f, 1.0f);
    private final Biome[][] OCEAN_BIOMES = new Biome[][]{{Biome.DEEP_FROZEN_OCEAN, Biome.DEEP_COLD_OCEAN, Biome.DEEP_OCEAN, Biome.DEEP_LUKEWARM_OCEAN, Biome.WARM_OCEAN}, {Biome.FROZEN_OCEAN, Biome.COLD_OCEAN, Biome.OCEAN, Biome.LUKEWARM_OCEAN, Biome.WARM_OCEAN}};
    private final Biome[][] COMMON_BIOMES = new Biome[][]{{Biome.SNOWY_PLAINS, Biome.SNOWY_PLAINS, Biome.SNOWY_PLAINS, Biome.SNOWY_TAIGA, Biome.TAIGA}, {Biome.PLAINS, Biome.PLAINS, Biome.FOREST, Biome.TAIGA, Biome.OLD_GROWTH_SPRUCE_TAIGA}, {Biome.FLOWER_FOREST, Biome.PLAINS, Biome.FOREST, Biome.BIRCH_FOREST, Biome.DARK_FOREST}, {Biome.SAVANNA, Biome.SAVANNA, Biome.FOREST, Biome.JUNGLE, Biome.JUNGLE}, {Biome.DESERT, Biome.DESERT, Biome.DESERT, Biome.DESERT, Biome.DESERT}};
    private final Biome[][] UNCOMMON_BIOMES = new Biome[][]{{Biome.ICE_SPIKES, null, Biome.SNOWY_TAIGA, null, null}, {null, null, null, null, Biome.OLD_GROWTH_PINE_TAIGA}, {Biome.SUNFLOWER_PLAINS, null, null, Biome.OLD_GROWTH_BIRCH_FOREST, null}, {null, null, Biome.PLAINS, Biome.SPARSE_JUNGLE, Biome.BAMBOO_JUNGLE}, {null, null, null, null, null}};
    private final Biome[][] NEAR_MOUNTAIN_BIOMES = new Biome[][]{{Biome.SNOWY_PLAINS, Biome.SNOWY_PLAINS, Biome.SNOWY_PLAINS, Biome.SNOWY_TAIGA, Biome.SNOWY_TAIGA}, {Biome.MEADOW, Biome.MEADOW, Biome.FOREST, Biome.TAIGA, Biome.OLD_GROWTH_SPRUCE_TAIGA}, {Biome.MEADOW, Biome.MEADOW, Biome.MEADOW, Biome.MEADOW, Biome.DARK_FOREST}, {Biome.SAVANNA_PLATEAU, Biome.SAVANNA_PLATEAU, Biome.FOREST, Biome.FOREST, Biome.JUNGLE}, {Biome.BADLANDS, Biome.BADLANDS, Biome.BADLANDS, Biome.WOODED_BADLANDS, Biome.WOODED_BADLANDS}};
    private final Biome[][] SPECIAL_NEAR_MOUNTAIN_BIOMES = new Biome[][]{{Biome.ICE_SPIKES, null, null, null, null}, {null, null, Biome.MEADOW, Biome.MEADOW, Biome.OLD_GROWTH_PINE_TAIGA}, {null, null, Biome.FOREST, Biome.BIRCH_FOREST, null}, {null, null, null, null, null}, {Biome.ERODED_BADLANDS, Biome.ERODED_BADLANDS, null, null, null}};
    private final Biome[][] HILL_BIOMES = new Biome[][]{{Biome.WINDSWEPT_GRAVELLY_HILLS, Biome.WINDSWEPT_GRAVELLY_HILLS, Biome.WINDSWEPT_HILLS, Biome.WINDSWEPT_FOREST, Biome.WINDSWEPT_FOREST}, {Biome.WINDSWEPT_GRAVELLY_HILLS, Biome.WINDSWEPT_GRAVELLY_HILLS, Biome.WINDSWEPT_HILLS, Biome.WINDSWEPT_FOREST, Biome.WINDSWEPT_FOREST}, {Biome.WINDSWEPT_HILLS, Biome.WINDSWEPT_HILLS, Biome.WINDSWEPT_HILLS, Biome.WINDSWEPT_FOREST, Biome.WINDSWEPT_FOREST}, {null, null, null, null, null}, {null, null, null, null, null}};

    public List<MultiNoiseUtil.NoiseHypercube> getSpawnSuitabilityNoises() {
        MultiNoiseUtil.ParameterRange lv = MultiNoiseUtil.ParameterRange.of(0.0f);
        float f = 0.16f;
        return List.of(new MultiNoiseUtil.NoiseHypercube(this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.combine(this.RIVER_CONTINENTALNESS, this.DEFAULT_PARAMETER), this.DEFAULT_PARAMETER, lv, MultiNoiseUtil.ParameterRange.of(-1.0f, -0.16f), 0L), new MultiNoiseUtil.NoiseHypercube(this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.combine(this.RIVER_CONTINENTALNESS, this.DEFAULT_PARAMETER), this.DEFAULT_PARAMETER, lv, MultiNoiseUtil.ParameterRange.of(0.16f, 1.0f), 0L));
    }

    public void writeVanillaBiomeParameters(
        Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters) {
        this.writeOceanBiomes(parameters);
        this.writeLandBiomes(parameters);
        this.writeCaveBiomes(parameters);
    }

    private void writeOceanBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters) {
        this.writeBiomeParameters(parameters, this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, this.MUSHROOM_FIELDS_CONTINENTALNESS, this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, 0.0f, Biome.MUSHROOM_FIELDS);
        for (int i = 0; i < this.TEMPERATURE_PARAMETERS.length; ++i) {
            MultiNoiseUtil.ParameterRange lv = this.TEMPERATURE_PARAMETERS[i];
            this.writeBiomeParameters(parameters, lv, this.DEFAULT_PARAMETER, this.DEEP_OCEAN_CONTINENTALNESS, this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, 0.0f,  (this.OCEAN_BIOMES[0][i]));
            this.writeBiomeParameters(parameters, lv, this.DEFAULT_PARAMETER, this.OCEAN_CONTINENTALNESS, this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, 0.0f,  (this.OCEAN_BIOMES[1][i]));
        }
    }

    private void writeLandBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters) {
        this.writeMixedBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-1.0f, -0.93333334f));
        this.writePlainBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.93333334f, -0.7666667f));
        this.writeMountainousBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.7666667f, -0.56666666f));
        this.writePlainBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.56666666f, -0.4f));
        this.writeMixedBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.4f, -0.26666668f));
        this.writeBiomesNearRivers(parameters, MultiNoiseUtil.ParameterRange.of(-0.26666668f, -0.05f));
        this.writeRiverBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.05f, 0.05f));
        this.writeBiomesNearRivers(parameters, MultiNoiseUtil.ParameterRange.of(0.05f, 0.26666668f));
        this.writeMixedBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.26666668f, 0.4f));
        this.writePlainBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.4f, 0.56666666f));
        this.writeMountainousBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.56666666f, 0.7666667f));
        this.writePlainBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.7666667f, 0.93333334f));
        this.writeMixedBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.93333334f, 1.0f));
    }

    private void writeMountainousBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
        for (int i = 0; i < this.TEMPERATURE_PARAMETERS.length; ++i) {
            MultiNoiseUtil.ParameterRange lv = this.TEMPERATURE_PARAMETERS[i];
            for (int j = 0; j < this.HUMIDITY_PARAMETERS.length; ++j) {
                MultiNoiseUtil.ParameterRange lv2 = this.HUMIDITY_PARAMETERS[j];
                Biome lv3 = this.getRegularBiome(i, j, weirdness);
                Biome lv4 = this.getBadlandsOrRegularBiome(i, j, weirdness);
                Biome lv5 = this.getMountainStartBiome(i, j, weirdness);
                Biome lv6 = this.getNearMountainBiome(i, j, weirdness);
                Biome lv7 = this.getHillBiome(i, j, weirdness);
                Biome lv8 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, lv7);
                Biome lv9 = this.getPeakBiome(i, j, weirdness);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[0], weirdness, 0.0f, lv9);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[1], weirdness, 0.0f, lv5);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[1], weirdness, 0.0f, lv9);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[2], this.EROSION_PARAMETERS[3]), weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[2], weirdness, 0.0f, lv6);
                this.writeBiomeParameters(parameters, lv, lv2, this.MID_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[3], weirdness, 0.0f, lv4);
                this.writeBiomeParameters(parameters, lv, lv2, this.FAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[3], weirdness, 0.0f, lv6);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[4], weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv8);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv7);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[6], weirdness, 0.0f, lv3);
            }
        }
    }

    private void writePlainBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
        for (int i = 0; i < this.TEMPERATURE_PARAMETERS.length; ++i) {
            MultiNoiseUtil.ParameterRange lv = this.TEMPERATURE_PARAMETERS[i];
            for (int j = 0; j < this.HUMIDITY_PARAMETERS.length; ++j) {
                MultiNoiseUtil.ParameterRange lv2 = this.HUMIDITY_PARAMETERS[j];
                Biome lv3 = this.getRegularBiome(i, j, weirdness);
                Biome lv4 = this.getBadlandsOrRegularBiome(i, j, weirdness);
                Biome lv5 = this.getMountainStartBiome(i, j, weirdness);
                Biome lv6 = this.getNearMountainBiome(i, j, weirdness);
                Biome lv7 = this.getHillBiome(i, j, weirdness);
                Biome lv8 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, lv3);
                Biome lv9 = this.getMountainSlopeBiome(i, j, weirdness);
                Biome lv10 = this.getPeakBiome(i, j, weirdness);
                this.writeBiomeParameters(parameters, lv, lv2, this.SHORE_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[1]), weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, this.NEAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[0], weirdness, 0.0f, lv9);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[0], weirdness, 0.0f, lv10);
                this.writeBiomeParameters(parameters, lv, lv2, this.NEAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[1], weirdness, 0.0f, lv5);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[1], weirdness, 0.0f, lv9);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[2], this.EROSION_PARAMETERS[3]), weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[2], weirdness, 0.0f, lv6);
                this.writeBiomeParameters(parameters, lv, lv2, this.MID_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[3], weirdness, 0.0f, lv4);
                this.writeBiomeParameters(parameters, lv, lv2, this.FAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[3], weirdness, 0.0f, lv6);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[4], weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv8);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv7);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[6], weirdness, 0.0f, lv3);
            }
        }
    }

    private void writeMixedBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
        this.writeBiomeParameters(parameters, this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, this.SHORE_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[2]), weirdness, 0.0f, Biome.STONY_SHORE);
        this.writeBiomeParameters(parameters, this.NON_FROZEN_TEMPERATURE_PARAMETERS, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.combine(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[6], weirdness, 0.0f, Biome.SWAMP);
        for (int i = 0; i < this.TEMPERATURE_PARAMETERS.length; ++i) {
            MultiNoiseUtil.ParameterRange lv = this.TEMPERATURE_PARAMETERS[i];
            for (int j = 0; j < this.HUMIDITY_PARAMETERS.length; ++j) {
                MultiNoiseUtil.ParameterRange lv2 = this.HUMIDITY_PARAMETERS[j];
                Biome lv3 = this.getRegularBiome(i, j, weirdness);
                Biome lv4 = this.getBadlandsOrRegularBiome(i, j, weirdness);
                Biome lv5 = this.getMountainStartBiome(i, j, weirdness);
                Biome lv6 = this.getHillBiome(i, j, weirdness);
                Biome lv7 = this.getNearMountainBiome(i, j, weirdness);
                Biome lv8 = this.getShoreBiome(i, j);
                Biome lv9 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, lv3);
                Biome lv10 = this.getFlatShoreBiome(i, j, weirdness);
                Biome lv11 = this.getMountainSlopeBiome(i, j, weirdness);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[0], weirdness, 0.0f, lv11);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.NEAR_INLAND_CONTINENTALNESS, this.MID_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[1], weirdness, 0.0f, lv5);
                this.writeBiomeParameters(parameters, lv, lv2, this.FAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[1], weirdness, 0.0f, i == 0 ? lv11 : lv7);
                this.writeBiomeParameters(parameters, lv, lv2, this.NEAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[2], weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, this.MID_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[2], weirdness, 0.0f, lv4);
                this.writeBiomeParameters(parameters, lv, lv2, this.FAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[2], weirdness, 0.0f, lv7);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[3], weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[3], weirdness, 0.0f, lv4);
                if (weirdness.max() < 0L) {
                    this.writeBiomeParameters(parameters, lv, lv2, this.SHORE_CONTINENTALNESS, this.EROSION_PARAMETERS[4], weirdness, 0.0f, lv8);
                    this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[4], weirdness, 0.0f, lv3);
                } else {
                    this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[4], weirdness, 0.0f, lv3);
                }
                this.writeBiomeParameters(parameters, lv, lv2, this.SHORE_CONTINENTALNESS, this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv10);
                this.writeBiomeParameters(parameters, lv, lv2, this.NEAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv9);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv6);
                if (weirdness.max() < 0L) {
                    this.writeBiomeParameters(parameters, lv, lv2, this.SHORE_CONTINENTALNESS, this.EROSION_PARAMETERS[6], weirdness, 0.0f, lv8);
                } else {
                    this.writeBiomeParameters(parameters, lv, lv2, this.SHORE_CONTINENTALNESS, this.EROSION_PARAMETERS[6], weirdness, 0.0f, lv3);
                }
                if (i != 0) continue;
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[6], weirdness, 0.0f, lv3);
            }
        }
    }

    private void writeBiomesNearRivers(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
        this.writeBiomeParameters(parameters, this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, this.SHORE_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[2]), weirdness, 0.0f, Biome.STONY_SHORE);
        this.writeBiomeParameters(parameters, this.NON_FROZEN_TEMPERATURE_PARAMETERS, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.combine(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[6], weirdness, 0.0f, Biome.SWAMP);
        for (int i = 0; i < this.TEMPERATURE_PARAMETERS.length; ++i) {
            MultiNoiseUtil.ParameterRange lv = this.TEMPERATURE_PARAMETERS[i];
            for (int j = 0; j < this.HUMIDITY_PARAMETERS.length; ++j) {
                MultiNoiseUtil.ParameterRange lv2 = this.HUMIDITY_PARAMETERS[j];
                Biome lv3 = this.getRegularBiome(i, j, weirdness);
                Biome lv4 = this.getBadlandsOrRegularBiome(i, j, weirdness);
                Biome lv5 = this.getMountainStartBiome(i, j, weirdness);
                Biome lv6 = this.getShoreBiome(i, j);
                Biome lv7 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, lv3);
                Biome lv8 = this.getFlatShoreBiome(i, j, weirdness);
                this.writeBiomeParameters(parameters, lv, lv2, this.NEAR_INLAND_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[1]), weirdness, 0.0f, lv4);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[1]), weirdness, 0.0f, lv5);
                this.writeBiomeParameters(parameters, lv, lv2, this.NEAR_INLAND_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[2], this.EROSION_PARAMETERS[3]), weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[2], this.EROSION_PARAMETERS[3]), weirdness, 0.0f, lv4);
                this.writeBiomeParameters(parameters, lv, lv2, this.SHORE_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[3], this.EROSION_PARAMETERS[4]), weirdness, 0.0f, lv6);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[4], weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, this.SHORE_CONTINENTALNESS, this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv8);
                this.writeBiomeParameters(parameters, lv, lv2, this.NEAR_INLAND_CONTINENTALNESS, this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv7);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[5], weirdness, 0.0f, lv3);
                this.writeBiomeParameters(parameters, lv, lv2, this.SHORE_CONTINENTALNESS, this.EROSION_PARAMETERS[6], weirdness, 0.0f, lv6);
                if (i != 0) continue;
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[6], weirdness, 0.0f, lv3);
            }
        }
    }

    private void writeRiverBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
        this.writeBiomeParameters(parameters, this.FROZEN_TEMPERATURE, this.DEFAULT_PARAMETER, this.SHORE_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[1]), weirdness, 0.0f, weirdness.max() < 0L ? Biome.STONY_SHORE : Biome.FROZEN_RIVER);
        this.writeBiomeParameters(parameters, this.NON_FROZEN_TEMPERATURE_PARAMETERS, this.DEFAULT_PARAMETER, this.SHORE_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[1]), weirdness, 0.0f, weirdness.max() < 0L ? Biome.STONY_SHORE : Biome.RIVER);
        this.writeBiomeParameters(parameters, this.FROZEN_TEMPERATURE, this.DEFAULT_PARAMETER, this.NEAR_INLAND_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[1]), weirdness, 0.0f, Biome.FROZEN_RIVER);
        this.writeBiomeParameters(parameters, this.NON_FROZEN_TEMPERATURE_PARAMETERS, this.DEFAULT_PARAMETER, this.NEAR_INLAND_CONTINENTALNESS, MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[1]), weirdness, 0.0f, Biome.RIVER);
        this.writeBiomeParameters(parameters, this.FROZEN_TEMPERATURE, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[2], this.EROSION_PARAMETERS[5]), weirdness, 0.0f, Biome.FROZEN_RIVER);
        this.writeBiomeParameters(parameters, this.NON_FROZEN_TEMPERATURE_PARAMETERS, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.combine(this.SHORE_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[2], this.EROSION_PARAMETERS[5]), weirdness, 0.0f, Biome.RIVER);
        this.writeBiomeParameters(parameters, this.FROZEN_TEMPERATURE, this.DEFAULT_PARAMETER, this.SHORE_CONTINENTALNESS, this.EROSION_PARAMETERS[6], weirdness, 0.0f, Biome.FROZEN_RIVER);
        this.writeBiomeParameters(parameters, this.NON_FROZEN_TEMPERATURE_PARAMETERS, this.DEFAULT_PARAMETER, this.SHORE_CONTINENTALNESS, this.EROSION_PARAMETERS[6], weirdness, 0.0f, Biome.RIVER);
        this.writeBiomeParameters(parameters, this.NON_FROZEN_TEMPERATURE_PARAMETERS, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.combine(this.RIVER_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[6], weirdness, 0.0f, Biome.SWAMP);
        this.writeBiomeParameters(parameters, this.FROZEN_TEMPERATURE, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.combine(this.RIVER_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSION_PARAMETERS[6], weirdness, 0.0f, Biome.FROZEN_RIVER);
        for (int i = 0; i < this.TEMPERATURE_PARAMETERS.length; ++i) {
            MultiNoiseUtil.ParameterRange lv = this.TEMPERATURE_PARAMETERS[i];
            for (int j = 0; j < this.HUMIDITY_PARAMETERS.length; ++j) {
                MultiNoiseUtil.ParameterRange lv2 = this.HUMIDITY_PARAMETERS[j];
                Biome lv3 = this.getBadlandsOrRegularBiome(i, j, weirdness);
                this.writeBiomeParameters(parameters, lv, lv2, MultiNoiseUtil.ParameterRange.combine(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), MultiNoiseUtil.ParameterRange.combine(this.EROSION_PARAMETERS[0], this.EROSION_PARAMETERS[1]), weirdness, 0.0f, lv3);
            }
        }
    }

    private void writeCaveBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters) {
        this.writeCaveBiomeParameters(parameters, this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.of(0.8f, 1.0f), this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, 0.0f, Biome.DRIPSTONE_CAVES);
        this.writeCaveBiomeParameters(parameters, this.DEFAULT_PARAMETER, MultiNoiseUtil.ParameterRange.of(0.7f, 1.0f), this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, this.DEFAULT_PARAMETER, 0.0f, Biome.LUSH_CAVES);
    }

    private Biome getRegularBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        if (weirdness.max() < 0L) {
            return (this.COMMON_BIOMES[temperature][humidity]);
        }
        Biome lv = (this.UNCOMMON_BIOMES[temperature][humidity]);
        return lv == null ? (this.COMMON_BIOMES[temperature][humidity]) : lv;
    }

    private Biome getBadlandsOrRegularBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        return temperature == 4 ? this.getBadlandsBiome(humidity, weirdness) : this.getRegularBiome(temperature, humidity, weirdness);
    }

    private Biome getMountainStartBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        return temperature == 0 ? this.getMountainSlopeBiome(temperature, humidity, weirdness) : this.getBadlandsOrRegularBiome(temperature, humidity, weirdness);
    }

    private Biome getBiomeOrWindsweptSavanna(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, Biome biome) {
        if (temperature > 1 && humidity < 4 && weirdness.max() >= 0L) {
            return Biome.WINDSWEPT_SAVANNA;
        }
        return biome;
    }

    private Biome getFlatShoreBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        Biome lv = weirdness.max() >= 0L ? this.getRegularBiome(temperature, humidity, weirdness) : this.getShoreBiome(temperature, humidity);
        return this.getBiomeOrWindsweptSavanna(temperature, humidity, weirdness, lv);
    }

    private Biome getShoreBiome(int temperature, int humidity) {
        if (temperature == 0) {
            return Biome.SNOWY_BEACH;
        }
        if (temperature == 4) {
            return Biome.DESERT;
        }
        return Biome.BEACH;
    }

    private Biome getBadlandsBiome(int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        if (humidity < 2) {
            return weirdness.max() < 0L ? Biome.ERODED_BADLANDS : Biome.BADLANDS;
        }
        if (humidity < 3) {
            return Biome.BADLANDS;
        }
        return Biome.WOODED_BADLANDS;
    }

    private Biome getNearMountainBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        if (weirdness.max() < 0L) {
            return  (this.NEAR_MOUNTAIN_BIOMES[temperature][humidity]);
        }
        Biome lv =  (this.SPECIAL_NEAR_MOUNTAIN_BIOMES[temperature][humidity]);
        return lv == null ?  (this.NEAR_MOUNTAIN_BIOMES[temperature][humidity]) : lv;
    }

    private Biome getPeakBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        if (temperature <= 2) {
            return weirdness.max() < 0L ? Biome.JAGGED_PEAKS : Biome.FROZEN_PEAKS;
        }
        if (temperature == 3) {
            return Biome.STONY_PEAKS;
        }
        return this.getBadlandsBiome(humidity, weirdness);
    }

    private Biome getMountainSlopeBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        if (temperature >= 3) {
            return this.getNearMountainBiome(temperature, humidity, weirdness);
        }
        if (humidity <= 1) {
            return Biome.SNOWY_SLOPES;
        }
        return Biome.GROVE;
    }

    private Biome getHillBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        Biome lv =  (this.HILL_BIOMES[temperature][humidity]);
        return lv == null ? this.getRegularBiome(temperature, humidity, weirdness) : lv;
    }

    private void writeBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, Biome biome) {
        parameters.accept(
            Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(0.0f), weirdness, offset), biome));
        parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(1.0f), weirdness, offset), biome));
    }

    private void writeCaveBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, Biome>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, Biome biome) {
        parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(0.2f, 0.9f), weirdness, offset), biome));
    }

    public static String getWeirdnessDescription(double weirdness) {
        if (weirdness < (double) VanillaTerrainParameters.getNormalizedWeirdness(0.05f)) {
            return "Valley";
        }
        if (weirdness < (double)VanillaTerrainParameters.getNormalizedWeirdness(0.26666668f)) {
            return "Low";
        }
        if (weirdness < (double)VanillaTerrainParameters.getNormalizedWeirdness(0.4f)) {
            return "Mid";
        }
        if (weirdness < (double)VanillaTerrainParameters.getNormalizedWeirdness(0.56666666f)) {
            return "High";
        }
        return "Peak";
    }

    public String getContinentalnessDescription(double continentalness) {
        double e = MultiNoiseUtil.method_38665((float)continentalness);
        if (e < (double)this.MUSHROOM_FIELDS_CONTINENTALNESS.max()) {
            return "Mushroom fields";
        }
        if (e < (double)this.DEEP_OCEAN_CONTINENTALNESS.max()) {
            return "Deep ocean";
        }
        if (e < (double)this.OCEAN_CONTINENTALNESS.max()) {
            return "Ocean";
        }
        if (e < (double)this.SHORE_CONTINENTALNESS.max()) {
            return "Coast";
        }
        if (e < (double)this.NEAR_INLAND_CONTINENTALNESS.max()) {
            return "Near inland";
        }
        if (e < (double)this.MID_INLAND_CONTINENTALNESS.max()) {
            return "Mid inland";
        }
        return "Far inland";
    }

    public String getErosionDescription(double erosion) {
        return VanillaBiomeParameters.getNoiseRangeIndex(erosion, this.EROSION_PARAMETERS);
    }

    public String getTemperatureDescription(double temperature) {
        return VanillaBiomeParameters.getNoiseRangeIndex(temperature, this.TEMPERATURE_PARAMETERS);
    }

    public String getHumidityDescription(double humidity) {
        return VanillaBiomeParameters.getNoiseRangeIndex(humidity, this.HUMIDITY_PARAMETERS);
    }

    private static String getNoiseRangeIndex(double noisePoint, MultiNoiseUtil.ParameterRange[] noiseRanges) {
        double e = MultiNoiseUtil.method_38665((float)noisePoint);
        for (int i = 0; i < noiseRanges.length; ++i) {
            if (!(e < (double)noiseRanges[i].max())) continue;
            return "" + i;
        }
        return "?";
    }
}

