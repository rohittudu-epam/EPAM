# JHipster Sample Application

A full-stack web application built with **JHipster 9.0.0-beta.3**, featuring a **Spring Boot** backend and **Angular** frontend.

## Table of Contents

- [Quick Start](#quick-start)
- [Prerequisites](#prerequisites)
- [Starting the Application](#starting-the-application)
- [Project Structure](#project-structure)
- [Development](#development)
- [Building for Production](#building-for-production)
- [Testing](#testing)
- [Docker Support](#docker-support)
- [Code Quality](#code-quality)
- [References](#references)

---

## Quick Start

```bash
# 1. Install dependencies
./npmw install

# 2. Start the database (PostgreSQL via Docker)
docker compose -f src/main/docker/postgresql.yml up -d

# 3. Start back-end (Terminal 1)
./mvnw

# 4. Start front-end (Terminal 2)
./npmw run start
```

**Access the application:** http://localhost:9000

---

## Prerequisites

| Requirement | Version | Notes |
|-------------|---------|-------|
| Java        | 17+     | Required for Spring Boot backend |
| Docker      | Latest  | Required for PostgreSQL database |
| Node.js     | Auto-installed | Managed by build system |

> **Note:** The build system automatically installs the recommended Node.js and npm versions. Use `./npmw` instead of `npm` to ensure consistency.

---

## Starting the Application

### Option 1: Development Mode (Recommended)

Run front-end and back-end separately for hot-reloading and faster development.

#### Step 1: Start the Database

```bash
# Start PostgreSQL in Docker
docker compose -f src/main/docker/postgresql.yml up -d

# Verify container is running
docker ps
```

#### Step 2: Start the Back-End (Spring Boot)

```bash
# Using Maven wrapper (recommended)
./mvnw

# Or using npm script
./npmw run backend:start
```

**Back-end URL:** http://localhost:8080

| Back-End Command | Description |
|------------------|-------------|
| `./mvnw` | Start Spring Boot server |
| `./npmw run backend:start` | Same as above (npm script) |
| `./npmw run backend:debug` | Start with remote debugging (port 8000) |

#### Step 3: Start the Front-End (Angular)

Open a **new terminal** and run:

```bash
# Start Angular dev server with hot module replacement
./npmw run start

# Or equivalently
ng serve --hmr
```

**Front-end URL:** http://localhost:9000

| Front-End Command | Description |
|-------------------|-------------|
| `./npmw run start` | Start Angular dev server (port 9000) |
| `./npmw run start-tls` | Start with HTTPS/SSL |
| `./npmw run webapp:dev` | Alternative: `ng serve` |

### Option 2: Watch Mode (Single Command)

Run both front-end and back-end concurrently in a single terminal:

```bash
./npmw run watch
```

### Option 3: Full Application Start

Start the complete application with a single command:

```bash
./npmw run app:start
```

**URL:** http://localhost:8080

---

## Project Structure

```
jhipster-sample-app/
├── src/
│   ├── main/
│   │   ├── java/                 # Spring Boot backend (Java)
│   │   ├── resources/            # Configuration files
│   │   ├── webapp/               # Angular frontend
│   │   │   ├── app/              # Angular components & services
│   │   │   ├── content/          # Static assets (CSS, images)
│   │   │   └── i18n/             # Internationalization
│   │   └── docker/               # Docker compose files
│   └── test/
│       ├── java/                 # Java unit/integration tests
│       └── javascript/cypress/   # E2E tests
├── package.json                  # Node.js dependencies & scripts
├── pom.xml                       # Maven build configuration
├── angular.json                  # Angular CLI configuration
└── README.md
```

### Key Configuration Files

| File | Purpose |
|------|---------|
| `.yo-rc.json` | JHipster configuration |
| `.jhipster/*.json` | Entity configurations |
| `npmw` / `npmw.cmd` | npm wrapper (ensures consistent npm version) |

---

## Development

### Install Dependencies

```bash
./npmw install
```

### Managing Dependencies

```bash
# Add a runtime dependency
./npmw install --save --save-exact <package-name>

# Add a dev dependency
./npmw install --save-dev --save-exact <package-name>

# Update dependencies
./npmw update
```

### Using Angular CLI

```bash
# Generate a new component
ng generate component my-component

# Generate a service
ng generate service my-service
```

### PWA Support

PWA is disabled by default. Enable it in `src/main/webapp/app/app.config.ts`:

```typescript
ServiceWorkerModule.register('ngsw-worker.js', { enabled: true }),
```

---

## Building for Production

### Build JAR

```bash
# Build optimized production JAR
./mvnw -Pprod clean verify

# Run the JAR
java -jar target/*.jar
```

### Build WAR

```bash
./mvnw -Pprod,war clean verify
```

### Build Docker Image

```bash
# Standard build
npm run java:docker

# ARM64 (Apple Silicon)
npm run java:docker:arm64
```

---

## Testing

### Back-End Tests (Java)

```bash
# Run all tests
./mvnw verify

# Run Gatling performance tests
./mvnw gatling:test
```

### Front-End Tests (Angular)

```bash
# Run unit tests with coverage
./npmw test

# Run tests in watch mode
./npmw run test:watch
```

### E2E Tests (Cypress)

```bash
# Start app first, then run E2E tests
./npmw run app:start   # Terminal 1
./npmw run e2e         # Terminal 2

# Or run both together
./npmw run e2e:dev
```

### Lighthouse Audits

```bash
./npmw run e2e:cypress:audits
```

---

## Docker Support

### Start Required Services

```bash
# Start PostgreSQL
docker compose -f src/main/docker/postgresql.yml up -d

# Start all services
docker compose -f src/main/docker/services.yml up -d

# Stop services
docker compose -f src/main/docker/services.yml down
```

### Run Full Application in Docker

```bash
# Build Docker image
npm run java:docker

# Start application with dependencies
docker compose -f src/main/docker/app.yml up -d
```

### JHipster Control Center

```bash
docker compose -f src/main/docker/jhipster-control-center.yml up
```

**Access:** http://localhost:7419

---

## Code Quality

### Linting

```bash
# Check linting
./npmw run lint

# Auto-fix issues
./npmw run lint:fix
```

### Prettier

```bash
# Check formatting
./npmw run prettier:check

# Format code
./npmw run prettier:format
```

### SonarQube Analysis

```bash
# Start Sonar server
docker compose -f src/main/docker/sonar.yml up -d

# Run analysis
./mvnw -Pprod clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
```

**Sonar Dashboard:** http://localhost:9001

---

## Common Commands Reference

| Task | Command |
|------|---------|
| Install dependencies | `./npmw install` |
| Start back-end | `./mvnw` or `./npmw run backend:start` |
| Start front-end | `./npmw run start` |
| Start both (watch) | `./npmw run watch` |
| Run all tests | `./mvnw verify && ./npmw test` |
| Build for production | `./mvnw -Pprod clean verify` |
| Start database | `docker compose -f src/main/docker/postgresql.yml up -d` |
| Stop database | `docker compose -f src/main/docker/postgresql.yml down` |

---

## References

- [JHipster Documentation](https://www.jhipster.tech/)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Angular Documentation](https://angular.dev/)
- [Cypress Documentation](https://docs.cypress.io/)
- [Docker Compose](https://docs.docker.com/compose/)
