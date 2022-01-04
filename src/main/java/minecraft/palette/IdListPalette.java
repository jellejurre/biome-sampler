/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package minecraft.palette;

import java.util.List;
import java.util.function.Predicate;

public class IdListPalette<T>
    implements Palette<T> {
    private final IndexedIterable<T> idList;

    public IdListPalette(IndexedIterable<T> idList) {
        this.idList = idList;
    }

    public static <A> Palette<A> create(int bits, IndexedIterable<A> idList, PaletteResizeListener<A> listener, List<A> list) {
        return new IdListPalette<A>(idList);
    }

    @Override
    public int index(T object) {
        int i = this.idList.getRawId(object);
        return i == -1 ? 0 : i;
    }

    @Override
    public boolean hasAny(Predicate<T> predicate) {
        return true;
    }

    @Override
    public T get(int id) {
        T object = this.idList.get(id);
        return object;
    }

    @Override
    public int getSize() {
        return this.idList.size();
    }

    @Override
    public Palette<T> copy() {
        return this;
    }
}

