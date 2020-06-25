package de.crycodes.addon.signsystem.objects;

import java.io.Serializable;
import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: SignLayout
 * Date : 25.06.2020
 * Time : 16:21
 * Project: LiptonCloud
 */

public class SignLayout implements Serializable {

    private final String line_1;
    private final String line_2;
    private final String line_3;
    private final String line_4;

    public SignLayout(String line_1, String line_2, String line_3, String line_4) {
        this.line_1 = line_1;
        this.line_2 = line_2;
        this.line_3 = line_3;
        this.line_4 = line_4;
    }

    @Override
    public String toString() {
        return "SignLayout{" +
                "line_1='" + line_1 + '\'' +
                ", line_2='" + line_2 + '\'' +
                ", line_3='" + line_3 + '\'' +
                ", line_4='" + line_4 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignLayout that = (SignLayout) o;
        return Objects.equals(line_1, that.line_1) &&
                Objects.equals(line_2, that.line_2) &&
                Objects.equals(line_3, that.line_3) &&
                Objects.equals(line_4, that.line_4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line_1, line_2, line_3, line_4);
    }

    public String getLine_1() {
        return line_1;
    }

    public String getLine_2() {
        return line_2;
    }

    public String getLine_3() {
        return line_3;
    }

    public String getLine_4() {
        return line_4;
    }
}
