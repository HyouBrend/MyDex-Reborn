package com.myor.mydex.util;

/* loaded from: classes2.dex */
public class DistanceCalculator {
    public static final double RADIUS_OF_EARTH = 6371.0d;

    public double calculateDistance(double d, double d2, double d3, double d4) {
        double radians = Math.toRadians(d);
        double radians2 = Math.toRadians(d2);
        double radians3 = Math.toRadians(d3);
        double d5 = (radians3 - radians) / 2.0d;
        double radians4 = (Math.toRadians(d4) - radians2) / 2.0d;
        double sin = (Math.sin(d5) * Math.sin(d5)) + (Math.cos(radians) * Math.cos(radians3) * Math.sin(radians4) * Math.sin(radians4));
        return Math.atan2(Math.sqrt(sin), Math.sqrt(1.0d - sin)) * 2.0d * 6371.0d * 1000.0d;
    }
}
