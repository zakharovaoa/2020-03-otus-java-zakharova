package ru.otus;

import java.util.*;

public class AtmImpl implements Atm {

    private final DeviceCellsBanknotes deviceCellsBanknotes;

    public AtmImpl(Integer initialCountBanknotes) {
        deviceCellsBanknotes = new DeviceCellsBanknotesImpl(initialCountBanknotes);
    }

    @Override
    public void receiveSum(FaceValueBanknote faceValueBanknote, Integer countBanknotes) {
        this.deviceCellsBanknotes.addBanknotes(faceValueBanknote, countBanknotes);
    }

    @Override
    public Map giveSum(Integer sum) {
        StructureCellsBanknotes structureCellsBanknotes = new StructureCellsBanknotesImpl();
        HashMap<FaceValueBanknote, Integer> mapCells = new HashMap<>();
        Integer count;
        int remainder = sum;
        Set<CellBanknotes> setKeys = structureCellsBanknotes.getSortedSetKeys();
        for (CellBanknotes key: setKeys) {
            int numberFaceValueBanknote = key.getNumberFaceValueBanknote();
            count = remainder / numberFaceValueBanknote;
            if (count != 0) {
                remainder = remainder - numberFaceValueBanknote * count;
                this.deviceCellsBanknotes.reduceBanknotes(key.getFaceValueBanknote(), count);
                mapCells.put(key.getFaceValueBanknote(), count);
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
