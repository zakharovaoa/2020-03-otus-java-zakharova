package ru.otus;

import java.util.Objects;

public class CellBanknotes {

    private FaceValueBanknote faceValueBanknote;

    private final Integer numberFaceValueBanknote;

    public CellBanknotes (FaceValueBanknote faceValueBanknote) {
        this.numberFaceValueBanknote = faceValueBanknote.getNumberFaceValueBanknote();
    }

    public Integer getNumberFaceValueBanknote() {
        return this.numberFaceValueBanknote;
    }

    public int compareTo(CellBanknotes o1) {
        return this.numberFaceValueBanknote - o1.numberFaceValueBanknote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellBanknotes that = (CellBanknotes) o;
        return faceValueBanknote == that.faceValueBanknote &&
                numberFaceValueBanknote.equals(that.numberFaceValueBanknote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faceValueBanknote, numberFaceValueBanknote);
    }
}
