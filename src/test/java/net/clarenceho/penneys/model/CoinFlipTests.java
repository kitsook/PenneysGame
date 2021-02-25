package net.clarenceho.penneys.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
