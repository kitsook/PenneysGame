
# Penney's Game

Verifying the result of [Penney's Game](https://en.wikipedia.org/wiki/Penney%27s_game).

## Getting Started

### Dependencies

* Java 11 runtime

### Executing the program

```
./gradlew bootRun
```

## Observations

Strangely, the result doesn't match. wiki and [one demo site](https://penneys.github.io/) listed these as the best bet:

| 1st player's choice  | 2nd player's choice  | Odds in favour of 2nd player |
|----------------------|----------------------|------------------------------|
| HHH                  | THH                  | 7 to 1                       |
| HHT                  | THH                  | 3 to 1                       |
| HTH                  | HHT                  | 2 to 1                       |
| HTT                  | HHT                  | 2 to 1                       |
| THH                  | TTH                  | 2 to 1                       |
| THT                  | TTH                  | 2 to 1                       |
| TTH                  | HTT                  | 3 to 1                       |
| TTT                  | HTT                  | 7 to 1                       |


But this Java program simulated these outputs:
```
Best choice against [HHH] is [THH]
Best choice against [THH] is [HTH]
Best choice against [HTH] is [TTH]
Best choice against [TTH] is [HTT]
Best choice against [HHT] is [THH]
Best choice against [THT] is [HHT]
Best choice against [HTT] is [THT]
Best choice against [TTT] is [HTT]
```

TODO: needed to figure out if there is a bug