package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Wordle {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(System.out, true);
            PrintWriter errorWriter = new PrintWriter(new FileWriter("errors.txt", true));
            ) {

            WordleDictionary wordleDictionary = WordleDictionaryLoader.downloadDictionaryFromFile("words_ru.txt", 5);

            WordleGame wordleGame = new WordleGame(wordleDictionary.chooseTheWord(), 6, wordleDictionary);

            wordleGame.startTheGame(scanner, out, errorWriter);

        } catch (IOException
                exception) {

            try (FileWriter fileWriter = new FileWriter("errors.txt", true);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {

                printWriter.println("Ошибка:");
                printWriter.println(exception.getMessage());
                printWriter.println("-----------------------");

            } catch (IOException ignored) {
            }
        }
    }
}
