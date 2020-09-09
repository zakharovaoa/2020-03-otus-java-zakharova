package ru.otus;

public interface DeviceCellsBanknotes {

    void addBanknotes(CellBanknotes cellBanknotes, Integer countBanknotes);

    void reduceBanknotes(CellBanknotes cellBanknotes, Integer countBanknotes);

    Integer getCountBanknotes(CellBanknotes cellBanknotes);

    Integer getBalance();
}
