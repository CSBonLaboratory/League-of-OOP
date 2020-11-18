package champions;

import strategy.WizardDefense;
import strategy.WizardOffense;
import main.Arena;
import angels.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Wizard extends Champion {

    public Wizard(int x,int y,ArrayList<HashMap<String,Float>> bonuses,int id)
    {
        super(x,y,bonuses,id);
        this.maxHp=400;
        currentHp=this.maxHp;
        this.hpPerLevel=30;

        HashMap<String,Float> terrain= new HashMap();   // deep copy al structurii ce memoreaza modificator teren

        terrain.put("Desert",bonuses.get(2).get("Desert"));

        this.bonuses.add(terrain);
    }

    public float firstAbility()
    {
        float percentage;
        percentage=0.2f+0.05f*this.level;
        return percentage;
    }
    public float secondAbility()
    {
        float percentage;
        percentage=0.35f+0.02f*this.level;
        if(percentage<0.7f)
            return percentage;
        else
            return 0.7f;

    }
    public float accept(Champion attacker)
    {
        return attacker.attack(this);
    }
    public float attack(Champion victim)
    {



        float percentage;
        percentage=this.firstAbility();


        percentage=percentage*victim.findModifier(this.bonuses.get(0),victim);  // aplica race+strategy+angels pe procent




        float minimum;

        if(0.3f*victim.maxHp<victim.currentHp)
            minimum=0.3f*victim.maxHp;
        else
            minimum=victim.currentHp;




        Arena arena=Arena.getInstance();

        for(String key:this.bonuses.get(2).keySet())
            if(arena.findTerrain(this.row,this.column)==key.charAt(0))      //aplica terrain pe procent
                percentage=percentage*this.bonuses.get(2).get("Desert");


        float firstDamage;

        firstDamage=percentage*minimum;


        firstDamage=Math.round(firstDamage);



        float secondDamage;
        secondDamage=this.secondAbility();


        float deflectDamage=0;

        if(victim instanceof Pyromancer)
            deflectDamage = ((Pyromancer) victim).deflectDamage(arena.findTerrain(this.row,this.column));
        if(victim instanceof Knight)
            deflectDamage=((Knight) victim).deflectDamage(this,arena.findTerrain(this.row,this.column));
        if(victim instanceof Rogue)
            deflectDamage=((Rogue) victim).deflectDamage(arena.findTerrain(this.row,this.column));
        if(victim instanceof Wizard)
            deflectDamage=0;



        secondDamage=secondDamage*deflectDamage;

        secondDamage=Math.round(secondDamage);



        for(String key: this.bonuses.get(2).keySet())
            if(arena.findTerrain(this.row,this.column)==key.charAt(0)) {
                secondDamage = secondDamage * this.bonuses.get(2).get("Desert");  //aplica terrain
                //secondDamage=Math.round(secondDamage);
            }



        secondDamage=secondDamage*victim.findModifier(this.bonuses.get(1),victim);



        secondDamage=secondDamage-0.0001f;    // am intampinat probleme la aproximari chiar daca toti modificatorii sunt pusi cu f


        secondDamage=Math.round(secondDamage);



        float totalDamage;



        totalDamage=firstDamage+secondDamage;





        return totalDamage;



    }

    public void chooseStrategy()
    {
        if(Math.floor(this.maxHp/4)<this.currentHp && this.currentHp< Math.floor(this.maxHp/2))
            this.strategy=new WizardOffense();
        else if(this.currentHp<Math.floor(this.maxHp/4)) {
            this.strategy=new WizardDefense();
        }
    }

    public void acceptAngel(Angel angel)
    {
        angel.interactWith(this);
    }
}
