package ru.yandex.practicum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordleGame {

    private final String answer;

    private final int steps;

    private final WordleDictionary dictionary;

    private final WordleHelper wordleHelper;

    public WordleGame(String answer, int steps, WordleDictionary dictionary) {
        this.answer = answer;
        this.steps = steps;
        this.dictionary = dictionary;
        this.wordleHelper = new WordleHelper(dictionary.getWords());
    }

    public String compareToAnswer(String word) {

        StringBuilder stringBuilder = new StringBuilder();

        if (word.equals(answer))
            return "Победа";

        for (int i = 0; i < answer.length(); ++i) {

            if (word.charAt(i) == answer.charAt(i)) {
                stringBuilder.append("+");
                continue;
            }

            if (answer.indexOf(word.charAt(i)) != -1) {
                stringBuilder.append("^");
            } else {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }

    public void startTheGame(Scanner scanner, PrintWriter out, PrintWriter errorWriter) throws IOException {

        String guess = "";

        for (int i = 0; i < steps; ++i) {

            do {
                try {
                    guess = scanner.nextLine();
                    if (guess.isEmpty()) {
                        out.println("Подсказка: " + wordleHelper.getHint());
                        continue;
                    }

                    if (guess.length() < 5) {
                        throw new NumberOfLettersBelowFiveException("Число букв в веденном слове меньше 5");
                    } else if (guess.length() > 5) {
                        throw new NumberOfLettersAboveFiveException("Число букв в веденном слове больше 5");
                    } else if (!dictionary.containsWord(guess)) {
                        throw new NoWordInDictionaryException("Введенного слова нет в словаре");
                    }

                } catch (NoWordInDictionaryException | NumberOfLettersBelowFiveException
                | NumberOfLettersAboveFiveException exc) {

                            errorWriter.println("Ошибка:");
                            errorWriter.println(exc.getMessage());
                            errorWriter.println("-----------------------");

                }

            } while (guess.length() != 5 || !dictionary.containsWord(guess));


            String hint = compareToAnswer(guess);
            wordleHelper.addGuess(guess, hint);
            out.println(hint);
            if (compareToAnswer(guess).equals("Победа")) {
                return;
            }

        }
        out.println("Проигрыш. Загаданное слово:" + answer);
    }

}
