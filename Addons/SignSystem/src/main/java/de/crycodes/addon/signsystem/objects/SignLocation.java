package de.crycodes.addon.signsystem.objects;

import java.io.Serializable;
import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: SignLocation
 * Date : 25.06.2020
 * Time : 16:20
 * Project: LiptonCloud
 */

public class SignLocation implements Serializable {

    private final String world;
    private final int x;
    private final int y;
    private final int z;

    public SignLocation(String world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "SignLocation{" +
                "world='" + world + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignLocation that = (SignLocation) o;
        return x == that.x &&
                y == that.y &&
                z == that.z &&
                Objects.equals(world, that.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, x, y, z);
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
