package xyz.zettca.unbreakable.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.zettca.unbreakable.UnbreakableBlocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnbreakableCommand implements CommandExecutor, TabExecutor {
    private final UnbreakableBlocks plugin;

    public UnbreakableCommand(UnbreakableBlocks plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String cmd, String[] strings) {
        int len = strings.length;

        if (len == 0) return false;

        String cmd0 = strings[0];

        if (cmd0.equalsIgnoreCase("help")) {
            commandSender.sendMessage("/ub block add/rm [BLOCK_NAME]");
        } else if (cmd0.equalsIgnoreCase("block")) {
            if (len <= 2) return false;

            FileConfiguration config = plugin.getConfig();

            String cmd1 = strings[1];
            String block = strings[2];

            Material mat = Material.getMaterial(block);
            if (mat != null) {
                List<String> blocks = config.getStringList("blocks");
                if (cmd1.equalsIgnoreCase("add")) {
                    blocks.add(mat.name());
                    commandSender.sendMessage(String.format("Added %s to UnbreakbleBlocks", mat.name()));
                } else if (cmd1.equalsIgnoreCase("rm")) {
                    blocks.remove(mat.name());
                    commandSender.sendMessage(String.format("Removed %s from UnbreakbleBlocks", mat.name()));
                }
                config.set("blocks", blocks);
            } else {
                commandSender.sendMessage(String.format("Block %s does not exist.", block));
            }

            plugin.saveConfig();

        }
        return true;
    }

    public List<String> onTabComplete(CommandSender commandSender, Command command, String cmd, String[] strings) {
        Player player;
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
            if (len == 2) {
                return Arrays.asList("add", "rm");
            } else if (len == 3) {
                String cmd1 = strings[1];

                if (cmd1.equalsIgnoreCase("add")) {
                    Material[] materials = Material.values();
                    List<String> finalMaterials = new ArrayList<>();

                    if (strings[2].length() >= 1) {
                        for (Material mat : materials) {
                            String m = mat.name();
                            if (mat.isBlock() && m.startsWith(strings[2])) {
                                finalMaterials.add(m);
                            }
                        }
                    }

                    return finalMaterials;

                } else if (cmd1.equalsIgnoreCase("rm")) {
                    return plugin.getConfig().getStringList("blocks");
                }
            }
        } else if (cmd0.equalsIgnoreCase("location") && player.hasPermission("unbreakable.edit.location")) {
            if (len == 2) return Arrays.asList("add", "rm");
        }

        return null;
    }
}
