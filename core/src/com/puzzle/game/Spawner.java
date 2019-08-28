package com.puzzle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Spawner extends TextButton {
    public ArrayList<Spawner> group = new ArrayList<Spawner>();
    public void setGroup(ArrayList<Spawner>g){
        group=g;
    }
    private Texture bt;
    public Texture getBt(){return bt;};
    public AtomicInteger count;
    private Spawn spawn;
    private TextButton.TextButtonStyle normal = new TextButton.TextButtonStyle();
    private TextButton.TextButtonStyle selected = new TextButton.TextButtonStyle();
public static ArrayList<Spawner> all = new ArrayList<Spawner>();
    public static void setAll(ArrayList<Spawner> a){
        all= a;
        System.out.println("Spawner.all set " +all.size());
    }
    public Spawner(Spawn a, String normal, String selected, Color color, AtomicInteger count){

        super("", PuzzleGame.tbs);
        this.count=count;

        this.setSize(60,60);
        this.setVisible(true);
        System.out.println("new spawner");
        this.spawn = a;
        bt = new Texture(Gdx.files.internal(normal));
        NinePatch bp = new NinePatch(bt,0,0,0,0);
        this.normal.up=new NinePatchDrawable(bp);
        this.normal.font=new BitmapFont(Gdx.files.internal("UI/ubuntu.fnt"));
        this.normal.fontColor= color;
        Texture bt1 = new Texture(Gdx.files.internal(selected));
        NinePatch bp1 = new NinePatch(bt1,0,0,0,0);
        this.selected.up=new NinePatchDrawable(bp1);
        this.selected.font=new BitmapFont(Gdx.files.internal("UI/ubuntu.fnt"));
        this.selected.fontColor= color;

        this.setStyle(this.normal);
        Spawner self = this;
        all.add(this);
        Pixmap pm = new Pixmap(32,32,Pixmap.Format.RGBA8888);
        pm.drawPixmap(new Pixmap(Gdx.files.internal(normal)),0,0);
        this.addListener((Event e)->{
            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {


                Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
                Empty.spawn=a;
                for(Spawner s : Spawner.all){
                    s.setStyle(s.normal);
                }
                self.setStyle(self.selected);
                self.setText("x"+self.count);


            }
            return false;
        });
    }
    public static void updateText(){
        for(Spawner s : Spawner.all){
            s.setText("x"+s.count);
        }
    }
    public boolean allUsed(){
        System.out.println("There are spawners "+all.size());
        for(Spawner s : group){
                System.out.println("Count "+s.count.get());
        }
        for(Spawner s : group){

            if(s.count.get()!=0){

                return false;
            }
        }
        return true;
    }

}
