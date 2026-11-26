# DAA Assignment 4B – Java 8 implementation

Programs are aligned with the uploaded Assignment 4B tasks:
1. Word Wrap DP with line reconstruction, slack and per-line penalty.
2. Fractional Knapsack and 0/1 Knapsack with a greedy counterexample.
3. Dijkstra, Bellman-Ford, negative-cycle detection, Floyd-Warshall and Warshall.
4. Two-line assembly scheduling with decision trace and route reconstruction.
5. Kruskal, Prim and Boruvka MST with selected-edge traces.
6. Held-Karp TSP DP with complete tour reconstruction and state-space count.

## Run
```powershell
cd src
javac *.java
java Task1_WordWrap
java Task1_WordWrap 20
java Task2_KnapsackComparison
java Task3_RoutingEngine
java Task4_AssemblyLine
java Task5_MSTAlgorithms
java Task6_TSPHeldKarp
```
Change word-wrap widths with command-line values (e.g. 12, 16, 20). Source files use Java 8-compatible syntax.
