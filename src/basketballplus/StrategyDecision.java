/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreboardgame;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author karti
 */
public final class StrategyDecision {
    public CalculatedStats offense;
    public CalculatedStats defense;
    public double offensestratrating;
    public double defensestratrating;
    public Strategy offensestrategy;
    public Strategy defensestrategy;
    public HashMap  <String, Strategy> offsegment;
    public HashMap  <String, Strategy> defsegment;
    public String extremes;


    public StrategyDecision (CalculatedStats offense, CalculatedStats defense){
        this.offense = offense;
        this.defense = defense;
        offensestratrating= offensecalculatestratrating();
        defensestratrating = defensecalculatestratrating();
        defsegment  = defstratlist();
        offsegment = offstratlist();
        offensestrategy = decideoffenseStrategy();
        defensestrategy = decidedefstrategy();
        extremes = checkextremes();
    }
    
    
    public Strategy decideoffenseStrategy (){
        double turnovercomp = offense.turnovers/offense.avgvalues.get("turnovers");
        double scoredcompar = offense.ptsscored/offense.avgvalues.get("ptsscored");
        double eFGcompar = offense.eFG/offense.avgvalues.get("eFG");
        double SDPcompar = offense.SDP/offense.avgvalues.get("SDP");
        Strategy offstrat = offsegment.get("triangle");
        if (offensestratrating<=55){
            //CONSERVATIVE STRATEGIES
            if(eFGcompar-1<1-SDPcompar && eFGcompar-1<1-turnovercomp){
                offstrat = offsegment.get("pickandroll");
            } else if (1-SDPcompar<1-turnovercomp){
                offstrat = offsegment.get("isolation");
            } else{
                offstrat = offsegment.get("princeton");
            }
        }else if (offensestratrating<=65){
            //NEUTRAL STRATEGIES
             if(eFGcompar-1<scoredcompar-1 && eFGcompar-1<1-turnovercomp){
                offstrat = offsegment.get("pickandroll");
            } else if (scoredcompar-1<1-turnovercomp){
                offstrat = offsegment.get("flex");
            } else{
                offstrat = offsegment.get("triangle");
            }
        } else{
            //AGGRESSIVE TACTICS
            if(scoredcompar-1<1-SDPcompar && scoredcompar-1<1-turnovercomp){
                offstrat = offsegment.get("fastbreak");
            } else if ( 1-SDPcompar<1-turnovercomp){
                offstrat = offsegment.get("shuffle");
            } else{
                offstrat = offsegment.get("movement");
            }
        }
        
        return offstrat;
    }
    public Strategy decidedefstrategy (){
        double turnovercomp = defense.turnovers/defense.avgvalues.get("turnovers");
        double scoredcompar = defense.ptsscored/defense.avgvalues.get("ptsscored");
        double eFGcompar = defense.eFG/defense.avgvalues.get("eFG");
        double SDPcompar = defense.SDP/defense.avgvalues.get("SDP");
        Strategy defstrat = defsegment.get("mantoman");
        if (defensestratrating<=25){
            //AGGRESIVE STRATEGIES
            if(eFGcompar-1>1-SDPcompar && eFGcompar-1>1-turnovercomp){
                defstrat = defsegment.get("doubleteam");
            } else if (1-SDPcompar>1-turnovercomp){
                defstrat = defsegment.get("fullcourt");
            } else{
                defstrat = defsegment.get("denyball");
            }
        }else if (defensestratrating<=50){
            //NEUTRAL STRATEGIES
             if(eFGcompar-1>scoredcompar-1 && eFGcompar-1>1-turnovercomp){
                defstrat = defsegment.get("2-3");
            } else if (scoredcompar-1<1-turnovercomp){
                defstrat = defsegment.get("1-3-1");
            } else{
                defstrat = defsegment.get("mantoman");
            }
        } else{
            //CONSERVATIVE TACTICS
            if(scoredcompar-1<eFGcompar-1 && eFGcompar-1<1-turnovercomp){
                defstrat = defsegment.get("mantoman");
            } else if ( 1-SDPcompar<1-turnovercomp){
                defstrat = defsegment.get("2-3");
            } else{
                defstrat = defsegment.get("sagoff");
            }
        } return defstrat; }
    
   
    
