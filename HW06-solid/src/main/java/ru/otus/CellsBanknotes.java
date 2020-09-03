package ru.otus;

public interface CellsBanknotes {

    void addBanknotes(Integer faceValueBanknote, Integer countBanknotes);

    void reduceBanknotes(Integer faceValueBanknote, Integer countBanknotes);

    Integer getCountBanknotes(Integer faceValueBanknote);

    Integer getBalance();
}
