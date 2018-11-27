# Lukuvinkkikirjasto
Ohjelmistotuotanto-miniprojekti 2018

Travis
[![Build Status](https://travis-ci.com/antarcticturtle/Lukuvinkkikirjasto.svg?branch=master)](https://travis-ci.com/antarcticturtle/Lukuvinkkikirjasto)

[Product and Sprint Backlog](https://docs.google.com/spreadsheets/d/1_fpNIoz3RRlVcXJGW_1-ZIt02osNVSdqZ4tlo_d4EaQ/edit?usp=sharing)

## Definition of done
- Acceptance criteria for the user story are defined in product backlog
- Code meets the acceptance criteria and is integrated into the software
- Test coverage for the code is adequate with both JUnit and Cucumber tests
- Code follows good practices and is easy to maintain:
  - Software architecture is logical
  - Classes, packages, methods amd variables have meaningful names
  - Code style is consistent
- Code is in Github and Travis tests are passing

## Installing and running the application

Clone this repository to your computer.

In the base directory run the command `~/Lukuvinkkikirjasto$ ./gradlew jar` to build the jar file

Start the application with command `~/Lukuvinkkikirjasto$ java -jar build/libs/Lukuvinkkikirjasto.jar`

Run tests with command `~/Lukuvinkkikirjasto$ ./gradlew test`
