package com.puzzle.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Tile extends BaseActor{

    private AtomicInteger n;
    private float startPositionX,startPositionY;
    private float offsetX, offsetY;
    private Tile self;
    public  AtomicInteger getN(){
        return n;
    };
    public float getStartPositionX(){
        return this.startPositionX;
    }
    public void setStartPositionX(float x){
        this.startPositionX=x;
    }
    public float getStartPositionY(){
        return this.startPositionY;
    }
    public void setStartPositionY(float y){
        this.startPositionY=y;
    }
    public void onDragStart(){

    }
    public void onDragStop(){

    }

    public void incrementN() {
        n.addAndGet(1);
     //   spawner.setText("x"+n);
    }


    public void decrementN() {
        n.addAndGet(-1);
    //    spawner.setText("x"+n);
    }
    public Tile(AtomicInteger n) {

        super();

        this.setTransform(true);
        this.setVisible(true);
        this.n=n;
        this.decrementN();
        System.out.println(n+" new tile");
        self = this;
        addListener(new InputListener() {
            public boolean touchDown(InputEvent e, float ofSetX, float ofSetY, int pointer, int button) {

             self.delete();
             Spawner.updateText();
                return true;
            }
        });
    }
    private Empty currentSquare;
    public  boolean isCorrectlyPlaced(){
        if(isCorrectSquare(currentSquare)){
            return true;
        }else{
            return false;
        }
    }
    public abstract boolean isCorrectSquare(Empty e);

    public void letGo() {
        System.out.println("has been let go");
        Empty place=null;
        //check which empty you are over
            for(Empty e : Empty.getList().values()) {
                    System.out.println("checking places");
                    if(e == null){
                        continue;
                    }

                    if (this.overLaps(e)) {
                        place = e;
                        break;
                    }

                }

            if(place == null){
                System.out.println("deleted as place was null");
                delete();
                return;
            }

        //check if this corresponds to a valid grid place
            if(isCorrectSquare(place)) {
                //if it does place it there;
                place.addTileToEmpty(this);
                System.out.println("tile added to empty");
            }else{
                System.out.println("deleted as place was incorrect");
                delete();
            }
            //if not delete
    }
    public void delete(){
        System.out.println(" deleted");
        this.remove();
        incrementN();
        if(this.getParent()!=null){
            System.out.println("deleted correctly ");
            ((Empty)this.getParent()).setTile(null);
        }
    }
    public float getCenterX(){
        return this.getX() + (this.getWidth()/2);
    }
    public float getCenterY(){
        return this.getY() + (this.getHeight()/2);
    }
}
