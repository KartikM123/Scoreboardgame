    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreboardgame;

import java.util.ArrayList;
import java.util.HashSet;

//  MAKE SURE THAT THE POPULATE PLAYERS WORKS
/**
 *
 * @author karti
 */
public  class Stats {
    public int scored;
    public double threepct;
    public int threemade,threeatt; 
    public double fgpct;
    public int fgatt,fgmade;
    public int rebound;
    public int astturndiff;
    public int assists, turnover;
    public boolean quarterdone;
    public int block, steal;
    public ArrayList <Player>  players;
    public Stats (int scored, int threemake, int threeatt, int fgmake, int fgatt, int rebound, int assists, int turnover){
        this.scored = scored;
        this.threepct = 0;
        this.fgpct = 0;
        this.rebound = rebound;
        this.astturndiff = assists-turnover;
        players = new ArrayList<Player> ();
for (int i = 0; i < 10; i++){
           int pos = determinepos(i);
           players.add(new Player (pos, i+1 +  ""));
        }
quarterdone = false;
this.block =0; this.steal = 0;
    }
    public Stats (int playercount){
        this.scored = 0;
        this.threepct = 0;
        this.fgatt = 0; this.fgmade = 0;
        this.fgpct = 0;
        this.rebound = 0;
        this.assists = 0; this.turnover = 0; 
        this.threeatt = 1; this.threemade = 0; this.block =0; this.steal = 0;
        this.astturndiff = 0;
        players = new ArrayList ();
for (int i = 0; i < playercount; i++){
           int pos = determinepos(i);
           players.add(new Player (pos, i+1 +  ""));
        }        quarterdone = false;
    }
    public Stats (){
        this.scored = 0;
        this.threepct = 0;
        this.fgatt = 0; this.fgmade = 0;
        this.fgpct = 0;
        this.rebound = 0;
        this.assists = 0; this.turnover = 0; 
        this.threeatt = 1; this.threemade = 0;
        this.astturndiff = 0;
        players = new ArrayList<Player> ();
        quarterdone = false;
    }
    
    public void populateplayers(){
        for (int i = 0; i < 10; i++){
           int pos = determinepos(i);
           players.add(new Player (pos, i+1 +  ""));
        }
    }
     public  Player getPlayer (int index){
            int count =1;
            for (Player play: this.players){
                if (index ==count){
                    return play;
                }
                index++;
            }
            Player p = new Player (1,"4");
            return p;
        }
    public int determinepos(int i){
        if (i<=2){
            return 1;
        } else if (i <= 4){
            return 2;
        } else if (i <= 6){
            return 3;
        } else if (i <= 8){
            return 4;
        } else {
            return 5;
        }
    }
    public void updatefields() {
        this.scored = 0;
        this.threepct = 0;
        this.fgatt = 0; this.fgmade = 0;
        this.fgpct = 0;
        this.rebound = 0;
        this.assists = 0; this.turnover = 0; 
        this.threeatt = 0; this.threemade = 0;
        this.astturndiff = 0; this.block=0;
        this.steal = 0;
        for (Player player: players){
            this.scored+=player.scored;
            this.threeatt+=player.threeatt;
            this.threemade+=player.threemake;
            this.fgatt+=player.fgatt;
            this.fgmade+=player.fgmake;
            this.rebound+= player.rebound;
            this.assists+=player.assist;
            this.turnover+=player.turnover;
            this.block+=player.block;
            this.steal+=player.steal;
        }   
        if (fgatt == 0){
            this.fgpct = 0;
        } else{
            this.fgpct = (double)fgmade/fgatt*100;
        }
        if (threeatt != 0){
            this.threepct = (double)threemade/threeatt*100;
        } else{
            this.threepct = 0;
        }
    
    }
    public void quarterdone (){
        quarterdone = true;
    }
    public String toString(){
        String thing = " ------------------------------\n SCOREBOARD SUMMARIZED \n Points: " +  scored + "\n Threes Made/Threes attempted (PCT): " + threemade + "/" + threeatt + "(" + threepct + ")";
        thing += "\n FG Made/FG Atempted (PCT): " + fgmade + "/" + fgatt + " (" + fgpct + ") \n ";
        thing += "Rebound: " + rebound + "\n Assists: " + assists + "\n Turnover: " + turnover + "\n ------------------------------";
        
        return thing;
    }
}   
