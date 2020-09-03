package ru.otus;

import java.util.*;

public class AtmImpl implements Atm {

    private final CellsBanknotes cellsBanknotes;

    public AtmImpl(Integer initialCountBanknotes) {
        cellsBanknotes = new CellsBanknotesImpl(initialCountBanknotes);
    }

    @Override
    public void receiveSum(Integer faceValueBanknote, Integer countBanknotes) {
        this.cellsBanknotes.addBanknotes(faceValueBanknote, countBanknotes);
    }

    @Override
    public Map giveSum(Integer sum) {
        StructureCellsBanknotes structureCellsBanknotes = new StructureCellsBanknotesImpl();
        HashMap<Integer, Integer> mapCells = new HashMap<>(structureCellsBanknotes.getMapStructureCells());
        int count;
        int remainder = sum;
        Set<Integer> setKeys = structureCellsBanknotes.getSortedSetKeys();
        for (Integer key: setKeys) {
            count = remainder / key;
            if (count != 0) {
                remainder = remainder - key * count;
                this.cellsBanknotes.reduceBanknotes(key, count);
                mapCells.replace(key, count);
            }
        }
        if (remainder > 0) throw new CellsBanknotesError();
        return mapCells;
    }

    @Override
    public Integer getBalance() {
        return this.cellsBanknotes.getBalance();
    }

}
