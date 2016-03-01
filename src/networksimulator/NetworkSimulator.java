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
public class NetworkSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConfigReader conf = new ConfigReader("abc");
        ArrayList<Node> nodelist = conf.getNetwork("config/netfile.csv");
        ArrayList<Flow> flowlist = conf.getFlows("config/flowfile.csv");
        for(Node n : nodelist) {
            for(Flow f : flowlist) {
                if(f.src.equals(n.name)) {
                    n.addFlow(f);
                }
            }
        }
    }
    
}
