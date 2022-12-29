package readability;

import java.util.*;
import java.util.regex.Pattern;

public class WorkWithText {
    String text;
    Map<Integer, Integer> table = new HashMap<>();
    private final int polySyllables;
    private final int syllables;
    private double average;

    public WorkWithText(String text) {
        this.text = text;
        table.put(1, 6);
        table.put(2, 7);
        table.put(3, 8);
        table.put(4, 9);
        table.put(5, 10);
        table.put(6, 11);
        table.put(7, 12);
        table.put(8, 13);
        table.put(9, 14);
        table.put(10, 15);
        table.put(11, 16);
        table.put(12, 17);
        table.put(13, 18);
        table.put(14, 22);
        this.polySyllables = (int) Arrays.stream(text.split(" ")).filter(word
                -> Pattern.compile("[aeyuio]+[bcdfghj-np-tvwxz]*(e\\\\b)?").matcher(word).results().count() >= 3).count();
        this.syllables = (int) Pattern.compile("[aeyuio]+[bcdfghj-np-tvwxz]*(e\\\\b)?").matcher(text).results().count();
    }

    public void printText() {
        System.out.println("The text is:\n" + text + "\n");
    }

    public int getSyllables() {
        return syllables;
    }

    public int getPolySyllables() {
        return polySyllables;
    }

    public int findAmountOfWords() {
        return text.split(" ").length;
    }

    public int findAmountOfSentences() {
        return text.split("[!.?]").length;
    }

    public int findAmountOfCharacters() {
        return text.replace(" ", "").length();
    }

    public double findScore() {
        return (4.71 * findAmountOfCharacters() / text.split(" ").length) + 0.5 * text.split(" ").length / text.split("[!.?]").length - 21.43;
    }

    public void findAge() {
        double score = (4.71 * findAmountOfCharacters() / text.split(" ").length) + 0.5 * text.split(" ").length / text.split("[!.?]").length - 21.43;
        for (var entry : table.entrySet()) {
            if (entry.getKey() == (int)Math.ceil(score)) {
                int tmp = entry.getValue();
                average += tmp;
                System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).\n", score, tmp);
                break;
            }
        }
    }

    public void fleschKincaidReadabilityTests() {
        double score = 0.39 * findAmountOfWords() / findAmountOfSentences() + 11.8 * syllables / findAmountOfWords() - 15.59;
        for (var entry : table.entrySet()) {
            if (entry.getKey() == Math.ceil(score)) {
                int tmp = entry.getValue();
                average += tmp;
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).\n", score, tmp);
                break;
            }
        }
    }

    public void smog() {
        double score = 1.043 * Math.sqrt(polySyllables * 30.0 / findAmountOfSentences()) + 3.1291;
        for (var entry : table.entrySet()) {
            if (entry.getKey() == Math.floor(score)) {
                int tmp = entry.getValue();
                average += tmp;
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).\n", score, tmp);
                break;
            }
        }
    }

    public void colemanLiau() {
        double score = 0.0588 * findAmountOfCharacters() / findAmountOfWords() * 100 - 0.296 * findAmountOfSentences() / findAmountOfWords() * 100 - 15.8;
        for (var entry : table.entrySet()) {
            if (entry.getKey() == Math.floor(score)) {
                int tmp = entry.getValue();
                average += tmp;
                System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds).\n", score, tmp);
                break;
            }
        }
    }

    public void printAverageAge() {
        System.out.printf("This text should be understood in average by %.2f-year-olds.\n", average / 4);
    }
}
