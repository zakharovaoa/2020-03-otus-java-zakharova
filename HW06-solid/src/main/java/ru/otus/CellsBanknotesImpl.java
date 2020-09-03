package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class CellsBanknotesImpl implements CellsBanknotes{

    private Map<Integer, Integer> mapCells;
    private final StructureCellsBanknotes structureCellsBanknotes = new StructureCellsBanknotesImpl();

    public CellsBanknotesImpl(Integer initialCountBanknotes) {
        this.mapCells = new HashMap<>(structureCellsBanknotes.getMapStructureCells());
        for (Map.Entry entry: this.mapCells.entrySet()) {
            entry.setValue(initialCountBanknotes);
        }
    }
    
    @Override
    public void addBanknotes(Integer faceValueBanknote, Integer countBanknotes) {
        if (!(this.mapCells.containsKey(faceValueBanknote))
                || (countBanknotes <= 0)) {
            throw new CellsBanknotesError();
        }
        Integer currentCount = getCountBanknotes(faceValueBanknote);
        this.mapCells.replace(faceValueBanknote, currentCount + countBanknotes);
    }

    @Override
    public void reduceBanknotes(Integer faceValueBanknote, Integer countBanknotes) {
        Integer currentCount = getCountBanknotes(faceValueBanknote);
        if (!(this.mapCells.containsKey(faceValueBanknote))
                || (countBanknotes <= 0)
                || (currentCount - countBanknotes) < 0) {
            throw new CellsBanknotesError();
        }
        this.mapCells.replace(faceValueBanknote, currentCount - countBanknotes);
    }

    @Override
    public Integer getCountBanknotes(Integer faceValueBanknote) {
        return this.mapCells.get(faceValueBanknote);
    }

    @Override
    public Integer getBalance() {
        Integer sum = 0;
        for (Map.Entry entry: this.mapCells.entrySet()) {
            sum += (Integer) (entry.getValue()) * (Integer) entry.getKey();
        }
        return sum;
    }
}
