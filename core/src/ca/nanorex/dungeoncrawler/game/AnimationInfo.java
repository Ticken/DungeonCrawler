package ca.nanorex.dungeoncrawler.game;

public class AnimationInfo {
    /** (row, col, rows, cols)
     *
     */
    private int info[];

    public AnimationInfo(int row, int col, int cols) {
        if(row < 0 || col < 0)
            System.out.println("ERROR: row and column indexes should be non-negative ");
        if(cols < 0)
            System.out.println("ERROR: columns should be positive ");
        info = new int[]{row, col, cols};
    }

    public int[] getInfo() {
        return info;
    }
}
