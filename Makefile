# Makefile
.PHONY: compile test package clean

compile:
	mvn compile

test:
	mvn test

package:
	mvn package

clean:
	mvn clean
