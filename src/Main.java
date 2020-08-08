import jm_program.calculator.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (calculator.isWorking) {
            System.out.print(">>> ");
            String operation = input.nextLine();
            calculator.calculate(operation);
        }

    }
}
