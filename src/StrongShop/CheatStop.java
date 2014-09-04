package StrongShop;

import java.util.List;

import mc.alk.arena.BattleArena;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import com.garbagemule.MobArena.MobArena;

public class CheatStop implements Listener
{
    StrongShop sh;
    MobArena ma;

    public CheatStop(StrongShop sh)
    {
	this.sh = sh;
	ma = sh.getMA();
    }

    // Battle Arena Anti-Cheat
    @EventHandler(priority = EventPriority.MONITOR)
    public void stopOutsideDamage(EntityDamageByEntityEvent e)
    {
	if (e.getEntity() instanceof Player)
	{
	    Player damaged = (Player) e.getEntity();
	    if (e.getDamager() instanceof Player)
	    {
		Player damager = (Player) e.getDamager();
		if (!BattleArena.inArena(damager))
		{
		    if (BattleArena.inArena(damaged))
		    {
			e.setCancelled(true);
			Bukkit.broadcastMessage("CANCELLED!");
		    }
		}
	    }
	}
    }

    @EventHandler
    public void stopArenaInteract(PlayerInteractEvent e)
    {
	if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
	{
	    Player p = e.getPlayer();
	    if (BattleArena.inArena(p))
	    {
		e.setCancelled(true);
	    }
	    List<Player> inProgress = ma.getArenaMaster().getAllPlayers();
	    for (Player p2 : inProgress)
	    {
		if (p == p2)
		{
		    e.setCancelled(true);
		}
	    }
	}
    }

    @EventHandler
    public void openInventory(InventoryOpenEvent e)
    {
	if (BattleArena.inArena((Player) e.getPlayer()))
	{
	    e.setCancelled(true);
	}
	List<Player> inProgress = ma.getArenaMaster().getAllPlayers();
	for (Player p2 : inProgress)
	{
	    if (e.getPlayer() == p2)
	    {
		e.setCancelled(true);
	    }
	}
    }
    // Anti Looting X
    @EventHandler
    public void removeSword(PlayerItemHeldEvent e)
    {
	ItemStack i = e.getPlayer().getInventory().getItem(e.getNewSlot());
	if (i != null)
	{
	    if (i.getType() != Material.AIR && i.containsEnchantment(Enchantment.LOOT_BONUS_MOBS))
	    {
		if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) >= 10)
		{
		    i.removeEnchantment(Enchantment.LOOT_BONUS_MOBS);
		}
	    }
	}
    }
}
