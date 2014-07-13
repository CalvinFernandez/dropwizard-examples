.PHONY: all
all:
	@mvn package

.PHONY: compile
compile:
	@mvn test

.PHONY: test
test:
	@mvn test

.PHONY: clean
clean:
	@mvn clean

.PHONY: serve
serve:
	@mvn exec:java -Dexec.mainClass="app.Main" -Dexec.args="server conf/server.yml"

