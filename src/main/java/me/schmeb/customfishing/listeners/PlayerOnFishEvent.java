package me.schmeb.customfishing.listeners;

import me.schmeb.customfishing.FishingDrops.FishCategory;
import me.schmeb.customfishing.FishingDrops.JunkCategory;
import me.schmeb.customfishing.FishingDrops.TreasureCategory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class PlayerOnFishEvent implements Listener {
    private final Map<UUID, Long> fishCaught = new HashMap<>();
    private final Map<UUID, Long> lastFishCaught = new HashMap<>();
    Random random = new Random();
    private final TreasureCategory treasureCategory;
    private final JunkCategory junkCategory;
    private final FishCategory fishCategory;

    public PlayerOnFishEvent(TreasureCategory treasureCategory, JunkCategory junkCategory, FishCategory fishCategory){
        this.treasureCategory = treasureCategory;
        this.junkCategory = junkCategory;
        this.fishCategory = fishCategory;
    }

    @EventHandler
    public void onFishEvent(PlayerFishEvent event){
        UUID playerUUID = event.getPlayer().getUniqueId();

        if(event.getState().equals(PlayerFishEvent.State.BITE)) {
            fishCaught.put(playerUUID, System.currentTimeMillis());
        }

        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if(!(event.getCaught() instanceof Item)) return;

            if(isAutoFisher(event)) return;

            if(!isUnderSunlight(event)) return;

            if(fishCaught.containsKey(playerUUID) && lastFishCaught.containsKey(playerUUID)) {
                if(Math.abs((System.currentTimeMillis() - fishCaught.get(playerUUID)) - lastFishCaught.get(playerUUID)) < 8) {
                    ((Item) event.getCaught()).setItemStack(new ItemStack(Material.AIR));
                    return;
                }
                lastFishCaught.put(playerUUID, System.currentTimeMillis() - fishCaught.get(playerUUID));
            }
            else if(fishCaught.containsKey(playerUUID)) {
                lastFishCaught.put(playerUUID, System.currentTimeMillis() - fishCaught.get(playerUUID));
            }

            Item caught = (Item) event.getCaught();
            Map<Enchantment, Integer> enchants = event.getPlayer().getInventory().getItemInMainHand().getEnchantments();

            if(enchants.containsKey(Enchantment.LUCK)) {
                switch (enchants.get(Enchantment.LUCK)) {
                    case 1:
                        caught.setItemStack(lootTableLevel1());
                        break;
                    case 2:
                        caught.setItemStack(lootTableLevel2());
                        break;
                    case 3:
                        caught.setItemStack(lootTableLevel3());
                        break;
                }
            }else {
                caught.setItemStack(lootTableNoLuck());
            }
        }
    }

    private ItemStack lootTableNoLuck(){
        ItemStack item;
        double randomVar = random.nextDouble();
        if(randomVar < 0.85)
            item = fishCategory.returnFish(random);
        else if(randomVar > 0.85 && randomVar < 0.975)
            item = junkCategory.returnJunk(random);
        else
            item = treasureCategory.returnTreasureBarrel();

        return item;
    }

    private ItemStack lootTableLevel1(){
        ItemStack item;
        double randomVar = random.nextDouble();
        if(randomVar < 0.848)
            item = fishCategory.returnFish(random);
        else if(randomVar > 0.848 && randomVar < 0.96)
            item = junkCategory.returnJunk(random);
        else
            item = treasureCategory.returnTreasureBarrel();

        return item;
    }

    private ItemStack lootTableLevel2(){
        ItemStack item;
        double randomVar = random.nextDouble();
        if(randomVar < 0.847)
            item = fishCategory.returnFish(random);
        else if(randomVar > 0.847 && randomVar < 0.945)
            item = junkCategory.returnJunk(random);
        else
            item = treasureCategory.returnTreasureBarrel();

        return item;
    }

    private ItemStack lootTableLevel3(){
        ItemStack item;
        double randomVar = random.nextDouble();
        if(randomVar < 0.845)
            item = fishCategory.returnFish(random);
        else if(randomVar > 0.845 && randomVar < 0.93)
            item = junkCategory.returnJunk(random);
        else
            item = treasureCategory.returnTreasureBarrel();

        return item;
    }

    private boolean isAutoFisher(PlayerFishEvent event) {
        Location loc = event.getHook().getLocation();

        loc.subtract(1, 1, 1);
        for (byte x = 0; x<3; x++) {
            for(byte y = 0; y<4; y++) {
                for (byte z = 0; z<3; z++) {
                    if (loc.getBlock().getType() == Material.TRIPWIRE_HOOK ||
                            loc.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE ||
                            loc.getBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE ||
                            loc.getBlock().getType() == Material.JUKEBOX) {
                        event.getPlayer().sendMessage("A block used commonly for auto-fishers has been detected, please do not be near one");
                        event.setCancelled(true);
                        return true;
                    }

                    loc.add(0, 0, 1);
                }
                loc.subtract(0, 0, 3);
                loc.add(0, 1, 0);
            }
            loc.subtract(0, 3, 0);
            loc.add(1, 0, 0);
        }

        return false;
    }

    private boolean isUnderSunlight(PlayerFishEvent event){
        if(event.getPlayer().getLocation().getBlock().getRelative(0, 1, 0).getLightFromSky() != 15){
            event.getPlayer().sendMessage("Please fish under direct sunlight");
            event.setCancelled(true);
            return false;
        }

        return true;
    }
}
