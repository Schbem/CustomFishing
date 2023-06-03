package me.schmeb.customfishing.commands;

import me.schmeb.customfishing.configfiles.ConfigFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NonNull CommandSender commandSender, Command command,@NonNull String s, String[] strings) {
        if(command.getName().equals("customfishing") && commandSender.hasPermission("terratalestools.reload")){
            if(strings.length == 1){
                if(strings[0].equals("reload")){
                    commandSender.sendMessage("TerraTalesTools has been reloaded!");
                    ConfigFile.reload();
                }
            }
        }
        return true;
    }
}
