package com.puzzle.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;


public class PuzzleGame extends Game {
    public static int currentLevel = 0;
	private Stage optionsStage;
	private Stage winStage;
	private Stage loseStage;
	private Screen levelChoiceScreen;
	public static TextButtonStyle tbs;
	public static PuzzleGame self;
	//public LevelScreen levelScreenLoader(){

    //}

    @Override
    public void create(){
        Gdx.input.setInputProcessor(new InputMultiplexer());
        tbs = new TextButtonStyle();
        Texture bt = new Texture(Gdx.files.internal("Buttons/PNGS/NumberButton.png"));
        NinePatch bp = new NinePatch(bt,40,40,40,40);
        bp.scale(2,1);
        tbs.up=new NinePatchDrawable(bp);
        tbs.font=new BitmapFont(Gdx.files.internal("UI/ubuntu.fnt"));
        tbs.fontColor= Color.GREEN;

        levelChoiceScreen = new LevelChoiceScreen();
        setScreen(levelChoiceScreen);
        super.render();
        for(int i = 0; i <9; i++){
            new LevelScreen();
        }
        self=this;
    }
    /*
    Initialize Game on Android
     */

}
