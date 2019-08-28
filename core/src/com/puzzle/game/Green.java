package com.puzzle.game;

import com.badlogic.gdx.graphics.Color;

import java.util.concurrent.atomic.AtomicInteger;


public class Green extends Tile {

    private static Integer n = 0;
    private static boolean sd = false;
    //public static Spawn spawn=()->new Green();
    //private static Spawner spawner = new Spawner(spawn,"Levels/green.png", Color.WHITE);
    public Green(AtomicInteger n){
        super(n);
        this.setTexture("Levels/green.png");
        //spawner.setText("x"+n);
    }
    public static boolean isSpawnerDown() {
        return sd;
    }


   // public static Spawner getSpawner() {
        //return spawner;
   // }

    @Override
    public boolean isCorrectSquare(Empty e) {
        return true;
    }
}
