package com.puzzle.game;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Blue extends Tile {

    private static boolean sd = false;
    //public  Spawn spawn=()->{return new Red(Integer)};
    //private  Spawner spawner = new Spawner(spawn,"Levels/red.png", Color.WHITE);
    public Blue(AtomicInteger n){
        super(n);
        this.setTexture("Levels/blue.png");
        //spawn=()->new Red();
        //new Spawner(spawn,"Levels/red.png", Color.WHITE);
        //spawner.setText("x"+n);
    }
    @Override
    public boolean isCorrectSquare(Empty e) {
        //Check if on an edge
        boolean nB=false;
        boolean nR=false;
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
                if (Empty.getList().get(k).getTile()!=null&&(Empty.getList().get(k).getTile().getClass().equals(Red.class))) {
                    nR = true;

                }else if (Empty.getList().get(k).getTile()!=null&&(Empty.getList().get(k).getTile().getClass().equals(Orange.class))) {
                    nB = true;

                }
                else if (Empty.getList().get(k).getTile()!=null&&(Empty.getList().get(k).getTile().getClass().equals(Green.class))) {
                    nG = true;

                }
            }

        }
        System.out.println(nB);
        System.out.println(nG);
        System.out.println(nR);
        return nB&&nG&&nR;

    }

    public static boolean isSpawnerDown() {
        return sd;
    }


    //public  Spawner getSpawner() {
      //  sd = true;
        //return spawner;
    //}

}
