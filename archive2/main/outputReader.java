package main;

import champions.*;


import java.util.ArrayList;

import fileio.implementations.FileWriter;
import greatMagician.GreatMagician;

public class outputReader {

    public outputReader(String outputPath, ArrayList<Champion> champions)
    {
        try {
            int i;
            FileWriter out = new FileWriter(outputPath);

            GreatMagician magician=GreatMagician.getInstance();

            for(i=0;i<magician.getNotifications().size();i++) {
                out.writeWord(magician.getNotifications().get(i));

                if(magician.getNotifications().get(i).equals("\n")==false)
                    out.writeNewLine();
            }

            for(i=0;i<champions.size();i++)
            {
                if(champions.get(i) instanceof Pyromancer) {
                    out.writeCharacter('P');

                }
                if(champions.get(i) instanceof Knight) {
                    out.writeCharacter('K');

                }
                if(champions.get(i) instanceof Wizard) {
                    out.writeCharacter('W');

                }
                if(champions.get(i) instanceof Rogue) {
                    out.writeCharacter('R');

                }

                out.writeCharacter(' ');
                if(champions.get(i).getCurrentHp()==0)
                    out.writeWord("dead");
                else
                {
                    out.writeInt(champions.get(i).getLevel());
                    out.writeCharacter(' ');
                    out.writeInt(champions.get(i).getXp());
                    out.writeCharacter(' ');
                    out.writeInt((int) champions.get(i).getCurrentHp());
                    out.writeCharacter(' ');
                    out.writeInt(champions.get(i).getRow());
                    out.writeCharacter(' ');
                    out.writeInt(champions.get(i).getColumn());

                }

                    out.writeNewLine();

            }

            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
