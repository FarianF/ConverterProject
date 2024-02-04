import java.util.Scanner;
import java.util.Arrays;

public class NumberConverter {
    private int[] digits;
    private int base;

    public NumberConverter(int number, int base) {
        if (base < 1 || base > 64) {
            throw new IllegalArgumentException("Invalid base. Please enter a base between 1 and 64.");
        }

        String numberAsString = Integer.toString(number);
        validateNumber(numberAsString, base);

        digits = new int[numberAsString.length()];
        for (int i = 0; i < numberAsString.length(); i++) {
            String single = numberAsString.substring(i, i + 1);
            int d = Integer.parseInt(single);
            validateDigit(d, base);
            digits[i] = d;
        }
        this.base = base;
    }

    private void validateNumber(String number, int base) {
        int maxDigit;
        if (base > 36) {
            maxDigit = 63; // Using characters for base > 36
        } else {
            maxDigit = base - 1;
        }

        for (char c : number.toCharArray()) {
            int digit = Character.getNumericValue(c);
            if (digit > maxDigit) {
                throw new IllegalArgumentException("Invalid digit for base " + base + ": " + digit);
            }
        }
    }

    private void validateDigit(int digit, int base) {
        if (digit >= base) {
            throw new IllegalArgumentException("Invalid digit for base " + base + ": " + digit);
        }
    }

    public String displayOriginalNumber() {
        StringBuilder o = new StringBuilder();
        for (int digit : digits) {
            if (base > 36) {
                o.append(getBase64Char(digit));
            } else {
                o.append(digit);
            }
        }
        o.append("\n");
        return o.toString();
    }

    private char getBase64Char(int value) {
        if (value >= 0 && value <= 25) {
            return (char) ('A' + value);
        } else if (value >= 26 && value <= 51) {
            return (char) ('a' + (value - 26));
        } else if (value >= 52 && value <= 61) {
            return (char) ('0' + (value - 52));
        } else if (value == 62) {
            return '+';
        } else {
            return '/';
        }
    }

    public int[] getDigits() {
        return digits;
    }

    public int[] convertToDecimal() {
        int decimalValue = 0;
        int power = digits.length - 1;

        for (int digit : digits) {
            decimalValue += digit * Math.pow(base, power);
            power--;
        }

        return new int[]{decimalValue};
    }

    public int[] convertToBinary() {
        if (base == 2) {
            return digits.clone(); // Already in binary
        }

        int decimalValue = convertToDecimal()[0];
        return convertFromDecimal(decimalValue, 2);
    }

    public int[] convertToOctal() {
        if (base == 8) {
            return digits.clone(); // Already in octal
        }

        int decimalValue = convertToDecimal()[0];
        return convertFromDecimal(decimalValue, 8);
    }

    public int[] convertToHex() {
        if (base == 16) {
            return digits.clone(); // Already in hexadecimal
        }

        int decimalValue = convertToDecimal()[0];
        return convertFromDecimal(decimalValue, 16);
    }

    public int[] convertToBase64() {
        if (base == 64) {
            return digits.clone(); // Already in Base64
        }

        int decimalValue = convertToDecimal()[0];
        return convertFromDecimal(decimalValue, 64);
    }

    private int[] convertFromDecimal(int decimalValue, int targetBase) {
        int temp = decimalValue;
        int length = 0;

        while (temp > 0) {
            temp /= targetBase;
            length++;
        }

        int[] result = new int[length];
        temp = decimalValue;

        for (int i = length - 1; i >= 0; i--) {
            result[i] = temp % targetBase;
            temp /= targetBase;
        }

        return result;
    }
}