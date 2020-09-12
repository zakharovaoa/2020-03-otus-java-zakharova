package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class DeviceCellsBanknotesImpl implements DeviceCellsBanknotes{

    private Map<CellBanknotes, Integer> mapCells;
    private final StructureCellsBanknotes structureCellsBanknotes = new StructureCellsBanknotesImpl();

    public DeviceCellsBanknotesImpl(Integer initialCountBanknotes) {
        this.mapCells = new HashMap<>(structureCellsBanknotes.getMapStructureCells());
        for (Map.Entry entry: this.mapCells.entrySet()) {
            entry.setValue(initialCountBanknotes);
        }
    }
    
    @Override
    public void addBanknotes(FaceValueBanknote faceValueBanknote, Integer countBanknotes) {
        if (countBanknotes <= 0) throw new CellsBanknotesError();
        Integer currentCount = getCountBanknotes(faceValueBanknote);
        this.mapCells.replace(structureCellsBanknotes.getCellBanknotes(faceValueBanknote), currentCount + countBanknotes);
    }

    @Override
    public void reduceBanknotes(FaceValueBanknote faceValueBanknote, Integer countBanknotes) {
        Integer currentCount = getCountBanknotes(faceValueBanknote);
        if ((countBanknotes <= 0)
                || (currentCount - countBanknotes) < 0) {
            throw new CellsBanknotesError();
        }
        this.mapCells.replace(structureCellsBanknotes.getCellBanknotes(faceValueBanknote), currentCount - countBanknotes);
    }

    @Override
    public Integer getCountBanknotes(FaceValueBanknote faceValueBanknote) {
        return this.mapCells.get(structureCellsBanknotes.getCellBanknotes(faceValueBanknote));
    }

    @Override
    public Integer getBalance() {
        Integer sum = 0;
        for (Map.Entry entry: this.mapCells.entrySet()) {
            CellBanknotes cellBanknotes = (CellBanknotes) entry.getKey();
            sum += (Integer) (entry.getValue()) * (Integer) cellBanknotes.getNumberFaceValueBanknote();
        }
        return sum;
    }
}
