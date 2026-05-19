package ru.yandex.practicum;

public class NoWordInDictionaryException extends Exception {

    public NoWordInDictionaryException() {
        super();
    }

    public NoWordInDictionaryException(String message) {
        super(message);
    }

}
