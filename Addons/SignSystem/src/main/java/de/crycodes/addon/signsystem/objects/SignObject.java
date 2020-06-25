package de.crycodes.addon.signsystem.objects;

import de.crycodes.addon.signsystem.enums.SignState;

import java.io.Serializable;
import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: SignConfig
 * Date : 25.06.2020
 * Time : 16:24
 * Project: LiptonCloud
 */

public class SignObject implements Serializable {

    private final SignLayout signLayout;
    private final SignLocation signLocation;
    private final SignState signState;

    public SignObject(SignLayout signLayout, SignLocation signLocation, SignState signState) {
        this.signLayout = signLayout;
        this.signLocation = signLocation;
        this.signState = signState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignObject that = (SignObject) o;
        return Objects.equals(signLayout, that.signLayout) &&
                Objects.equals(signLocation, that.signLocation) &&
                signState == that.signState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(signLayout, signLocation, signState);
    }

    @Override
    public String toString() {
        return "SignConfig{" +
                "signLayout=" + signLayout +
                ", signLocation=" + signLocation +
                ", signState=" + signState +
                '}';
    }

    public SignLayout getSignLayout() {
        return signLayout;
    }

    public SignLocation getSignLocation() {
        return signLocation;
    }

    public SignState getSignState() {
        return signState;
    }
}
