package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {

    public static WordleDictionary downloadDictionaryFromFile(String filename, int theLengthOfWord) {

        WordleDictionary wordleDictionary = new WordleDictionary();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(filename), StandardCharsets.UTF_8)) {

            String word;
            while((word = bufferedReader.readLine()) != null) {
                if (word.length() == theLengthOfWord) {
                    wordleDictionary.addWord(word);
                }
            }

        } catch (IOException exc) {
            throw new RuntimeException("Возникли проблемы с чтением данных из словаря");
        }

        return wordleDictionary;
    }

}
