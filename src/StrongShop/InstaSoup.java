package StrongShop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class InstaSoup implements Listener
{
    @EventHandler
    public void onEat(PlayerInteractEvent e)
    {	
	if(e.getPlayer().getItemInHand().getType()==Material.MUSHROOM_SOUP)
	{
	    Player p = e.getPlayer();
	    Damageable le = (Damageable)p;
	    double health = le.getHealth();
	    ItemStack soup = p.getItemInHand();
	    if(health+2<20)
	    {
		e.setCancelled(true);
		le.setHealth(health+2);
		p.setFoodLevel(p.getFoodLevel()+2);
		takeOne(p, new ItemStack(Material.MUSHROOM_SOUP,1));
		p.updateInventory();
	    }
	}
    }
    public void takeOne(Player p, ItemStack i)
    {
	if (i.getAmount() <= 1)
	{
	    p.getInventory().removeItem(i);
	}
	if (i.getAmount() > 1)
	{
	    i.setAmount(i.getAmount() - 1);
	}
	p.updateInventory();
    }
}
