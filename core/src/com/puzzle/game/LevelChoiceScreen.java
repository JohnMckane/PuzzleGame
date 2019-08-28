package com.puzzle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.Stack;



public class LevelChoiceScreen extends BaseScreen {
    public static LevelChoiceScreen self;
    private BaseStage levelChoices;
    public LevelChoiceScreen(){

        levelChoices = new BaseStage(1,true);

        levelChoices.addToTable(new Banner(),100f,25f,true);
        boolean newLine = true;

        for(int i = 1; i <= 9; i++){
            Integer I = new Integer(i-1);
            TextButton tb = new TextButton("Level: " +i,PuzzleGame.tbs);
            tb.setTransform(true);
            System.out.println("listner added");
            tb.addListener((Event e)->{

                if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
                    PuzzleGame.self.setScreen(LevelScreen.getLevel(I));
                    System.out.println("touched");
                    PuzzleGame.currentLevel=I;
                }
                return false;
            });

            levelChoices.addToTable(tb,33,19,newLine);
            newLine = i%3==0;
        }
        TextButton tb = new TextButton("Help" ,PuzzleGame.tbs);
        tb.setTransform(true);
        tb.addListener((Event e)->{

            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
                PuzzleGame.self.setScreen(new Help(LevelChoiceScreen.self));
                Empty.reset();
            }
            return false;
        });


        levelChoices.addToTable(tb,33,15,true);
        this.addStage(levelChoices);
        self=this;
    }
}
