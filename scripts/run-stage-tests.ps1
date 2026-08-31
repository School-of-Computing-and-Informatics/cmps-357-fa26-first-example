# PowerShell version of run-stage-tests.sh
# Usage: pwsh -ExecutionPolicy Bypass -File .\scripts\run-stage-tests.ps1

$ErrorActionPreference = 'Stop'

# Run from repository root (script is in scripts/)
$scriptRoot = Split-Path -Parent $MyInvocation.MyCommand.Definition
Set-Location (Resolve-Path (Join-Path $scriptRoot '..'))

$testOutputDir = 'out\stage-tests'
if (Test-Path $testOutputDir) {
    Remove-Item -Recurse -Force $testOutputDir
}
New-Item -ItemType Directory -Path $testOutputDir | Out-Null

Write-Host "Compiling Java sources..."
$srcFiles = Get-ChildItem -Path 'src' -Filter '*.java' -File | ForEach-Object { $_.FullName }
$testRunner = Join-Path -Path 'tests' -ChildPath 'StageTestRunner.java'

$javacArgs = @('-encoding','UTF-8','-source','17','-target','17','-d', $testOutputDir) + $srcFiles + @($testRunner)
& javac @javacArgs
if ($LASTEXITCODE -ne 0) {
    Write-Error "javac failed with exit code $LASTEXITCODE"
    exit $LASTEXITCODE
}

Write-Host "Running StageTestRunner..."
$javaArgs = @('-ea','-cp',$testOutputDir,'StageTestRunner')
& java @javaArgs
exit $LASTEXITCODE
