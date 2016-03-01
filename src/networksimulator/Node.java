/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksimulator;

import java.util.ArrayList;

/**
 *
 * @author EliFriedman
 */
public class Node {
    final String name;
    private ArrayList<Link> linklist;
    private ArrayList<Flow> flowlist;
    
    public Node(String name) {
        this.name = name;
        linklist = new ArrayList<>();
    }
    public void addLink(Link link) {
        linklist.add(link);
    }
    public void addFlow(Flow flow) {
        flowlist.add(flow);
    }
    public String getName() { return name; }
}

class NodeEvent extends Event {
    
    public NodeEvent(double time) {
        super(time);
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
