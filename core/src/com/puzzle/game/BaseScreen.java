package com.puzzle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

public class BaseScreen implements Screen, InputProcessor {

    private ArrayList<BaseStage> stages;
    public void addStage(BaseStage stage){
        if(this.stages.size()==0){
            this.stages.add(stage);
        }
    }
    @Override
    public void show(){
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        if(im==null) {
            System.out.println("im is null");
        }
        if(this==null){
            System.out.println("this is null");
        }
        im.addProcessor(this);
        for (BaseStage stage : stages){
            im.addProcessor(stage);
        }
    }

    public BaseScreen() {
        this.stages = new ArrayList<BaseStage>();
    }

    @Override
    public void render(float dt){
        for(BaseStage b : stages){
            b.act(dt);
        }

        Gdx.gl.glClearColor(5,5,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(BaseStage b : stages){
            b.draw();
        }
    }

    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        for (BaseStage stage : stages){
            im.removeProcessor(stage);
        }
    }

    @Override
    public void dispose() {

    }
    @Override
    public void resize(int dx, int dy){

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
