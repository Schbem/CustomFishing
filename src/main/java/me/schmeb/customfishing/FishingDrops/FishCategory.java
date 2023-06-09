package me.schmeb.customfishing.FishingDrops;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FishCategory {
    public ItemStack returnFish(Random random){
        ItemStack item;
        double randomVar = random.nextDouble();
        if(randomVar < 0.60)
            item = new ItemStack(Material.COD);
        else if(randomVar > 0.60 && randomVar < 0.85)
            item = new ItemStack(Material.SALMON);
        else if(randomVar > 0.85 && randomVar < 0.87)
            item = new ItemStack(Material.TROPICAL_FISH);
        else
            item = new ItemStack(Material.PUFFERFISH);

        return item;
    }
}
