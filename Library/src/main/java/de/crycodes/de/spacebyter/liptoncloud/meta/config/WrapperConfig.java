package de.crycodes.de.spacebyter.liptoncloud.meta.config;

import java.io.Serializable;
import java.util.Objects;

public class WrapperConfig implements Serializable {

    private final String wrapperId;
    private final String host;
    private final Boolean autoUpdate;

    public WrapperConfig(String wrapperId, String host, Boolean autoUpdate) {
        this.wrapperId = wrapperId;
        this.host = host;
        this.autoUpdate = autoUpdate;
    }

    @Override
    public String toString() {
        return "WrapperConfig{" +
                "wrapperId='" + wrapperId + '\'' +
                ", host=" + host +
                ", autoUpdate=" + autoUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperConfig that = (WrapperConfig) o;
        return Objects.equals(wrapperId, that.wrapperId) &&
                Objects.equals(host, that.host) &&
                Objects.equals(autoUpdate, that.autoUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wrapperId, host, autoUpdate);
    }

    public String getWrapperId() {
        return wrapperId;
    }

    public String getHost() {
        return host;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }
}
