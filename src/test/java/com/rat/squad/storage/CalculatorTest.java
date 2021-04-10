package com.rat.squad.storage;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    // Функциональные тесты
    @Test
    public void testStraightAddOperation() {
        Calculator calculator = new Calculator();
        calculator.setOperation("ADD");
        Double result = calculator.calculate(1.0, 3.0);
        assertEquals(4.0, result);
    }

    // Проверка дефолтной операции(сложение)
    @Test
    public void testAddOperation() {
        Calculator calculator = new Calculator();
        calculator.setOperation("");
        Double result = calculator.calculate(1.0, 3.0);
        assertEquals(4.0, result);
    }


    // Юнит тест

    // Проверка метода setOperation на то, что
    // при некорректном значении операции сетится дефолтное значение "операция сложения"
    @Test
    public void testSetOperationMethodDefaultValue() throws NoSuchFieldException, IllegalAccessException {
        Calculator calculator = new Calculator();
        calculator.setOperation("");
        Field operation = calculator.getClass().getDeclaredField("operation");
        operation.setAccessible(true);
        Calculator.Operation operation1 = (Calculator.Operation) operation.get(calculator);
        assertEquals(Calculator.Operation.ADD, operation1);
    }
}