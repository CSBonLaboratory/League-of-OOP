package main;

public class Arena {

    private int rows;
    private int columns;
    private static char[][] terrain;
    private static Arena instance=null;
    private Arena(int Rows,int Columns,char[][] Terrain)
    {

        this.rows=Rows;
        this.columns=Columns;
        this.terrain=Terrain;

    }

    public static Arena getInstance()
    {
        if(instance==null)
            return null;
        else return instance;
    }

    public static void buildInstance(int rows,int columns,char [][] terrain)
    {
        if(instance==null) {
            instance = new Arena(rows, columns, terrain);
        }
    }

    public char findTerrain(int i,int j)
    {
        return terrain[i][j];
    }



}
