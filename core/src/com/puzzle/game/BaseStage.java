package com.puzzle.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class BaseStage extends Stage {
    private int priority;
    private boolean visible;
    private Table table;
    private Table currentTable;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isVisible() {
        return visible;
    }

    public BaseStage(int p, boolean v) {
        priority = p;
        visible = v;
        table = new Table();
        this.addActor(table);
        table.setFillParent(true);
        currentTable = table;

    }

    public void addToTableC(Actor a, float px, float py, boolean row) {
        float maxX = Gdx.graphics.getWidth() * px * 0.01f;
        float maxY = Gdx.graphics.getHeight() * py * 0.01f;
        float ratio = a.getWidth() / a.getHeight();
        float width = Math.min(maxX, maxY * ratio);
        float height = width / ratio;
        a.setSize(width, height);
    }
    public void addToTable(Actor a, float px, float py, boolean row) {
        float maxX = Gdx.graphics.getWidth() * px * 0.01f;
        float maxY = Gdx.graphics.getHeight() * py * 0.01f;
        float ratio = a.getWidth() / a.getHeight();
        float width = Math.min(maxX, maxY * ratio);
        float height = width / ratio;

        a.setSize(width, height);

        if (row) {
            table.row();
            currentTable = new Table();
            table.add(currentTable).top().left();

        }
        float pw = (maxX - width) / 2;
        float ph = (maxY - height) / 2;
        currentTable.add(a).padLeft(pw).padRight(pw).padTop(ph).padBottom(ph).top().left().width(width+pw).height(height+ph).top().left();



    }

    public void addToTable(TextButton a, float px, float py, boolean row) {
        float maxX = Gdx.graphics.getWidth() * px * 0.01f;
        float maxY = Gdx.graphics.getHeight() * py * 0.01f;
        float ratio = a.getWidth() / a.getHeight();
        float width = Math.min(maxX, maxY * ratio);
        float height = width / ratio;

        a.setSize(width, height);

        if (row) {
            table.row();
            currentTable = new Table();
            table.add(currentTable).center();

        }
        float pw = (maxX - width) / 2;
        float ph = (maxY - height) / 2;
        a.getLabel().setFontScaleX(0.07f*width/a.getText().length());
        currentTable.add(a).padLeft(pw).padTop(ph).padBottom(ph).top().left().width(width+pw).height(height+ph);



    }
    public Table getTable(){
        return this.table;
    }
    @Override
    public void draw(){
        super.draw();
    }

}

