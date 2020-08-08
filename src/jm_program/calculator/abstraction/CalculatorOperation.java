package jm_program.calculator.abstraction;

public abstract class CalculatorOperation {

    public int sum(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        return a - b;
    }


    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if(b == 0) throw new ArithmeticException("[-] " + a + " не может делиться на 0");
        return a / b;
    }

    public abstract int  calculate(int a, int b);

}
