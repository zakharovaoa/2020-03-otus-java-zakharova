package ru.otus;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.FaceValueBanknote.*;

class AtmImplTest {

    private Atm atm;
    public static final FaceValueBanknote FACE_VALUE_BANKNOTE = BANKNOTE_500;
    public static final Integer COUNT_BANKNOTES = 3;
    public static final Integer SUM = 90000;
    private static final Integer INITIAL_COUNT_BANKNOTES = 10;
    public static final Integer INITIAL_SUM = 88500;
    public static final Integer MIN_SINGLE_SUM = 8850;
    public static final Integer COUNT_MIN = 1;

    @BeforeEach
    void setUp() {
        atm = new AtmImpl(INITIAL_COUNT_BANKNOTES);
    }

    @DisplayName("должен возвращать корректный баланс когда принял банкноты")
    @Test
    void shouldReturnCorrectBalanceWhenReceiveSum() {
        atm.receiveSum(FACE_VALUE_BANKNOTE, COUNT_BANKNOTES);
        Integer result = atm.getBalance();
        Assert.assertEquals(result, SUM);
    }

    @DisplayName("должен кидать нужное исключение, если сумму нельзя выдать")
    @Test
    void shouldThrowExpectedExceptionWhenInvalidSum() {
        assertThrows(CellsBanknotesError.class,
                () -> atm.giveSum(SUM));
    }

    @DisplayName("должен выдавать сумму минимальным количеством банкнот")
    @Test
    void shouldGiveSumMinCountBanknotes() {
        Map<CellBanknotes, Integer> map = atm.giveSum(MIN_SINGLE_SUM);
        Collection<Integer> list = map.values();
        for (Integer value : list) {
            Assert.assertEquals(value, COUNT_MIN);
        }
    }

    @DisplayName("должен возвращать баланс инициализации АТМ")
    @Test
    void shouldReturnInitialBalance() {
        Integer result = atm.getBalance();
        Assert.assertEquals(result, INITIAL_SUM);
    }
}