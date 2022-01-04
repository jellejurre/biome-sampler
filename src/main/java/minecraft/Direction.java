/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 *
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package minecraft;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nl.kallestruik.noisesampler.minecraft.util.Util;

public enum Direction implements StringIdentifiable
{
    DOWN(0, 1, -1, "down", AxisDirection.NEGATIVE, Axis.Y, new Vec3i(0, -1, 0)),
    UP(1, 0, -1, "up", AxisDirection.POSITIVE, Axis.Y, new Vec3i(0, 1, 0)),
    NORTH(2, 3, 2, "north", AxisDirection.NEGATIVE, Axis.Z, new Vec3i(0, 0, -1)),
    SOUTH(3, 2, 0, "south", AxisDirection.POSITIVE, Axis.Z, new Vec3i(0, 0, 1)),
    WEST(4, 5, 1, "west", AxisDirection.NEGATIVE, Axis.X, new Vec3i(-1, 0, 0)),
    EAST(5, 4, 3, "east", AxisDirection.POSITIVE, Axis.X, new Vec3i(1, 0, 0));

    private final int id;
    private final int idOpposite;
    private final int idHorizontal;
    private final String name;
    private final Axis axis;
    private final AxisDirection direction;
    private final Vec3i vector;
    private static final Direction[] ALL;
    private static final Map<String, Direction> NAME_MAP;
    private static final Direction[] VALUES;
    private static final Direction[] HORIZONTAL;

    private Direction(int id, int idOpposite, int idHorizontal, String name, AxisDirection direction, Axis axis, Vec3i vector) {
        this.id = id;
        this.idHorizontal = idHorizontal;
        this.idOpposite = idOpposite;
        this.name = name;
        this.axis = axis;
        this.direction = direction;
        this.vector = vector;
    }

    private static Direction[] listClosest(Direction first, Direction second, Direction third) {
        return new Direction[]{first, second, third, third.getOpposite(), second.getOpposite(), first.getOpposite()};
    }

    public int getId() {
        return this.id;
    }

    public int getHorizontal() {
        return this.idHorizontal;
    }

    public AxisDirection getDirection() {
        return this.direction;
    }


    public Direction getOpposite() {
        return Direction.byId(this.idOpposite);
    }

    public Direction rotateClockwise(Axis axis) {
        return switch (axis) {
            default -> throw new IncompatibleClassChangeError();
            case X -> {
                if (this == WEST || this == EAST) {
                    yield this;
                }
                yield this.rotateXClockwise();
            }
            case Y -> {
                if (this == UP || this == DOWN) {
                    yield this;
                }
                yield this.rotateYClockwise();
            }
            case Z -> this == NORTH || this == SOUTH ? this : this.rotateZClockwise();
        };
    }

    public Direction rotateCounterclockwise(Axis axis) {
        return switch (axis) {
            default -> throw new IncompatibleClassChangeError();
            case X -> {
                if (this == WEST || this == EAST) {
                    yield this;
                }
                yield this.rotateXCounterclockwise();
            }
            case Y -> {
                if (this == UP || this == DOWN) {
                    yield this;
                }
                yield this.rotateYCounterclockwise();
            }
            case Z -> this == NORTH || this == SOUTH ? this : this.rotateZCounterclockwise();
        };
    }

