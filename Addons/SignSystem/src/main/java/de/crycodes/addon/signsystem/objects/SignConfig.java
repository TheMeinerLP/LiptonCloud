package de.crycodes.addon.signsystem.objects;

import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: SignConfig
 * Date : 25.06.2020
 * Time : 17:46
 * Project: LiptonCloud
 */

public class SignConfig {

    private final Integer signLayoutId;
    private final SignLocation signLocation;

    public SignConfig(Integer signLayoutId, SignLocation signLocation) {
        this.signLayoutId = signLayoutId;
        this.signLocation = signLocation;
    }

    @Override
    public String toString() {
        return "SignConfig{" +
                "signLayoutId=" + signLayoutId +
                ", signLocation=" + signLocation +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignConfig that = (SignConfig) o;
        return Objects.equals(signLayoutId, that.signLayoutId) &&
                Objects.equals(signLocation, that.signLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(signLayoutId, signLocation);
    }

    public Integer getSignLayoutId() {
        return signLayoutId;
    }

    public SignLocation getSignLocation() {
        return signLocation;
    }
}
