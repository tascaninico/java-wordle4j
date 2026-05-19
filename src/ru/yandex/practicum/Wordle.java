package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
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

            } catch (IOException ignored) {}

        }
    }

}
