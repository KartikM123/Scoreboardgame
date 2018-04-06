/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreboardgame;

/**
 *
 * @author karti
 */
public class quarter {
      public Stats home;
      public Stats away;
        public quarter(int playercount){
            home = new Stats(playercount);
            away = new Stats(playercount);
        }
        public quarter(){
            home = new Stats();
            away = new Stats();
        }
        public quarter (Stats home, Stats away){
            this.home = home;
            this.away = away;
        }
        
   }
