package jm_program.calculator;

import jm_program.calculator.abstraction.CalculatorOperation;
import jm_program.calculator.abstraction.NumeralConverter;
import jm_program.calculator.exception.NewLineException;
import jm_program.calculator.exception.OperationTypeException;

public class Calculator {

    private final String ROMAN_REGEX = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    private final String ARAB_REGEX = "-?[1-9]\\d*|0";
    private final String MATH_OPERATOR_REGEX = "[-+*/]";
    public boolean isWorking = true;
    private String operandA;
    private String operandB;
    private String operationType;
    private NumeralConverter numeralConverter;

    public Calculator() {
        this.numeralConverter = new NumeralConverterImpl();
    }

    public void calculate(String operation) {
        try {
            if (inputValidity(operation)) {
                if (getNumeralType().equals("arabic")) {
                    int a = Integer.parseInt(this.operandA);
                    int b = Integer.parseInt(this.operandB);
                    CalculatorOperation calculatorOperation = new CalculatorOperationImpl(this.operationType);
                    System.out.println(calculatorOperation.calculate(a, b));

                } else {
                    int a = this.numeralConverter.romanToArabic(this.operandA);
                    int b = this.numeralConverter.romanToArabic(this.operandB);
                    CalculatorOperation calculatorOperation = new CalculatorOperationImpl(this.operationType);
                    int result = calculatorOperation.calculate(a, b);
                    System.out.println(this.numeralConverter.arabicToRoman(result));
                }
            } else {
                isWorking = false;
            }
        } catch (OperationTypeException | NewLineException | IllegalArgumentException | ArithmeticException e) {
            isWorking = false;
            System.out.println(e.getMessage());
        }
    }


    private boolean inputValidity(String input) throws OperationTypeException, NewLineException, IllegalArgumentException {

        String[] operand = input.split(" ");

        if (operand.length != 3) throw new NewLineException("[-] укажите числа, операции через пробел.");

        this.operandA = operand[0].toUpperCase();
        this.operationType = operand[1];
        this.operandB = operand[2].toUpperCase();

        if (!this.operationType.matches(MATH_OPERATOR_REGEX)) {
            throw new OperationTypeException("[-] (" + this.operationType + ") неподдерживаемая операция. " + "Калькулятор умеет выполнять операции сложения(+), вычитания(-), умножения(*) и деления(/).");
        }
        if ((this.operandA.matches(ROMAN_REGEX) && this.operandB.matches(ROMAN_REGEX)) || (this.operandA.matches(ARAB_REGEX) && this.operandB.matches(ARAB_REGEX))) {
            return true;
        } else {
            throw new IllegalArgumentException("[-] Калькулятор умеет работать только с арабскими или римскими цифрами одновременно.");
        }
    }

    private String getNumeralType() {
        if (this.operandA.matches(ROMAN_REGEX)) {
            return "roman";
        }
        if (this.operandA.matches(ARAB_REGEX)) {
            return "arabic";
        }
        return "error";
    }

}
