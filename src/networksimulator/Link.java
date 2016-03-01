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
public class Link {
    private String name;
    private int buffersize;
    private float linkrate;
    private float propdelay;
    private Node n1;
    private Node n2;
    private boolean direction; // direction is true if going from a to b
    
    public Link(Node a, Node b,
            String name,
            int buffersize,
            float linkrate, 
            float propdelay) {
        this.n1 = a;
        this.n2 = b;
        this.name = name;
        this.buffersize = buffersize;
        this.linkrate = linkrate;
        this.propdelay = propdelay;
        this.direction = true;
    }
    public Node getOther(Node n) {
        if(n==n1) return n2;
        else return n1;
    }
}
