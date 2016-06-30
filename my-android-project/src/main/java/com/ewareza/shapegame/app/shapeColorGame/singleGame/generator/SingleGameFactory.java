package com.ewareza.shapegame.app.shapeColorGame.singleGame.generator;

import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.shapeColorGame.singleGame.SingleColorGame;
import com.ewareza.shapegame.app.shapeColorGame.singleGame.SingleGame;
import com.ewareza.shapegame.app.shapeColorGame.singleGame.SingleGameState;
import com.ewareza.shapegame.app.shapeColorGame.singleGame.SingleShapeGame;
import com.ewareza.shapegame.resources.SoundResources;
import com.ewareza.shapegame.shape.objects.Shape;

import java.util.List;

public class SingleGameFactory {
    private static final Object lock = new Object();
    private static final RandomShapesGenerator shapesGenerator = RandomShapesGenerator.getInstance();

    public static SingleGame createNewSingleGame() {
        List<Shape> shapes = generateShapes();
        SingleGameState singleGameState = new SingleGameState(shapes);

        //@TODO take game type from some list, in file or class
        if (shouldGenerateColorGame()) {
            return generateSingleColorGame(singleGameState, shapes);
        } else {
            return generateSingleShapeGame(singleGameState);
        }
    }

    private static SingleShapeGame generateSingleShapeGame(SingleGameState singleGameState) {
        SingleShapeGame singleShapeGame = new SingleShapeGame(singleGameState, shapesGenerator.getCurrentLookedForShapeGenerator());
        SoundResources.INSTANCE.playGameTitleSound(singleShapeGame.getCurrentLookedForObject());

        return singleShapeGame;
    }

    private static SingleColorGame generateSingleColorGame(SingleGameState singleGameState, List<Shape> shapes) {
        SingleColorGame singleColorGame = new SingleColorGame(singleGameState, shapes.get(0).getColor());

        return singleColorGame;
    }

    private static boolean shouldGenerateColorGame() {
        return Game.getGameNumber() > Game.getNumberOfDifferentGameSpeeds();
    }

    private static List<Shape> generateShapes() {
        synchronized (lock) {
            return shapesGenerator.generateShapes();
        }
    }
}
