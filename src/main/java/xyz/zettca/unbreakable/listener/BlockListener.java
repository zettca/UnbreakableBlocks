package xyz.zettca.unbreakable.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

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

        if (material == Material.SPAWNER) {
            plugin.getLogger().warning(String.format("%s tried to destroy %s at %s",
                    player.getDisplayName(), material, block.getLocation()));
            player.sendMessage(String.format("[%s] You cannot break blocks of type %s",
                    plugin.getName(), material));
            event.setCancelled(true);
        }
    }

}
