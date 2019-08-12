package xyz.zettca.unbreakable.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Utils {

    public static boolean isMaterialUnbreakable(Material material, FileConfiguration config) {
        List<String> blockNames = config.getStringList("blocks");
        return blockNames.contains(material.name());
    }

    public static String locationCoords(Location location) {
        return String.format("(%d, %d, %d)", location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
