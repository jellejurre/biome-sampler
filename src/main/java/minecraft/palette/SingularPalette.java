/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 *
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package minecraft.palette;

import java.util.List;
import java.util.function.Predicate;

public class SingularPalette<T>
    implements Palette<T> {
    private final IndexedIterable<T> idList;
    private T entry;
    private final PaletteResizeListener<T> listener;

    public SingularPalette(IndexedIterable<T> idList, PaletteResizeListener<T> listener, List<T> entries) {
        this.idList = idList;
        this.listener = listener;
        if (entries.size() > 0) {
            this.entry = entries.get(0);
        }
    }

    public static <A> Palette<A> create(int bitSize, IndexedIterable<A> idList, PaletteResizeListener<A> listener, List<A> entries) {
        return new SingularPalette<A>(idList, listener, entries);
    }

    @Override
    public int index(T object) {
        if (this.entry == null || this.entry == object) {
            this.entry = object;
            return 0;
        }
        return this.listener.onResize(1, object);
    }

    @Override
    public boolean hasAny(Predicate<T> predicate) {
        if (this.entry == null) {
            throw new IllegalStateException("Use of an uninitialized palette");
        }
        return predicate.test(this.entry);
    }

    @Override
    public T get(int id) {
        if (this.entry == null || id != 0) {
            throw new IllegalStateException("Missing Palette entry for id " + id + ".");
        }
        return this.entry;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public Palette<T> copy() {
        if (this.entry == null) {
            throw new IllegalStateException("Use of an uninitialized palette");
        }
        return this;
    }
}

