package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        StringBuilder text = new StringBuilder();
        while (scanner.hasNext()) {
            text.append(scanner.nextLine());
        }
        WorkWithText txt = new WorkWithText(text.toString());
        scanner.close();
        txt.printText();
        System.out.printf("Words: %d\n", txt.findAmountOfWords());
        System.out.printf("Sentences: %d\n", txt.findAmountOfSentences());
        System.out.printf("Characters: %d\n", txt.findAmountOfCharacters());
        System.out.printf("The score is: %.2f\n", txt.findScore());
        System.out.printf("Syllables: %d\n", txt.getSyllables());
        System.out.printf("Polysyllables: %d\n", txt.getPolySyllables());
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        System.out.println();
        Scanner scanner1 = new Scanner(System.in);
        String input = scanner1.nextLine();
        switch (input) {
            case "ARI" -> txt.findAge();
            case "FK" -> txt.fleschKincaidReadabilityTests();
            case "SMOG" -> txt.smog();
            case "CL" -> txt.colemanLiau();
            case "all" -> {
                txt.findAge();
                txt.fleschKincaidReadabilityTests();
                txt.smog();
                txt.colemanLiau();
            }
            default -> System.out.println("false");
        }
        txt.printAverageAge();
    }

}
