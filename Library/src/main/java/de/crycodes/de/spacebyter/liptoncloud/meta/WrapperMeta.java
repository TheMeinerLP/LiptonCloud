package de.crycodes.de.spacebyter.liptoncloud.meta;

import de.crycodes.de.spacebyter.liptoncloud.interfaces.Meta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;

import java.io.Serializable;
import java.util.Objects;

public class WrapperMeta implements Serializable, Meta {

    private final boolean available;
    private final WrapperConfig wrapperConfig;

    public WrapperMeta(boolean available, WrapperConfig wrapperConfig) {
        this.available = available;
        this.wrapperConfig = wrapperConfig;
    }

    @Override
    public String toString() {
        return "WrapperMeta{" +
                "available=" + available +
                ", wrapperConfig=" + wrapperConfig +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperMeta that = (WrapperMeta) o;
        return available == that.available &&
                Objects.equals(wrapperConfig, that.wrapperConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(available, wrapperConfig);
    }

    public boolean isAvailable() {
        return available;
    }

    public WrapperConfig getWrapperConfig() {
        return wrapperConfig;
    }
}
