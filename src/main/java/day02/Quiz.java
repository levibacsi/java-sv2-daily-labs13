package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Quiz {
    private List<String> input = new ArrayList<>();
    String solution;
    Map<String, String> answers = new HashMap<>();

    public void reader (Path path){
        try {
            input = Files.readAllLines(path);
        }
        catch (IOException ioe){
            throw new IllegalStateException("File not found.");
        }
    }


    private void solutionSorter (){
        solution = input.get(0);
        for (int i = 1; i < input.size(); i++){
            String[] temp = input.get(i).split(" ");
            String contestant = temp[0];

            if (answers.containsKey(contestant)){
                answers.put(contestant, (answers.get(contestant) + temp[1]));
            } else {
                answers.put(contestant, temp[1]);
            }
        }
    }

    public boolean evaluator (String code, int question){
        answers.clear();
        reader(Paths.get("src/test/resources/result.txt"));
        solutionSorter();
        if (answers.get(code).charAt(question - 1) == solution.charAt(question - 1)){
            return true;
        }
        return false;
    }


    public String winnerCalculator() {
        answers.clear();
        reader(Paths.get("src/test/resources/result.txt"));
        solutionSorter();
        String winner = "No winner";
        int maxScore = 0;
        for (String a: answers.keySet()){
            int score = 0;
            for (int i = 0; i < answers.get(a).length(); i++){
                if (answers.get(a).charAt(i) == solution.charAt(i)){
                    score += i+1;
                } else {
                    if (answers.get(a).charAt(i) != 'X'){
                        score -=2;
                    }
                }
            }
            if (score > maxScore){
                maxScore = score;
                winner = a;
            }
        }
        return winner;
    }


    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        System.out.println(quiz.evaluator("BD452", 2));
        System.out.println(quiz.evaluator("AB123", 1));
        System.out.println(quiz.winnerCalculator());

    }
}