package net.clarenceho.penneys.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
public class PenneysGame {

    private Choice firstPlayer, secondPlayer;

    public static Choice bestChoiceToBetAgainst(Choice givenChoice) {
        return beatenBy.get(givenChoice);
    }

    public PenneysGame(Choice firstPlayer, Choice secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    /**
     * Simulate a series of coin flips and see if second player wins
     * @return returns true if second player wins
     */
    public boolean doesSecondPlayerWin() {

        StringBuilder latestThree = new StringBuilder();

        CoinFlip.flips()
            .takeWhile(r ->
                !firstPlayer.rawString().equals(latestThree.toString()) &&
                !secondPlayer.rawString().equals(latestThree.toString())
            )
            .forEach(r -> {
                if (latestThree.length() < 3) {
                    latestThree.append(r.toString());
                } else {
                    latestThree.append(r.toString()).delete(0, 1);
                }
            });

        return secondPlayer.rawString().equals(latestThree.toString());
    }

    @Getter @EqualsAndHashCode
    public static class Choice {
        private CoinFlip.Result first, second, third;
        private String rawString;

        public Choice(CoinFlip.Result first, CoinFlip.Result second, CoinFlip.Result third) {
            this.first = first;
            this.second = second;
            this.third = third;
            rawString = String.format("%s%s%s", first, second, third);
        }

        public CoinFlip.Result get(int pos) {
            if (pos == 0) {
                return first;
            }
            if (pos == 1) {
                return second;
            }
            return third;
        }

        public String toString() {
            return String.format("[%s]", rawString);
        }

        public String rawString() {
            return rawString;
        }
    }

    public static List<Choice> allPossibleChoices = new ArrayList<>();
    static {
        allPossibleChoices.add(new Choice(CoinFlip.Result.H, CoinFlip.Result.H, CoinFlip.Result.H));
        allPossibleChoices.add(new Choice(CoinFlip.Result.H, CoinFlip.Result.H, CoinFlip.Result.T));
        allPossibleChoices.add(new Choice(CoinFlip.Result.H, CoinFlip.Result.T, CoinFlip.Result.H));
        allPossibleChoices.add(new Choice(CoinFlip.Result.H, CoinFlip.Result.T, CoinFlip.Result.T));
        allPossibleChoices.add(new Choice(CoinFlip.Result.T, CoinFlip.Result.H, CoinFlip.Result.H));
        allPossibleChoices.add(new Choice(CoinFlip.Result.T, CoinFlip.Result.H, CoinFlip.Result.T));
        allPossibleChoices.add(new Choice(CoinFlip.Result.T, CoinFlip.Result.T, CoinFlip.Result.H));
        allPossibleChoices.add(new Choice(CoinFlip.Result.T, CoinFlip.Result.T, CoinFlip.Result.T));
    }

    private static Map<Choice, Choice> beatenBy = new HashMap<>();
    static {
        // from wiki
        // https://en.wikipedia.org/wiki/Penney%27s_game
        /*
        +----------------------+----------------------+------------------------------+
        | 1st player's choice  | 2nd player's choice  | Odds in favour of 2nd player |
        +----------------------+----------------------+------------------------------+
        | HHH                  | THH                  | 7 to 1                       |
        | HHT                  | THH                  | 3 to 1                       |
        | HTH                  | HHT                  | 2 to 1                       |
        | HTT                  | HHT                  | 2 to 1                       |
        | THH                  | TTH                  | 2 to 1                       |
        | THT                  | TTH                  | 2 to 1                       |
        | TTH                  | HTT                  | 3 to 1                       |
        | TTT                  | HTT                  | 7 to 1                       |
        +----------------------+----------------------+------------------------------+
        */
        beatenBy.put(
            new Choice(CoinFlip.Result.H, CoinFlip.Result.H, CoinFlip.Result.H),
            new Choice(CoinFlip.Result.T, CoinFlip.Result.H, CoinFlip.Result.H));
        beatenBy.put(
            new Choice(CoinFlip.Result.H, CoinFlip.Result.H, CoinFlip.Result.T),
            new Choice(CoinFlip.Result.T, CoinFlip.Result.H, CoinFlip.Result.H));
        beatenBy.put(
            new Choice(CoinFlip.Result.H, CoinFlip.Result.T, CoinFlip.Result.H),
            new Choice(CoinFlip.Result.H, CoinFlip.Result.H, CoinFlip.Result.T));
        beatenBy.put(
            new Choice(CoinFlip.Result.H, CoinFlip.Result.T, CoinFlip.Result.T),
            new Choice(CoinFlip.Result.H, CoinFlip.Result.H, CoinFlip.Result.T));
        beatenBy.put(
            new Choice(CoinFlip.Result.T, CoinFlip.Result.H, CoinFlip.Result.H),
            new Choice(CoinFlip.Result.T, CoinFlip.Result.T, CoinFlip.Result.H));
        beatenBy.put(
            new Choice(CoinFlip.Result.T, CoinFlip.Result.H, CoinFlip.Result.T),
            new Choice(CoinFlip.Result.T, CoinFlip.Result.T, CoinFlip.Result.H));
        beatenBy.put(
            new Choice(CoinFlip.Result.T, CoinFlip.Result.T, CoinFlip.Result.H),
            new Choice(CoinFlip.Result.H, CoinFlip.Result.T, CoinFlip.Result.T));
        beatenBy.put(
            new Choice(CoinFlip.Result.T, CoinFlip.Result.T, CoinFlip.Result.T),
            new Choice(CoinFlip.Result.H, CoinFlip.Result.T, CoinFlip.Result.T));
    }
}
