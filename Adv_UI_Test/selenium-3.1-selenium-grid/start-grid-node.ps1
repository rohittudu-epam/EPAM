param(
    [string]$SeleniumServerJar = "selenium-server-4.29.0.jar",
    [string]$HubUrl = "http://localhost:4444"
)

if (-not (Test-Path $SeleniumServerJar)) {
    throw "Selenium Server JAR not found: $SeleniumServerJar"
}

Write-Host "Starting Selenium Grid node and registering to $HubUrl ..."
java -jar $SeleniumServerJar node --hub $HubUrl
