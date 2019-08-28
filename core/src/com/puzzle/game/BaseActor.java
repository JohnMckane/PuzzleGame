package com.puzzle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class BaseActor extends Group implements Drawable {
    public Polygon boundaryPolygon;
    public void setBondaryRectangle() {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0,0,w,0,w,h,0,h};
        boundaryPolygon = new Polygon(vertices);
    }
    public Polygon getBoundaryPolygon() {
        setBondaryRectangle();
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        boundaryPolygon.setRotation(getRotation());
        return boundaryPolygon;
    }
    public boolean overLaps(BaseActor other) {
        Polygon p1 = this.getBoundaryPolygon();
        Polygon p2 = other.getBoundaryPolygon();
        if(!p1.getBoundingRectangle().overlaps(p2.getBoundingRectangle())) {
            return false;
        }else {
            return Intersector.overlapConvexPolygons(p1,p2);
        }
    }
    public Vector2 getAbsolutePosition(){
        float x = this.getX();
        float y = this.getY();
        Actor a = this;
        while(a.hasParent()){
            a=a.getParent();
            x+=a.getX();
            y+=a.getY();
            System.out.println(""+a.getClass() + " "+a.getX()+" "+a.getY());
        }
        return new Vector2(x,y);
    }
    private TextureRegion texture;
    public TextureRegion getTexture(){
        return this.texture;
    }
    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        batch.draw(this.texture,getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public float getLeftWidth() {
        return this.getWidth();
    }

    @Override
    public void setLeftWidth(float leftWidth) {

    }

    @Override
    public float getRightWidth() {
        return 0;
    }

    @Override
    public void setRightWidth(float rightWidth) {

    }

    @Override
    public float getTopHeight() {
        return 0;
    }

    @Override
    public void setTopHeight(float topHeight) {

    }

    @Override
    public float getBottomHeight() {
        return 0;
    }

    @Override
    public void setBottomHeight(float bottomHeight) {

    }

    @Override
    public float getMinWidth() {
        return 0;
    }

    @Override
    public void setMinWidth(float minWidth) {

    }

    @Override
    public float getMinHeight() {
        return 0;
    }

    @Override
    public void setMinHeight(float minHeight) {

    }
    @Override
    public void drawChildren(Batch batch, float parentAlpha){
        for(Actor a : this.getChildren()){
            a.draw(batch,parentAlpha);
        }
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
        applyTransform(batch,computeTransform());
        this.drawChildren(batch,parentAlpha);
        resetTransform(batch);

        super.draw(batch,parentAlpha);


    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
    public void setTexture(String fileName) {

        this.texture = new TextureRegion(new Texture(Gdx.files.internal(fileName)));
        this.setWidth(texture.getRegionWidth());
        this.setHeight(texture.getRegionHeight());
    }
    public BaseActor(String filename){
        super();
        this.setTexture(filename);

    }
    public BaseActor(){
        super();
    }

    public BaseActor setColorR(float r, float g, float b, float a){
        super.setColor(r,g,b,a);
        return this;
    }


}

