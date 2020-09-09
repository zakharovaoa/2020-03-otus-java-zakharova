package ru.otus;

import java.util.*;

public class StructureCellsBanknotesImpl implements StructureCellsBanknotes{

    private Map<CellBanknotes, Integer> mapStructureCells = new HashMap<>();

    public StructureCellsBanknotesImpl() {
        for (FaceValueBanknote f : FaceValueBanknote.values()) {
            this.mapStructureCells.put(
                    new CellBanknotes(f),
                    0
            );
        }
    }

    @Override
    public Map getMapStructureCells() {
        return this.mapStructureCells;
    }

    @Override
    public Set getSortedSetKeys() {
        Set<CellBanknotes> sortedSet = new TreeSet<CellBanknotes>((o1, o2) -> o2.compareTo(o1));
        sortedSet.addAll(this.mapStructureCells.keySet());
        return sortedSet;
    }
}
