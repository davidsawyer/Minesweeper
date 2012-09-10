compile:
	@javac -d build src/*.java
run:
	@java -cp build Minesweeper
jar:
	@cd build; jar cmf MainClass.txt Minesweeper.jar *.class; mv Minesweeper.jar ../releases; cd ..
clean:
	@rm build/*.class; rm .DS_Store; rm src/.DS_Store; rm build/.DS_Store; rm releases/.DS_Store; rm images/.DS_Store
