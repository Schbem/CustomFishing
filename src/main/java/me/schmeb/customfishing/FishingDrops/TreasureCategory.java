package me.schmeb.customfishing.FishingDrops;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreasureCategory {
    private final NamespacedKey treasureBarrel;

    public TreasureCategory(NamespacedKey treasureBarrel){
        this.treasureBarrel = treasureBarrel;
    }
    public ItemStack returnTreasure(Random random){
        ItemStack item;
        ItemMeta itemMeta;
        switch (random.nextInt(6)){
            case 0:
                item = new ItemStack(Material.BOW);
                itemMeta = item.getItemMeta();
                bowEnchanter(itemMeta, random);
                item.setItemMeta(itemMeta);
                break;
            case 1:
                item = new ItemStack(Material.ENCHANTED_BOOK);
                itemMeta = item.getItemMeta();
                bookEnchanter(itemMeta, random);
                item.setItemMeta(itemMeta);
                break;
            case 2:
                item = new ItemStack(Material.FISHING_ROD);
                itemMeta = item.getItemMeta();
                fishingRodEnchanter(itemMeta, random);
                item.setItemMeta(itemMeta);
                break;
            case 3:
                item = new ItemStack(Material.NAME_TAG);
                break;
            case 4:
                item = new ItemStack(Material.NAUTILUS_SHELL);
                break;
            case 5:
                item = new ItemStack(Material.SADDLE);
                break;
            default:
                item = new ItemStack(Material.GLASS_BOTTLE);
                break;
        }
        return item;
    }

    private void bowEnchanter(ItemMeta itemmeta, Random random){
        if(random.nextDouble() < 0.25) {
            if(random.nextBoolean()){
                itemmeta.addEnchant(Enchantment.MENDING, 1, false);
            } else{
                itemmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
            }
        }

        if(random.nextDouble() < 0.4) {
            itemmeta.addEnchant(Enchantment.ARROW_FIRE, 1, false);
        }

        if(random.nextDouble() < 0.3) {
            itemmeta.addEnchant(Enchantment.ARROW_KNOCKBACK, random.nextInt(2) + 1, false);
        }

        itemmeta.addEnchant(Enchantment.ARROW_DAMAGE, random.nextInt(5) + 1, false);
        itemmeta.addEnchant(Enchantment.DURABILITY, random.nextInt(3) + 1, false);

        Damageable damage = (Damageable) itemmeta;
        damage.setDamage(random.nextInt(380) + 1);
    }

    private void bookEnchanter(ItemMeta itemMeta, Random random){
        EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) itemMeta;

        int howManyRolls = random.nextInt(3) + 1;
        int randomNumberGenerator = random.nextInt(105) + 1;
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
            randomNumberGenerator = random.nextInt(105) + 1;
        }while (howManyRolls > 0);
    }

    private void fishingRodEnchanter(ItemMeta itemmeta, Random random){
        if(random.nextDouble() < 0.25){
            itemmeta.addEnchant(Enchantment.MENDING, 1, false);
        }

        if(random.nextDouble() < 0.5) {
            itemmeta.addEnchant(Enchantment.LUCK, random.nextInt(3) + 1, false);
        }

        if(random.nextDouble() < 0.5){
            itemmeta.addEnchant(Enchantment.DURABILITY, random.nextInt(3) + 1, false);
        }

        itemmeta.addEnchant(Enchantment.LURE, random.nextInt(3) + 1, false);

        Damageable damage = (Damageable) itemmeta;
        damage.setDamage(random.nextInt(60) + 1);
    }

    public ItemStack returnTreasureBarrel(){
        ItemStack item = new ItemStack(Material.BARREL);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.getPersistentDataContainer().set(
                treasureBarrel,
                PersistentDataType.STRING,
                "DATA"
        );

        itemMeta.displayName(Component.text("Treasure Barrel", NamedTextColor.GOLD));

        List<TextComponent> lore = new ArrayList<>();
        lore.add(Component.text("Right Click to see what treasure is hidden inside this Barrel.", NamedTextColor.WHITE));
        itemMeta.lore(lore);

        itemMeta.addEnchant(Enchantment.LURE, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);

        return item;
    }
}
