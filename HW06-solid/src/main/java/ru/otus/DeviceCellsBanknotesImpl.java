package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class DeviceCellsBanknotesImpl implements DeviceCellsBanknotes{

    private final CellsBanknotes cellsBanknotes = new CellsBanknotes();

    public DeviceCellsBanknotesImpl(Integer initialCountBanknotes) {

        for (Map.Entry entry: cellsBanknotes.getMap().entrySet()) {
            entry.setValue(initialCountBanknotes);
        }
    }
    
    @Override
    public void addBanknotes(Integer faceValueBanknote, Integer countBanknotes) {
        if (!(cellsBanknotes.getMap().containsKey(faceValueBanknote))
                || (countBanknotes <= 0)) {
            throw new CellsBanknotesError();
        }
        Integer currentCount = getCountBanknotes(faceValueBanknote);
        cellsBanknotes.getMap().replace(faceValueBanknote, currentCount + countBanknotes);
    }

    @Override
    public void reduceBanknotes(Integer faceValueBanknote, Integer countBanknotes) {
        Integer currentCount = getCountBanknotes(faceValueBanknote);
        if (!(cellsBanknotes.getMap().containsKey(faceValueBanknote))
                || (countBanknotes <= 0)
                || (currentCount - countBanknotes) < 0) {
            throw new CellsBanknotesError();
        }
        cellsBanknotes.getMap().replace(faceValueBanknote, currentCount - countBanknotes);
    }

    @Override
    public Integer getCountBanknotes(Integer faceValueBanknote) {
        return cellsBanknotes.getMap().get(faceValueBanknote);
    }

    @Override
    public Integer getBalance() {
        Integer sum = 0;
        for (Map.Entry entry: cellsBanknotes.getMap().entrySet()) {
            sum += (Integer) (entry.getValue()) * (Integer) entry.getKey();
        }
        return sum;
    }
}
