package net.clarenceho.penneys.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.clarenceho.penneys.model.PenneysGame.Choice;

@SpringBootTest
public class PenneysGameTests {

    @Test
    void testChoiceConstructor() {
        Choice c1 = new Choice(CoinFlip.Result.T, CoinFlip.Result.H, CoinFlip.Result.T);
        assertEquals(c1.get(0), CoinFlip.Result.T);
        assertEquals(c1.get(1), CoinFlip.Result.H);
        assertEquals(c1.get(2), CoinFlip.Result.T);
    }

    @Test
    void testChoiceEquals() {
        Choice c1 = new Choice(CoinFlip.Result.H, CoinFlip.Result.T, CoinFlip.Result.H);
        Choice c2 = new Choice();
        c2.setFirst(CoinFlip.Result.H);
        c2.setSecond(CoinFlip.Result.T);
        c2.setThird(CoinFlip.Result.H);
        assertEquals(c1, c2);
    }
}
