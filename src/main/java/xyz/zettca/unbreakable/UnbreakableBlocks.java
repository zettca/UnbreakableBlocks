package xyz.zettca.unbreakable;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.zettca.unbreakable.command.UnbreakableCommand;
import xyz.zettca.unbreakable.listener.BlockListener;
import xyz.zettca.unbreakable.listener.EntityListener;
import xyz.zettca.unbreakable.listener.PlayerListener;

public class UnbreakableBlocks extends JavaPlugin {

    private final BlockListener blockListener = new BlockListener(this);
    private final EntityListener entityListener = new EntityListener(this);
    private final PlayerListener playerListener = new PlayerListener(this);

    @Override
    public void onEnable() {
        super.onEnable();
        //getLogger().info(getName() + " successfully enabled.");

        registerPluginEvents();
        registerPluginCommands();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        //getLogger().info(getName() + " successfully disabled.");
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