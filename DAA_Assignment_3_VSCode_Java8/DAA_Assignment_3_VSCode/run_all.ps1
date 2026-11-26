$ErrorActionPreference = "Stop"
New-Item -ItemType Directory -Force out | Out-Null
javac -encoding UTF-8 -d out .\src\*.java
if ($LASTEXITCODE -ne 0) { throw "Compilation failed." }

$classes = @(
  "Q1_MaxMin",
  "Q1_BinarySearch",
  "Q2_MergeSort",
  "Q2_QuickSort",
  "Q3_Karatsuba",
  "Q4_Strassen",
  "Q5_ClosestPair",
  "Q6_MedianOfMedians",
  "Q6_MeetInTheMiddle",
  "Q7_CountInversions"
)

foreach ($class in $classes) {
  Write-Host "`n========== $class ==========" -ForegroundColor Cyan
  java -cp out $class
  if ($LASTEXITCODE -ne 0) { throw "$class failed." }
}
