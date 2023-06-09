package me.schmeb.customfishing.listeners;

import me.schmeb.customfishing.FishingDrops.TreasureCategory;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class OnPlayerRightClick implements Listener {
    TreasureCategory treasureCategory;
    private final NamespacedKey treasureBarrel;

    public OnPlayerRightClick(TreasureCategory treasureCategory, NamespacedKey treasureBarrel){
        this.treasureCategory = treasureCategory;
        this.treasureBarrel = treasureBarrel;
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event){
        if(event.getItem() == null || event.getItem().getItemMeta() == null) return;
        if(event.getHand() != EquipmentSlot.HAND || !event.getAction().isRightClick()) return;
        if(!event.getItem().getType().equals(Material.BARREL)) return;

        ItemMeta itemMeta = event.getItem().getItemMeta();
        if(itemMeta.getPersistentDataContainer().has(treasureBarrel)) {
            event.setCancelled(true);
            int amount = event.getItem().getAmount();
            event.getItem().setAmount(amount - 1);
            event.getPlayer().getInventory().addItem(treasureCategory.returnTreasure(new Random()));
        }
    }
}
