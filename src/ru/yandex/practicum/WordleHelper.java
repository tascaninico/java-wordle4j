package ru.yandex.practicum;

import java.util.*;

public class WordleHelper {

    private final List<String> dictionary;

    private final Set<Character> requiredLetters = new HashSet<>();

    private final Set<Character> bannedLetters = new HashSet<>();

    private final Map<Integer, Character> fixedPositions = new HashMap<>();

    private final Map<Integer, Set<Character>> wrongPositions = new HashMap<>();

    private final Random random = new Random();

    public WordleHelper(List<String> dictionary){
        this.dictionary = dictionary;
    }

    public void addGuess(String guess, String result){

        for (int i = 0; i < guess.length(); ++i){

            char letter = guess.charAt(i);
            char mark = result.charAt(i);

            if (mark == '+'){
                requiredLetters.add(letter);
                fixedPositions.put(i, letter);
            } else if (mark == '^'){
                requiredLetters.add(letter);

                if (!wrongPositions.containsKey(i)){
                   wrongPositions.put(i, new HashSet<>());
                }

                wrongPositions.get(i).add(letter);

            } else if (mark == '-') {

                if (!requiredLetters.contains(letter)){
                    bannedLetters.add(letter);
                }
            }
        }
    }

    private boolean isSuitable(String word){

        for (char bannedLetter : bannedLetters){

            if (word.indexOf(bannedLetter) != -1)
                return false;
        }

        for (char requiredletter: requiredLetters){

            if (word.indexOf(requiredletter) == -1){
                return false;
            }
        }

        for (Map.Entry<Integer, Character> entry : fixedPositions.entrySet()){

            int position = entry.getKey();
            char letter = entry.getValue();

            if (word.charAt(position) != letter)
                return false;
        }

        for (Map.Entry<Integer, Set<Character>> entry : wrongPositions.entrySet()){

            int position = entry.getKey();

            Set<Character> letters = entry.getValue();

            for (char letter : letters){
                if (word.charAt(position) == letter){
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> getPossibleWords() {

        List<String> possibleWords = new ArrayList<>();

        for (String word : dictionary) {

            if (isSuitable(word)) {
                possibleWords.add(word);
            }
        }

        return possibleWords;
    }

    public String getHint() {

        List<String> possibleWords = getPossibleWords();

        if (possibleWords.isEmpty()) {
            return dictionary.get(random.nextInt(dictionary.size()));
        }

        int randomIndex = random.nextInt(possibleWords.size());

        return possibleWords.get(randomIndex);
    }

}
