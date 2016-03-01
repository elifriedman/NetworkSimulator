/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksimulator;

/**
 *
 * @author EliFriedman
 */
public abstract class Event implements Comparable<Event> {
    
    private double time;
    public Event(double time) {
        this.time = time;
    }
    public double getTime() {
        return this.time;
    }
    public void setTime(double time) {
        this.time = time;
    }
    public abstract void run();
    
    @Override
    public int compareTo(Event o) {
        double diff = this.time - o.getTime();
        if(Math.abs(diff)<0.0001) return 0;
        if(diff > 0) return 1;
        return -1;
    }
}