    public double offensecalculatestratrating (){
        double turnovercomp = offense.turnovers/offense.avgvalues.get("turnovers");
        double scoredcompar = offense.ptsscored/offense.avgvalues.get("ptsscored");
        double eFGcompar = offense.eFG/offense.avgvalues.get("eFG");
        double SDPcompar = offense.SDP/offense.avgvalues.get("SDP");
        return statratecalc (turnovercomp, scoredcompar, eFGcompar, SDPcompar);
    }  
    public double defensecalculatestratrating (){
        double turnovercomp = defense.turnovers/defense.avgvalues.get("turnovers");
        double scoredcompar = defense.ptsscored/defense.avgvalues.get("ptsscored");
        double eFGcompar = defense.eFG/defense.avgvalues.get("eFG");
        double SDPcompar = defense.SDP/defense.avgvalues.get("SDP");
        return statratecalc (turnovercomp, scoredcompar, eFGcompar, SDPcompar);
    }  
    public double statratecalc (double turnovercomp, double scoredcompar, double eFGcompar, double SDPcompar){
        double offrating = 50;
        offrating+=exponential(turnovercomp);
        offrating+=exponential(scoredcompar);
        offrating+=exponential(eFGcompar);
        offrating+=exponential(SDPcompar);
        return offrating;
    }
    
    public double exponential (double val){
        return (Math.pow(2*(val-1), 3));
    }
    
