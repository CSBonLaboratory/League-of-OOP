package champions;

import angels.*;
import strategy.PyromancerDefense;
import strategy.PyromancerOffense;
import main.Arena;
import spells.Ignite;
import spells.Spell;

import java.util.ArrayList;
import java.util.HashMap;

public class Pyromancer extends Champion {

    public Pyromancer(int x, int y,ArrayList<HashMap<String ,Float>> bonuses,int id)
    {
        super(x,y,bonuses,id);
        maxHp=500;
        currentHp=maxHp;
        hpPerLevel=50;

        HashMap<String,Float> terrain= new HashMap();   // deep copy al structurii ce memoreaza modificator teren

        terrain.put("Volcanic",bonuses.get(2).get("Volcanic"));

        this.bonuses.add(terrain);
    }

    public float firstAbility()   //FIREBLAST
    {
      float damage;
      damage=350+this.level*50;
      return damage;
    }
    public float secondAbility()   //IGNITE DAMAGE INSTANT
    {
        float damage;
        damage=150+level*20;
        return damage;
    }
    public float accept(Champion attacker)
    {
        return attacker.attack(this);
    }
    public float deflectDamage(char terrain)
    {



        float secondDamage;
        secondDamage=this.secondAbility();
        float firstDamage;
        firstDamage=this.firstAbility();
        for (String key:this.bonuses.get(2).keySet())
            if(terrain==key.charAt(0))
            {
                firstDamage=firstDamage*this.bonuses.get(2).get("Volcanic");
                secondDamage=secondDamage*this.bonuses.get(2).get("Volcanic");
            }
        firstDamage=Math.round(firstDamage);
        secondDamage=Math.round(secondDamage);


        float totalDamage;


        totalDamage=firstDamage+secondDamage;

        return totalDamage;

    }
    public float attack(Champion victim)
    {

        float firstDamage,secondDamage,damageOverTime;
        float totalDamage;

        firstDamage=this.firstAbility();


        secondDamage=this.secondAbility();


        damageOverTime=50+this.level*30;



        Arena arena=Arena.getInstance();

        for(String key:this.bonuses.get(2).keySet())   //Aplica terrain modifier daca este cazul
            if(arena.findTerrain(this.row,this.column)==key.charAt(0))
            {
                firstDamage=firstDamage*this.bonuses.get(2).get("Volcanic");
                secondDamage=secondDamage*this.bonuses.get(2).get("Volcanic");
                damageOverTime=damageOverTime*this.bonuses.get(2).get("Volcanic");
            }
        firstDamage=Math.round(firstDamage);   //Aproximeaza
        secondDamage=Math.round(secondDamage);
        damageOverTime=Math.round(damageOverTime);

        firstDamage=firstDamage*victim.findModifier(this.bonuses.get(0),victim);    //aplica rasa
        secondDamage=secondDamage*victim.findModifier(this.bonuses.get(1),victim);
        damageOverTime=damageOverTime*victim.findModifier(this.bonuses.get(1),victim);

        firstDamage=Math.round(firstDamage);
        secondDamage=Math.round(secondDamage);
        damageOverTime=Math.round(damageOverTime);



        Spell ignite=new Ignite(damageOverTime);  //creaza efectul ignite pe victima
        victim.effect=ignite;

        totalDamage=firstDamage+secondDamage;


        return totalDamage;


    }

    public void chooseStrategy()
    {
        if(Math.floor(this.maxHp/4)< this.currentHp && this.currentHp<Math.floor(this.maxHp/3))
        {
            this.strategy=new PyromancerOffense();
        }
        else if(this.currentHp<Math.floor(this.maxHp/4))
        {
            this.strategy=new PyromancerDefense();
        }
    }

    public void acceptAngel(Angel angel)
    {
        angel.interactWith(this);
    }



}
