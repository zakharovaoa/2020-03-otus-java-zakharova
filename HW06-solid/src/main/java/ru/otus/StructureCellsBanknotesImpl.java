package ru.otus;

import java.util.*;

public class StructureCellsBanknotesImpl implements StructureCellsBanknotes{

    private Map<Integer, Integer> mapStructureCells = new HashMap<>();

    public StructureCellsBanknotesImpl() {
        for (FaceValueBanknote f : FaceValueBanknote.values()) {
            this.mapStructureCells.put(
                    f.getFaceValueBanknote(),
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
        Set<Integer> sortedSet = new TreeSet<Integer>((o1, o2) -> o2.compareTo(o1));
        sortedSet.addAll(this.mapStructureCells.keySet());
        return sortedSet;
    }
}
