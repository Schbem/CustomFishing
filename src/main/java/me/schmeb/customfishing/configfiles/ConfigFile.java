package me.schmeb.customfishing.configfiles;

import me.schmeb.customfishing.CustomFishing;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    private static File file;
    private static FileConfiguration fileConfiguration;

    public static void setup(){
        file = new File(CustomFishing.getInstance().getDataFolder(), "config.yml");

        if(!file.exists()){
            try{
                boolean status = file.createNewFile();
                if(status) System.out.println("File 'config.yml' created");
            }catch (IOException e){
                System.out.println("Something went wrong with creating the config file");
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return fileConfiguration;
    }

    public static void save(){
        try{
            fileConfiguration.save(file);
        }catch(IOException e){
            System.out.println("Cannot Save File");
        }
    }

    public static void reload(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
}
