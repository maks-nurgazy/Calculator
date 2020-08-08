package jm_program.calculator;

import jm_program.calculator.abstraction.CalculatorOperation;

public class CalculatorOperationImpl extends CalculatorOperation {

    private String operationType;

    CalculatorOperationImpl(String operationType){
        this.operationType = operationType;
    }

    @Override
    public int calculate(int a, int b) {

        switch (this.operationType){
            case "+":
                return this.sum(a,b);
            case "-":
                return this.minus(a,b);
            case "/":
                return this.divide(a,b);
            case "*":
                return this.multiply(a,b);
        }

        return 0;
    }
}
