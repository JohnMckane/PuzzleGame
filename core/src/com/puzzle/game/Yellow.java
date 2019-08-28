package com.puzzle.game;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Yellow extends Tile {

    private static boolean sd = false;
    //public  Spawn spawn=()->{return new Red(Integer)};
    //private  Spawner spawner = new Spawner(spawn,"Levels/red.png", Color.WHITE);
    public Yellow(AtomicInteger n){
        super(n);
        this.setTexture("Levels/yellow.png");
        //spawn=()->new Red();
        //new Spawner(spawn,"Levels/red.png", Color.WHITE);
        //spawner.setText("x"+n);
    }
    @Override
    public boolean isCorrectSquare(Empty e) {
        //Check if on an edge
        boolean nB=false;
        boolean nG=false;

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
                if (Empty.getList().get(k).getTile()!=null&&(Empty.getList().get(k).getTile().getClass().equals(Blue.class))) {
                    nB = true;

                }
            }

        }
        ArrayList<Integer> leftg = new ArrayList<Integer>();
        leftg.add(e.getPosX() - 2);
        leftg.add(e.getPosY());
        ArrayList<Integer> rightg = new ArrayList<Integer>();
        rightg.add(e.getPosX()+2);
        rightg.add(e.getPosY());
        ArrayList<Integer> topg = new ArrayList<Integer>();
        topg.add(e.getPosX());
        topg.add(e.getPosY() + 2);
        ArrayList<Integer> bottomg = new ArrayList<Integer>();
        bottomg.add(e.getPosX());
        bottomg.add(e.getPosY() - 2);
        keys = new ArrayList<ArrayList<Integer>>();
        keys.add(leftg);
        keys.add(rightg);
        keys.add(topg);
        keys.add(bottomg);


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
                if (Empty.getList().get(k).getTile()!=null&&(Empty.getList().get(k).getTile().getClass().equals(Green.class))) {
                    nG = true;

                }
            }

        }
        System.out.println(nB);
        System.out.println(nG);

        return nB&&nG;

    }

    public static boolean isSpawnerDown() {
        return sd;
    }


    //public  Spawner getSpawner() {
      //  sd = true;
        //return spawner;
    //}

}
