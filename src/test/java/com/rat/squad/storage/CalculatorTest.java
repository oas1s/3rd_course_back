package com.rat.squad.storage;

import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    /**
     * BUISNESS LOGIC TESTING
     * Тестирование бизнес логики
     * Проверка операций калькулятора
     */

    /**
     * Проверка на то, что в случае непредвиденного оператора будет выброшен
     * @throws IllegalArgumentException
     */
    @Test
    public void test_illegalOperationThrowsException(){
        assertThrows(IllegalArgumentException.class, () ->{
            Calculator calculator = new Calculator("1 $ 2");
        });
    }

    /**
     * проверка логики на операцию вычитания
     */
    @Test
    public void test_subCalcOperation() throws MessagingException {
        Calculator calculator = new Calculator("1 - 1");
        Double actual = calculator.calculateSentence();
        assertEquals(0.0,actual);
    }

    /**
     * проверка логики на операцию умножения
     */
    @Test
    public void test_multiCalcOperation() throws MessagingException {
        Calculator calculator = new Calculator("2 * 3");
        Double actual = calculator.calculateSentence();
        assertEquals(6.0,actual);
    }


    /**
     * проверка логики на операцию сложения
     */
    @Test
    public void test_divCalcOperation() throws MessagingException {
        Calculator calculator = new Calculator("1 + 1");
        Double actual = calculator.calculateSentence();
        assertEquals(2.0,actual);
    }


    /**
     * проверка логики на операцию деления
     */
    @Test
    public void test_sumCalcOperation() throws MessagingException {
        Calculator calculator = new Calculator("20 / 5");
        Double actual = calculator.calculateSentence();
        assertEquals(4.0,actual);
    }

    /**
     * UNIT TESTING
     * Тестирование отдельно разных внутренних компонентов калькулятора
     * Из-за инкапсуляции методов был использован Reflection API
     */

    /**
     * Метод который с помощью Reflection API возвращает инкапсулированный класс Calculator$EmailSenderCreator
     * @return Calculator$EmailSenderCreator
     * @throws Exception
     */
    public static Object getEmailSenderCreator() throws Exception{
        Class<?> inner = Class.forName("com.rat.squad.storage.Calculator$EmailSenderCreator");
        Constructor<?> constructor = inner.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    /**
     * Проверка на то, что SINGLETON pattern на emailSenderCreator'e
     * Возвращает один и тот же созданный им инстанс
     * Для получения доступа к классу используется Java Reflection API
     * @throws Exception
     */
    @Test
    public void test_emailSenderCreatorReturnsSameInstance() throws Exception {
        Object emailSenderCreator = getEmailSenderCreator();
        Method getEmailSenderMethod = emailSenderCreator.getClass().getDeclaredMethod("getEmailSenderInstance");
        getEmailSenderMethod.setAccessible(true);
        Object obj2 = getEmailSenderMethod.invoke(emailSenderCreator);
        Object obj3 = getEmailSenderMethod.invoke(emailSenderCreator);
        assertEquals(obj2,obj3);
    }

    /**
     * Проверка метода defineOperation(String sentence)
     * Метод, получая стринговое выражение, определяет какого вида математическая операция
     * Для получения доступа к приватному полю operation используется Java Reflection API
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void test_defineOperationMethod() throws NoSuchFieldException, IllegalAccessException {
        Calculator calculator = new Calculator("1 + 1");
        Field field = Calculator.class.getDeclaredField("operation");
        field.setAccessible(true);
        String operation = (String) field.get(calculator);
        assertEquals("ADD", operation);
    }
}