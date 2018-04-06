/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreboardgame;

import java.util.ArrayList;

/**
 *
 * @author karti
 */
public class Player {
    public int scored;
    public int threeatt,threemake,fgmake,fgatt;
    public double threepct;
    public double fgpct;
    public int rebound;
    public int assist, turnover;
    public int steal;
    public int pos;
    public int block;
    public String id;
    public Player (int scored, int threemake, int threeatt, int fgmake, int fgatt, int rebound, int assists, int turnover){
        this.scored = scored;
        this.threepct = 0;
        this.fgpct = fgmake/fgatt;
        this.rebound = rebound;
        this.assist = assists;
        this.turnover = turnover;
                block = 0; steal = 0;

    }
    public Player (int pos, String id){
        this.pos = pos; this.id = id;
        scored = 0;
        threeatt = 0; threemake = 0;
        fgatt = 0; fgmake = 0;
        rebound = 0;
        assist = 0; turnover = 0;
        block = 0; steal = 0;
    }
    
    public void threeatt() {
        threeatt++;
        threepct = threemake/threeatt;   
    }
    public void threemake() {
     threeatt++;
     threemake++;
     scored+=3;
     threepct = threemake/threeatt;  
    }
    
    public void fgmiss(){
     fgatt++;
     fgpct = fgmake/fgatt;
    }
    public void fgmake() {
     fgatt++;
     fgmake++;
     scored+=2;
     fgpct = fgmake/fgatt;
    }
    
    public void turnover() {
     turnover++;
    }
    public void assist() {
     assist++;
    }
    public String toString(){
    String thing = " ------------------------------\n PLAYER " + id +  " \n Points: " +  scored + "\n Threes Made/Threes attempted (PCT): " + threemake + "/" + threeatt + "(" + threepct*100 + ")";
        thing += "\n FG Made/FG Atempted (PCT): " + fgmake + "/" + fgatt + " (" + fgpct*100 + ") \n ";
        thing += "Rebound: " + rebound + "\n Assists: " + assist + "\n Turnover: " + turnover + "\n ------------------------------";
        
        return thing;
    }
}

