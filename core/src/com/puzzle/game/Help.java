package com.puzzle.game;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Help extends BaseScreen {

    private BaseStage stage;
    private static Help self;
    public Help(BaseScreen screen){
        super();
        stage = new BaseStage(1,true);
        stage.addToTable(new BaseActor("UI/inv.png"),10,10,true);
        stage.addToTable(new BaseActor("UI/Help.png"),100,25,false);
        stage.addToTable(new BaseActor("UI/inv.png"),20,10,true);
        stage.addToTable(new BaseActor("UI/greenHelp.png"),40,19,false);
        stage.addToTable(new BaseActor("UI/redHelp.png"),40,19,false);
        stage.addToTable(new BaseActor("UI/inv.png"),20,10,true);
        stage.addToTable(new BaseActor("UI/blueHelp.png"),40,19,false);
        stage.addToTable(new BaseActor("UI/yellowHelp.png"),40,19,false);
        stage.addToTable(new BaseActor("UI/inv.png"),40,10,true);
        stage.addToTable(new BaseActor("UI/orangeHelp.png"),40,19,false);

        BaseActor nextButton = new BaseActor("UI/Back.png");
        nextButton.addListener((Event e)->{

            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
                PuzzleGame.self.setScreen(screen);


            }
            return false;
        });
        stage.addToTable(new BaseActor("UI/inv.png"),10,10,true);
        stage.addToTable(nextButton,40,10,false);
        this.addStage(stage);
        self=this;
    }

}
