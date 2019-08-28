package com.puzzle.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class LevelScreentest extends BaseScreen {
    private ArrayList<Spawner> sl= new ArrayList<Spawner>();
    private AtomicInteger redCount = new AtomicInteger(0);
    private AtomicInteger greenCount = new AtomicInteger(0);
    private AtomicInteger orangeCount = new AtomicInteger(0);
    private AtomicInteger blueCount = new AtomicInteger(0);
    private AtomicInteger yellowCount = new AtomicInteger(0);
    private Boolean redSpawnerDown = false;
    private Boolean blueSpawnerDown = false;
    private Boolean greenSpawnerDown = false;
    private Boolean orangeSpawnerDown = false;
    private Boolean yellowSpawnerDown = false;
    public  Spawn greenSpawn=()->new Green(greenCount);
    private  Spawner greenSpawner = new Spawner(greenSpawn,"Levels/green.png","Levels/greenSelected.png", Color.WHITE,greenCount);


    public  Spawn blueSpawn=()->new Blue(blueCount);
    private  Spawner blueSpawner = new Spawner(blueSpawn,"Levels/blue.png","Levels/blueSelected.png", Color.WHITE,blueCount);
    public  Spawn redSpawn=()->new Red(redCount);
    private  Spawner redSpawner = new Spawner(redSpawn,"Levels/red.png","Levels/redSelected.png", Color.WHITE,redCount);
    public  Spawn orangeSpawn=()->new Orange(orangeCount);
    private  Spawner orangeSpawner = new Spawner(orangeSpawn,"Levels/orange.png","Levels/orangeSelected.png", Color.WHITE,orangeCount);
    public  Spawn yellowSpawn=()->new Yellow(yellowCount);
    private  Spawner yellowSpawner = new Spawner(yellowSpawn,"Levels/yellow.png","Levels/yellowSelected.png", Color.WHITE,yellowCount);
    private static ArrayList<LevelScreentest> levels = new ArrayList<LevelScreentest>();
    private static int nLevels = 0;
    private TiledMap tiledMap;
    private BaseStage mainStage;
    private BaseActor grid;
    private ArrayList<Spawner> spawners = new ArrayList<Spawner>();
    private void spawnerAndCounter(Boolean sd,Spawner spawner,Integer count){
        //if (!greenSpawnerDown){
        //  System.out.println("added a green spawner");
        //greenSpawner.setVisible(true);
        // greenSpawner.setTransform(true);
        //spawners.add(greenSpawner);
        // System.out.println("\n\n\n!!!!!!!!spawner added!!!n\n\n");
        //greenSpawnerDown = true;
        //}
        //greenSpawner.setText("x"+greenCount);
        //greenCount++;
        if (!sd){
            spawner.setVisible(true);
            spawner.setTransform(true);
            spawners.add(spawner);
            sd = true;
        }
        count++;
        spawner.setText("x"+count);
    }
    public LevelScreentest(){
        super();
        greenSpawnerDown=false;
        redSpawnerDown=false;
        orangeSpawnerDown=false;
        blueSpawnerDown=false;
        yellowSpawnerDown=false;
        nLevels++;
        mainStage = new BaseStage(1,true);
        mainStage.addToTable(new Banner(), 100, 20, true);
        levels.add(this);
        this.addStage(mainStage);
        tiledMap = new TmxMapLoader().load("Levels/level"+nLevels+".tmx");
        System.out.println("Levels/level"+nLevels+".tmx");
        grid = new BaseActor();
        grid.setTexture("badlogic.jpg");
        grid.setSize((Integer)tiledMap.getProperties().get("width"),(Integer)tiledMap.getProperties().get("height"));
        grid.setColor(Color.BLUE);

        mainStage.addToTableC(grid,80,60,true);

        int squareSize = (int)grid.getWidth()/(Integer) tiledMap.getProperties().get("width");

        int  ssFile=(Integer)tiledMap.getProperties().get("tilewidth");

        BaseActor spawnerTable = new BaseActor("Levels/spawnerbg.png");
        for(MapLayer layer : tiledMap.getLayers()){
            System.out.println("in next layer");

            for(MapObject obj : layer.getObjects()){
                System.out.println("in next object");
                if(!(obj instanceof TiledMapTileMapObject)){
                    System.out.println("continuing");
                    continue;
                }
                System.out.println("making");
                TiledMapTileMapObject tmtmo = (TiledMapTileMapObject)obj;
                TiledMapTile t = tmtmo.getTile();
                MapProperties propt = t.getProperties();
                if(propt.containsKey("Class")){
                    System.out.println("making object");
                    propt=obj.getProperties();
                    if(propt==null){
                        System.out.println("propt is null");
                    }
                    if(propt.get("x")==null){
                        System.out.println("x is null");
                    }
                    if(propt.get("y")==null){
                        System.out.println("y is null");
                    }
                    System.out.println(ssFile);
                    Empty e= new Empty(squareSize);
                    ArrayList<Integer> a = new ArrayList<Integer>();
                    a.add(Math.round(((Float)propt.get("x"))/ssFile));
                    a.add(Math.round(((Float)propt.get("y"))/ssFile));
                    System.out.println("Empty xy ref "+a.get(0)+" "+a.get(1));
                    Empty.grid.put(a,e);
                    e.s=greenSpawner;
                    e.pos=a;
                    grid.addActor(e);
                    e.setPosition(((Float)propt.get("x")/ssFile)*squareSize,((Float)propt.get("y")/ssFile)*squareSize);
                    System.out.println("grid "+grid.getX());
                    String className = propt.get("Class",String.class);
                    if(className==null){
                        System.out.println("\n\n\nClassname null \n\n\n");
                    }
                    if(className!=null&&className.equals("Red")){
                        if (!redSpawnerDown){
                            if(nLevels==2){
                                System.out.println("red was put down");
                            }
                            System.out.println("added a red spawner");
                            redSpawner.setVisible(true);
                            redSpawner.setTransform(true);
                            spawners.add(redSpawner);
                            System.out.println("\n\n\n!!!!!!!!spawner added!!!n\n\n");
                            redSpawnerDown = true;
                            sl.add(redSpawner);

                        }
                        redCount.addAndGet(1);
                        redSpawner.setText("x"+redCount);
                        redSpawner.count=redCount;

                    }
                    if(className!=null&&className.equals("Blue")){
                        if (!blueSpawnerDown){


                            blueSpawner.setVisible(true);
                            blueSpawner.setTransform(true);
                            spawners.add(blueSpawner);
                            System.out.println("\n\n\n!!!!!!!!spawner added!!!n\n\n");
                            blueSpawnerDown = true;
                            sl.add(blueSpawner);

                        }
                        blueCount.addAndGet(1);
                        blueSpawner.setText("x"+blueCount);
                        blueSpawner.count=blueCount;

                    }
                    if(className!=null&&className.equals("Green")){
                        if (!greenSpawnerDown){
                            if(nLevels==2){
                                System.out.println("green was put down");
                            }
                            System.out.println("added a green spawner");
                            greenSpawner.setVisible(true);
                            greenSpawner.setTransform(true);
                        spawners.add(greenSpawner);
                            System.out.println("\n\n\n!!!!!!!!spawner added!!!n\n\n");
                            greenSpawnerDown = true;
                            sl.add(greenSpawner);


                        }

                        greenCount.addAndGet(1);
                        greenSpawner.setText("x"+greenCount);
                        greenSpawner.count=greenCount;

                    }
                    if(className!=null&&className.equals("Orange")){
                        if (!orangeSpawnerDown){

                            System.out.println("added a orange spawner");
                            orangeSpawner.setVisible(true);
                            orangeSpawner.setTransform(true);
                            spawners.add(orangeSpawner);
                            System.out.println("\n\n\n!!!!!!!!spawner added!!!n\n\n");
                            orangeSpawnerDown = true;
                            sl.add(orangeSpawner);


                        }

                        orangeCount.addAndGet(1);
                        orangeSpawner.setText("x"+orangeCount);
                        orangeSpawner.count=orangeCount;

                    }
                    if(className!=null&&className.equals("Yellow")){
                        if (!yellowSpawnerDown){
                            yellowSpawner.setVisible(true);
                            yellowSpawner.setTransform(true);
                            spawners.add(yellowSpawner);
                            System.out.println("\n\n\n!!!!!!!!spawner added!!!n\n\n");
                            yellowSpawnerDown = true;
                            sl.add(yellowSpawner);


                        }

                        yellowCount.addAndGet(1);
                        yellowSpawner.setText("x"+yellowCount);
                        yellowSpawner.count=yellowCount;

                    }
                }
            }
        }
        greenSpawner.setGroup(sl);
        redSpawner.setGroup(sl);
        orangeSpawner.setGroup(sl);
        blueSpawner.setGroup(sl);
        yellowSpawner.setGroup(sl);
        System.out.println(spawners.size());
        mainStage.addToTable(grid,70,60,true);
        float y =0;
        for(Spawner spawner : spawners) {
            spawnerTable.addActor(spawner);
            spawner.setPosition(0,y);
            y+=spawner.getHeight();

        }
        mainStage.addToTable(spawnerTable,30,60,false);
        TextButton tb = new TextButton("Help" ,PuzzleGame.tbs);
        tb.setTransform(true);
        tb.addListener((Event e)->{

            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
                PuzzleGame.self.setScreen(new Help(this));
            }
            return false;
        });
        TextButton tb2 = new TextButton("Reset" ,PuzzleGame.tbs);
        tb2.setTransform(true);
        tb2.addListener((Event e)->{

            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
                int i = 0;
                for(Empty tile : Empty.getList().values()){
                    i++;
                    if(tile.getTile()!=null) {
                        tile.getTile().delete();
                        Spawner.updateText();
                        System.out.println("tile wsnt null");
                    }
                    System.out.println(" in reset loop "+i);
                }
            }
            return false;
        });
        TextButton tb3 = new TextButton("Quit" ,PuzzleGame.tbs);
        tb3.setTransform(true);
        tb3.addListener((Event e)->{

            if((e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown)) {
                PuzzleGame.self.setScreen(LevelChoiceScreen.self);
            }
            return false;
        });
        mainStage.addToTable(tb3,30,30,true);
        mainStage.addToTable(tb2,30,30,false);
        mainStage.addToTable(tb,30,30,false);



        System.out.println("Grid Positon"+grid.getX()+grid.getY());


        for(Actor a : grid.getChildren()){
            System.out.println("child");


        }
        grid.setName("grid");


    }
    public static BaseScreen getLevel(int i){
        if(levels.size()>i){
            return levels.get(i);
        }else{
            System.out.println("levs size was "+levels.size()+"i was "+i);
            return LevelChoiceScreen.self;
        }
    }


}
