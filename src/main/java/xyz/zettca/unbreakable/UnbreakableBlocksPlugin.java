package xyz.zettca.unbreakable;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.zettca.unbreakable.listener.BlockListener;
import xyz.zettca.unbreakable.listener.PlayerListener;

public class UnbreakableBlocksPlugin extends JavaPlugin {

    private final BlockListener blockListener = new BlockListener(this);
    private final PlayerListener playerListener = new PlayerListener(this);

    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info(getName() + " successfully enabled.");

        registerPluginEvents();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info(getName() + " successfully disabled.");
    }

    private void registerPluginEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(blockListener, this);
        pm.registerEvents(playerListener, this);
    }

}
