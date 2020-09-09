package ru.otus;

import java.util.*;

public class AtmImpl implements Atm {

    private final DeviceCellsBanknotes deviceCellsBanknotes;

    public AtmImpl(Integer initialCountBanknotes) {
        deviceCellsBanknotes = new DeviceCellsBanknotesImpl(initialCountBanknotes);
    }

    @Override
    public void receiveSum(CellBanknotes cellBanknotes, Integer countBanknotes) {
        this.deviceCellsBanknotes.addBanknotes(cellBanknotes, countBanknotes);
    }

    @Override
    public Map giveSum(Integer sum) {
        StructureCellsBanknotes structureCellsBanknotes = new StructureCellsBanknotesImpl();
        HashMap<CellBanknotes, Integer> mapCells = new HashMap<>(structureCellsBanknotes.getMapStructureCells());
        int count;
        int remainder = sum;
        Set<CellBanknotes> setKeys = structureCellsBanknotes.getSortedSetKeys();
        for (CellBanknotes key: setKeys) {
            int numberFaceValueBanknote = key.getNumberFaceValueBanknote();
            count = remainder / numberFaceValueBanknote;
            if (count != 0) {
                remainder = remainder - numberFaceValueBanknote * count;
                this.deviceCellsBanknotes.reduceBanknotes(key, count);
                mapCells.replace(key, count);
            }
        }
        if (remainder > 0) throw new CellsBanknotesError();
        return mapCells;
    }

    @Override
    public Integer getBalance() {
        return this.deviceCellsBanknotes.getBalance();
    }

}
