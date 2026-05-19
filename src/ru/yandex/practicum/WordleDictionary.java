package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private List<String> words = new ArrayList<>();

    public boolean addWord(String word) {
        if (words.contains(word)){
            return false;
        } else {
            words.add(word);
            return true;
        }
    }


    public void printAllWords() {

        System.out.println(words.size());
        for (String word : words){
            System.out.println(word);
        }
    }

    public boolean containsWord(String word) {
        return words.contains(word);
    }

    public int size() {
        return words.size();
    }

    public String get(int num) {
        return words.get(num);
    }

    public String chooseTheWord() {

        Random random = new Random();

        return words.get(random.nextInt(words.size()));
    }

    public List<String> getWords() {
        return words;
    }
}
