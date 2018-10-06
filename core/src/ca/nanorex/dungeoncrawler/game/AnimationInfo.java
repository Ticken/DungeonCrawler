package ca.nanorex.dungeoncrawler.game;

public class AnimationInfo {
    /**
     * holds the information on what row [0] and column [1] the animation starts on and for how
     * many columns [2] the animation continues until looping.
     */
    private int info[];

    /**
     * @param row the row the animation is on
     * @param col the column the animation starts in
     * @param cols how many columns the animation runs for before looping
     */
    public AnimationInfo(int row, int col, int cols) {
        if(row < 0 || col < 0)
            System.out.println("ERROR: row and column indexes should be non-negative. ");
        if(cols < 0)
            System.out.println("ERROR: columns should be positive. ");
        info = new int[]{row, col, cols};
    }

    /**
     *
     * @return (row, col, cols)
     */
    public int[] getInfo() {
        return info;
    }
}
