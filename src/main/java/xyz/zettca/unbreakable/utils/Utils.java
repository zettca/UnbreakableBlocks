package xyz.zettca.unbreakable.utils;

import org.bukkit.Location;

public class Utils {

    public static String locationCoords(Location location) {
        return String.format("(%d, %d, %d)", location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
