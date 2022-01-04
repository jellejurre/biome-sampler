/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package minecraft.palette;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class EmptyPaletteStorage
    implements PaletteStorage {
    public static final long[] EMPTY_DATA = new long[0];
    private final int size;

    public EmptyPaletteStorage(int size) {
        this.size = size;
    }

    @Override
    public int swap(int index, int value) {
        return 0;
    }

    @Override
    public void set(int index, int value) {

    }

    @Override
    public int get(int index) {
        return 0;
    }

    @Override
    public long[] getData() {
        return EMPTY_DATA;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getElementBits() {
        return 0;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i = 0; i < this.size; ++i) {
            action.accept(0);
        }
    }

    @Override
    public void method_39892(int[] is) {
        Arrays.fill(is, 0, this.size, 0);
    }

    @Override
    public PaletteStorage copy() {
        return this;
    }
}

