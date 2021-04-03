package com.rat.squad.storage;

import javax.mail.MessagingException;

public class Calculator {

    //Decorator
    private final EmailSender emailSender = new EmailSender();

    public static enum Operation {
        ADD, SUB, MULTI, DIV
    }

    //Singleton
    private static class ResultCalculateCreator {

        private static class ResultCalculator {

            //Another factory
            Double calculateResult(Operation operation, Double arg1, Double arg2) {
                switch (operation) {
                    case ADD:
                        return arg1 + arg2;
                    case SUB:
                        return arg1 - arg2;
                    case DIV:
                        return arg1 / arg2;
                    case MULTI:
                        return arg1 * arg2;
                    default:
                        throw new RuntimeException("Invalid operation type");
                }
            }
        }

        private ResultCalculator resultCalculator;

        public ResultCalculator getResultCalculatorInstance() {
            if (this.resultCalculator == null) {
                ResultCalculator resultCalculator = new ResultCalculator();
                this.resultCalculator = resultCalculator;
                return resultCalculator;
            } else {
                return this.resultCalculator;
            }
        }
    }


    private Operation operation;


    //Factory pattern
    public Double calculate(Double arg1, Double arg2) throws MessagingException {
        ResultCalculateCreator resultCalculateCreator = new ResultCalculateCreator();
        Double res = resultCalculateCreator.getResultCalculatorInstance().calculateResult(operation, arg1, arg2);
        emailSender.sendMail("unknownthugger@gmail.com",res.toString());
        return res;
    }

    //Strategy
    public void setOperation(String operation) {
        operation = operation.toLowerCase();
        switch (operation) {
            case "multi":
                this.operation = Operation.MULTI;
                break;
            case "add":
                this.operation = Operation.ADD;
                break;
            case "div":
                this.operation = Operation.DIV;
                break;
            case "sub":
                this.operation = Operation.SUB;
                break;
            default:
                this.operation = Operation.ADD;
                System.out.println("Sorry input operation is unknown, we set it to default addition");
        }
    }


}
