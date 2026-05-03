# Build Locally

- Project uses **Maven** wrapper (`mvnw.cmd` on Windows, `mvnw` on Linux/macOS) — never call `mvn` directly.
- All commands must be run from the project root `c:\Java\8080-emulator` (or repo root on other machines).

## Run tests

- Run all unit tests:
  ```
  .\mvnw.cmd test
  ```
- Run a single test class:
  ```
  .\mvnw.cmd test "-Dtest=CpuTest"
  ```
- Run a single test method:
  ```
  .\mvnw.cmd test "-Dtest=CpuTest#code00__NOP"
  ```
- Suppress Maven noise, show only last lines:
  ```
  .\mvnw.cmd test "-Dtest=CpuTest#code00__NOP" 2>&1 | Select-Object -Last 15
  ```

## Build server (jar + run)

- From `build/` folder run `build-server.bat` (Windows) or `build-server.sh` (Linux/macOS).
- What it does:
  + Packages with `mvnw clean package -DskipTests=true -Pjar-with-dependencies`
  + Starts Jetty on `http://localhost:8080/` with `-Prun-server`

## Build client (static assets)

- From `build/` folder run `build-client.bat` (Windows) or `build-client.sh` (Linux/macOS).
- Output lands in `build/out/` — copy that folder to deploy.

## JavaScript tests

- Run with: `npm test` (requires Node.js, packages installed via `npm install`).
- Config: `jest.config.js`.

## Build profiles (Maven)

- `-Pjar-with-dependencies` — fat jar with all deps.
- `-Prun-server` — starts embedded Jetty after package.
- `-DskipTests=true` — skip tests during package.
