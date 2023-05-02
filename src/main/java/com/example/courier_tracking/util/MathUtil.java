package com.example.courier_tracking.util;

import lombok.experimental.UtilityClass;
import org.apache.lucene.util.SloppyMath;


@UtilityClass
public class MathUtil {

    public double calculateDistance(final double latitude1, final double longitude1, final double latitude2,
        final double longitude2) {

       return SloppyMath.haversinMeters(latitude1, longitude1, latitude2, longitude2);
    }
}
