param(
    [string]$SeleniumServerJar = "selenium-server-4.29.0.jar"
)

if (-not (Test-Path $SeleniumServerJar)) {
    throw "Selenium Server JAR not found: $SeleniumServerJar"
}

Write-Host "Starting Selenium Grid standalone mode on http://localhost:4444 ..."
java -jar $SeleniumServerJar standalone
