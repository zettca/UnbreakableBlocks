package xyz.zettca.unbreakable;

import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.zettca.unbreakable.command.UnbreakableCommand;
import xyz.zettca.unbreakable.listener.BlockListener;
import xyz.zettca.unbreakable.listener.EntityListener;
import xyz.zettca.unbreakable.listener.PlayerListener;

import java.util.ArrayList;
import java.util.List;

public class UnbreakableBlocks extends JavaPlugin {

    private final BlockListener blockListener = new BlockListener(this);
    private final EntityListener entityListener = new EntityListener(this);
    private final PlayerListener playerListener = new PlayerListener(this);

    @Override
    public void onEnable() {
        super.onEnable();

        saveResource("config.yml", false); // Initial configuration

        printUnbreakableBlocks();

        registerPluginEvents();
        registerPluginCommands();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void printUnbreakableBlocks() {
        List<String> blocks = getConfig().getStringList("blocks");
        List<String> goodBlocks = new ArrayList<String>();
        List<String> badBlocks = new ArrayList<String>();

        for (String block : blocks) {
            if (Material.getMaterial(block) != null) {
                goodBlocks.add(block);
            } else {
                badBlocks.add(block);
            }
        }

        if (goodBlocks.size() > 0) {
            getLogger().info(String.format("(%s) are now Unbreakable!", String.join(" ", goodBlocks)));
        }
        if (badBlocks.size() > 0) {
            getLogger().warning(String.format("(%s) not found.", String.join(" ", badBlocks)));
        }
    }

    private void registerPluginEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(blockListener, this);
        pm.registerEvents(entityListener, this);
        pm.registerEvents(playerListener, this);
    }

    private void registerPluginCommands() {
        getCommand("unbreakable").setExecutor(new UnbreakableCommand(this));
    }

}
