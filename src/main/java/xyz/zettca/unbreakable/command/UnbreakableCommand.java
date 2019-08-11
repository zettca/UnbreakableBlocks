package xyz.zettca.unbreakable.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import xyz.zettca.unbreakable.UnbreakableBlocks;

import java.util.Arrays;
import java.util.List;

public class UnbreakableCommand implements CommandExecutor, TabExecutor {
    private final UnbreakableBlocks plugin;

    public UnbreakableCommand(UnbreakableBlocks plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String cmd, String[] strings) {
        return true;
    }

    public List<String> onTabComplete(CommandSender commandSender, Command command, String cmd, String[] strings) {

        // TODO: refactor code and permissions autocomplete

        Player player = null;
        int len = strings.length;

        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        } else {
            return null;
        }

        if (len == 1) return Arrays.asList("help", "block", "location");

        String cmd0 = strings[0];

        if (cmd0.equalsIgnoreCase("help")) {
            if (len == 2) return Arrays.asList("block", "location");
        } else if (cmd0.equalsIgnoreCase("block") && player.hasPermission("unbreakable.edit.block")) {
            if (len == 2) return Arrays.asList("add", "rm");
        } else if (cmd0.equalsIgnoreCase("location") && player.hasPermission("unbreakable.edit.location")) {
            if (len == 2) return Arrays.asList("add", "rm");
        }

        return null;
    }
}
