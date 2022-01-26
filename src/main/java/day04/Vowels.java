package day04;

import java.util.HashMap;
import java.util.Map;

public class Vowels {
    public Map<Character, Integer> vowelCounter (String word) {
        Map<Character, Integer> vowels = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a' ||
                    word.charAt(i) == 'e' ||
                    word.charAt(i) == 'i' ||
                    word.charAt(i) == 'o' ||
                    word.charAt(i) == 'u') {

                if (!vowels.containsKey(word.charAt(i))) {
                    vowels.put(word.charAt(i), 1);
                } else {
                    vowels.put(word.charAt(i), vowels.get(word.charAt(i)) + 1);
                }
            }
        }
        return vowels;
    }

    public static void main(String[] args) {
        Vowels vowels = new Vowels();
        System.out.println(vowels.vowelCounter("babaruha"));
    }
}
