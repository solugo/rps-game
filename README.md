#Rock Paper Scissors

This is an implementation of the game "Rock Paper Scissors" according to the rules describe in 
the [Wikipedia article](https://en.wikipedia.org/wiki/Rock_paper_scissors).

## Run

The game can be compiled and executed by running
```bash
./gradlew run
```

## Executable Jar

An executable jar can be created by running
```bash
./gradlew shadowJar
```
The jar can then be executed using
```bash
java -jar ./build/libs/rps-game-<version>.jar --help
```