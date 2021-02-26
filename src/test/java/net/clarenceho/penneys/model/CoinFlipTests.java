package net.clarenceho.penneys.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CoinFlipTests {

    @Test
    void flipCoinFor100Times() {
        CoinFlip.flips().limit(100).forEach(r -> {
            assertTrue(r == CoinFlip.Result.H || r == CoinFlip.Result.T);
        });
    }

    @Test
    void flipCoinResultEven() {
        AtomicInteger headCount = new AtomicInteger(0);
        AtomicInteger tailCount = new AtomicInteger(0);
        int totalCount = 100000;

        CoinFlip.flips().limit(totalCount).forEach(r -> {
            if (r == CoinFlip.Result.H) {
                headCount.incrementAndGet();
            } else if (r == CoinFlip.Result.T) {
                tailCount.incrementAndGet();
            }
        });
        // the counts should be similar
        assertTrue(Math.abs(Double.valueOf(headCount.get() - tailCount.get()) / totalCount) < 0.1);
    }
}
