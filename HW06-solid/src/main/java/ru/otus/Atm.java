package ru.otus;

import java.util.Map;

public interface Atm {

    void receiveSum(CellBanknotes cellBanknotes, Integer countBanknotes);

    Map giveSum(Integer sum);

    Integer getBalance();

}
