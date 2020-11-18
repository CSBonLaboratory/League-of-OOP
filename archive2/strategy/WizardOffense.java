package strategy;

import champions.Champion;

public class WizardOffense extends Strategy {

    public void apply(Champion champion)
    {
        double sacrificeHp;

        sacrificeHp=Math.floor(champion.getCurrentHp()/10);

        champion.setCurrentHp(champion.getCurrentHp()-(float) sacrificeHp);

        changeModifiers(champion,0.6f);


    }
}
