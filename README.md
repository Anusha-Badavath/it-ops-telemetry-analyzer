# IT Ops Telemetry Analyzer Prototype

Forward Deployed Engineer Assessment Project

# IT Ops Telemetry Analyzer

A full-stack IT operations telemetry analyzer that reads raw log data, parses valid log entries, stores them in PostgreSQL, performs log analytics, and displays system health through a web dashboard.

## Project Overview

The IT Ops Telemetry Analyzer converts unstructured operational log data into useful monitoring information.

The application:

- Reads logs from `logs.txt`
- Parses log entries using Java Regular Expressions
- Extracts timestamp, severity, system component, and log message
- Skips malformed log lines without crashing
- Stores valid logs in PostgreSQL
- Calculates severity statistics
- Calculates component statistics
- Identifies critical issues by component
- Determines overall system health
- Displays the results in a responsive web dashboard

## Architecture

```text
                logs.txt
                   |
                   v
          Log Ingestion Service
                   |
                   v
              Log Parser
          (Java Regex Pattern)
                   |
                   v
              PostgreSQL
                   |
                   v
          Spring Boot REST APIs
                   |
                   v
       JavaScript fetch() calls
                   |
                   v
          Telemetry Dashboard


Technologies Used
Backend
Java 17
Spring Boot
Spring Data JPA
Maven
Regular Expressions (java.util.regex)

Database
PostgreSQL
SQL
JPA/Hibernate

Frontend
HTML5
CSS3
JavaScript
Fetch API
Responsive CSS Grid/Flexbox

Tools
Visual Studio Code
pgAdmin 4
Git/GitHub

it-ops-telemetry-analyzer/
│
├── backend/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── telemetry/
│           │           └── analyzer/
│           │               ├── controller/
│           │               ├── model/
│           │               ├── parser/
│           │               ├── repository/
│           │               └── service/
│           │
│           └── resources/
│               └── application.properties
│
├── database/
│   └── schema.sql
│
├── frontend/
│   ├── index.html
│   ├── style.css
│   └── script.js
│
├── logs.txt
└── README.md

Log Format

Valid logs follow this structure:

YYYY-MM-DD HH:mm:ss [SEVERITY] COMPONENT - MESSAGE


example:
2026-08-24 10:18:12 [CRITICAL] Gateway-Router - Firewalled packet drop rate exceeded safety limits

The parser extracts:

Timestamp
Severity
System Component
Log Message

Malformed Log Handling

The application uses defensive parsing.

If a log line does not match the expected pattern, it is skipped instead of causing the application to crash.

Example malformed line:
2026-08-24 10:33:40 [MALFORMED_LINE_TEST] Broken-Component-No-Data


This line is rejected because the application accepts only:

INFO
WARN
CRITICAL

Malformed entries are logged as warnings.


Database

The application stores valid logs in the PostgreSQL table:

telemetry_logs

Columns:

Column	                  Type
id          	        BIGSERIAL
timestamp           	TIMESTAMP
severity	           VARCHAR(20)
system_component	  VARCHAR(100)
log_message	            TEXT


Indexes are created for:

Severity
System component
Timestamp

The complete database setup is available in:

database/schema.sql


REST API Endpoints
Get Logs
GET /api/logs

Severity Statistics
GET /api/analytics/severity
Returns the number of logs for each severity.

Example:
{
  "INFO": 4,
  "WARN": 2,
  "CRITICAL": 3
}


Component Statistics
GET /api/analytics/components

Critical Issues
GET /api/analytics/critical
Returns critical log counts grouped by component.

System Health
GET /api/analytics/health

Returns the overall system health
Possible values
HEALTHY
WARNING
CRITICAL


System Health Logic

The application determines system health based on severity

If CRITICAL logs > 0
        |
        v
     CRITICAL

Otherwise if WARN logs > 0
        |
        v
     WARNING

Otherwise
        |
        v
     HEALTHY


     Running the Backend

Navigate to the backend directory:

cd backend

Build the project:

mvn clean install

Start Spring Boot:

mvn spring-boot:run

The backend runs on:

http://localhost:8080
Running the Frontend

Open the frontend using a local web server such as VS Code Live Server.

Example:

http://127.0.0.1:5500/it-ops-telemetry-analyzer/frontend/index.html

The frontend communicates with the Spring Boot backend using JavaScript fetch().

Database Configuration

PostgreSQL connection is configured in:

backend/src/main/resources/application.properties

Example configuration:

spring.datasource.url=jdbc:postgresql://localhost:5433/telemetry_analyzer
spring.datasource.username=postgres
spring.datasource.password=YOUR_POSTGRES_PASSWORD

Do not commit real database passwords to GitHub.

Dashboard Features

The dashboard displays:

System health
INFO log count
WARN log count
CRITICAL log count
Component statistics
Critical issues by component
Complete telemetry logs

The dashboard updates the page dynamically using JavaScript without requiring a full page reload.

Current Test Data

The supplied test data contains:

INFO      : 4
WARN      : 2
CRITICAL  : 3
Valid Logs: 9
Malformed : 1

Therefore the current system health is:

CRITICAL
Defensive Engineering

The application is designed to continue processing when invalid log data is encountered.

Invalid entries are:

1.Detected using the regular expression parser
2.Rejected safely
3.Logged as warnings
4.Excluded from database insertion

This prevents a single malformed log from stopping the entire ingestion process.

Future Improvements

Possible future enhancements include:

Automatic scheduled log ingestion
Pagination for large log datasets
Search and filtering
Charts for telemetry trends
Docker deployment
Cloud deployment using Render or Railway
Frontend deployment using Vercel or Netlify

### After pasting


### After pasting

Save:

**`Ctrl + S`**

Then tell me **`README done`**.

After that, we'll do a very important **final project check** before deployment: verify backend + database + frontend together and make sure there are no remaining issues.