    public Direction rotateYClockwise() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
        };
    }

    private Direction rotateXClockwise() {
        return switch (this) {
            case UP -> NORTH;
            case NORTH -> DOWN;
            case DOWN -> SOUTH;
            case SOUTH -> UP;
            default -> throw new IllegalStateException("Unable to get X-rotated facing of " + this);
        };
    }

    private Direction rotateXCounterclockwise() {
        return switch (this) {
            case UP -> SOUTH;
            case SOUTH -> DOWN;
            case DOWN -> NORTH;
            case NORTH -> UP;
            default -> throw new IllegalStateException("Unable to get X-rotated facing of " + this);
        };
    }

    private Direction rotateZClockwise() {
        return switch (this) {
            case UP -> EAST;
            case EAST -> DOWN;
            case DOWN -> WEST;
            case WEST -> UP;
            default -> throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
        };
    }

    private Direction rotateZCounterclockwise() {
        return switch (this) {
            case UP -> WEST;
            case WEST -> DOWN;
            case DOWN -> EAST;
            case EAST -> UP;
            default -> throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
        };
    }

    public Direction rotateYCounterclockwise() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            default -> throw new IllegalStateException("Unable to get CCW facing of " + this);
        };
    }

    public int getOffsetX() {
        return this.vector.getX();
    }

    public int getOffsetY() {
        return this.vector.getY();
    }

    public int getOffsetZ() {
        return this.vector.getZ();
    }

    public String getName() {
        return this.name;
    }

    public Axis getAxis() {
        return this.axis;
    }

    
    public static Direction byName( String name) {
        if (name == null) {
            return null;
        }
        return NAME_MAP.get(name.toLowerCase(Locale.ROOT));
    }

    public static Direction byId(int id) {
        return VALUES[MathHelper.abs(id % VALUES.length)];
    }

    public static Direction fromHorizontal(int value) {
        return HORIZONTAL[MathHelper.abs(value % HORIZONTAL.length)];
    }

    public static Direction fromRotation(double rotation) {
        return Direction.fromHorizontal(
            MathHelper.floor(rotation / 90.0 + 0.5) & 3);
    }

    public static Direction from(Axis axis, AxisDirection direction) {
        return switch (axis) {
            default -> throw new IncompatibleClassChangeError();
            case X -> {
                if (direction == AxisDirection.POSITIVE) {
                    yield EAST;
                }
                yield WEST;
            }
            case Y -> {
                if (direction == AxisDirection.POSITIVE) {
                    yield UP;
                }
                yield DOWN;
            }
            case Z -> direction == AxisDirection.POSITIVE ? SOUTH : NORTH;
        };
    }

    public float asRotation() {
        return (this.idHorizontal & 3) * 90;
    }

    public static Direction getFacing(double x, double y, double z) {
        return Direction.getFacing((float)x, (float)y, (float)z);
    }

    public static Direction getFacing(float x, float y, float z) {
        Direction lv = NORTH;
        float i = Float.MIN_VALUE;
        for (Direction lv2 : ALL) {
            float j = x * (float)lv2.vector.getX() + y * (float)lv2.vector.getY() + z * (float)lv2.vector.getZ();
            if (!(j > i)) continue;
            i = j;
            lv = lv2;
        }
        return lv;
    }

    public String toString() {
        return this.name;
    }

    
    public String asString() {
        return this.name;
    }

    public static Direction get(AxisDirection direction, Axis axis) {
        for (Direction lv : ALL) {
            if (lv.getDirection() != direction || lv.getAxis() != axis) continue;
            return lv;
        }
        throw new IllegalArgumentException("No such direction: " + direction + " " + axis);
    }

    public Vec3i getVector() {
        return this.vector;
    }

    public boolean pointsTo(float yaw) {
        float g = yaw * ((float)Math.PI / 180);
        float h = -MathHelper.sin(g);
        float i = MathHelper.cos(g);
        return (float)this.vector.getX() * h + (float)this.vector.getZ() * i > 0.0f;
    }

    static {
        ALL = Direction.values();
        NAME_MAP = Arrays.stream(ALL).collect(Collectors.toMap(Direction::getName, direction -> direction));
        VALUES = (Direction[])Arrays.stream(ALL).sorted(Comparator.comparingInt(direction -> direction.id)).toArray(Direction[]::new);
        HORIZONTAL = (Direction[])Arrays.stream(ALL).filter(direction -> direction.getAxis().isHorizontal()).sorted(Comparator.comparingInt(direction -> direction.idHorizontal)).toArray(Direction[]::new);
    }

    /*
     * Uses 'sealed' constructs - enablewith --sealed true
     */
    public static enum Axis implements StringIdentifiable
    {
        X("x"){

            
            public int choose(int x, int y, int z) {
                return x;
            }

            
            public double choose(double x, double y, double z) {
                return x;
            }

            
            public /* synthetic */ boolean test(Object object) {
                return super.test((Direction)object);
            }
        }
        ,
        Y("y"){

            
            public int choose(int x, int y, int z) {
                return y;
            }

            
            public double choose(double x, double y, double z) {
                return y;
            }

            
            public /* synthetic */ boolean test(Object object) {
                return super.test((Direction)object);
            }
        }
        ,
        Z("z"){

            
            public int choose(int x, int y, int z) {
                return z;
            }

            
            public double choose(double x, double y, double z) {
                return z;
            }

            
            public /* synthetic */ boolean test(Object object) {
                return super.test((Direction)object);
            }
        };

        public static final Axis[] VALUES;
        private static final Map<String, Axis> BY_NAME;
        private final String name;

        Axis(String name) {
            this.name = name;
        }

        
        public static Axis fromName(String name) {
            return BY_NAME.get(name.toLowerCase(Locale.ROOT));
        }

        public String getName() {
            return this.name;
        }

        public boolean isVertical() {
            return this == Y;
        }

        public boolean isHorizontal() {
            return this == X || this == Z;
        }

        public String toString() {
            return this.name;
        }
        
        public boolean test(Direction arg) {
            return arg != null && arg.getAxis() == this;
        }

        public Type getType() {
            return switch (this) {
                default -> throw new IncompatibleClassChangeError();
                case X, Z -> Type.HORIZONTAL;
                case Y -> Type.VERTICAL;
            };
        }

        
        public String asString() {
            return this.name;
        }

        public abstract int choose(int var1, int var2, int var3);

        public abstract double choose(double var1, double var3, double var5);

        
        public /* synthetic */ boolean test( Object object) {
            return this.test((Direction)object);
        }

        static {
            VALUES = Axis.values();
            BY_NAME = Arrays.stream(VALUES).collect(Collectors.toMap(Axis::getName, axis -> axis));
        }
    }

    public static enum AxisDirection {
        POSITIVE(1, "Towards positive"),
        NEGATIVE(-1, "Towards negative");

        private final int offset;
        private final String description;

        private AxisDirection(int offset, String description) {
            this.offset = offset;
            this.description = description;
        }

        public int offset() {
            return this.offset;
        }

        public String getDescription() {
            return this.description;
        }

        public String toString() {
            return this.description;
        }

        public AxisDirection getOpposite() {
            return this == POSITIVE ? NEGATIVE : POSITIVE;
        }
    }

    public static enum Type
    {
        HORIZONTAL(new Direction[]{NORTH, EAST, SOUTH, WEST}, new Axis[]{Axis.X, Axis.Z}),
        VERTICAL(new Direction[]{UP, DOWN}, new Axis[]{Axis.Y});

        private final Direction[] facingArray;
        private final Axis[] axisArray;

        private Type(Direction[] facingArray, Axis[] axisArray) {
            this.facingArray = facingArray;
            this.axisArray = axisArray;
        }

        
        public boolean test( Direction arg) {
            return arg != null && arg.getAxis().getType() == this;
        }


        public Stream<Direction> stream() {
            return Arrays.stream(this.facingArray);
        }

        
        public /* synthetic */ boolean test( Object direction) {
            return this.test((Direction)direction);
        }
    }
}

