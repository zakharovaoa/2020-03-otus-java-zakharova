package ru.otus;

import java.util.*;

public class StructureCellsBanknotesImpl implements StructureCellsBanknotes{

    private Map<Integer, Integer> mapStructureCells = new HashMap<>();

    public StructureCellsBanknotesImpl() {
        this.mapStructureCells.put(50, 0);
        this.mapStructureCells.put(100, 5);
        this.mapStructureCells.put(200, 0);
        this.mapStructureCells.put(500, 0);
        this.mapStructureCells.put(1000, 0);
        this.mapStructureCells.put(2000, 0);
        this.mapStructureCells.put(5000, 0);
    }

    @Override
    public Map getMapStructureCells() {
        return this.mapStructureCells;
    }

    @Override
    public Set getSortedSetKeys() {
        Set<Integer> sortedSet = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        sortedSet.addAll(this.mapStructureCells.keySet());
        return sortedSet;
    }
}
