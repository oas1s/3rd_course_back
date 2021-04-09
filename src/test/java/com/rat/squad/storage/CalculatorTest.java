package com.rat.squad.storage;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    //UNIT TESTы

    // Метод достает статический класс Calculator$ResultCalculateCreator
    // Делает его конструктор допустимым и возвращает объект приватного класса
    // Класс достается посредством Java Reflection Api
    public static Object getResultCalculateCreatorInstance() throws Exception {
        Class<?> inner = Class.forName("com.rat.squad.storage.Calculator$ResultCalculateCreator");
        Constructor<?> constructor = inner.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    // Тест проверяющий что статический класс ResultCalculateCreator возвращает один и тот же объект
    // Используя паттерн singleton
    @Test
    public void test_ResultCalculateCreateReturnsSameInstance() throws Exception {
        Object calculatorCreator = getResultCalculateCreatorInstance();
        Method getResultCalculatorInstanceMethod = calculatorCreator.getClass().getMethod("getResultCalculatorInstance");
        getResultCalculatorInstanceMethod.setAccessible(true);
        Object obj2 = getResultCalculatorInstanceMethod.invoke(calculatorCreator);
        Object obj3 = getResultCalculatorInstanceMethod.invoke(calculatorCreator);
        assertEquals(obj2, obj3);
    }

    // Тест проверяющий метод SetOperation(string Operation)
    // Получение доступа к полю организовано с помощью Java Reflection Api
    @Test
    public void test_setOperationMethod() throws Exception {
        Calculator calculator = new Calculator();
        calculator.setOperation("multi");
        Field operation = calculator.getClass().getDeclaredField("operation");
        operation.setAccessible(true);
        Calculator.Operation operation1 = (Calculator.Operation) operation.get(calculator);
        assertEquals(Calculator.Operation.MULTI, operation1);
    }

    // БИЗНЕС ТЕСТЫ

    // Тест на проверку операции умножения
    @Test
    public void test_multiOperation() throws Exception {
        Calculator calculator = new Calculator();
        calculator.setOperation("multi");
        Double result = calculator.calculate(1.0, 30.0);
        assertEquals(30.0, result);
    }

    // Тест на проверку операции сложения
    @Test
    public void test_addOperation() throws Exception {
        Calculator calculator = new Calculator();
        calculator.setOperation("add");
        Double result = calculator.calculate(1.0, 30.0);
        assertEquals(31.0, result);
    }

    // Тест на проверку операции вычитания
    @Test
    public void test_subOperation() throws Exception {
        Calculator calculator = new Calculator();
        calculator.setOperation("sub");
        Double result = calculator.calculate(1.0, 30.0);
        assertEquals(-29.0, result);
    }

    // Тест на проверку операции деления
    @Test
    public void test_divOperation() throws Exception {
        Calculator calculator = new Calculator();
        calculator.setOperation("div");
        Double result = calculator.calculate(35.0, 3.5);
        assertEquals(10.0, result);
    }

    //Тест на проверку нечувствительности к регистру вводимой операции
    // Тест на проверку операции деления
    @Test
    public void test_caseSense() throws Exception {
        Calculator calculator = new Calculator();
        calculator.setOperation("DiV");
        Double result1 = calculator.calculate(35.0, 3.5);
        calculator.setOperation("dIv");
        Double result2 = calculator.calculate(35.0, 3.5);
        calculator.setOperation("DIV");
        Double result3 = calculator.calculate(35.0, 3.5);
        calculator.setOperation("div");
        Double result4 = calculator.calculate(35.0, 3.5);
        assertEquals(10.0, result1);
        assertEquals(10.0, result2);
        assertEquals(10.0, result3);
        assertEquals(10.0, result4);
    }
}