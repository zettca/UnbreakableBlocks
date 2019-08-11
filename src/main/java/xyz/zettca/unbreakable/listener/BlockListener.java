package xyz.zettca.unbreakable.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.plugin.Plugin;
import xyz.zettca.unbreakable.utils.Utils;

public class BlockListener implements Listener {

    private final Plugin plugin;

    public BlockListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        Material material = block.getType();

        if (player.hasPermission("unbreakable.bypass")) return;

        if (material == Material.SPAWNER) {
            plugin.getLogger().info(String.format("%s tried to break %s at %s",
                    player.getDisplayName(), material, Utils.locationCoords(block.getLocation())));
            player.sendMessage(String.format("[%s] You cannot break blocks of type %s",
                    plugin.getName(), material));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();

        if (material == Material.SPAWNER) {
            plugin.getLogger().info(String.format("%s prevented from exploding at %s",
                    material, Utils.locationCoords(block.getLocation())));
            event.setCancelled(true);
        }
    }

}
