[CmdletBinding()]
Param(
   [Parameter(Mandatory=$False,Position=1)] [int]$target
)

If (-Not $target) { $target = 150 }

ForEach ($f in (Get-ChildItem -Recurse *.java)) {
  If ($f.Name -NotMatch "_Expression|_Reserved_Words") {
    $lines = (Get-Content $f.FullName | Measure).Count
    If ($lines -gt $target) {
      Write-Output "$lines - $($f.FullName)"
    }
  }
}