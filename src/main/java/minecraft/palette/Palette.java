/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package minecraft.palette;
import java.util.List;
import java.util.function.Predicate;

public interface Palette<T> {
    public int index(T var1);

    public boolean hasAny(Predicate<T> var1);

    public T get(int var1);

    public int getSize();

    public Palette<T> copy();

    public static interface Factory {
        public <A> Palette<A> create(int var1, IndexedIterable<A> var2, PaletteResizeListener<A> var3, List<A> var4);
    }
}

