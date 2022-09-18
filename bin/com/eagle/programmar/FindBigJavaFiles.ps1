[CmdletBinding()]
Param(
   [Parameter(Mandatory=$False,Position=1)] [int]$target
)

$fmtCommas = "{0:N0}"
$fmtDec = "{0:F1}"

If (-Not $target) { $target = 150 }

$here = ($pwd).Path

$totalFiles = 0
$totalLines = 0
ForEach ($f in (Get-ChildItem -Recurse *.java)) {
  $lines = (Get-Content $f.FullName | Measure).Count
  $totalFiles += 1
  $totalLines += $lines
  If ($f.Name -NotMatch "_Expression|_Reserved_Words") {
    If ($lines -gt $target) {
      $name = $f.FullName.Replace($here, "")
      Write-Output "$lines - .$name"
    }
  }
}

$avg = $fmtDec -f ($totalLines / $totalFiles)
$fmtFiles = $fmtCommas -f $totalFiles
$fmtLines = $fmtCommas -f $totalLines
Write-Output "Total java files = $fmtFiles  lines = $fmtLines  average = $avg lines/file"
