package strategy;

import champions.Champion;

public class KnightDefense extends Strategy {


    public void apply(Champion champion)
    {
        double bonusHP;
        bonusHP=Math.floor(champion.getCurrentHp()/4);

        champion.setCurrentHp(champion.getCurrentHp()+(float) bonusHP);   //castiga 1/4 din hp\

        this.changeModifiers(champion,-0.2f);

        champion.getBonuses().get(0).put("Knight",champion.getBonuses().get(0).get("Knight")+0.2f);   // mofificator de Knight pe execute este 1f(+0%) deci nu se modifica

    }
}
