package asciimirror;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// This project takes user input and prints out the original copy and an exact mirror copy to console.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> mirrorCopy = new ArrayList<>();
        StringBuilder tempCopy = new StringBuilder();
        StringBuilder mirroredTempCopy = new StringBuilder();
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<String> mirroredAnswer = new ArrayList<>();

        // This is used, so we know how much whitespace to append to the end of the string
        int biggestStringSize = 0;

        System.out.println("Input the file path:");
        try {
            Scanner fileReader = new Scanner(new File(scanner.nextLine()));
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                mirrorCopy.add(line);
                biggestStringSize = Math.max(line.length(), biggestStringSize);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        for (String s : mirrorCopy) {
            // Clear both variables in order to start fresh
            tempCopy.delete(0, tempCopy.length());
            mirroredTempCopy.delete(0, mirroredTempCopy.length());
            for (int j = 0; j < biggestStringSize; j++) {
                if (j < s.length()) {
                    tempCopy.append(s.charAt(j));
                    // We send the char to our mirrorChar method to reverse the char if needed.
                    mirroredTempCopy.append(mirrorChar(s.charAt(j)));
                }
                if (j >= s.length()) {
                    tempCopy.append(" ");
                    mirroredTempCopy.append(" ");
                }
                if (j == biggestStringSize - 1) {
                    // Add the reversed Sb to our ArrayList
                    mirroredAnswer.add(String.valueOf(mirroredTempCopy.reverse()));
                    // Append " | " to separate the original and mirrored.
                    tempCopy.append(" | ");
                }
            }
            answer.add(String.valueOf(tempCopy));
        }

        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i) + mirroredAnswer.get(i));
        }
    }

    /**
     * Returns a reversed character if it's detected, or the original if not reversed.
     * @param c the char that may need reversing.
     * @return the original or reversed char.
     */
    private static char mirrorChar(char c) {
        switch (c) {
            case '/' -> c = '\\';
            case '\\' -> c = '/';
            case '[' -> c = ']';
            case ']' -> c = '[';
            case '(' -> c = ')';
            case ')' -> c = '(';
            case '<' -> c = '>';
            case '>' -> c = '<';
        }
        return c;
    }
}