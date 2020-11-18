package strategy;

import champions.Champion;

public class KnightOffense extends Strategy{

    public void apply(Champion champion)
    {
        double sacrificeHp;
        sacrificeHp=Math.floor(champion.getCurrentHp()/5);

        champion.setCurrentHp(champion.getCurrentHp()-(float) sacrificeHp);   // pierde 1/5 din hp

        this.changeModifiers(champion,0.5f);

        champion.getBonuses().get(0).put("Knight",champion.getBonuses().get(0).get("Knight")-0.5f);   // mofificator de Knight pe execute este 1f(+0%) deci nu se modifica

    }
}
