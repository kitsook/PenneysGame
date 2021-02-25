package net.clarenceho.penneys.model;

import java.util.Random;
import java.util.stream.Stream;

public class CoinFlip {
    public enum Result {
        H,
        T
    }

    private static CoinFlip.Result mapIntToResult(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException();
        }

        if (i == 0) {
            return CoinFlip.Result.H;
        }
        return CoinFlip.Result.T;
    }

    public static Stream<CoinFlip.Result> flips() {
        return new Random().ints(0, 2).mapToObj(CoinFlip::mapIntToResult);
    }

}
