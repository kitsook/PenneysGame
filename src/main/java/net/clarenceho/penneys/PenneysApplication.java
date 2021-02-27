package net.clarenceho.penneys;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.clarenceho.penneys.model.PenneysGame;
import net.clarenceho.penneys.model.PenneysGame.Choice;

@SpringBootApplication
public class PenneysApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PenneysApplication.class, args);

        // test out all scenarios list in wiki in which player 2 should have advantage
        System.out.println("Testing out cases shown in wiki where player 2 has higher chance of winning:");
        PenneysGame.allPossibleChoices.parallelStream().forEach(firstPlayer -> {

            Choice secondPlayer = PenneysGame.bestChoiceToBetAgainst(firstPlayer);

            int firstPlayerWins = 0;
            int secondPlayerWins = 0;
            // test each scenario multiple times
            for (int i = 0; i < 1000000; i++) {
                PenneysGame game = new PenneysGame(firstPlayer, secondPlayer);
                if (game.doesSecondPlayerWin()) {
                    secondPlayerWins += 1;
                } else {
                    firstPlayerWins += 1;
                }
            }
            System.out.println(String.format("Player 1 %s wins %d times. Player 2 %s wins %d times.", firstPlayer, firstPlayerWins, secondPlayer, secondPlayerWins));
        });

        System.out.println("Loop all combinations to find best choice:");
        PenneysGame.allPossibleChoices.parallelStream().forEach(c1 -> {
            Map<Choice, Integer> results = new HashMap<>();
            for (Choice c2 : PenneysGame.allPossibleChoices) {
                if (c1 == c2) {
                    // player 2 can't pick the same pattern as player 1
                    continue;
                }

                int c2WinCount = 0;
                for (int i = 0; i < 1000000; i++) {
                    PenneysGame game = new PenneysGame(c1, c2);
                    if (game.doesSecondPlayerWin()) {
                        c2WinCount += 1;
                    }
                }
                results.put(c2, c2WinCount);
            }

            // sort the result and see which pattern is the best against c1
            List<Map.Entry<Choice, Integer>> sorted = results.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
            Choice bestChoice = sorted.get(sorted.size()-1).getKey();
            System.err.println(String.format("Best choice against %s is %s", c1, bestChoice));
        });
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
