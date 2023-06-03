package me.schmeb.customfishing.listeners;

import me.schmeb.customfishing.CustomFishing;
import me.schmeb.customfishing.configfiles.ConfigFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayerOnFishEvent implements Listener {
    @EventHandler
    public void onFishEvent(PlayerFishEvent event){
        if (event.getCaught() instanceof Item && event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if(isAutoFisher(event.getHook().getLocation())){
                event.getPlayer().sendMessage("A tripwire hook has been detected, please do not be near one");
                event.setCancelled(true);
            }

            Item caught = (Item) event.getCaught();
            Map<Enchantment, Integer> enchants = event.getPlayer().getInventory().getItemInMainHand().getEnchantments();
            if(enchants.containsKey(Enchantment.LUCK)){
                switch (enchants.get(Enchantment.LUCK)){
                    case 1:
                        caught.setItemStack(lootTableLevel1());
                        break;
                    case 2:
                        caught.setItemStack(lootTableLevel2());
                        break;
                    case 3:
                        caught.setItemStack(lootTableLevel3());
                        break;
                    default:
                        caught.setItemStack(lootTableNoLuck());
                }
            }else{
                caught.setItemStack(lootTableNoLuck());
            }
        }
    }

    private ItemStack lootTableNoLuck(){
        ItemStack item;
        double randomVar = Math.random();
        if(randomVar < 0.85)
            item = fishCategory();
        else if(randomVar > 0.85 && randomVar < 0.975)
            item = junkCategory();
        else
            item = returnTreasureBarrel();

        return item;
    }

    private ItemStack lootTableLevel1(){
        ItemStack item;
        double randomVar = Math.random();
        if(randomVar < 0.848)
            item = fishCategory();
        else if(randomVar > 0.848 && randomVar < 0.96)
            item = junkCategory();
        else
            item = returnTreasureBarrel();

        return item;
    }

    private ItemStack lootTableLevel2(){
        ItemStack item;
        double randomVar = Math.random();
        if(randomVar < 0.847)
            item = fishCategory();
        else if(randomVar > 0.847 && randomVar < 0.945)
            item = junkCategory();
        else
            item = returnTreasureBarrel();

        return item;
    }

    private ItemStack lootTableLevel3(){
        ItemStack item;
        double randomVar = Math.random();
        if(randomVar < 0.845)
            item = fishCategory();
        else if(randomVar > 0.845 && randomVar < 0.93)
            item = junkCategory();
        else
            item = returnTreasureBarrel();

        return item;
    }

    private ItemStack fishCategory(){
        ItemStack item;
        double randomVar = Math.random();
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

    private ItemStack junkCategory(){
        ItemStack item;
        double randomVar = Math.random();
        if(randomVar < 0.17)
            item = new ItemStack(Material.LILY_PAD);
        else if(randomVar > 0.17 && randomVar < 0.27)
            item = new ItemStack(Material.BOWL);
        else if(randomVar > 0.27 && randomVar < 0.29){
            item = new ItemStack(Material.FISHING_ROD);
            ItemMeta itemMeta = item.getItemMeta();
            Damageable damage = (Damageable) itemMeta;
            Random r = new Random();
            int randomInt = r.nextInt(60) + 1;
            damage.setDamage(randomInt);
            item.setItemMeta(itemMeta);
        }else if(randomVar > 0.29 && randomVar < 0.39)
            item = new ItemStack(Material.LEATHER);
        else if(randomVar > 0.39 && randomVar < 0.49){
            item = new ItemStack(Material.LEATHER_BOOTS);
            ItemMeta itemMeta = item.getItemMeta();
            Damageable damage = (Damageable) itemMeta;
            Random r = new Random();
            int randomInt = r.nextInt(60) + 1;
            damage.setDamage(randomInt);
            item.setItemMeta(itemMeta);
        }else if(randomVar > 0.49 && randomVar < 0.79)
            item = notesCategory();
        else if(randomVar > 0.79 && randomVar < 0.89)
            item = new ItemStack(Material.BONE);
        else
            item = new ItemStack(Material.TRIPWIRE_HOOK);


        return item;
    }

    public ItemStack treasureCategory(){
        ItemStack item;
        double randomVar = Math.random();
        if(randomVar < 0.167){
            item = new ItemStack(Material.BOW);
            ItemMeta itemMeta = item.getItemMeta();
            Damageable damage = (Damageable) itemMeta;
            Random r = new Random();
            int randomInt = r.nextInt(380) + 1;
            damage.setDamage(randomInt);
            bowEnchanter(itemMeta);
            item.setItemMeta(itemMeta);
        }
        else if(randomVar > 0.167 && randomVar < 0.334){
            item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta itemMeta = item.getItemMeta();
            bookEnchanter(itemMeta);
            item.setItemMeta(itemMeta);
        }
        else if(randomVar > 0.334 && randomVar < 0.501){
            item = new ItemStack(Material.FISHING_ROD);
            ItemMeta itemMeta = item.getItemMeta();
            Damageable damage = (Damageable) itemMeta;
            Random r = new Random();
            int randomInt = r.nextInt(60) + 1;
            damage.setDamage(randomInt);
            fishingRodEnchanter(itemMeta);
            item.setItemMeta(itemMeta);
        }
        else if(randomVar > 0.501 && randomVar < 0.668)
            item = new ItemStack(Material.NAME_TAG);
        else if(randomVar > 0.668 && randomVar < 0.835)
            item = new ItemStack(Material.NAUTILUS_SHELL);
        else
            item = new ItemStack(Material.SADDLE);

        return item;
    }

    private ItemStack notesCategory(){
        ItemStack item;
        double randomVar = Math.random();
        if(randomVar < 0.20){
            item = new ItemStack(Material.PAPER);
            notesWriter(item, ConfigFile.get().getStringList("First Note Message"));
        } else if(randomVar > 0.20 && randomVar < 0.40){
            item = new ItemStack(Material.PAPER);
            notesWriter(item, ConfigFile.get().getStringList("Second Note Message"));
        } else if(randomVar > 0.40 && randomVar < 0.60){
            item = new ItemStack(Material.PAPER);
            notesWriter(item, ConfigFile.get().getStringList("Third Note Message"));
        } else if(randomVar > 0.60 && randomVar < 0.80){
            item = new ItemStack(Material.PAPER);
            notesWriter(item, ConfigFile.get().getStringList("Fourth Note Message"));
        } else{
            item = new ItemStack(Material.PAPER);
            notesWriter(item, ConfigFile.get().getStringList("Fifth Note Message"));
        }

        return item;
    }

    private ItemStack returnTreasureBarrel(){
        ItemStack item = new ItemStack(Material.BARREL);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(CustomFishing.getInstance(), "treasure_barrel"), PersistentDataType.STRING, "DATA");
        itemMeta.displayName(Component.text("Treasure Barrel", NamedTextColor.GOLD));
        List<TextComponent> lore = new ArrayList<>();
        lore.add(Component.text("Right Click to see what treasure is hidden inside this Barrel.", NamedTextColor.WHITE));
        itemMeta.lore(lore);
        itemMeta.addEnchant(Enchantment.LURE, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }

    private void notesWriter(ItemStack item, List<String> lore){
        ItemMeta itemMeta = item.getItemMeta();
        List<Component> loreList = new ArrayList<>();
        for(int i = 0; i < lore.size(); i++)
            loreList.add(i, Component.text(lore.get(i), NamedTextColor.WHITE));
        itemMeta.lore(loreList);
        item.setItemMeta(itemMeta);
    }

    private void bowEnchanter(ItemMeta itemmeta){
        Random power = new Random();
        Random unbreaking = new Random();
        int randomPowerLevel = power.nextInt(4) + 1;
        int randomUnbreakingLevel = unbreaking.nextInt(2) + 1;

        double randomMending = Math.random();
        double randomFlame = Math.random();
        double randomPunch = Math.random();
        if(randomMending < 0.25)
            itemmeta.addEnchant(Enchantment.MENDING, 1, false);
        else if(randomMending > 0.25 && randomMending < 0.60)
            itemmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);

        if(randomFlame < 0.4)
            itemmeta.addEnchant(Enchantment.ARROW_FIRE, 1, false);

        if(randomPunch < 0.3)
            itemmeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, false);
        else if(randomPunch > 0.3 && randomPunch < 0.5)
            itemmeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, false);

        itemmeta.addEnchant(Enchantment.ARROW_DAMAGE, randomPowerLevel, false);
        itemmeta.addEnchant(Enchantment.DURABILITY, randomUnbreakingLevel, false);
    }

    private void bookEnchanter(ItemMeta item){
        EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) item;

        Random RNG = new Random();
        Random RNG2 = new Random();
        int howManyRolls = RNG2.nextInt(4);
        int randomNumberGenerator = RNG.nextInt(105) + 1;
        do{
            switch (randomNumberGenerator){
                case 1: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 1, false); break;
                case 2: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 2, false); break;
                case 3: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_INFINITE, 1, false); break;
                case 4: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 1, false); break;
                case 5: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 2, false); break;
                case 6: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 3, false); break;
                case 7: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 4, false); break;
                case 8: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 5, false); break;
                case 9: enchantmentStorageMeta.addStoredEnchant(Enchantment.ARROW_FIRE, 1, false); break;
                case 10: enchantmentStorageMeta.addStoredEnchant(Enchantment.BINDING_CURSE, 1, false); break;
                case 11: enchantmentStorageMeta.addStoredEnchant(Enchantment.CHANNELING, 1, false); break;
                case 12: enchantmentStorageMeta.addStoredEnchant(Enchantment.DURABILITY, 1, false); break;
                case 13: enchantmentStorageMeta.addStoredEnchant(Enchantment.DURABILITY, 2, false); break;
                case 14: enchantmentStorageMeta.addStoredEnchant(Enchantment.DURABILITY, 3, false); break;
                case 15: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ALL, 1, false); break;
                case 16: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ALL, 2, false); break;
                case 17: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ALL, 3, false); break;
                case 18: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ALL, 4, false); break;
                case 19: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ALL, 5, false); break;
                case 20: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ARTHROPODS, 1, false); break;
                case 21: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ARTHROPODS, 2, false); break;
                case 22: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ARTHROPODS, 3, false); break;
                case 23: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ARTHROPODS, 4, false); break;
                case 24: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_ARTHROPODS, 5, false); break;
                case 25: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_UNDEAD, 1, false); break;
                case 26: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_UNDEAD, 2, false); break;
                case 27: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_UNDEAD, 3, false); break;
                case 28: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_UNDEAD, 4, false); break;
                case 29: enchantmentStorageMeta.addStoredEnchant(Enchantment.DAMAGE_UNDEAD, 5, false); break;
                case 30: enchantmentStorageMeta.addStoredEnchant(Enchantment.DEPTH_STRIDER, 1, false); break;
                case 31: enchantmentStorageMeta.addStoredEnchant(Enchantment.DEPTH_STRIDER, 2, false); break;
                case 32: enchantmentStorageMeta.addStoredEnchant(Enchantment.DEPTH_STRIDER, 3, false); break;
                case 33: enchantmentStorageMeta.addStoredEnchant(Enchantment.DIG_SPEED, 1, false); break;
                case 34: enchantmentStorageMeta.addStoredEnchant(Enchantment.DIG_SPEED, 2, false); break;
                case 35: enchantmentStorageMeta.addStoredEnchant(Enchantment.DIG_SPEED, 3, false); break;
                case 36: enchantmentStorageMeta.addStoredEnchant(Enchantment.DIG_SPEED, 4, false); break;
                case 37: enchantmentStorageMeta.addStoredEnchant(Enchantment.DIG_SPEED, 5, false); break;
                case 38: enchantmentStorageMeta.addStoredEnchant(Enchantment.FIRE_ASPECT, 1, false); break;
                case 39: enchantmentStorageMeta.addStoredEnchant(Enchantment.FIRE_ASPECT, 2, false); break;
                case 40: enchantmentStorageMeta.addStoredEnchant(Enchantment.IMPALING, 1, false); break;
                case 41: enchantmentStorageMeta.addStoredEnchant(Enchantment.IMPALING, 2, false); break;
                case 42: enchantmentStorageMeta.addStoredEnchant(Enchantment.IMPALING, 3, false); break;
                case 43: enchantmentStorageMeta.addStoredEnchant(Enchantment.IMPALING, 4, false); break;
                case 44: enchantmentStorageMeta.addStoredEnchant(Enchantment.IMPALING, 5, false); break;
                case 45: enchantmentStorageMeta.addStoredEnchant(Enchantment.KNOCKBACK, 1, false); break;
                case 46: enchantmentStorageMeta.addStoredEnchant(Enchantment.KNOCKBACK, 2, false); break;
                case 47: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false); break;
                case 48: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, false); break;
                case 49: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, false); break;
                case 50: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOOT_BONUS_MOBS, 1, false); break;
                case 51: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOOT_BONUS_MOBS, 2, false); break;
                case 52: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOOT_BONUS_MOBS, 3, false); break;
                case 53: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOYALTY, 1, false); break;
                case 54: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOYALTY, 2, false); break;
                case 55: enchantmentStorageMeta.addStoredEnchant(Enchantment.LOYALTY, 3, false); break;
                case 56: enchantmentStorageMeta.addStoredEnchant(Enchantment.LUCK, 1, false); break;
                case 57: enchantmentStorageMeta.addStoredEnchant(Enchantment.LUCK, 2, false); break;
                case 58: enchantmentStorageMeta.addStoredEnchant(Enchantment.LUCK, 3, false); break;
                case 59: enchantmentStorageMeta.addStoredEnchant(Enchantment.LURE, 1, false); break;
                case 60: enchantmentStorageMeta.addStoredEnchant(Enchantment.LURE, 2, false); break;
                case 61: enchantmentStorageMeta.addStoredEnchant(Enchantment.LURE, 3, false); break;
                case 62: enchantmentStorageMeta.addStoredEnchant(Enchantment.MULTISHOT, 1, false); break;
                case 63: enchantmentStorageMeta.addStoredEnchant(Enchantment.OXYGEN, 1, false); break;
                case 64: enchantmentStorageMeta.addStoredEnchant(Enchantment.OXYGEN, 2, false); break;
                case 65: enchantmentStorageMeta.addStoredEnchant(Enchantment.OXYGEN, 3, false); break;
                case 66: enchantmentStorageMeta.addStoredEnchant(Enchantment.PIERCING, 1, false); break;
                case 67: enchantmentStorageMeta.addStoredEnchant(Enchantment.PIERCING, 2, false); break;
                case 68: enchantmentStorageMeta.addStoredEnchant(Enchantment.PIERCING, 3, false); break;
                case 69: enchantmentStorageMeta.addStoredEnchant(Enchantment.PIERCING, 4, false); break;
                case 70: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false); break;
                case 71: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false); break;
                case 72: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false); break;
                case 73: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false); break;
                case 74: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false); break;
                case 75: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_EXPLOSIONS, 2, false); break;
                case 76: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3, false); break;
                case 77: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false); break;
                case 78: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_FALL, 1, false); break;
                case 79: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_FALL, 2, false); break;
                case 80: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_FALL, 3, false); break;
                case 81: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_FALL, 4, false); break;
                case 82: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_FIRE, 1, false); break;
                case 83: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_FIRE, 2, false); break;
                case 84: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_FIRE, 3, false); break;
                case 85: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_FIRE, 4, false); break;
                case 86: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_PROJECTILE, 1, false); break;
                case 87: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_PROJECTILE, 2, false); break;
                case 88: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_PROJECTILE, 3, false); break;
                case 89: enchantmentStorageMeta.addStoredEnchant(Enchantment.PROTECTION_PROJECTILE, 4, false); break;
                case 90: enchantmentStorageMeta.addStoredEnchant(Enchantment.QUICK_CHARGE, 1, false); break;
                case 91: enchantmentStorageMeta.addStoredEnchant(Enchantment.QUICK_CHARGE, 2, false); break;
                case 92: enchantmentStorageMeta.addStoredEnchant(Enchantment.QUICK_CHARGE, 3, false); break;
                case 93: enchantmentStorageMeta.addStoredEnchant(Enchantment.RIPTIDE, 1, false); break;
                case 94: enchantmentStorageMeta.addStoredEnchant(Enchantment.RIPTIDE, 2, false); break;
                case 95: enchantmentStorageMeta.addStoredEnchant(Enchantment.RIPTIDE, 3, false); break;
                case 96: enchantmentStorageMeta.addStoredEnchant(Enchantment.SILK_TOUCH, 1, false); break;
                case 97: enchantmentStorageMeta.addStoredEnchant(Enchantment.SWEEPING_EDGE, 1, false); break;
                case 98: enchantmentStorageMeta.addStoredEnchant(Enchantment.SWEEPING_EDGE, 2, false); break;
                case 99: enchantmentStorageMeta.addStoredEnchant(Enchantment.SWEEPING_EDGE, 3, false); break;
                case 100: enchantmentStorageMeta.addStoredEnchant(Enchantment.THORNS, 1, false); break;
                case 101: enchantmentStorageMeta.addStoredEnchant(Enchantment.THORNS, 2, false); break;
                case 102: enchantmentStorageMeta.addStoredEnchant(Enchantment.THORNS, 3, false); break;
                case 103: enchantmentStorageMeta.addStoredEnchant(Enchantment.VANISHING_CURSE, 1, false); break;
                case 104: enchantmentStorageMeta.addStoredEnchant(Enchantment.WATER_WORKER, 1, false); break;
                case 105: enchantmentStorageMeta.addStoredEnchant(Enchantment.MENDING, 3, false); break;
            }

            howManyRolls--;
            RNG = new Random();
            randomNumberGenerator = RNG.nextInt(105) + 1;
        }while (howManyRolls > 0);
    }

    private void fishingRodEnchanter(ItemMeta itemmeta){
        Random lure = new Random();
        Random luck = new Random();
        Random durability = new Random();
        int randomLureLevel = lure.nextInt(2) + 1;
        int randomLuckLevel = luck.nextInt(2) + 1;
        int randomDurabilityLevel = durability.nextInt(2) + 1;

        double randomLuck = Math.random();
        double randomMending = Math.random();
        double randomDurability = Math.random();
        if(randomMending < 0.25)
            itemmeta.addEnchant(Enchantment.MENDING, 1, false);

        if(randomLuck < 0.5)
            itemmeta.addEnchant(Enchantment.LUCK, randomLuckLevel, false);

        if(randomDurability < 0.75){
            itemmeta.addEnchant(Enchantment.DURABILITY, randomDurabilityLevel, false);
        }

        itemmeta.addEnchant(Enchantment.LURE, randomLureLevel, false);
    }

    protected boolean isAutoFisher(Location loc) {
        loc.subtract(1, 1, 1);
        for (byte x = 0; x<3; x++) {
            for(byte y = 0; y<4; y++) {
                for (byte z = 0; z<3; z++) {
                    if (loc.getBlock().getType() == Material.TRIPWIRE_HOOK)
                        return true;
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
}
