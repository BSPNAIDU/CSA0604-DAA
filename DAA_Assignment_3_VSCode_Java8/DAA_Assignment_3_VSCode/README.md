# DAA Assignment 3 — Java 8 / VS Code

## Requirements
- JDK 8 (or newer; code uses Java 8-compatible language features)
- VS Code
- Extension Pack for Java (recommended)

## Open the project
Open this folder in VS Code:
`DAA_Assignment_3_VSCode`

## PowerShell terminal commands
Run these commands from the project root:

```powershell
javac -version
java -version
New-Item -ItemType Directory -Force out
javac -encoding UTF-8 -d out .\src\*.java
```

If compilation succeeds, run each question:

```powershell
java -cp out Q1_MaxMin
java -cp out Q1_BinarySearch
java -cp out Q2_MergeSort
java -cp out Q2_QuickSort
java -cp out Q3_Karatsuba
java -cp out Q4_Strassen
java -cp out Q5_ClosestPair
java -cp out Q6_MedianOfMedians
java -cp out Q6_MeetInTheMiddle
java -cp out Q7_CountInversions
```

Save each output as a CSV/text file if desired, for example:
```powershell
java -cp out Q1_MaxMin | Tee-Object .\results\q1_maxmin.csv
```

## Important notes
- Each class is independent and has its own `main` method.
- Q2 Quick Sort uses the last element as pivot, as stated in the report.
- Q3 uses `BigInteger` so large integer multiplication is exact. The Karatsuba routine uses a small-input cutoff of 18 decimal digits.
- Q4 uses `long` matrix entries and Strassen recursion with a conventional multiplication cutoff at 2x2. Keep values and matrix sizes modest to avoid `long` overflow.
- Q5 generates deterministic random point sets using seed 42. The strip checks at most the next seven points in y-order.
- Q6 Meet-in-the-Middle stores half-subset sums; memory usage grows exponentially. Do not increase n blindly.
- Q7 counts strict inversions (`A[i] > A[j]`), not equal pairs.

## Evidence
The programs print operation counts and/or elapsed times. Run them on your machine and use the observed output in the assignment. Timing varies with CPU load, JVM warm-up, and hardware; do not copy timings from another machine.
