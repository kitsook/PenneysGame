package net.clarenceho.penneys.model;

import java.util.stream.Stream;
import java.util.concurrent.ThreadLocalRandom;

public class CoinFlip {
    public enum Result {
        H,
        T
    }

    public static Stream<CoinFlip.Result> flips() {
        return ThreadLocalRandom.current().ints(0, 2).mapToObj(CoinFlip::mapIntToResult);
    }

    private static CoinFlip.Result mapIntToResult(int i) {
        if (i == 0) {
            return CoinFlip.Result.H;
        }
        return CoinFlip.Result.T;
    }
}
