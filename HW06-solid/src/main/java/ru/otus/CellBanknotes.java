package ru.otus;

public interface CellBanknotes {

    Integer getNumberFaceValueBanknote();

    FaceValueBanknote getFaceValueBanknote();

    int compareTo(CellBanknotes o1);

    boolean equals(Object o);

    int hashCode();
}