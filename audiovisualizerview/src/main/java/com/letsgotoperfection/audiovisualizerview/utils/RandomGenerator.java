package com.letsgotoperfection.audiovisualizerview.utils;

/**
 * @author hossam.
 */

public class RandomGenerator {
    //Random random=new Random();
    public static float generateNumbersBetween(float start, float end) {
        return (float) (Math.random() * end + start);
    }
}
