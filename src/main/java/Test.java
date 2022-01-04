public class Test {
    public static void main(String[] args) {
        BiomeSampler sampler = new BiomeSampler(1L);
        System.out.println(sampler.getBiome(1162/4, 66, -575/4, Dimension.THEEND).getName());
        System.out.println(sampler.getBiome(1162/4, 66, -575/4, Dimension.NETHER).getName());
        System.out.println(sampler.getBiome(1162/4, 66, -575/4, Dimension.OVERWORLD).getName());
    }
}
