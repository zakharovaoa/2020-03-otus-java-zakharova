package ru.otus;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;

class StructureCellsBanknotesImplTest {
    private Map<CellBanknotes, Integer> mapStructureCells;

    private StructureCellsBanknotes structureCellsBanknotes;


    @BeforeEach
    void setUp() {
        structureCellsBanknotes = new StructureCellsBanknotesImpl();
    }

    @DisplayName("должен возвращать Set с сортировкой по убыванию")
    @Test
    void shouldReturnDescSortedSet() {
        Set<CellBanknotes> set = structureCellsBanknotes.getSortedSetKeys();
        Integer prev = Integer.MAX_VALUE;
        for (CellBanknotes key : set) {
            Integer numberFaceValueBanknote = key.getNumberFaceValueBanknote();
            Assert.assertFalse(numberFaceValueBanknote > prev);
            prev = numberFaceValueBanknote;
        }
    }
}