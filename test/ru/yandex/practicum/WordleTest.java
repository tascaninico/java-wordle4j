package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordleTest {

    @Test
    void shouldPrintHintWhenInputIsEmpty() throws IOException {
        Scanner scanner = new Scanner("\nштаны\n");

        StringWriter output = new StringWriter();
        PrintWriter out = new PrintWriter(output);

        StringWriter errors = new StringWriter();
        PrintWriter errorWriter = new PrintWriter(errors);

        WordleDictionary wordleDictionary = WordleDictionaryLoader.downloadDictionaryFromFile("words_ru.txt", 5);

        WordleGame wordleGame = new WordleGame("штаны", 6, wordleDictionary);

        wordleGame.startTheGame(scanner, out, errorWriter);

        out.flush();
        errorWriter.flush();

        assertTrue(output.toString().contains("Подсказка: "));

    }

    @Test
    void shouldPrintErrorWhenWordIsTooShort() throws IOException {
        Scanner scanner = new Scanner("лес\nлось\nштаны\n");

        StringWriter output = new StringWriter();
        PrintWriter out = new PrintWriter(output);

        StringWriter errors = new StringWriter();
        PrintWriter errorWriter = new PrintWriter(errors);

        WordleDictionary wordleDictionary = WordleDictionaryLoader.downloadDictionaryFromFile("words_ru.txt", 5);

        WordleGame wordleGame = new WordleGame("штаны", 6, wordleDictionary);

        wordleGame.startTheGame(scanner, out, errorWriter);

        out.flush();
        errorWriter.flush();

        assertTrue(errors.toString().contains("Ошибка:"));
        assertTrue(errors.toString().contains("Число букв в веденном слове меньше 5"));

    }

    @Test
    void shouldPrintErrorWhenWordIsTooLong() throws IOException {
        Scanner scanner = new Scanner("человек\nштаны\n");

        StringWriter output = new StringWriter();
        PrintWriter out = new PrintWriter(output);

        StringWriter errors = new StringWriter();
        PrintWriter errorWriter = new PrintWriter(errors);

        WordleDictionary wordleDictionary = WordleDictionaryLoader.downloadDictionaryFromFile("words_ru.txt", 5);

        WordleGame wordleGame = new WordleGame("штаны", 6, wordleDictionary);

        wordleGame.startTheGame(scanner, out, errorWriter);

        out.flush();
        errorWriter.flush();

        assertTrue(errors.toString().contains("Ошибка:"));
        assertTrue(errors.toString().contains("Число букв в веденном слове больше 5"));

    }

    @Test
    void shouldPrintWordThatIsNotInTheDictionary() throws IOException {
        Scanner scanner = new Scanner("абвгд\nштаны\n");

        StringWriter output = new StringWriter();
        PrintWriter out = new PrintWriter(output);

        StringWriter errors = new StringWriter();
        PrintWriter errorWriter = new PrintWriter(errors);

        WordleDictionary wordleDictionary = WordleDictionaryLoader.downloadDictionaryFromFile("words_ru.txt", 5);

        WordleGame wordleGame = new WordleGame("штаны", 6, wordleDictionary);

        wordleGame.startTheGame(scanner, out, errorWriter);

        out.flush();
        errorWriter.flush();

        assertTrue(errors.toString().contains("Ошибка:"));
        assertTrue(errors.toString().contains("Введенного слова нет в словаре"));
    }

    @Test
    void shouldPrintWordThatIsInTheDictionary() throws IOException {
        Scanner scanner = new Scanner("волна\nштаны\n");

        StringWriter output = new StringWriter();
        PrintWriter out = new PrintWriter(output);

        StringWriter errors = new StringWriter();
        PrintWriter errorWriter = new PrintWriter(errors);

        WordleDictionary wordleDictionary = WordleDictionaryLoader.downloadDictionaryFromFile("words_ru.txt", 5);

        WordleGame wordleGame = new WordleGame("штаны", 6, wordleDictionary);

        wordleGame.startTheGame(scanner, out, errorWriter);

        out.flush();
        errorWriter.flush();

        assertTrue(output.toString().contains("---+^"));
    }

    @Test
    void shouldPrintMoreThanSixWords() throws IOException {
        Scanner scanner = new Scanner("волна\nчувак\nжесть\nптица\nабхаз\nабвер\n");

        StringWriter output = new StringWriter();
        PrintWriter out = new PrintWriter(output);

        StringWriter errors = new StringWriter();
        PrintWriter errorWriter = new PrintWriter(errors);

        WordleDictionary wordleDictionary = WordleDictionaryLoader.downloadDictionaryFromFile("words_ru.txt", 5);

        WordleGame wordleGame = new WordleGame("штаны", 6, wordleDictionary);

        wordleGame.startTheGame(scanner, out, errorWriter);

        out.flush();
        errorWriter.flush();

        assertTrue(output.toString().contains("Проигрыш"));
    }

    @Test
    void shouldPrintRightAnswer() throws IOException {
        Scanner scanner = new Scanner("волна\nчувак\nштаны\n");

        StringWriter output = new StringWriter();
        PrintWriter out = new PrintWriter(output);

        StringWriter errors = new StringWriter();
        PrintWriter errorWriter = new PrintWriter(errors);

        WordleDictionary wordleDictionary = WordleDictionaryLoader.downloadDictionaryFromFile("words_ru.txt", 5);

        WordleGame wordleGame = new WordleGame("штаны", 6, wordleDictionary);

        wordleGame.startTheGame(scanner, out, errorWriter);

        out.flush();
        errorWriter.flush();

        assertTrue(output.toString().contains("Победа"));
    }
}
