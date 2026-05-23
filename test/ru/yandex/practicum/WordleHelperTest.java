package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WordleHelperTest {

    @Test
    void shouldReturnAllWordsWhenNoGuessesAdded() {
        List<String> dictionary = List.of("штаны", "волна", "банан");

        WordleHelper helper = new WordleHelper(dictionary);

        List<String> result = helper.getPossibleWords();

        assertEquals(dictionary, result);
    }

    @Test
    void shouldRemoveWordsWithBannedLetters(){
        List<String> dictionary = List.of(
                "штаны",
                "волна",
                "банан",
                "груша",
                "битюг"
        );

        WordleHelper helper = new WordleHelper(dictionary);

        helper.addGuess("волна", "-----");

        List<String> result = helper.getPossibleWords();

        assertFalse(result.contains("волна"));
        assertFalse(result.contains("банан"));
        assertFalse(result.contains("груша"));
        assertFalse(result.contains("штаны"));
        assertTrue(result.contains("битюг"));

    }

    @Test
    void shouldKeepOnlyWordsWithFixedLetterAtPosition() {
        List<String> dictionary = List.of(
                "штаны",
                "школа",
                "банан",
                "груша"
        );

        WordleHelper helper = new WordleHelper(dictionary);

        helper.addGuess("штаны", "+----");

        List<String> result = helper.getPossibleWords();

        assertFalse(result.contains("школа"));
        assertFalse(result.contains("банан"));
        assertFalse(result.contains("груша"));
    }

    @Test
    void shouldKeepWordsWithRequiredLetterButNotAtWrongPosition() {
        List<String> dictionary = List.of(
                "волна",
                "зверь",
                "банан"
        );

        WordleHelper helper = new WordleHelper(dictionary);

        helper.addGuess("волна", "^----");

        List<String> result = helper.getPossibleWords();

        assertFalse(result.contains("волна"));
        assertTrue(result.contains("зверь"));
        assertFalse(result.contains("банан"));
    }
}
