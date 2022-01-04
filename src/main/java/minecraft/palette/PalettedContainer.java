/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 *
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package minecraft.palette;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import minecraft.MathHelper;
import minecraft.PackedIntegerArray;

public class PalettedContainer<T>
    implements PaletteResizeListener<T> {
    private final PaletteResizeListener<T> dummyListener = (newSize, added) -> 0;
    private final IndexedIterable<T> idList;
    private volatile Data<T> data;
    private final PaletteProvider paletteProvider;

    public PalettedContainer(IndexedIterable<T> idList, PaletteProvider paletteProvider, DataProvider<T> dataProvider, PaletteStorage storage, List<T> paletteEntries) {
        this.idList = idList;
        this.paletteProvider = paletteProvider;
        this.data = new Data<T>(dataProvider, storage, dataProvider.factory().create(dataProvider.bits(), idList, this, paletteEntries));
    }

    private PalettedContainer(IndexedIterable<T> idList, PaletteProvider paletteProvider, Data<T> data) {
        this.idList = idList;
        this.paletteProvider = paletteProvider;
        this.data = data;
    }

    public PalettedContainer(IndexedIterable<T> idList, T object, PaletteProvider paletteProvider) {
        this.paletteProvider = paletteProvider;
        this.idList = idList;
        this.data = this.getCompatibleData(null, 0);
        this.data.palette.index(object);
    }

    private Data<T> getCompatibleData(Data<T> previousData, int bits) {
        DataProvider<T> lv = this.paletteProvider.createDataProvider(this.idList, bits);
        if (previousData != null && lv.equals(previousData.configuration())) {
            return previousData;
        }
        return lv.createData(this.idList, this, this.paletteProvider.getContainerSize());
    }

    @Override
    public int onResize(int i, T object) {
        Data<T> lv = this.data;
        Data lv2 = this.getCompatibleData(lv, i);
        lv2.importFrom(lv.palette, lv.storage);
        this.data = lv2;
        return lv2.palette.index(object);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public T swap(int x, int y, int z, T value) {
        this.lock();
        try {
            T t = this.swap(this.paletteProvider.computeIndex(x, y, z), value);
            return t;
        }
        finally {
            this.unlock();
        }
    }

    public T swapUnsafe(int x, int y, int z, T value) {
        return this.swap(this.paletteProvider.computeIndex(x, y, z), value);
    }

    private T swap(int index, T value) {
        int j = this.data.palette.index(value);
        int k = this.data.storage.swap(index, j);
        return this.data.palette.get(k);
    }

    public void lock(){

    }

    public void unlock(){

    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void set(int x, int y, int z, T value) {
        this.lock();
        try {
            this.set(this.paletteProvider.computeIndex(x, y, z), value);
        }
        finally {
            this.unlock();
        }
    }

    private void set(int index, T value) {
        int j = this.data.palette.index(value);
        this.data.storage.set(index, j);
    }

    public T get(int x, int y, int z) {
        return this.get(this.paletteProvider.computeIndex(x, y, z));
    }

    protected T get(int index) {
        Data<T> lv = this.data;
        return lv.palette.get(lv.storage.get(index));
    }

    public void method_39793(Consumer<T> consumer) {
        Palette<T> lv = this.data.palette();
        HashSet<Integer> intSet = new HashSet<>();
        this.data.storage.forEach(intSet::add);
        intSet.forEach(id -> consumer.accept(lv.get(id)));
    }

    private static <T> void method_39894(int[] is, IntUnaryOperator intUnaryOperator) {
        int i = -1;
        int j = -1;
        for (int k = 0; k < is.length; ++k) {
            int l = is[k];
            if (l != i) {
                i = l;
                j = intUnaryOperator.applyAsInt(l);
            }
            is[k] = j;
        }
    }

    public boolean hasAny(Predicate<T> predicate) {
        return this.data.palette.hasAny(predicate);
    }

    public PalettedContainer<T> copy() {
        return new PalettedContainer<T>(this.idList, this.paletteProvider, new Data<T>(this.data.configuration(), this.data.storage().copy(), this.data.palette().copy()));
    }

    public void count(Counter<T> counter) {
        if (this.data.palette.getSize() == 1) {
            counter.accept(this.data.palette.get(0), this.data.storage.getSize());
            return;
        }
        HashMap<Integer, Integer> int2IntOpenHashMap = new HashMap<>();
        this.data.storage.forEach(key -> int2IntOpenHashMap.put(key, 1));
        int2IntOpenHashMap.entrySet().forEach(entry -> counter.accept(this.data.palette.get(entry.getKey()), entry.getValue()));
    }

    public abstract static class PaletteProvider {
        public static final Palette.Factory SINGULAR = SingularPalette::create;
        public static final Palette.Factory ARRAY = ArrayPalette::create;
        public static final Palette.Factory BI_MAP = BiMapPalette::create;
        public static final Palette.Factory ID_LIST = IdListPalette::create;
        public static final PaletteProvider BLOCK_STATE = new PaletteProvider(4){

            @Override
            public <A> DataProvider<A> createDataProvider(IndexedIterable<A> idList, int bits) {
                return switch (bits) {
                    case 0 -> new DataProvider(SINGULAR, bits);
                    case 1, 2, 3, 4 -> new DataProvider(ARRAY, 4);
                    case 5, 6, 7, 8 -> new DataProvider(BI_MAP, bits);
                    default -> new DataProvider(ID_LIST, MathHelper.ceilLog2(idList.size()));
                };
            }
        };
        public static final PaletteProvider BIOME = new PaletteProvider(2){

            @Override
            public <A> DataProvider<A> createDataProvider(IndexedIterable<A> idList, int bits) {
                return switch (bits) {
                    case 0 -> new DataProvider(SINGULAR, bits);
                    case 1, 2, 3 -> new DataProvider(ARRAY, bits);
                    default -> new DataProvider(ID_LIST, MathHelper.ceilLog2(idList.size()));
                };
            }
        };
        private final int edgeBits;

        public PaletteProvider(int edgeBits) {
            this.edgeBits = edgeBits;
        }

        public int getContainerSize() {
            return 1 << this.edgeBits * 3;
        }

        public int computeIndex(int x, int y, int z) {
            return (y << this.edgeBits | z) << this.edgeBits | x;
        }

        public abstract <A> DataProvider<A> createDataProvider(IndexedIterable<A> var1, int var2);

        <A> int getBits(IndexedIterable<A> idList, int size) {
            int j = MathHelper.ceilLog2(size);
            DataProvider<A> lv = this.createDataProvider(idList, j);
            return lv.factory() == ID_LIST ? j : lv.bits();
        }
    }

    record Data<T>(DataProvider<T> configuration, PaletteStorage storage, Palette<T> palette) {
        public void importFrom(Palette<T> palette, PaletteStorage storage) {
            for (int i = 0; i < storage.getSize(); ++i) {
                T object = palette.get(storage.get(i));
                this.storage.set(i, this.palette.index(object));
            }
        }
    }

    public record DataProvider<T>(Palette.Factory factory, int bits) {
        public Data<T> createData(IndexedIterable<T> idList, PaletteResizeListener<T> listener, int size) {
            PaletteStorage lv = this.bits == 0 ? new EmptyPaletteStorage(size) : new PackedIntegerArray(this.bits, size);
            Palette<T> lv2 = this.factory.create(this.bits, idList, listener, List.of());
            return new Data<T>(this, lv, lv2);
        }
    }

    record Serialized<T>(List<T> paletteEntries, Optional<LongStream> storage) {
    }

    @FunctionalInterface
    public static interface Counter<T> {
        public void accept(T var1, int var2);
    }
}
