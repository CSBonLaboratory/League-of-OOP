package champions;

import angels.*;
import strategy.RogueDefense;
import strategy.RogueOffense;
import main.Arena;
import spells.Paralisys;
import spells.Spell;
import spells.WoodsParalisys;

import java.util.ArrayList;
import java.util.HashMap;

public class Rogue extends Champion {

    private int hits;
    private int previousHits;

    public Rogue(int x,int y,ArrayList<HashMap<String,Float>> bonuses,int id)
    {
        super(x,y,bonuses,id);
        this.maxHp=600;
        currentHp=maxHp;
        this.hpPerLevel=40;
        this.hits=0;

        HashMap<String,Float> terrain= new HashMap();   // deep copy al structurii ce memoreaza modificator teren

        terrain.put("Woods",bonuses.get(2).get("Woods"));

        this.bonuses.add(terrain);
    }

    public float firstAbility()
    {
        float damage;
        damage=200+this.level*20;
        return damage;

    }
    public float secondAbility()
    {
        float damage;
        damage=40+this.level*10;
        return damage;

    }
    public float accept(Champion attacker)
    {
        return attacker.attack(this);
    }
    public float deflectDamage(char terrain)
    {

        float firstDamage;
        firstDamage=this.firstAbility();

        for (String key :this.bonuses.get(2).keySet())
            if(terrain==key.charAt(0))
            {
                firstDamage=firstDamage*this.bonuses.get(2).get("Woods");   //aplica modificator teren backstab
            }
        firstDamage=Math.round(firstDamage);

        if(this.previousHits%3==0)
        {
            for (String key:this.bonuses.get(2).keySet())
            {
                if(terrain==key.charAt(0)) {
                    firstDamage = firstDamage * 1.5f;
                    firstDamage=Math.round(firstDamage);
                }
            }
        }
        float secondDamage;
        secondDamage=this.secondAbility();

        for (String key :this.bonuses.get(2).keySet())
            if(terrain==key.charAt(0))
            {
                secondDamage=secondDamage*this.bonuses.get(2).get("Woods");   //aplica modificator teren paralisys
            }

        secondDamage=Math.round(secondDamage);

        float totalDamage;
        totalDamage=firstDamage+secondDamage;


        return totalDamage;



    }
    public float attack(Champion victim)
    {


        float firstDamage,secondDamage;
        float totalDamage;

        previousHits=hits;

        Arena arena=Arena.getInstance();
        firstDamage=this.firstAbility();
        for (String key :this.bonuses.get(2).keySet())
            if(arena.findTerrain(this.row,this.column)==key.charAt(0))
            {
                firstDamage=firstDamage*this.bonuses.get(2).get("Woods");   //aplica modificator teren backstab
            }

        firstDamage=Math.round(firstDamage);
        if(this.hits%3==0)
        {
            for (String key:this.bonuses.get(2).keySet()) {
                if (arena.findTerrain(this.row,this.column) == key.charAt(0)) {
                    firstDamage = firstDamage * 1.5f;
                    this.hits=1;            //critical hit si reseteaza
                } else
                    this.hits=1;          //reseteaza
            }

        }
        else
            this.hits++;
        firstDamage=Math.round(firstDamage);

        firstDamage=firstDamage*victim.findModifier(this.bonuses.get(0),victim);  //aplica modificator rasa backstab
        firstDamage=Math.round(firstDamage);



        secondDamage=this.secondAbility();


        for(String key: this.bonuses.get(2).keySet())
        {
            if (arena.findTerrain(this.row,this.column) == key.charAt(0)) {
                secondDamage =secondDamage * this.bonuses.get(2).get("Woods");    //aplica modificator teren paralisys
                secondDamage = Math.round(secondDamage);
                secondDamage=secondDamage*victim.findModifier(this.bonuses.get(1),victim);    // aplica modificator rasa
                secondDamage=Math.round(secondDamage);
                Spell woodsParalisys = new WoodsParalisys(secondDamage);      //da efect paralisys prelungit pe woods
                victim.effect = woodsParalisys;

            } else {
                secondDamage=secondDamage*victim.findModifier(this.bonuses.get(1),victim);   //aplica modificator rasa
                secondDamage = Math.round(secondDamage);
                Spell paralisys = new Paralisys(secondDamage);    //da efect paralisys normal
                victim.effect = paralisys;

            }
        }

        totalDamage=firstDamage+secondDamage;





        return totalDamage;



    }

    public void chooseStrategy()
    {
        if(Math.floor(this.maxHp/7)< this.currentHp && this.currentHp< Math.floor(this.maxHp/5))
            this.strategy=new RogueOffense();
        else if(this.currentHp<Math.floor(this.maxHp/7)) {
            this.strategy=new RogueDefense();
        }
    }

    public void acceptAngel(Angel angel)
    {
        angel.interactWith(this);
    }
}
