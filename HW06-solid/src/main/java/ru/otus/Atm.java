package ru.otus;

import java.util.Map;

public interface Atm {

    void receiveSum(FaceValueBanknote faceValueBanknote, Integer countBanknotes);

    Map giveSum(Integer sum);

    Integer getBalance();

}
