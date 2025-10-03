package JavaInternPrograms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * TextFileAnalyzer - A program to analyze a text file.
 * Counts words, lines, and characters, and allows searching for specific words.
 * Demonstrates file I/O, string manipulation, and exception handling.
 */
public class TextFileAnalyzer {

    public static void analyzeFile(String filePath) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();

                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }

            System.out.println("\n===== File Analysis =====");
            System.out.println("Total Lines: " + lineCount);
            System.out.println("Total Words: " + wordCount);
            System.out.println("Total Characters: " + charCount);

        } catch (IOException e) {
            System.out.println("Error: Unable to read file. " + e.getMessage());
        }
    }

    public static void searchWord(String filePath, String searchWord) {
        int occurrences = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(searchWord)) {
                        occurrences++;
                    }
                }
            }

            System.out.println("\nThe word \"" + searchWord + "\" occurred " + occurrences + " times in the file.");

        } catch (IOException e) {
            System.out.println("Error: Unable to search in file. " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Text File Analyzer =====");
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        boolean running = true;

        while (running) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Analyze File (lines, words, characters)");
            System.out.println("2. Search for a Word");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    analyzeFile(filePath);
                    break;
                case 2:
                    System.out.print("Enter word to search: ");
                    String word = scanner.nextLine();
                    searchWord(filePath, word);
                    break;
                case 3:
                    System.out.println("Exiting Analyzer... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
