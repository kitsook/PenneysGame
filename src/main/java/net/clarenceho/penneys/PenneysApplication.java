package net.clarenceho.penneys;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

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
        PenneysGame.allPossibleChoices.stream().forEach(firstPlayer -> {

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
        for (Choice c1 : PenneysGame.allPossibleChoices) {
            Map<Choice, Integer> result = new HashMap<>();
            for (Choice c2 : PenneysGame.allPossibleChoices) {
                if (c1 == c2) {
                    continue;
                }
                int c2WinCount = 0;

                for (int i = 0; i < 1000000; i++) {
                    PenneysGame game = new PenneysGame(c1, c2);
                    if (game.doesSecondPlayerWin()) {
                        c2WinCount += 1;
                    }
                }

                result.put(c2, c2WinCount);
            }

            // sort the result and see which pattern is the best against c1
            Stream<Map.Entry<Choice, Integer>> sorted = result.entrySet().stream().sorted(Map.Entry.comparingByValue());
            Choice best = sorted.skip(result.size()-1).findFirst().get().getKey();
            System.err.println(String.format("Best choice against %s is %s", c1, best));
        }
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
