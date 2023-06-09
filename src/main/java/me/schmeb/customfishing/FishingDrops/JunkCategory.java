package me.schmeb.customfishing.FishingDrops;

import me.schmeb.customfishing.configfiles.ConfigFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JunkCategory {
    public ItemStack returnJunk(Random random){
        ItemStack item;

        switch (random.nextInt(10)){
            case 0:
                item = new ItemStack(Material.LILY_PAD);
                break;
            case 1:
                item = new ItemStack(Material.BOWL);
                break;
            case 2:
                item = new ItemStack(Material.FISHING_ROD);
                ItemMeta itemMeta = item.getItemMeta();
                Damageable damage = (Damageable) itemMeta;
                damage.setDamage(random.nextInt(60) + 1);
                item.setItemMeta(itemMeta);
                break;
            case 3:
                item = new ItemStack(Material.LEATHER);
                break;
            case 4:
                item = new ItemStack(Material.LEATHER_BOOTS);
                itemMeta = item.getItemMeta();
                damage = (Damageable) itemMeta;
                damage.setDamage(random.nextInt(60) + 1);
                item.setItemMeta(itemMeta);
            case 5:
            case 6:
            case 7:
            case 8:
                item = notesCategory(random);
                break;
            case 9:
                item = new ItemStack(Material.BONE);
                break;
            default:
                item = new ItemStack(Material.TRIPWIRE_HOOK);
                break;
        }

        return item;
    }

    private ItemStack notesCategory(Random random){
        ItemStack item = new ItemStack(Material.PAPER);
        double randomVar = random.nextDouble();

        if(randomVar < 0.20){
            notesWriter(item, ConfigFile.get().getStringList("First Note Message"));
        } else if(randomVar > 0.20 && randomVar < 0.40){
            notesWriter(item, ConfigFile.get().getStringList("Second Note Message"));
        } else if(randomVar > 0.40 && randomVar < 0.60){
            notesWriter(item, ConfigFile.get().getStringList("Third Note Message"));
        } else if(randomVar > 0.60 && randomVar < 0.80){
            notesWriter(item, ConfigFile.get().getStringList("Fourth Note Message"));
        } else{
            notesWriter(item, ConfigFile.get().getStringList("Fifth Note Message"));
        }

        return item;
    }

    private void notesWriter(ItemStack item, List<String> lore){
        ItemMeta itemMeta = item.getItemMeta();
        List<Component> loreList = new ArrayList<>();

        for(int i = 0; i < lore.size(); i++)
            loreList.add(i, LegacyComponentSerializer.legacyAmpersand().deserialize((lore.get(i))));

        itemMeta.lore(loreList);
        item.setItemMeta(itemMeta);
    }
}
