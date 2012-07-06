package de.javandry.minigolfapp.parser;

import de.javandry.minigolfapp.entities.Ball;

public interface BalllisteParser {

    int getNumberOfBalls();

    Ball getBall(int i) throws BalllisteParserException;

}