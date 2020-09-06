package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class CellsBanknotes {
    
    private Map<Integer, Integer> mapCells;
    private final StructureCellsBanknotes structureCellsBanknotes = new StructureCellsBanknotesImpl();

    public CellsBanknotes () {
        this.mapCells = new HashMap<>(structureCellsBanknotes.getMapStructureCells());
    }

    public Map<Integer, Integer> getMap() {
        return this.mapCells;
    }

}
