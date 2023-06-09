package me.schmeb.customfishing;

import me.schmeb.customfishing.FishingDrops.FishCategory;
import me.schmeb.customfishing.FishingDrops.JunkCategory;
import me.schmeb.customfishing.FishingDrops.TreasureCategory;
import me.schmeb.customfishing.commands.ReloadCommand;
import me.schmeb.customfishing.configfiles.ConfigFile;
import me.schmeb.customfishing.listeners.OnPlayerRightClick;
import me.schmeb.customfishing.listeners.PlayerOnFishEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomFishing extends JavaPlugin {
    public static CustomFishing instance;
    private final NamespacedKey treasureBarrel = new NamespacedKey(this, "treasure_barrel");
    @Override
    public void onEnable() {
        instance = this;
        TreasureCategory treasureCategory = new TreasureCategory(treasureBarrel);
        JunkCategory junkCategory = new JunkCategory();
        FishCategory fishCategory = new FishCategory();

        ConfigFile.setup();
        ConfigFile.get().addDefault("First Note Message", "N/A");
        ConfigFile.get().addDefault("Second Note Message", "N/A");
        ConfigFile.get().addDefault("Third Note Message", "N/A");
        ConfigFile.get().addDefault("Fourth Note Message", "N/A");
        ConfigFile.get().addDefault("Fifth Note Message", "N/A");
        ConfigFile.get().options().copyDefaults(true);
        ConfigFile.save();

        PluginCommand reloadCommand = this.getCommand("customfishing");
        if(reloadCommand != null)
            reloadCommand.setExecutor(new ReloadCommand());

        getServer().getPluginManager().registerEvents(new PlayerOnFishEvent(treasureCategory, junkCategory, fishCategory), this);
        getServer().getPluginManager().registerEvents(new OnPlayerRightClick(treasureCategory, treasureBarrel), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static CustomFishing getInstance() {
        return instance;
    }
}
