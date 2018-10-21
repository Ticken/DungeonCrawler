package ca.nanorex.dungeoncrawler.game.render;

import java.util.List;

/**
 * Represents a sequence of values in an animation
 */
public class ModelAnimation {

    /**
     * The animation play mode
     */
    public enum Mode {
        NORMAL,
        REVERSE,
        LOOP,
        LOOP_REVERSE,
        LOOP_PINGPONG
    }

    /**
     * Contains the data necessary to represent the state of a currently playing instance of an animation
     */
    protected static class Data {

        protected ModelAnimation animation;

        protected String name;
        protected float time;
        protected float speed;
        protected Mode mode;

        /**
         * Constructor for ModelAnimation.Data
         *
         * @param name The name of the animation that is playing
         * @param speed The speed multiplier of the animation
         * @param mode The play mode for the animation
         */
        public Data(float speed, Mode mode) {
            this.speed = speed;
            this.mode = mode;
        }

        protected boolean isPlaying() {
            return (mode == Mode.NORMAL || mode == Mode.REVERSE)
                    && (int)(time * animation.baseFps * speed) > animation.length;
        }
    }

    protected int index;
    protected int length;
    protected int priority;
    protected Mode defaultMode;
    protected float baseFps;
    protected List<String> layers;

    /**
     * Constructor for ModelAnimation
     *
     * @author David Neuman
     * @param index
     * @param length
     * @param baseFps
     * @param priority
     * @param defaultMode
     */
    public ModelAnimation(int index, int length, float baseFps, int priority, Mode defaultMode, String[] layers) {
        this.index = index;
        this.length = length;
        this.baseFps = baseFps;
        this.priority = priority;
        this.defaultMode = defaultMode;
        //this.layers = new ArrayList<String>(layers);
    }

    protected int getFrame(Data data) {

        int frameNum = (int)(data.time * baseFps * data.speed);

        switch (data.mode) {

            case NORMAL:
                return Math.min(length - 1, frameNum);

            case REVERSE:
                return Math.max(length - frameNum - 1, 0);

            case LOOP:
                return frameNum % length;

            case LOOP_REVERSE:
                return length - frameNum % length - 1;

            case LOOP_PINGPONG:
                frameNum = frameNum % (length * 2 - 2);
                if (frameNum >= length)
                    return length * 2 - 2 - frameNum;
                else
                    return frameNum;
        }

        //Should not reach here; statement only added to avoid errors
        return 0;
    }
}