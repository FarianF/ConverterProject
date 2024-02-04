import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
class ConverterRunner {
    public static void main(String[] args) {
        System.out.println("Welcome to the Number Converter!");
        System.out.println("--------------------------------");
        System.out.print("Enter the base of your number (2, 8, 10, 16, or 64): ");

        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        int base = Integer.parseInt(choice);

        int n;
        if (base == 10) {
            System.out.print("Enter your Base 10 number: ");
            String number = s.nextLine();
            n = Integer.parseInt(number);
        } else {
            System.out.print("Enter your number: ");
            String number = s.nextLine();
            n = Integer.parseInt(number);
        }

        System.out.print("Enter the target base for conversion (1-64): ");
        int targetBase = Integer.parseInt(s.nextLine());

        s.close();

        NumberConverter nc = new NumberConverter(n, base);
        int[] digits = nc.getDigits();
        System.out.println("\nDigit array: " + Arrays.toString(digits));
        System.out.println("Number: " + nc.displayOriginalNumber());

        int[] decimalResult = nc.convertToDecimal();
        int[] binaryResult = nc.convertToBinary();
        int[] octalResult = nc.convertToOctal();
        int[] hexResult = nc.convertToHex();
        int[] base64Result = nc.convertToBase64();

        System.out.println("Decimal number: " + Arrays.toString(decimalResult));
        System.out.println("Binary number: " + Arrays.toString(binaryResult));
        System.out.println("Octal number: " + Arrays.toString(octalResult));
        System.out.println("Hexadecimal number: " + Arrays.toString(hexResult));
        System.out.println("Base64 number: " + Arrays.toString(base64Result));
    }
}