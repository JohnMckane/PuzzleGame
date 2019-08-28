package com.puzzle.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MyListner extends ClickListener {
    public static BaseActor parent = new BaseActor();
    private  Vector2 tmp = new Vector2();

    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        tmp=event.getTarget().localToParentCoordinates(tmp.set(x, y));
        Actor releaseOverActor = parent.hit(tmp.x, tmp.y, true);
        if (releaseOverActor != null){
            System.out.println(releaseOverActor.getName());
        }
        return true;
    }

    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        tmp=event.getTarget().localToParentCoordinates(tmp.set(x, y));
        Actor releaseOverActor = parent.hit(tmp.x, tmp.y, true);
        if (releaseOverActor != null){
           System.out.println(releaseOverActor.getName());
        }
    }
}

