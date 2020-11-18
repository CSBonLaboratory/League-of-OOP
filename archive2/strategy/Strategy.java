package strategy;

import champions.Champion;

abstract public class Strategy {

     protected void changeModifiers(Champion champion,float bonus)
     {
         champion.getBonuses().get(0).put("Rogue",champion.getBonuses().get(0).get("Rogue")+bonus);
         champion.getBonuses().get(0).put("Knight",champion.getBonuses().get(0).get("Knight")+bonus);
         champion.getBonuses().get(0).put("Pyromancer",champion.getBonuses().get(0).get("Pyromancer")+bonus);
         champion.getBonuses().get(0).put("Wizard",champion.getBonuses().get(0).get("Wizard")+bonus);

         champion.getBonuses().get(1).put("Rogue",champion.getBonuses().get(1).get("Rogue")+bonus);
         champion.getBonuses().get(1).put("Knight",champion.getBonuses().get(1).get("Knight")+bonus);
         champion.getBonuses().get(1).put("Pyromancer",champion.getBonuses().get(1).get("Pyromancer")+bonus);
         champion.getBonuses().get(1).put("Wizard",champion.getBonuses().get(1).get("Wizard")+bonus);
     }

     abstract public void apply(Champion champion);

}
