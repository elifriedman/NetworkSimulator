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
class Flow {
    final String name;
    final String src;
    final String dest;
    final float start;
    private byte[] data; // each byte of data represent 1 kb
    
    public Flow(String name, String src, String dest, int KBamount, float start) {
        this.name = name;
        this.src = src;
        this.dest = dest;
        this.start = start;
        this.data = new byte[KBamount];
    }
    
}
