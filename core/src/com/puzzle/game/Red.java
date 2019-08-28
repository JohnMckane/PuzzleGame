package com.puzzle.game;

import com.badlogic.gdx.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Red extends Tile {

    private static boolean sd = false;
    //public  Spawn spawn=()->{return new Red(Integer)};
    //private  Spawner spawner = new Spawner(spawn,"Levels/red.png", Color.WHITE);
    public Red(AtomicInteger n){
        super(n);
        this.setTexture("Levels/red.png");
        //spawn=()->new Red();
        //new Spawner(spawn,"Levels/red.png", Color.WHITE);
        //spawner.setText("x"+n);
    }
    @Override
    public boolean isCorrectSquare(Empty e) {
        //Check if on an edge

        ArrayList<Integer> left = new ArrayList<Integer>();
        left.add(e.getPosX() - 1);
        left.add(e.getPosY());
        ArrayList<Integer> right = new ArrayList<Integer>();
        right.add(e.getPosX()+1);
        right.add(e.getPosY());
        ArrayList<Integer> top = new ArrayList<Integer>();
        top.add(e.getPosX());
        top.add(e.getPosY() + 1);
        ArrayList<Integer> bottom = new ArrayList<Integer>();
        bottom.add(e.getPosX());
        bottom.add(e.getPosY() - 1);
        ArrayList<ArrayList<Integer>> keys = new ArrayList<ArrayList<Integer>>();
        keys.add(left);
        keys.add(right);
        keys.add(top);
        keys.add(bottom);


        for (ArrayList<Integer> k : keys) {
            System.out.println("key "+k.get(0)+" "+k.get(1));
            for(ArrayList<Integer> a :Empty.getList().keySet()) {
                System.out.println("KEY"+a.get(0)+" "+a.get(1));
                if(!a.equals(k)){
                    //System.out.println("not wright key");
                    continue;

                }
                System.out.println(" had key");
                if(Empty.getList().get(k).getTile()==null){
                    System.out.println("empt.ygetlist.getknull");
                }
                if (Empty.getList().get(k).getTile()!=null&&!(Empty.getList().get(k).getTile().getClass().equals(this.getClass()))) {
                   // System.out.println("Correct squar e");
                    System.out.println("this class is "+this.getClass());
                    if(Empty.getList().get(k).getTile()!=null){
                        System.out.println("other class is " + Empty.getList().get(k).getTile().getClass());
                    }
                    return true;

                }
            }

        }
        System.out.println("InCorrect squar e");
        return false;
    }

    public static boolean isSpawnerDown() {
        return sd;
    }


    //public  Spawner getSpawner() {
      //  sd = true;
        //return spawner;
    //}

}
