//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package nl.jellejurre.biomesampler.minecraft;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import nl.kallestruik.noisesampler.minecraft.util.NoiseValuePoint;
import nl.kallestruik.noisesampler.minecraft.util.Pair;

public class MultiNoiseUtil {

    public MultiNoiseUtil() {
    }

    public static NoiseValuePoint createNoiseValuePoint(float temperatureNoise, float humidityNoise, float continentalnessNoise, float erosionNoise, float depth, float weirdnessNoise) {
        return new NoiseValuePoint(method_38665(temperatureNoise), method_38665(humidityNoise), method_38665(continentalnessNoise), method_38665(erosionNoise), method_38665(depth), method_38665(weirdnessNoise));
    }

    public static MultiNoiseUtil.NoiseHypercube createNoiseHypercube(float temperature, float humidity, float continentalness, float erosion, float depth, float weirdness, float offset) {
        return new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(temperature), MultiNoiseUtil.ParameterRange.of(humidity), MultiNoiseUtil.ParameterRange.of(continentalness), MultiNoiseUtil.ParameterRange.of(erosion), MultiNoiseUtil.ParameterRange.of(depth), MultiNoiseUtil.ParameterRange.of(weirdness), method_38665(offset));
    }

    public static MultiNoiseUtil.NoiseHypercube createNoiseHypercube(MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange depth, MultiNoiseUtil.ParameterRange weirdness, float offset) {
        return new MultiNoiseUtil.NoiseHypercube(temperature, humidity, continentalness, erosion, depth, weirdness, method_38665(offset));
    }

    public static long method_38665(float f) {
        return (long)(f * 10000.0F);
    }

    public static float method_38666(long l) {
        return (float)l / 10000.0F;
    }

    public static record NoiseHypercube(MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange depth, MultiNoiseUtil.ParameterRange weirdness, long offset) {
        long getSquaredDistance(NoiseValuePoint point) {
            return MathHelper.square(this.temperature.getDistance(point.temperatureNoise())) + MathHelper.square(this.humidity.getDistance(point.humidityNoise())) + MathHelper.square(this.continentalness.getDistance(point.continentalnessNoise())) + MathHelper.square(this.erosion.getDistance(point.erosionNoise())) + MathHelper.square(this.depth.getDistance(point.depth())) + MathHelper.square(this.weirdness.getDistance(point.weirdnessNoise())) + MathHelper.square(this.offset);
        }

        protected List<MultiNoiseUtil.ParameterRange> getParameters() {
            return List.of(this.temperature, this.humidity, this.continentalness, this.erosion, this.depth, this.weirdness, new MultiNoiseUtil.ParameterRange(this.offset, this.offset));
        }

        public MultiNoiseUtil.ParameterRange temperature() {
            return this.temperature;
        }

        public MultiNoiseUtil.ParameterRange humidity() {
            return this.humidity;
        }

        public MultiNoiseUtil.ParameterRange continentalness() {
            return this.continentalness;
        }

        public MultiNoiseUtil.ParameterRange erosion() {
            return this.erosion;
        }

        public MultiNoiseUtil.ParameterRange depth() {
            return this.depth;
        }

        public MultiNoiseUtil.ParameterRange weirdness() {
            return this.weirdness;
        }

        public long offset() {
            return this.offset;
        }
    }

    public static record ParameterRange(long min, long max) {
        public static MultiNoiseUtil.ParameterRange of(float point) {
            return of(point, point);
        }

        public static MultiNoiseUtil.ParameterRange of(float min, float max) {
            if (min > max) {
                throw new IllegalArgumentException("min > max: " + min + " " + max);
            } else {
                return new MultiNoiseUtil.ParameterRange(MultiNoiseUtil.method_38665(min), MultiNoiseUtil.method_38665(max));
            }
        }

        public static MultiNoiseUtil.ParameterRange combine(MultiNoiseUtil.ParameterRange min, MultiNoiseUtil.ParameterRange max) {
            if (min.min() > max.max()) {
                throw new IllegalArgumentException("min > max: " + min + " " + max);
            } else {
                return new MultiNoiseUtil.ParameterRange(min.min(), max.max());
            }
        }

        public String toString() {
            return this.min == this.max ? String.format("%d", this.min) : String.format("[%d-%d]", this.min, this.max);
        }

        public long getDistance(long noise) {
            long l = noise - this.max;
            long m = this.min - noise;
            return l > 0L ? l : Math.max(m, 0L);
        }

        public long getDistance(MultiNoiseUtil.ParameterRange other) {
            long l = other.min() - this.max;
            long m = this.min - other.max();
            return l > 0L ? l : Math.max(m, 0L);
        }

        public MultiNoiseUtil.ParameterRange combine(MultiNoiseUtil.ParameterRange other) {
            return other == null ? this : new MultiNoiseUtil.ParameterRange(Math.min(this.min, other.min()), Math.max(this.max, other.max()));
        }

        public long min() {
            return this.min;
        }

        public long max() {
            return this.max;
        }
    }


    public interface MultiNoiseSampler {
        NoiseValuePoint sample(int x, int y, int z);
    }

    public static class Entries<T> {
        private final List<Pair<NoiseHypercube, T>> entries;
        private final MultiNoiseUtil.SearchTree<T> tree;

        public Entries(List<Pair<MultiNoiseUtil.NoiseHypercube, T>> entries) {
            this.entries = entries;
            this.tree = MultiNoiseUtil.SearchTree.create(entries);
        }

        public List<Pair<MultiNoiseUtil.NoiseHypercube, T>> getEntries() {
            return this.entries;
        }

        public T method_39530(NoiseValuePoint noiseValuePoint, T object) {
            long l = 9223372036854775807L;
            T object2 = object;
            Iterator var6 = this.getEntries().iterator();

            while(var6.hasNext()) {
                Pair<MultiNoiseUtil.NoiseHypercube, T> pair = (Pair)var6.next();
                long m = ((MultiNoiseUtil.NoiseHypercube)pair.getLeft()).getSquaredDistance(noiseValuePoint);
                if (m < l) {
                    l = m;
                    object2 = pair.getRight();
                }
            }

            return object2;
        }

        public T method_39527(NoiseValuePoint noiseValuePoint) {
            return this.method_39528(noiseValuePoint, MultiNoiseUtil.SearchTree.TreeNode::getSquaredDistance);
        }

        protected T method_39528(NoiseValuePoint noiseValuePoint, MultiNoiseUtil.NodeDistanceFunction<T> nodeDistanceFunction) {
            return this.tree.get(noiseValuePoint, nodeDistanceFunction);
        }
    }

    protected static final class SearchTree<T> {
        private static final int MAX_NODES_FOR_SIMPLE_TREE = 10;
        private final MultiNoiseUtil.SearchTree.TreeNode<T> firstNode;
        private final ThreadLocal<MultiNoiseUtil.SearchTree.TreeLeafNode<T>> previousResultNode = new ThreadLocal();

        private SearchTree(MultiNoiseUtil.SearchTree.TreeNode<T> firstNode) {
            this.firstNode = firstNode;
        }

        public static <T> MultiNoiseUtil.SearchTree<T> create(List<Pair<MultiNoiseUtil.NoiseHypercube, T>> entries) {
            if (entries.isEmpty()) {
                throw new IllegalArgumentException("Need at least one value to build the search tree.");
            } else {
                int i = ((MultiNoiseUtil.NoiseHypercube)((Pair)entries.get(0)).getLeft()).getParameters().size();
                if (i != 7) {
                    throw new IllegalStateException("Expecting parameter space to be 7, got " + i);
                } else {
                    List<MultiNoiseUtil.SearchTree.TreeLeafNode<T>> list = (List)entries.stream().map((entry) -> {
                        return new MultiNoiseUtil.SearchTree.TreeLeafNode((MultiNoiseUtil.NoiseHypercube)entry.getLeft(), entry.getRight());
                    }).collect(Collectors.toCollection(ArrayList::new));
                    return new MultiNoiseUtil.SearchTree(createNode(i, list));
                }
            }
        }

        private static <T> MultiNoiseUtil.SearchTree.TreeNode<T> createNode(int parameterNumber, List<? extends MultiNoiseUtil.SearchTree.TreeNode<T>> subTree) {
            if (subTree.isEmpty()) {
                throw new IllegalStateException("Need at least one child to build a node");
            } else if (subTree.size() == 1) {
                return (MultiNoiseUtil.SearchTree.TreeNode)subTree.get(0);
            } else if (subTree.size() <= 10) {
                subTree.sort(Comparator.comparingLong((node) -> {
                    long l = 0L;

                    for(int j = 0; j < parameterNumber; ++j) {
                        MultiNoiseUtil.ParameterRange parameterRange = node.parameters[j];
                        l += Math.abs((parameterRange.min() + parameterRange.max()) / 2L);
                    }

                    return l;
                }));
                return new MultiNoiseUtil.SearchTree.TreeBranchNode(subTree);
            } else {
                long l = 9223372036854775807L;
                int i = -1;
                List<MultiNoiseUtil.SearchTree.TreeBranchNode<T>> list = null;

                for(int j = 0; j < parameterNumber; ++j) {
                    sortTree(subTree, parameterNumber, j, false);
                    List<MultiNoiseUtil.SearchTree.TreeBranchNode<T>> list2 = getBatchedTree(subTree);
                    long m = 0L;

                    MultiNoiseUtil.SearchTree.TreeBranchNode treeBranchNode;
                    for(Iterator var10 = list2.iterator(); var10.hasNext(); m += getRangeLengthSum(treeBranchNode.parameters)) {
                        treeBranchNode = (MultiNoiseUtil.SearchTree.TreeBranchNode)var10.next();
                    }

                    if (l > m) {
                        l = m;
                        i = j;
                        list = list2;
                    }
                }

                sortTree(list, parameterNumber, i, true);
                return new MultiNoiseUtil.SearchTree.TreeBranchNode((List)list.stream().map((node) -> {
                    return createNode(parameterNumber, Arrays.asList(node.subTree));
                }).collect(Collectors.toList()));
            }
        }

        private static <T> void sortTree(List<? extends MultiNoiseUtil.SearchTree.TreeNode<T>> subTree, int parameterNumber, int currentParameter, boolean abs) {
            Comparator<MultiNoiseUtil.SearchTree.TreeNode<T>> comparator = createNodeComparator(currentParameter, abs);

            for(int i = 1; i < parameterNumber; ++i) {
                comparator = comparator.thenComparing(createNodeComparator((currentParameter + i) % parameterNumber, abs));
            }

            subTree.sort(comparator);
        }

        private static <T> Comparator<MultiNoiseUtil.SearchTree.TreeNode<T>> createNodeComparator(int currentParameter, boolean abs) {
            return Comparator.comparingLong((treeNode) -> {
                MultiNoiseUtil.ParameterRange parameterRange = treeNode.parameters[currentParameter];
                long l = (parameterRange.min() + parameterRange.max()) / 2L;
                return abs ? Math.abs(l) : l;
            });
        }

        private static <T> List<MultiNoiseUtil.SearchTree.TreeBranchNode<T>> getBatchedTree(List<? extends MultiNoiseUtil.SearchTree.TreeNode<T>> nodes) {
            List<MultiNoiseUtil.SearchTree.TreeBranchNode<T>> list = new ArrayList<>();
            List<MultiNoiseUtil.SearchTree.TreeNode<T>> list2 = new ArrayList<>();
            int i = (int)Math.pow(10.0D, Math.floor(Math.log((double)nodes.size() - 0.01D) / Math.log(10.0D)));
            Iterator var4 = nodes.iterator();

            while(var4.hasNext()) {
                MultiNoiseUtil.SearchTree.TreeNode<T> treeNode = (MultiNoiseUtil.SearchTree.TreeNode)var4.next();
                list2.add(treeNode);
                if (list2.size() >= i) {
                    list.add(new MultiNoiseUtil.SearchTree.TreeBranchNode(list2));
                    list2 = new ArrayList<>();
                }
            }

            if (!list2.isEmpty()) {
                list.add(new MultiNoiseUtil.SearchTree.TreeBranchNode(list2));
            }

            return list;
        }

        private static long getRangeLengthSum(MultiNoiseUtil.ParameterRange[] parameters) {
            long l = 0L;
            MultiNoiseUtil.ParameterRange[] var3 = parameters;
            int var4 = parameters.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                MultiNoiseUtil.ParameterRange parameterRange = var3[var5];
                l += Math.abs(parameterRange.max() - parameterRange.min());
            }

            return l;
        }

        static <T> List<MultiNoiseUtil.ParameterRange> getEnclosingParameters(List<? extends MultiNoiseUtil.SearchTree.TreeNode<T>> subTree) {
            if (subTree.isEmpty()) {
                throw new IllegalArgumentException("SubTree needs at least one child");
            } else {
                int i = 7;
                List<MultiNoiseUtil.ParameterRange> list = new ArrayList<>();

                for(int j = 0; j < 7; ++j) {
                    list.add((ParameterRange) null);
                }

                Iterator var6 = subTree.iterator();

                while(var6.hasNext()) {
                    MultiNoiseUtil.SearchTree.TreeNode<T> treeNode = (MultiNoiseUtil.SearchTree.TreeNode)var6.next();

                    for(int k = 0; k < 7; ++k) {
                        list.set(k, treeNode.parameters[k].combine((MultiNoiseUtil.ParameterRange)list.get(k)));
                    }
                }

                return list;
            }
        }

        public T get(NoiseValuePoint point, MultiNoiseUtil.NodeDistanceFunction<T> distanceFunction) {
            long[] ls = new long[]{point.temperatureNoise(), point.humidityNoise(), point.continentalnessNoise(), point.erosionNoise(), point.depth(), point.weirdnessNoise(), 0L};
            MultiNoiseUtil.SearchTree.TreeLeafNode<T> treeLeafNode = this.firstNode.getResultingNode(ls, (MultiNoiseUtil.SearchTree.TreeLeafNode)this.previousResultNode.get(), distanceFunction);
            this.previousResultNode.set(treeLeafNode);
            return treeLeafNode.value;
        }

        abstract static class TreeNode<T> {
            protected final MultiNoiseUtil.ParameterRange[] parameters;

            protected TreeNode(List<MultiNoiseUtil.ParameterRange> parameters) {
                this.parameters = (MultiNoiseUtil.ParameterRange[])parameters.toArray(new MultiNoiseUtil.ParameterRange[0]);
            }

            protected abstract MultiNoiseUtil.SearchTree.TreeLeafNode<T> getResultingNode(long[] otherParameters,  MultiNoiseUtil.SearchTree.TreeLeafNode<T> alternative, MultiNoiseUtil.NodeDistanceFunction<T> distanceFunction);

            protected long getSquaredDistance(long[] otherParameters) {
                long l = 0L;

                for(int i = 0; i < 7; ++i) {
                    l += MathHelper.square(this.parameters[i].getDistance(otherParameters[i]));
                }

                return l;
            }

            public String toString() {
                return Arrays.toString(this.parameters);
            }
        }

        private static final class TreeBranchNode<T> extends MultiNoiseUtil.SearchTree.TreeNode<T> {
            final MultiNoiseUtil.SearchTree.TreeNode<T>[] subTree;

            protected TreeBranchNode(List<? extends MultiNoiseUtil.SearchTree.TreeNode<T>> list) {
                this(MultiNoiseUtil.SearchTree.getEnclosingParameters(list), list);
            }

            protected TreeBranchNode(List<MultiNoiseUtil.ParameterRange> parameters, List<? extends MultiNoiseUtil.SearchTree.TreeNode<T>> subTree) {
                super(parameters);
                this.subTree = (MultiNoiseUtil.SearchTree.TreeNode[])subTree.toArray(new MultiNoiseUtil.SearchTree.TreeNode[0]);
            }

            protected MultiNoiseUtil.SearchTree.TreeLeafNode<T> getResultingNode(long[] otherParameters, MultiNoiseUtil.SearchTree.TreeLeafNode<T> alternative, MultiNoiseUtil.NodeDistanceFunction<T> distanceFunction) {
                long l = alternative == null ? 9223372036854775807L : distanceFunction.getDistance(alternative, otherParameters);
                MultiNoiseUtil.SearchTree.TreeLeafNode<T> treeLeafNode = alternative;
                MultiNoiseUtil.SearchTree.TreeNode[] var7 = this.subTree;
                int var8 = var7.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    MultiNoiseUtil.SearchTree.TreeNode<T> treeNode = var7[var9];
                    long m = distanceFunction.getDistance(treeNode, otherParameters);
                    if (l > m) {
                        MultiNoiseUtil.SearchTree.TreeLeafNode<T> treeLeafNode2 = treeNode.getResultingNode(otherParameters, treeLeafNode, distanceFunction);
                        long n = treeNode == treeLeafNode2 ? m : distanceFunction.getDistance(treeLeafNode2, otherParameters);
                        if (l > n) {
                            l = n;
                            treeLeafNode = treeLeafNode2;
                        }
                    }
                }

                return treeLeafNode;
            }
        }

        private static final class TreeLeafNode<T> extends MultiNoiseUtil.SearchTree.TreeNode<T> {
            final T value;

            TreeLeafNode(MultiNoiseUtil.NoiseHypercube parameters, T value) {
                super(parameters.getParameters());
                this.value = value;
            }

            protected MultiNoiseUtil.SearchTree.TreeLeafNode<T> getResultingNode(long[] otherParameters,  MultiNoiseUtil.SearchTree.TreeLeafNode<T> alternative, MultiNoiseUtil.NodeDistanceFunction<T> distanceFunction) {
                return this;
            }
        }
    }

    interface NodeDistanceFunction<T> {
        long getDistance(MultiNoiseUtil.SearchTree.TreeNode<T> node, long[] ls);
    }
}
