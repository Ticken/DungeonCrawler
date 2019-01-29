package ca.nanorex.dungeoncrawler.engine.render.sprite;

import java.util.Arrays;
import java.util.List;

class SpriteModelAnimation {

    static class InstanceData {

        String name;
        float time;
        float speed; //is a multiplier
        Mode mode;

        InstanceData(String name, float speed, Mode mode) {
            this.name = name;
            this.speed = speed;
            this.mode = mode;
        }
    }

    public enum Mode {
        NORMAL,
        REVERSE,
        LOOP,
        LOOP_REVERSE,
        LOOP_PINGPONG
    }

    int textureIndex;
    int length;
    int priority;
    Mode defaultMode;
    float baseFps;
    List<String> layers;

    public SpriteModelAnimation(int textureIndex, int length, int priority, float baseFps,
                                Mode defaultMode, String[] layers) {

        this.textureIndex = textureIndex;
        this.length = length;
        this.priority = priority;
        this.baseFps = baseFps;
        this.defaultMode = defaultMode;
        this.layers = Arrays.asList(layers);
    }

    boolean isPlaying(InstanceData data) {

        //todo: rewrite this in terms of getFrame
        return !((data.mode == Mode.NORMAL || data.mode == Mode.REVERSE)
                && (int) (data.time * baseFps * data.speed) > length);
    }

    int getFrame(InstanceData data) {

        int frameNum = (int)(data.time * baseFps);

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

            default:
                //Should not reach here
                return 0;
        }
    }
}