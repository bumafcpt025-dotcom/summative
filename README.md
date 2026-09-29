# Summative Assessment — StreamWise Platform

## Learning Outcomes

| Learning Outcome | Section |
|---|---|
| Project Management / Agile | Section 1 |
| Brownfields Development | Section 2 |
| Build Pipelines / Scripting | Section 3 |
| OOP | Section 4 |
| Relational Database Design | Section 5 |

---

## Duration

**Total time: 3 hours**

| Section | Recommended Time |
|---|---|
| Section 1 — Project Management | 35 minutes |
| Section 2 — Brownfields Development | 50 minutes |
| Section 3 — Build and Release Management | 25 minutes |
| Section 4 — Practical OOP Implementation | 30 minutes |
| Section 5 — Database Schema Design | 30 minutes |

---

## Scoring

| Section | Marks |
|---|---|
| Section 1 — Project Management | 25 |
| Section 2 — Brownfields Development | 40 |
| Section 3 — Build and Release Management | 21 |
| Section 4 — Practical OOP Implementation | 14 |
| Section 5 — Database Schema Design | 20 |
| **Total** | **120** |

---

# Section 1 — Project Management

## Scenario

StreamWise — Viewer Recommendations

A four-person startup called StreamWise has been building a Java-based streaming analytics platform for the past year. The team writes code, runs it locally, and decides what to work on during weekly meetings. When something breaks, a developer fixes it in whatever branch they were already working in. There is no shared task board, no definition of done, and no agreed sprint structure. Features get started and left half-finished as more urgent things come up.

The business has just signed its first enterprise customer, who expects a new Viewer Recommendations feature to be live within six weeks. The CTO has asked you to introduce a proper Agile workflow before a single line of new code is written.

