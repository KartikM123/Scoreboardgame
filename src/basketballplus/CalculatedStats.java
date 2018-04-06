/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreboardgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author karti
 */
public class CalculatedStats {
    ScoreCenter score;
    int turnovers;
    int rebounds;
    double eFG;
        //equation: (2pt FGM + (1.5 * 3pt FGM)/ Total FGA)
    double possessionTime;
        //use system.nanoTime
    double SDP;
        //StandardDeviationPoints
    double twopctM, threepctM;
        //Percentage of total points which are taken by 3's and 2's
    int scoreDifference;
    int prevquarterDifference;
    int ptsscored;
    //specific quarter vs entire game.
    double timeLeft;
    HashMap <String, Integer> avgvalues;
    
    
    public CalculatedStats (ScoreCenter score,  boolean hometeam, int [] numbers) throws FileNotFoundException{
       quarter lastq;
       double timeleft;
       this.avgvalues = averagevaluespreset("rec");
        switch (score.quartersfinished){
            case 1:
              lastq = score.q1[0];
              timeleft = 30;
              break;
            case 2:
              lastq = score.q1[1];
              timeleft = 20;
              break;
            case 3:
              lastq = score.q1[2];
              timeleft = 10;
              break;
            default:
              lastq = score.q1[3];
                timeleft = 0;
        }
        this.timeLeft = timeleft;
         if (hometeam==true){
            this.turnovers = lastq.home.turnover;
            this.rebounds = lastq.home.rebound;
            this.eFG = eFGcalculation(lastq.home.fgmade-lastq.home.threemade,lastq.home.threemade,lastq.home.fgatt);    
            this.twopctM = ((lastq.home.fgmade-lastq.home.threemade)/(lastq.home.threemade+1)*100); this.threepctM = (lastq.home.threemade/(lastq.home.fgmade+1)*100);             this.timeLeft = timeleft;
            this.ptsscored = lastq.home.scored;
            this.scoreDifference = score.home.scored-score.away.scored;
            this.prevquarterDifference = lastq.home.scored-lastq.away.scored;   
       
        } else {
            
            this.turnovers = lastq.away.turnover;
            this.rebounds = lastq.away.rebound;
            this.eFG = eFGcalculation(lastq.away.fgmade-lastq.away.threemade,lastq.away.threemade,lastq.away.fgatt);    
            this.twopctM = ((lastq.away.fgmade-lastq.away.threemade)/(lastq.away.threemade+1)*100); this.threepctM = (lastq.away.threemade/(lastq.away.fgmade+1)*100);             this.timeLeft = timeleft;
            this.ptsscored = lastq.away.scored;
            this.scoreDifference = score.away.scored-score.home.scored;
            this.prevquarterDifference = lastq.away.scored-lastq.home.scored;   
        }
    }
    public CalculatedStats (ScoreCenter score,boolean hometeam, int [] playerscores, String league) throws FileNotFoundException{
       quarter lastq;
       double timeleft;
       this.avgvalues = averagevaluespreset(league);
        switch (score.quartersfinished){
            case 1:
              lastq = score.q1[0];
              timeleft = 30;
              break;
            case 2:
              lastq = score.q1[1];
              timeleft = 20;
              break;
            case 3:
              lastq = score.q1[2];
              timeleft = 10;
              break;
            default:
              lastq = score.q1[3];
                timeleft = 0;
        }
        this.timeLeft = timeleft;
        if (hometeam==true){
            this.turnovers = lastq.home.turnover;
            this.rebounds = lastq.home.rebound;
            this.eFG = eFGcalculation(lastq.home.fgmade-lastq.home.threemade,lastq.home.threemade,lastq.home.fgatt);    
            double mean = returnMean(playerscores);
            this.SDP = standardeviation(playerscores, mean);
            this.twopctM = ((lastq.home.fgmade-lastq.home.threemade)/lastq.home.threemade*100); this.threepctM = (lastq.home.threemade/lastq.home.fgmade*100);             this.timeLeft = timeleft;
            this.ptsscored = lastq.home.scored;
            this.scoreDifference = score.home.scored-score.away.scored;
            this.prevquarterDifference = lastq.home.scored-lastq.away.scored;   
       
        } else {
            
            this.turnovers = lastq.away.turnover;
            this.rebounds = lastq.away.rebound;
            this.eFG = eFGcalculation(lastq.away.fgmade-lastq.away.threemade,lastq.away.threemade,lastq.away.fgatt);    
            this.twopctM = ((lastq.away.fgmade-lastq.away.threemade)/lastq.away.threemade*100); this.threepctM = (lastq.away.threemade/lastq.away.fgmade*100);             this.timeLeft = timeleft;
            this.ptsscored = lastq.away.scored;
            this.scoreDifference = score.away.scored-score.home.scored;
            this.prevquarterDifference = lastq.away.scored-lastq.home.scored;   
        }
    }
     
        public double returnMean (int [] numbers){
            double sum = 0;
            for (int i = 0; i< numbers.length ; i++){
                sum+=numbers[i];
            }
            return sum/numbers.length;
            
        }
        public double standardeviation (int [] numbers, double mean){
        double numerator = 0;
            for (int i = 0; i < numbers.length; i++){
            numerator+=(numbers[i]-mean);
            }
            return Math.sqrt(mean/(numbers.length-1));
        }
        public double eFGcalculation (int twopointmade, int threepointmade, int fgattempted){
            return (twopointmade+1.5*threepointmade/fgattempted);
        }
        public HashMap <String, Integer> averagevaluespreset (String league) throws FileNotFoundException{
            HashMap <String, Integer> avgval = new HashMap();
            String filename;
            if (league.equalsIgnoreCase("select")){
                filename = "select.txt";
            } else if (league.equalsIgnoreCase("nba")){
                filename = "nba.txt";
            } else if (league.equalsIgnoreCase("college")){
                filename = "college.txt";
            } else{
                filename = "rec.txt";
            }
            File f = new File (filename);
            Scanner scan = new Scanner (f);
            while (scan.hasNext()){
                String statname = scan.next();
                int statval = scan.nextInt();
                avgval.put(statname, statval);
            }
            return avgval;
        }
}
