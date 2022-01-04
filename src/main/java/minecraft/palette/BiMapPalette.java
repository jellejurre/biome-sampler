/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package minecraft.palette;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import minecraft.Int2ObjectBiMap;

public class BiMapPalette<T>
    implements Palette<T> {
    private final IndexedIterable<T> idList;
    private final Int2ObjectBiMap<T> map;
    private final PaletteResizeListener<T> listener;
    private final int indexBits;

    public BiMapPalette(IndexedIterable<T> idList, int bits, PaletteResizeListener<T> listener, List<T> entries) {
        this(idList, bits, listener);
        entries.forEach(this.map::add);
    }

    public BiMapPalette(IndexedIterable<T> idList, int indexBits, PaletteResizeListener<T> listener) {
        this(idList, indexBits, listener, Int2ObjectBiMap.create(1 << indexBits));
    }

    private BiMapPalette(IndexedIterable<T> arg, int i, PaletteResizeListener<T> arg2, Int2ObjectBiMap<T> arg3) {
        this.idList = arg;
        this.indexBits = i;
        this.listener = arg2;
        this.map = arg3;
    }

    public static <A> Palette<A> create(int bits, IndexedIterable<A> idList, PaletteResizeListener<A> listener, List<A> entries) {
        return new BiMapPalette<A>(idList, bits, listener, entries);
    }

    @Override
    public int index(T object) {
        int i = this.map.getRawId(object);
        if (i == -1 && (i = this.map.add(object)) >= 1 << this.indexBits) {
            i = this.listener.onResize(this.indexBits + 1, object);
        }
        return i;
    }

    @Override
    public boolean hasAny(Predicate<T> predicate) {
        for (int i = 0; i < this.getSize(); ++i) {
            if (!predicate.test(this.map.get(i))) continue;
            return true;
        }
        return false;
    }

    @Override
    public T get(int id) {
        T object = this.map.get(id);
        return object;
    }

    public List<T> getElements() {
        ArrayList arrayList = new ArrayList();
        this.map.iterator().forEachRemaining(arrayList::add);
        return arrayList;
    }

    @Override
    public int getSize() {
        return this.map.size();
    }

    @Override
    public Palette<T> copy() {
        return new BiMapPalette<T>(this.idList, this.indexBits, this.listener, this.map.copy());
    }
}

