package ru.otus;

public interface DeviceCellsBanknotes {

    void addBanknotes(FaceValueBanknote faceValueBanknote, Integer countBanknotes);

    void reduceBanknotes(FaceValueBanknote faceValueBanknote, Integer countBanknotes);

    Integer getCountBanknotes(FaceValueBanknote faceValueBanknote);

    Integer getBalance();
}
