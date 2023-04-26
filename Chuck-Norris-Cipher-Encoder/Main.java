package chucknorris;
import java.util.Scanner;

/* This is an application that takes in a String and, depending on the users choice, will encode the String
or decode the String.
E.g. input = "1000000" output = '@'
E.g. input "@" output = '1000000' */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean userQuit = false;
        while (!userQuit) {
            System.out.println("\nPlease input operation (encode/decode/exit):");
            String userChoice = scanner.nextLine();
            switch (userChoice) {
                case "encode" -> {
                    System.out.println("Input string:");
                    char[] userInput = scanner.nextLine().toCharArray();
                    System.out.println("Encoded string:");
                    encode(userInput);
                }
                case "decode" -> {
                    System.out.println("Input encoded string:");
                    String userInput = scanner.nextLine();
                    boolean isValid = isValidInput(userInput);
                    if (isValid) {
                        System.out.println("Decoded string:");
                        decode(userInput.toCharArray());
                    } else {
                        System.out.println("Encoded string is not valid");
                    }
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    userQuit = true;
                }
                default -> System.out.printf("There is no '%s' operation", userChoice);
            }
        }
    }

    // ===============
    // Private Methods
    // ===============

    /**
     * Checks whether input is valid
     * @param checkInput String validation
     * @return Boolean
     */

    // This validates the String is correctly formatted and contains the correct digits for decoding.
    private static boolean isValidInput(String checkInput) {
        if (checkInput.matches(".*[a-zA-Z].*") || checkInput.length() % 7 == 0) {
            return false;
        }
        String[] splitInput = checkInput.split(" ");
        for (int i = 0; i < splitInput.length; i++) {
            if (i % 2 == 0 && splitInput[i].length() > 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Encodes the inputted Unary Code, Prints result to console
     * @param toEncode String to encode
     */
    private static void encode(char[] toEncode) {
        StringBuilder answer = new StringBuilder();
        StringBuilder convertToBinary = new StringBuilder();
        char currentNum = '9';

        // As we're working with 7 bits, add a 0 if the length less than 7.
        for (char c : toEncode) {
            if (Integer.toBinaryString(c).length() < 7) {
                convertToBinary.append("0");
            }
            convertToBinary.append(Integer.toBinaryString(c));
        }

        // Encoding happens here.
        for (int i = 0; i < convertToBinary.length(); i++) {
            if (convertToBinary.charAt(i) != currentNum) {
                answer.append(" ");
            }
            if (convertToBinary.charAt(i) == '0') {
                if (currentNum != convertToBinary.charAt(i)) {
                    currentNum = convertToBinary.charAt(i);
                    answer.append("00 0");
                } else {
                    answer.append("0");
                }
            } else {
                if (currentNum != convertToBinary.charAt(i)) {
                    currentNum = convertToBinary.charAt(i);
                    answer.append("0 0");
                } else {
                    answer.append("0");
                }
            }
        }
        System.out.println(answer.toString().trim());
    }

    /**
     * Decodes String from Cipher, Prints result to console
     * @param toDecode String to decode
     */
    private static void decode(char[] toDecode) {
        String[] splitString = String.valueOf(toDecode).split(" ");
        StringBuilder answer = new StringBuilder();
        char currentNum = '9';

        // Decoding happens here.
        for (int i = 0; i < splitString.length; i++) {
            if (i % 2 == 0) {
                if (splitString[i].length() == 1) {
                    currentNum = '1';
                } else {
                    currentNum = '0';
                }
            } else {
                for (int j = 0; j < splitString[i].length(); j++) {
                    if (currentNum == '0') {
                        answer.append('0');
                    } else {
                        answer.append('1');
                    }
                }
            }
        }
        // We are working with 7 bits, so we split the answer every 7 characters.
        if (answer.length() > 7) {
            String[] splitBinary = answer.toString().split("(?<=\\G.{7})");
            for (String s : splitBinary) {
                System.out.print((char) Integer.parseInt(s, 2));
            }
        } else {
            System.out.println((char) Integer.parseInt(answer.toString(), 2));
        }
    }
}

