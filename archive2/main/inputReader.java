package main;

import angels.*;
import champions.*;
import fileio.implementations.FileReader;
import fileio.implementations.FileWriter;
import greatMagician.GreatMagician;

import java.util.ArrayList;

public class inputReader {


    private static int nrChampions;
    private static ArrayList<Champion> championArray;
    private static int nrRounds;
    private static String[] moves;
    public  static ArrayList<ArrayList<Angel>> angelArray;

    public int getNrChampions()
    {
        return nrChampions;
    }
    public ArrayList<Champion> getChampionArray()
    {
        return championArray;
    }
    public int getNrRounds()
    {
        return nrRounds;
    }
    public String[] getMoves()
    {
        return moves;
    }
    public ArrayList<ArrayList<Angel>> getAngelArray()
    {
        return angelArray;
    }

    public inputReader(String inputPath)
    {
        try{
            FileReader fileReader=new FileReader(inputPath);

            buildArena(fileReader);

            championArray=new ArrayList<Champion>();

            buildChampions(fileReader);

            buildMoves(fileReader);

            buildAngels(fileReader);

            int i,j;



        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void buildArena(FileReader fileReader)
    {
        try {
            int N=fileReader.nextInt();
            int M=fileReader.nextInt();


            char[][] terrain=new char[N][M];
            int i,j;
            for(i=0;i<N;i++)
            {
                String rowLine;
                rowLine=fileReader.nextWord();
                for(j=0;j<rowLine.length();j++) {
                    terrain[i][j] = rowLine.charAt(j);
                }


            }

            Arena.buildInstance(N,M,terrain);




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void buildChampions(FileReader fileReader)
    {
        try{
            nrChampions=fileReader.nextInt();

            ChampionFactory instance=ChampionFactory.getInstance();
            GreatMagician magicianInstance=GreatMagician.getInstance();
            String type;
            int x,y;


            for(int i=0;i<nrChampions;i++) {
                type = fileReader.nextWord();
                x = fileReader.nextInt();
                y = fileReader.nextInt();
                Champion player =instance.getChampion(type.charAt(0),x,y,i);
                magicianInstance.addObserver(player);         //adauga observatorul campion
                championArray.add(player);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private static void buildMoves(FileReader fileReader)
    {
        try {
            nrRounds = fileReader.nextInt();

            moves = new String[nrRounds];

            for(int i=0;i<nrRounds;i++)
            {
                moves[i]=new String();
                moves[i]=fileReader.nextWord();

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void buildAngels(FileReader fileReader)
    {
        try {
            int j, row, column, i, k;
            String angelName;
            angelArray = new ArrayList<ArrayList<Angel>>(nrRounds);
            ArrayList<Angel> angelRound;

            for (i = 0; i < nrRounds; i++) {
                j = fileReader.nextInt();       // numarul de ingeri din runda respectiva
                angelRound = new ArrayList<>(j);


                angelArray.add(i, angelRound);
                for (k = 0; k < j; k++) {
                    angelName = fileReader.nextWord();

                    row=0;
                    column=0;
                    int x=angelName.indexOf(',');
                    x++;
                    while (angelName.charAt(x)!=',')
                    {
                        row=row*10+angelName.charAt(x)-'0';
                        x++;

                    }
                    x++;

                    while (x<angelName.length())
                    {
                        column=column*10+angelName.charAt(x)-'0';
                        x++;
                    }

                    if(angelName.contains("DamageAngel"))
                        angelArray.get(i).add(new DamageAngel(row,column));

                    if(angelName.contains("DarkAngel"))
                        angelArray.get(i).add(new DarkAngel(row,column));

                    if(angelName.contains("Dracula"))
                        angelArray.get(i).add(new Dracula(row,column));

                    if(angelName.contains("GoodBoy"))
                        angelArray.get(i).add(new GoodBoy(row,column));

                    if(angelName.contains("LevelUpAngel"))
                        angelArray.get(i).add(new LevelUpAngel(row,column));

                    if(angelName.contains("LifeGiver"))
                        angelArray.get(i).add(new LifeGiver(row,column));

                    if(angelName.contains("SmallAngel"))
                        angelArray.get(i).add(new SmallAngel(row,column));

                    if(angelName.contains("Spawner"))
                        angelArray.get(i).add(new Spawner(row,column));

                    if(angelName.contains("TheDoomer"))
                        angelArray.get(i).add(new TheDoomer(row,column));

                    if(angelName.contains("XPAngel"))
                        angelArray.get(i).add(new XPAngel(row,column));


                }


            }

            GreatMagician magicianInstance=GreatMagician.getInstance();

            for (i=0;i<angelArray.size();i++)              //adauga observatorii ingeri
                for (j=0;j<angelArray.get(i).size();j++)
                    magicianInstance.addObserver(angelArray.get(i).get(j));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }



}
