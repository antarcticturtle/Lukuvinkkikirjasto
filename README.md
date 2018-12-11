# Lukuvinkkikirjasto
Ohjelmistotuotanto-miniprojekti 2018

Travis
[![Build Status](https://travis-ci.com/antarcticturtle/Lukuvinkkikirjasto.svg?branch=master)](https://travis-ci.com/antarcticturtle/Lukuvinkkikirjasto)

Codecov
[![codecov](https://codecov.io/gh/antarcticturtle/Lukuvinkkikirjasto/branch/master/graph/badge.svg)](https://codecov.io/gh/antarcticturtle/Lukuvinkkikirjasto)

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

### Downloading the application

Download the most recent version of the application by saving the file [Lukuvinkkikirjasto.jar](https://github.com/antarcticturtle/Lukuvinkkikirjasto/releases/download/Sprint4/Lukuvinkkikirjasto.jar) to your computer.

Navigate to the folder containing the jar file and start the application with command
`java -jar Lukuvinkkikirjasto.jar`

A database file items.db will be created automatically to the same folder, if it doesn't already exist. If you remove the database file, a new empty database file will be created next time you run the application.

### Cloning the repository

Clone this repository to your computer.

In the base directory run the command `~/Lukuvinkkikirjasto$ ./gradlew jar` to build the jar file

Start the application with command `~/Lukuvinkkikirjasto$ java -jar build/libs/Lukuvinkkikirjasto.jar`

Run tests with command `~/Lukuvinkkikirjasto$ ./gradlew test`

## License

This project is licensed under the terms of the MIT license.