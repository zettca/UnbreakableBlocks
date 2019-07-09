package xyz.zettca.unbreakable.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;
import xyz.zettca.unbreakable.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EntityListener implements Listener {

    private final Plugin plugin;

    public EntityListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        List<Block> blocksToExplode = event.blockList();
        for (Block block : new ArrayList<Block>(blocksToExplode)) {
            Material material = block.getType();
            if (material == Material.SPAWNER) {
                plugin.getLogger().info(String.format("%s prevented from exploding at %s",
                        material, Utils.locationCoords(block.getLocation())));
                blocksToExplode.remove(block);
            }
        }

    }
}
