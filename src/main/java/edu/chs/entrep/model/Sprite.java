package edu.chs.entrep.model;

import javafx.geometry.Rectangle2D;

/**
 * Created by niklasohlsson on 2017-04-07.
 * This class is a parent class for all interactive game objects (carachter, monster, missile, cover, etc).
 * It contains methods and attributes for managing position, velocity, checking intersection, etc.
 */

public class Sprite {

    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
        width=0;
        height=0;
    }

    public void setWidth(int x){
        this.width = x;
    }

    public void setHeight(int x){
        this.height = x;
    }

    public double getWidht(){
        return width;
    }

    public double getHeight(){
        return height;
    }


    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public double getVelocityX(){
        return velocityX;
    }

    public double getVelocityY(){
        return velocityY;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

}
