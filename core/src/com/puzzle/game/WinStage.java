package com.puzzle.game;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class WinStage extends BaseScreen {
    private static WinStage self;
    private BaseStage stage;
    public WinStage(){
        super();
        stage = new BaseStage(1,true);
        stage.addToTable(new BaseActor("UI/LevelComplete.png"),100,30,true);
        TextButton button = new TextButton("Next Level",PuzzleGame.tbs);
        button.addListener((Event e)->{

            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
                PuzzleGame.currentLevel++;
                PuzzleGame.self.setScreen(LevelScreen.getLevel(PuzzleGame.currentLevel));


            }
            return false;
        });
        TextButton nextButton = new TextButton("Choose Level",PuzzleGame.tbs);
        nextButton.addListener((Event e)->{

            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
            PuzzleGame.self.setScreen(LevelChoiceScreen.self);


            }
            return false;
        });
        stage.addToTable(button,50,30,true);
        stage.addToTable(nextButton,50,30,true);
        this.addStage(stage);
    }
    public static void levelComplete(){
        self = new WinStage();
        PuzzleGame.self.setScreen(self);
    }
}
