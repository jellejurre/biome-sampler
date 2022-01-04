package minecraft.palette;

public interface IndexedIterable<T>
    extends Iterable<T> {
    public static final int ABSENT_RAW_ID = -1;

    public int getRawId(T var1);

    public T get(int var1);

    default public T getOrThrow(int index) {
        T object = this.get(index);
        if (object == null) {
            throw new IllegalArgumentException("No value with id " + index);
        }
        return object;
    }

    public int size();
}

