package com.puzzle.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class OptionsStage extends BaseStage {
    public OptionsStage(int p,boolean v){
        super(p, v);
    }
    private Table items;
    private void add(Actor actor){
        items.addActor(actor);
    }

}