    public String checkextremes () {
        String extremes = "";
        if (offense.turnovers > (offense.avgvalues.get("turnovers")*1.3)){
            extremes += "Turnovers higher than usual. Try shorter passes || \n";
        }
        if (offense.SDP >(offense.avgvalues.get("SDP")*1.5) ){            
            extremes += "Standard Deviation higher than usual. Balance the offense to confuse defense|| \n";
        }
        if (offense.rebounds<=(offense.avgvalues.get("rebounds")*0.8)){
            extremes += "Rebounds lower than usual. Focus on plays near the rim|| \n";
        }
      /*  if (offense.possessionTime <= (offense.avgvalues.get("possessionTime")*0.6)){
            extremes += "Short Possession Time. Make sure to slow down if necessary especially if low rotations\n";
        }
        if (offense.possessionTime >= (offense.avgvalues.get("possessionTime")*1.5)){
            extremes += "Long Possession Time. Make sure to try to speed up plays and watch the clock\n";
        }*/
         if (offense.ptsscored < (offense.avgvalues.get("ptsscored")*0.7)){
            extremes += "Unusually low scoring quarter. Try and speed up offense|| \n";
        }
         if (offense.eFG < (offense.avgvalues.get("eFG")*0.7)){
            extremes += "Unusually bad eFG. Get some smarter shots up!||  \n";
        }
        
        return extremes;
    }
    public HashMap  <String, Strategy> defstratlist(){
        HashMap <String, Strategy> deflist = new HashMap ();
            Strategy onethreeone = new Strategy ("1-3-1 zone","This defense is named for its formation since there is one defender at the point, three defenders at the free throw level, and one defender at the base line. The main focus of this defensive strategy is to force turnovers. This is done by using quick, pestering defense and anticipating any passes to attempt a steal. A two-man trap is implemented in this defense. ");
            Strategy denyball = new Strategy ("Deny Ball", "Defenders play close man on man to try and force turnover");
            Strategy fullcourt = new Strategy ("Full Court Press", "Defend from inbound to make force other team to struggle up court");
            Strategy doubleteam = new Strategy ("Double Team", "force the offense into corners to put pressure on bad dribblers and passers");
            Strategy twothree = new Strategy ("2-3 zone", "It is referred to as the 2–3 because of its formation on the court, which consists of two players at the front of the defense (and closer to half court) and three players behind (and closer to the team's basket).");
            Strategy mantoman = new Strategy ("man to man","basic defense tactic where the players guard their own man");
            Strategy sagoffdefense = new Strategy ("sag off defense", "Similar to man to man but sag off to guard paint. This dares the offense to attack");
            
        deflist.put("1-3-1",onethreeone);
        deflist.put("denyball", denyball);
        deflist.put ("fullcourt", fullcourt);
        deflist.put("doubleteam", doubleteam);
        deflist.put("2-3", twothree);
        deflist.put("mantoman",mantoman);
        deflist.put("sagoffdefense",sagoffdefense);
        return deflist;
    }
    public HashMap <String, Strategy> offstratlist (){
        HashMap <String, Strategy> offlist = new HashMap();
            Strategy pickandroll = new Strategy ("Pick and Roll", "One of the players sets a screen and rolls consistently with point guard. Usually safe and reliable");
            Strategy princetonoffense = new Strategy ("Princeton Offense", "The features of this tactic are control on ball,constant motion,dribbling,high number of passing,3 point shooting,back door cuts and disciplined teamwork.The most conventional scheme in princeton is 4-out with 1-in, in this scheme four players set up outside the 3 point arc and one player inside at the top of the key.As this scheme aims to slow down the tempo of the game,if often results in low scoring affairs.");
            Strategy isolationoffense = new Strategy ("Isolation Offense", "This strategy is designed to bring out the talent of the best offensive player on a team.It gives that  big player a chance to play his own game,he gets a ball,creates his own shots and passes and takes on defender one on one.In isolation all offensive players accept the assigned one moves to the weak side of the court,bringing their defenders with them and maintaining a minimum distance of 10 to 15 ft from the best offensive player on a team.");
            Strategy flexoffense = new Strategy ("Flex Offense", "The  basic idea of flex offense is quite similar to shuffle offense. According to this offense,the players are not bound to perform a specific  role,their reponsibilies are multiple and interchangeable. ");
            Strategy triangleoffense = new Strategy ("Triangle Offense", "Triangle offense is a complexed offensive strategy,in which the offensive players are placed on one side of the court with an offensive player on low post,a player on corner and one on wing, making a sideline triangle and  remianing two players form a two man game on weak side.This strategy creates a good spacing about 15 to 18 ft between players and makes passing easy for players.");
            Strategy fastbreakoffense = new Strategy ("Fast Break Offense", "Fast break is a basic offensive strategy,used by most of the teams.In fast break offense,a team attempts to push the ball quickly up the court before defense gets in to its position.Players run at high speed  make quick passes,communicate with each other to understand  the plan of action and make adjustments if needed,when a player with the ball reaches the free throw line,he quickly decides his next step, whether he wants to continue driving to himself take a shot or he wants to pass the ball to his teammnate,in short,fast break offense requires agility, speed,stamina,ball handling skills,decision making power and better understanding between players.");
            Strategy movementoffense = new Strategy ("Movement Offense", "Mainly consists of circles being run either side from the three point line. This keeps the ball constantly moving through off ball screeens until an opening comes up");
    offlist.put("pickandroll",pickandroll);
    offlist.put("princeton",princetonoffense);
    offlist.put("isolation",isolationoffense);
    offlist.put("flex",flexoffense);
    offlist.put("triangle",triangleoffense);
    offlist.put("fastbreak",fastbreakoffense);
    offlist.put("movementoffense",movementoffense);
    return offlist;
    } 

    public class Strategy {
        public String stratname;
        public String description;
        public Strategy (String stratname, String description){
            this.stratname = stratname;
            this.description = description;
        }
        
    } 
}
   