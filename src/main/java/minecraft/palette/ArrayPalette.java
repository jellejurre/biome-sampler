/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package minecraft.palette;

import java.util.List;
import java.util.function.Predicate;

public class ArrayPalette<T>
    implements Palette<T> {
    private final IndexedIterable<T> idList;
    private final Object[] array;
    private final PaletteResizeListener<T> listener;
    private final int indexBits;
    private int size;

    private ArrayPalette(IndexedIterable<T> idList, int bits, PaletteResizeListener<T> listener, List<T> list) {
        this.idList = idList;
        this.array = new Object[1 << bits];
        this.indexBits = bits;
        this.listener = listener;
        for (int j = 0; j < list.size(); ++j) {
            this.array[j] = list.get(j);
        }
        this.size = list.size();
    }

    private ArrayPalette(IndexedIterable<T> arg, T[] objects, PaletteResizeListener<T> arg2, int i, int j) {
        this.idList = arg;
        this.array = objects;
        this.listener = arg2;
        this.indexBits = i;
        this.size = j;
    }

    public static <A> Palette<A> create(int bits, IndexedIterable<A> idList, PaletteResizeListener<A> listener, List<A> list) {
        return new ArrayPalette<A>(idList, bits, listener, list);
    }

    @Override
    public int index(T object) {
        int i;
        for (i = 0; i < this.size; ++i) {
            if (this.array[i] != object) continue;
            return i;
        }
        if ((i = this.size++) < this.array.length) {
            this.array[i] = object;
            return i;
        }
        return this.listener.onResize(this.indexBits + 1, object);
    }

    @Override
    public boolean hasAny(Predicate<T> predicate) {
        for (int i = 0; i < this.size; ++i) {
            if (!predicate.test((T)this.array[i])) continue;
            return true;
        }
        return false;
    }

    @Override
    public T get(int id) {
        if (id >= 0 && id < this.size) {
            return (T)this.array[id];
        }
        return null;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Palette<T> copy() {
        return new ArrayPalette<T>(this.idList, (T[])this.array.clone(), this.listener, this.indexBits, this.size);
    }
}

