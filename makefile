compile:
	@javac -d build src/*.java
run:
	@java -cp build Minesweeper
clean:
	@rm build/*.class; rm .DS_Store; rm src/.DS_Store; rm build/.DS_Store; rm releases/.DS_Store; rm images/.DS_Store
