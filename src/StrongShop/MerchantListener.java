package StrongShop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MerchantListener implements Listener
{
    FileConfiguration fc;

    @EventHandler
    public void onRightclick(PlayerInteractEntityEvent e)
    {
	if (e.getRightClicked() instanceof Villager)
	{
	    Villager v = (Villager) e.getRightClicked();
	    if (StrongShop.getFC().contains(v.getCustomName()))
	    {
		e.setCancelled(true);
		String mobname = v.getCustomName();
		Player p = e.getPlayer();
		p.openInventory(MerchantInventory.getInventory(mobname));
	    }
	}
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
	if (e.getEntity() instanceof Villager)
	{
	    Villager v = (Villager) e.getEntity();
	    if (v.getCustomName() != null)
	    {
		if (fc.contains(v.getCustomName()))
		{
		    e.setCancelled(true);
		}
	    }
	}
    }
}