> **Reference:** Schwaber, K., and Sutherland, J. (2020). *The Scrum Guide*.
> [Full text available here](https://scrumguides.org/scrum-guide.html)

| Task | Marks | What earns marks |
|---|---|---|
| Q1.1 — Waterfall vs Agile | 5 | Accurate definition of both (2), clear reasoning tied to StreamWise's specific situation (3) |
| Q1.2 — Taskboard | 14 | Lanes cover the full backlog-to-delivery flow with no gaps (8), lanes are genuinely distinct and domain-specific — not copied from curriculum examples (4), default Open/Closed columns correctly removed (2) |
| Q1.3 — Tickets and user stories | 6 | Correct user story format (1), actor and goal reflect the actual scenario for each ticket (3), tickets linked, assigned to yourself, due date set to 30 August 2026 (2) |

---

### Q1.1 — Waterfall vs Agile *(5 marks)*

Define and contrast the Waterfall model and Agile (time-boxed) delivery. StreamWise's problems are half-finished features, no shared workflow, and a hard six-week deadline. Explain how Agile delivery specifically addresses problems like these in a way Waterfall would not.

---

### Q1.2 — Set Up the Taskboard *(14 marks)*

Set up a GitLab taskboard for the StreamWise Recommendations team. Design every lane yourself so that together they trace the full journey of a piece of work from idea to production release.

> GitLab's default Open and Closed columns do not count as lanes and must not appear when you submit your board. Every lane must be one you have deliberately created and named.

---

### Q1.3 — Create the Tickets *(6 marks)*

Create two tickets on your board:

- **Ticket 1:** Viewer Recommendations are generated from watch history
- **Ticket 2:** Recommendations are shown on the viewer's home screen

Under each ticket, write a user story from the viewer's perspective.

These tickets are related:

- Link the two tickets to each other in GitLab.
- Assign both to yourself.
- Set a due date of **30 August 2026** on both.

---

# Section 2 — Brownfields Development

| Task | Marks | What earns marks |
|---|---|---|
| Q2.1 — Brownfield vs Greenfield | 7 | Accurate contrast (3), Robot Worlds example genuinely illustrates the risk of writing code before tests (4) |
| Q2.2 — Refactoring vs Debugging | 7 | Correct definitions of both (2), accurate before/after contrast for each (3), named-variable example explained correctly (2) |
| Q2.3 — Refactoring for Maintainability | 26 | Split across two methods, 13 each: correct identification of the specific code smell (3), accurate explanation of why it is a problem (4), refactor resolves it (3), all tests pass unmodified — binary (2), written justification is specific to the change (1) |

---

### Q2.1 — Brownfield vs Greenfield *(7 marks)*

Define and contrast brownfield and greenfield software development. When extending a brownfield codebase, why is it risky to write the new code first and think about tests only afterwards? Use a specific example from your work on Robot Worlds to illustrate your answer.

---

### Q2.2 — Refactoring vs Debugging *(7 marks)*

Define refactoring and define debugging. Contrast the two: what is true about the code's behaviour before you start each activity, and what must be true once you are done? Using an example from the codebase in this assessment — or one you invent — describe what it means to extract conditions into well-named boolean variables, and explain why this improves the code.

---

### Q2.3 — Refactoring for Maintainability *(26 marks)*

The provided codebase contains two methods, each with a distinct maintainability problem in how its conditional logic is structured.

For each of the two methods, complete all three steps:

#### Q2.3.1 — Locate and describe the problem

Identify the method by name. Explain in your own words what is wrong with its structure and why that matters for readability, testability, or safe modification. *(Write this in `answers.txt`.)*

#### Q2.3.2 — Refactor

Refactor the method to resolve the problem you identified. Do not change any behaviour.

#### Q2.3.3 — Prove behaviour preservation

Run `mvn test` before and after your refactor. All tests must pass without modification.

Write 3–5 sentences per method in `answers.txt` justifying why your new structure is more maintainable than the original.

---

# Section 3 — Build and Release Management

Write your answers in `answers.txt`, under the matching question heading.

| Task | Marks | What earns marks |
|---|---|---|
| Q3.1 — Reproducible builds | 5 | Identifies three distinct causes of build failures rooted in the build not being reproducible (3), explains what a shared automated build gives the team instead (2) |
| Q3.2 — The release process | 5 | Correct order of the four release steps (1), packaging vs deployment correctly distinguished (2), why class files alone are not enough for a user (2) |
| Q3.3 — Dependencies and the pom | 5 | Accurate description of pom.xml (2), correct definition of a dependency and what the three coordinates identify (1), why declaring in the pom beats manual downloading (2) |
| Q3.4 — Versioning a release | 6 | Correct version number and sound reason for (a) (2), (b) (2), (c) (2) |

---

### Q3.1 — Reproducible Builds *(5 marks)*

A developer on the StreamWise team pushes a change. The GitLab pipeline fails to build it, but the developer insists it works — because it ran fine on their laptop.

Explain at least three distinct reasons why a build succeeds on one machine and fails on another, and what a shared automated build process gives the team instead.

---

### Q3.2 — The Release Process *(5 marks)*

The following four steps of a release are listed in the wrong order:

```
deploy the application
package the application
compile the source code
run the automated tests
```

State the correct order. Then explain what it means to **package** a Java application and what it means to **deploy** it, and why compiled `.class` files alone cannot be handed to a user to run.

---

### Q3.3 — Dependencies and the pom *(5 marks)*

The StreamWise project uses a library it did not write. The jar is not committed to the repository, but every developer's build includes it because of this entry in `pom.xml`:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.53.2.1</version>
</dependency>
```

Explain what `pom.xml` is for, what a dependency is in this context, and what `groupId`, `artifactId`, and `version` each identify. Then explain why declaring dependencies in the pom is better than each developer downloading the jars themselves.

---

### Q3.4 — Versioning a Release *(6 marks)*

The StreamWise platform is currently at version `3.0.2`.

Each change below is made **independently**, starting from `3.0.2` in every case. State the new version number and explain why:

- **(a)** You fix a bug where the recommendation engine returns duplicate titles.
- **(b)** You add a new `topPicks(int limit)` method. No existing methods change.
- **(c)** You remove the `legacyRecommend()` method that two partner integrations still call.

---

# Section 4 — Practical OOP Implementation

## Scenario

You have been handed the StreamWise starter project. `StreamSession` and `WatchlistEntry` are both implemented but neither currently compiles. A missing dependency is also preventing the build from succeeding.

| Task | Marks | What earns marks |
|---|---|---|
| Q4.1 — Fix the build | 5 | Correct dependency added with correct scope — `mvn compile` succeeds (5) |
| Q4.2 — Fix the OOP structure | 4 | Correctly identifies both structural issues (2), resolves both in both classes without changing other logic (2) |
| Q4.3 — Build a pipeline | 5 | Correct stages defined in correct order (1), `test` job defined in its own stage running `make test` (2), `package` job defined in its own stage running `make package` with jar artifact (2) |

---

### Q4.1 — Fix the Build *(5 marks)*

A missing dependency prevents the project from compiling. Find the correct dependency on [Maven Central](https://mvnrepository.com/repos/central) and add it to `pom.xml`.

> The project uses JUnit Jupiter for testing and AssertJ for assertions.

---

### Q4.2 — Fix the OOP Structure *(4 marks)*

`StreamSession` and `WatchlistEntry` do not compile. Both classes are missing their inheritance relationship to `ViewerActivity`. Apply the necessary fix in both classes without changing any other logic.

> You may not modify the tests. This should take no more than 5 minutes.

---

### Q4.3 — Build a Pipeline *(5 marks)*

The `compile` job is already provided in `.gitlab-ci.yml`. Define the `stages` for this pipeline and add the `test` and `package` jobs so the pipeline runs all three stages in order, keeping the built jar as a pipeline artifact.

---

# Section 5 — Database Schema Design

## Scenario

The StreamWise platform currently stores viewer data in memory. The next step is to persist `viewers` and `watch_history` in a SQLite database. The ERD is in `resources/erd.png`.

`StreamSchema.createSchema(Connection connection)` is where the schema is created — the method body is currently empty.

| Task | Marks | What earns marks |
|---|---|---|
| Q5.1 — Create the `viewers` table | 8 | Correct columns and types (4), `id` as primary key, `username` and `email` as NOT NULL (4) |
| Q5.2 — Create the `watch_history` table | 8 | Correct columns and types (4), `id` as primary key, `viewer_id`, `title`, and `watched_at` as NOT NULL (4) |
| Q5.3 — Foreign key | 4 | `watch_history.viewer_id` correctly references `viewers.id` (4) |

---

### Q5.1 – Q5.3 — Create the Schema *(20 marks)*

Implement `StreamSchema.createSchema(Connection connection)` so that it creates the `viewers` and `watch_history` tables exactly as shown in the ERD, using standard SQL `CREATE TABLE` statements executed via JDBC.

**viewers table:**

| Column   | Type    | Constraints  |
|----------|---------|--------------|
| id       | INTEGER | PRIMARY KEY  |
| username | TEXT    | NOT NULL     |
| email    | TEXT    | NOT NULL     |

**watch_history table:**

| Column     | Type    | Constraints               |
|------------|---------|---------------------------|
| id         | INTEGER | PRIMARY KEY               |
| viewer_id  | INTEGER | NOT NULL, FK → viewers.id |
| title      | TEXT    | NOT NULL                  |
| watched_at | TEXT    | NOT NULL                  |
| rating     | INTEGER | (nullable)                |

> Run `mvn test` to check your schema against `StreamSchemaTest`.

---

### End of Assessment

---

## Project Structure

```
streamwise-summative/
  README.md
  answers.txt
  pom.xml
  Makefile
  .gitlab-ci.yml
  resources/
    erd.png
    SQL-Cheat-Sheet.pdf
  src/
    main/java/za/co/wethinkcode/streaming/
      model/
        ViewerActivity.java        ← abstract base class
        StreamSession.java         ← broken: missing extends + super()
        WatchlistEntry.java        ← broken: missing extends + super()
      service/
        RecommendationService.java ← contains two refactoring targets
        RecommendationResult.java
      Main.java
      Database.java
      StreamSchema.java
    test/java/za/co/wethinkcode/streaming/
      ViewerActivityTest.java
      StreamSessionTest.java
      WatchlistEntryTest.java
      RecommendationServiceTest.java
      StreamSchemaTest.java
```

## Useful Commands

```bash
mvn compile
mvn test
mvn package
java -jar target/streamwise-summative-jar-with-dependencies.jar

make compile
make test
make package
```
