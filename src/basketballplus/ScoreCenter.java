/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreboardgame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author karti
 */
public class ScoreCenter {
        public quarter [] q1;
        public int quartersfinished;
        public Stats home, away;
        public ScoreCenter(int playercount) {
            q1 = new quarter [4];
            q1[0] = new quarter (playercount);
            q1[1] = new quarter (playercount);
            q1[2] = new quarter (playercount);
            q1[3] = new quarter (playercount);
            home = new Stats (playercount);
            away = new Stats (playercount);
            quartersfinished = 0;
        }
         public ScoreCenter() {
            q1 = new quarter [4];
            q1[0] = new quarter ();
            q1[1] = new quarter ();
            q1[2] = new quarter ();
            q1[3] = new quarter ();
            home = new Stats ();
            away = new Stats ();
            quartersfinished = 0;
        }
      /*  public boolean add(quarter q) throws Exception{
            if (q1.home.quarterdone == false){
                q1 = q;
                updategame(q1);
                quartersfinished = 1;
                return true;
            } else if (q2.home.quarterdone == false){
                q2 = q;
                updategame(q2);
                quartersfinished = 2;
                return true;
            } else if (q3.home.quarterdone == false){
                q3 = q;
                updategame(q3);
                quartersfinished = 3;
                return true;
            } else if (q4.home.quarterdone == false){
                q4 = q;
                updategame(q4);
                quartersfinished = 4;
                updateDB();
                return true;
            }
            return false;
        }*/
 public void updategame (quarter q, int qnum){
        home.scored += q.home.scored;
        home.threepct = (q.home.threepct+home.threepct)/qnum;
        home.fgatt += q.home.fgatt; 
        home.fgmade += q.home.fgmade;
        home.fgpct = (q.home.fgpct+home.fgpct)/qnum;
        home.rebound += q.home.rebound;
        home.assists += q.home.assists; 
        home.turnover += q.home.turnover; 
        home.threeatt += q.home.threeatt; 
        home.threemade += q.home.threemade;
        home.steal+=q.home.steal;
        home.block+=q.home.block;
        
        away.scored += q.away.scored;
        away.threepct = (q.away.threepct+away.threepct)/qnum;
        away.fgatt += q.away.fgatt; 
        away.fgmade += q.away.fgmade;
        away.fgpct = (q.away.fgpct+away.fgpct)/qnum;
        away.rebound += q.away.rebound;
        away.assists += q.away.assists; 
        away.turnover += q.away.turnover; 
        away.threeatt += q.away.threeatt; 
        away.threemade += q.away.threemade;
        away.steal+=q.away.steal;
        away.block+=q.away.block;

 }
        public void updateDB() throws Exception{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ScoreApp", "root", "kartik");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ScoreApp.gamelog ORDER BY idgamelog");
            rs.afterLast();
            int newid= 0;
            
            while (rs.previous()){
             newid = Integer.parseInt(rs.getString(1))+1;
             break;
            }
            st.executeUpdate("INSERT INTO gamelog (idgamelog, ptsscored, threepct, fgpct, rebound, assists, turnover) VALUES (" + newid + ", " + home.scored + ", "
                + home.threepct + ", " + home.fgpct + ", " + home.rebound + ", " + home.assists + ", " + home.turnover +")");
            conn.close();
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ScoreApp", "root", "kartik");
            Statement st1 = conn1.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT * FROM ScoreApp.playerstat ORDER BY playername");
            rs1.beforeFirst();
            rs1.next();
       /*    for (Player player: home.players){
            st1.executeUpdate("UPDATE playerstat SET gamesplayed = " + (Integer.parseInt(rs1.getString(2))+1) + ", scored = " + (player.scored+Integer.parseInt(rs1.getString(3)+1)) +", eFG = " + ((Double.parseDouble(rs1.getString(2))+player.fgpct)/Double.parseDouble(rs1.getString(1))) +", assist = " + player.assist + ", turnover = " + player.turnover + ", rebound = " + player.rebound + "WHERE playername = " + player.id);
            rs1.next();
            }*/
                        conn.close();
                        
        }
        public  Player getPlayer (int index){
            int count =1;
            for (Player play: home.players){
                if (index ==count){
                    return play;
                }
                index++;
            }
            return null;
        }
        
        
   
      
  
}

 
  