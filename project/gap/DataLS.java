/*
This class is a constructor of an Object that contains data that is needed for Local Search
*/ 

public class DataLS {
    int[][][] data;
    boolean[][] x;
    int[] bi;
    int of;

    public DataLS(int[][][] data, boolean[][] x, int[] bi, int of) {
        this.data = data;
        this.x = x;
        this.bi = bi;
        this.of = of;
    }

    public int[][][] getData() {
        return data;
    }

    public void setData(int[][][] data) {
        this.data = data;
    }

    public boolean[][] getX() {
        return x;
    }

    public void setX(boolean[][] x) {
        this.x = x;
    }

    public int[] getBi() {
        return bi;
    }

    public void setBi(int[] bi) {
        this.bi = bi;
    }

    public int getOf() {
        return of;
    }

    public void setOf(int of) {
        this.of = of;
    }
}