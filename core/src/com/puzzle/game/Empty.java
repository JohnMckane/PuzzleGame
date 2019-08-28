package com.puzzle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Empty extends BaseActor {
   public Spawner s;
    //private MyListner listner = new MyListner();
    public static Spawn spawn;
    private int posX;
    public ArrayList<Integer> pos;
    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        grid.put(pos,this);
    }

    private int posY;
    private Tile tile;
    private static float originX = 0;
    private static  float originY = 0;
    public static ConcurrentHashMap<ArrayList<Integer>,Empty> grid = new ConcurrentHashMap<ArrayList<Integer>,Empty>();

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Empty(float size){
        super("Levels/tile.png");

        this.setSize(size,size);
        this.setVisible(true);
        System.out.println(this.getX()+" "+this.getY()+" "+this.getWidth());
        Empty self=this;
        //this.addListener(listner);
        this.setName("Empty "+grid.size());

        this.addListener((Event e)->{
            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
                self.setTile(null);
                if(Empty.spawn!=null) {
                    self.addTileToEmpty(Empty.spawn.spawn());
                }

            }
            return false;
        });


    }
    @Override
    public void setPosition(float x, float y){
        super.setPosition(x,y);
        if(grid.size()==0){
            originX = this.getX();
            originY = this.getY();
        }
        posX = Math.round((getX()-Empty.originX)/getWidth());
        posY = Math.round((getY()-Empty.originY)/getHeight());
        ArrayList<Integer> k = new ArrayList<Integer>();
        k.add(getPosX());
        k.add(getPosY());
        grid.put(k,this);
    }
    public void addTileToEmpty(Tile tile){

        if(tile.getN().get()<=-1||!(tile.isCorrectSquare(this))){
            tile.delete();
        }else {
            this.tile=tile;
            this.addActorAt(0, tile);
            tile.setPosition(0, 0);
            tile.setWidth(this.getWidth());
            tile.setHeight(this.getHeight());
            tile.setVisible(true);
            this.setTile(tile);

            for (Empty a : Empty.getList().values()) {
                if(a.getTile()!=null)
                System.out.println("my value "+a.getTile().getClass().toString());
            }
            grid.put(pos,this);
            if(!Empty.allOk()) {
                this.setTile(null);
                tile.delete();
                grid.put(pos, this);
            }else{
                if(s.allUsed()){
                    System.out.println("You win");
                    WinStage.levelComplete();
                    Empty.reset();
                }else{
                    System.out.println("not quite");
                }
            }
        }


        System.out.println("texture changed");
        Spawner.updateText();



    }

    public static ConcurrentHashMap<ArrayList<Integer>, Empty> getList(){
        return grid;
    }
    public static boolean allOk(){
        for(Empty e : Empty.getList().values()){
            if(e.getTile()!=null&&!e.getTile().isCorrectSquare(e)){
                return false;
            }
        }
        return true;
    }
public static void reset(){
System.out.println("in reset ");
            for(Empty e : Empty.getList().values()){
                if(e.getTile()!=null){
                e.getTile().delete();
}
                e.setTile(null);
System.out.println("reset a tile");
        }
        Spawner.updateText();
    }
}
