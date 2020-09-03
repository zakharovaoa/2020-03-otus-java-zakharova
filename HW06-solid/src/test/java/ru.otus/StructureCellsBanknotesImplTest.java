package ru.otus;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;

class StructureCellsBanknotesImplTest {
    private Map<Integer, Integer> mapStructureCells;

    private StructureCellsBanknotes structureCellsBanknotes;


    @BeforeEach
    void setUp() {
        structureCellsBanknotes = new StructureCellsBanknotesImpl();
    }

    @DisplayName("должен возвращать Set с сортировкой по убыванию")
    @Test
    void shouldReturnDescSortedSet() {
        Set<Integer> set = structureCellsBanknotes.getSortedSetKeys();
        Integer prev = Integer.MAX_VALUE;
        for (Integer key : set) {
            Assert.assertFalse(key > prev);
            prev = key;
        }
    }
}