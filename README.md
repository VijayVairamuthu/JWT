# Task-Runner Allocation (Spring Boot + MySQL)

Implements optimal assignment of READY tasks to runners using Haversine distance and the Hungarian algorithm.

## Prerequisites
- JDK 17+
- Maven 3.9+
- MySQL 8+

## Setup
1. Create a database:
   ```sql
   CREATE DATABASE allocationdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
2. Open `src/main/resources/application.properties` and set your MySQL `username` and `password`.
3. Build & run:
   ```bash
   mvn spring-boot:run
   ```
   Boot will initialize the schema and sample data from `schema.sql` and `data.sql`.

## API
`POST http://localhost:8080/api/assignments`

Sample request body:
```json
{
  "type": "runner",
  "org": "sample",
  "version": "1.00"
}
```

Sample successful response (values depend on actual distances):
```json
{
  "statusCode": "1",
  "statusMessage": "SUCCESS",
  "readyTasks": [
    { "taskId": "1", "runner": "runner1", "label": "1001 | READY", "distance": "0.00 Km", "colorCode": "#077411" },
    { "taskId": "2", "runner": "runner2", "label": "1002 | READY", "distance": "2.44 Km", "colorCode": "#06D001" },
    { "taskId": "3", "runner": "runner3", "label": "1003 | READY", "distance": "4.47 Km", "colorCode": "#FF8225" }
  ]
}
```

Failure example:
```json
{
  "statusCode": "0",
  "statusMessage": "NO ENTRIES FOUND",
  "readyTasks": []
}
```

## Notes
- We **do not** update the DB when assigning; we just compute optimal assignments in-memory and return them.
- If there are more tasks than runners, the algorithm assigns up to `min(tasks, runners)` tasks. Unassigned tasks are omitted.
- Distance colors:
  - `<= 1.0 km` → `#077411`
  - `<= 3.0 km` → `#06D001`
  - `> 3.0 km`  → `#FF8225`
