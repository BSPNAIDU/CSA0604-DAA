# DAA Assignment 5 – Java 8 implementation

Aligned with the uploaded Assignment 5 CO5 specification:
- B1: Instrumented graph colouring, input-order vs highest-degree-first heuristic, colourable and non-colourable cases.
- B2: Adversarial graph case (complete graph K4 with only 3 colours) and search counters.
- B3: Exact minimum set cover for small instances vs greedy maximum-new-coverage heuristic, including comparison cases and a counterexample.
- B4: First-Fit Decreasing bin packing, server assignments and remaining capacities.
- Extra: subset-sum include/exclude backtracking with positive-number pruning; N-Queens and graph-colouring backtracking pattern demo.

## Run
```powershell
cd src
javac *.java
java TaskB1_GraphColouring
java TaskA2_SubsetSumBacktracking
java TaskA3_BacktrackingPatterns
java TaskB3_SetCover
java TaskB4_BinPacking
```

Exact Set Cover is exponential and intentionally intended only for small inputs. Greedy is fast but does not guarantee a minimum cover. Bin packing uses First-Fit Decreasing, a heuristic rather than an exact optimiser.
