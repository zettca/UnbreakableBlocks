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

import static xyz.zettca.unbreakable.utils.Utils.isMaterialUnbreakable;

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

        if (isMaterialUnbreakable(material, plugin.getConfig())) {
            plugin.getLogger().info(String.format("%s tried to break %s at %s",
                    player.getDisplayName(), material, Utils.locationCoords(block.getLocation())));
            player.sendMessage(String.format("[%s] You cannot break %s blocks",
                    plugin.getName(), material));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();

        if (isMaterialUnbreakable(material, plugin.getConfig())) {
            plugin.getLogger().info(String.format("%s prevented from exploding at %s",
                    material, Utils.locationCoords(block.getLocation())));
            event.setCancelled(true);
        }
    }

}
