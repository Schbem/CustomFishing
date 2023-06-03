package me.schmeb.customfishing.listeners;

import me.schmeb.customfishing.CustomFishing;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

public class OnPlayerRightClick implements Listener {
    PlayerOnFishEvent playerOnFishEvent = new PlayerOnFishEvent();
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event){
        if(event.getItem() != null && event.getHand() == EquipmentSlot.HAND && event.getAction().isRightClick() && event.getItem().getType().equals(Material.BARREL) && event.getItem().getItemMeta() != null)
        {
            ItemMeta itemMeta = event.getItem().getItemMeta();
            if(itemMeta.getPersistentDataContainer().has(new NamespacedKey(CustomFishing.getInstance(), "treasure_barrel")))
            {
                event.setCancelled(true);
                int amount = event.getItem().getAmount();
                event.getItem().setAmount(amount - 1);
                event.getPlayer().getInventory().addItem(playerOnFishEvent.treasureCategory());
            }
        }
    }
}
