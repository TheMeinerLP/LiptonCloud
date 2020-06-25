package de.crycodes.addon.signsystem.objects;

import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: SavedSignLayoutObject
 * Date : 25.06.2020
 * Time : 17:48
 * Project: LiptonCloud
 */

public class SavedSignLayoutObject {

    private final int id;
    private final SignLayout signLayout;

    public SavedSignLayoutObject(int id, SignLayout signLayout) {
        this.id = id;
        this.signLayout = signLayout;
    }

    @Override
    public String toString() {
        return "SavedSignLayoutObject{" +
                "id=" + id +
                ", signLayout=" + signLayout +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedSignLayoutObject that = (SavedSignLayoutObject) o;
        return id == that.id &&
                Objects.equals(signLayout, that.signLayout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, signLayout);
    }

    public int getId() {
        return id;
    }

    public SignLayout getSignLayout() {
        return signLayout;
    }
}
