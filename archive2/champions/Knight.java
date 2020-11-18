package champions;

import angels.*;
import strategy.KnightDefense;
import strategy.KnightOffense;
import main.Arena;
import spells.Slam;
import spells.Spell;

import java.util.ArrayList;
import java.util.HashMap;

public class Knight extends Champion {

    public boolean firstVisit;

    public Knight(int x,int y,ArrayList<HashMap<String ,Float>> bonuses,int id)
    {
        super(x,y,bonuses,id);
        this.maxHp=900;
        this.currentHp=maxHp;
        this.hpPerLevel=80;
        firstVisit=false;

        HashMap<String,Float> terrain= new HashMap();   // deep copy al structurii ce memoreaza modificator teren

        terrain.put("Lands",bonuses.get(2).get("Lands"));

        this.bonuses.add(terrain);

    }

    public float firstAbility()
    {
       float damage;
       damage=200+this.level*30;
       return damage;
    }
    public float secondAbility()
    {
        float damage;
        damage=100+this.level*40;
        return damage;
    }
    public float accept(Champion attacker)
    {
        return attacker.attack(this);
    }
    public float deflectDamage(Champion wizard,char terrain)
    {

        float executePercentage;        //calculeaza procentul de executie
        executePercentage=0.2f+0.01f*this.level;
        if(executePercentage>=0.4f)
            executePercentage=0.4f;

        //if(wizard.currentHp<=wizard.maxHp*executePercentage)
        //{

            //return wizard.currentHp;
        //}

        float firstDamage,secondDamage;
        firstDamage=this.firstAbility();
        secondDamage=this.secondAbility();

         for(String key:this.bonuses.get(2).keySet())
             if(terrain==key.charAt(0))
             {
                 firstDamage=firstDamage*this.bonuses.get(2).get("Lands");
                 secondDamage=secondDamage*this.bonuses.get(2).get("Lands");
             }
         firstDamage=Math.round(firstDamage);
         secondDamage=Math.round(secondDamage);

         float totalDamage;
         totalDamage=firstDamage+secondDamage;
         return totalDamage;
    }
    public float attack(Champion victim)
    {


        float firstDamage,secondDamage;
        float totalDamage;

        float executePercentage;        //calculeaza procentul de executie
        executePercentage=0.2f+0.01f*this.level;
        if(executePercentage>=0.4f)
            executePercentage=0.4f;

        if(victim.currentHp<=victim.maxHp*executePercentage)  //executa victima daca este cazul
        {
            return victim.currentHp;
        }

        firstDamage=this.firstAbility();   // victima nu poate fi executata asa ca isi ia base damage


        secondDamage=this.secondAbility();


        Arena arena=Arena.getInstance();

        for(String key:this.bonuses.get(2).keySet())    //aplica modificator teren
            if(arena.findTerrain(this.row,this.column)==key.charAt(0))
            {
                firstDamage=firstDamage*this.bonuses.get(2).get("Lands");
                secondDamage=secondDamage*this.bonuses.get(2).get("Lands");
            }
        firstDamage=Math.round(firstDamage);
        secondDamage=Math.round(secondDamage);

        firstDamage=firstDamage*victim.findModifier(this.bonuses.get(0),victim);     //aplica rasa
        secondDamage=secondDamage*victim.findModifier(this.bonuses.get(1),victim);


        firstDamage=Math.round(firstDamage);
        secondDamage=Math.round(secondDamage);

        Spell slam=new Slam(0);  //aplica efectul slam asupra victimei
        victim.effect=slam;

        totalDamage=firstDamage+secondDamage;
        


        return totalDamage;



    }

    public void chooseStrategy()
    {
        if(Math.floor(this.maxHp/3)< this.currentHp && this.currentHp< Math.floor(this.maxHp/2))
            this.strategy=new KnightOffense();
        else if(this.currentHp<Math.floor(this.maxHp/3)) {
            this.strategy=new KnightDefense();
        }
    }

    public void acceptAngel(Angel angel)
    {
        angel.interactWith(this);
    }
}
