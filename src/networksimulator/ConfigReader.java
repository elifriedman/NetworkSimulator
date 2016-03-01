/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksimulator;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for reading the configuration files. The default file is traffic.properties.
 * 
 * @author EliFriedman
 */
public class ConfigReader {

    Properties prop;
    ArrayList<Node> nodes;
    IndexMinPQ<Event> pq;
    int max_events;

    
    public ConfigReader(String propfile) {
        prop = new Properties();
        nodes = null;
        
        try {
            prop.load(new FileInputStream(propfile));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found!");
            Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        max_events = Integer.parseInt(prop.getProperty("max_events", "1000"));
        pq = new IndexMinPQ<>(max_events);
    }
    
    public IndexMinPQ<Event> getPriorityQueue() {
        return pq;
    }
    
    public ArrayList<Node> getNetwork(String netfile) {
        ArrayList<Node> nodelist = new ArrayList<>();
        HashMap<String, Node> nodeMap = new HashMap<>();
        try (Scanner s = new Scanner(new File(netfile))) {
            s.useDelimiter("[,\\n]");
            while (s.hasNext()) {
                try {
                    s.skip("#.*");
                    String linkname = s.next(); // read link name
                    String c1 = s.next(); // read node 1
                    String c2 = s.next(); // read node 2
                    float linkrate = s.nextFloat(); // read linkrate
                    float propdelay = s.nextFloat(); // read propogation delay
                    int buffersize = s.nextInt(); // read buffersize
                    
                    System.out.printf("%s,%s,%s,%.2f,%.2f,%d\n", c1,c2,linkname,
                            linkrate,propdelay,buffersize);
                    Node a = nodeMap.get(c1);
                    Node b = nodeMap.get(c2);
                    if(a==null) { // if we haven't seen c1 before
                        if(c1.charAt(0)=='R') a = new Router(c1);
                        else a = new Host(c1);
                        nodeMap.put(c1, a);
                        nodelist.add(a);
                    } 
                    
                    if(b==null) { // if we haven't seen c2 before
                        if(c2.charAt(0)=='R') b = new Router(c1);
                        else b = new Host(c1);
                        nodeMap.put(c2, b);
                        nodelist.add(b);
                    } 
                    
                    Link link = new Link(a,b,linkname, buffersize, linkrate, propdelay);
                    
                    a.addLink(link);
                    b.addLink(link);
                } catch (NoSuchElementException ne) {
                    // Skip line b/c it's probably empty
                    // TODO tell user we're skipping line
                }
            }
        } catch (FileNotFoundException fe) {
            Logger.getLogger(ConfigReader.class.getName()).log(
                    Level.SEVERE,
                    "Could not find linknet file: '{0}'", netfile);
            System.exit(-1);
        } catch (InputMismatchException im) {
            Logger.getLogger(ConfigReader.class.getName()).log(
                    Level.SEVERE,
                    "Please check that file '{0}' is in the correct place and has the correct format.", netfile);
            System.exit(-1);
        }
        return nodelist;
    }

    public ArrayList<Flow> getFlows(String flowfile) {
        ArrayList<Flow> flowlist = new ArrayList<>();
        try (Scanner s = new Scanner(new File(flowfile))) {
            s.useDelimiter("[,\\n]");
            while (s.hasNext()) {
                try {
                    s.skip("#.*");
                    String flowname = s.next(); // read link name
                    String c1 = s.next(); // read node 1
                    String c2 = s.next(); // read node 2
                    int amount = s.nextInt(); // read linkrate
                    float start = s.nextFloat(); // read propogation delay
                    
                    System.out.printf("%s,%s,%s,%d,%.2f\n", c1,c2,flowname,
                            amount,start);
                    Flow f = new Flow(flowname, c1, c2, amount, start);
                    flowlist.add(f);
                } catch (NoSuchElementException ne) {
                    // Skip line b/c it's probably empty
                    // TODO tell user we're skipping line
                }
            }
        } catch (FileNotFoundException fe) {
            Logger.getLogger(ConfigReader.class.getName()).log(
                    Level.SEVERE,
                    "Could not find linknet file: '{0}'", flowfile);
            System.exit(-1);
        } catch (InputMismatchException im) {
            Logger.getLogger(ConfigReader.class.getName()).log(
                    Level.SEVERE,
                    "Please check that file '{0}' is in the correct place and has the correct format.", flowfile);
            System.exit(-1);
        }
        return flowlist;
    }
    
    public String getParam(String param) {
        return prop.getProperty(param);
    }


    public int getNumIterations() {
        return Integer.parseInt(prop.getProperty("num_iterations", "1"));
    }
  
}
