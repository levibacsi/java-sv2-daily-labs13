package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Transfers {
    private List<String> readFromFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalStateException("File not found", ioe);
        }
    }

    public Map<String, Integer> transactionSorter(){
        List<String > input = readFromFile(Paths.get("src/test/resources/transfers.csv"));
        Map<String, Integer> transfers = new TreeMap<>();
        for (String line: input) {
            String[] temp = line.split(",");
            String payer = temp[0];
            String receiver = temp[1];
            int sum = Integer.parseInt(temp[2]);

            if (!transfers.containsKey(payer)) {
                transfers.put(payer, -1 * sum);
            } else {
                transfers.put(payer, transfers.get(payer) - sum);
            }

            if (!transfers.containsKey(receiver)) {
                transfers.put(receiver, sum);
            } else {
                transfers.put(receiver, transfers.get(receiver) + sum);
            }
        }
        return transfers;
    }

    public static void main(String[] args) {
        Transfers transfers = new Transfers();
        System.out.println(transfers.transactionSorter());
        System.out.println(transfers.transactionSorter().values().stream().mapToInt(c-> c).max());
        System.out.println(transfers.transactionSorter().values().stream().mapToInt(c-> c).min());
    }
}

