package strategy;

import champions.Champion;

public class RogueOffense extends Strategy {

    public void apply(Champion champion)
    {
        double sacrificeHp;

        sacrificeHp=Math.floor(champion.getCurrentHp()/7);

        champion.setCurrentHp(champion.getCurrentHp()-(float)sacrificeHp);

        changeModifiers(champion,0.4f);

    }


}
