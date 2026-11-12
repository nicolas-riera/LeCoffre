# LeCoffre

## Dependencies and quick start

### Dependencies

- Java 25 or later
- PostgreSQL 16 or later

### 1. Create the database

In your SQL CLI or UI, connected to the default `postgres` database, run:

```sql
CREATE DATABASE secretmanager;
```

### 2. Set your credentials

Copy `.env.example` into `/src` as `.env`

Then fill in your own `DB_USER` and `DB_PASSWORD` in `src/.env`, as well as `SECURITY_USER` and `  SECURITY_PASSWORD`.
This file is ignored by git: never commit it.

### 3. Run the application

From `src/`:

```
./mvnw spring-boot:run
```