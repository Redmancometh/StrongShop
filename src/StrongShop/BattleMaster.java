package StrongShop;

import java.util.ArrayList;
import java.util.List;

import mc.alk.arena.BattleArena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BattleMaster implements Listener
{
    @EventHandler
    public void openMenu(PlayerInteractEntityEvent e)
    {
	if(e.getRightClicked() instanceof Player)
	{
	    Player p2 = (Player) e.getRightClicked();
	    if(p2.getName().contains("Battlemaster"))
	    {
		openMenu(e.getPlayer());
	    }
	}
    }
    @EventHandler
    public void openMenu(Player p)
    {
	List<String> lore0 = new ArrayList<String>();
	Inventory BattleMaster = (Inventory) Bukkit.createInventory(null, 9, "Battle Menu");
	ItemStack[] options = new ItemStack[9];
	ItemMeta[] imeta = new ItemMeta[9];
	lore0.add("Duel Another Player at Random!");
	for (int x = 0; x < 6; x++)
	{
	    options[x] = new ItemStack(Material.GOLD_SWORD);
	    imeta[x] = options[x].getItemMeta();
	    imeta[x].setDisplayName("Join 1v1 Arena Number: " + (x + 1));
	    imeta[x].setLore(lore0);
	    options[x].setItemMeta(imeta[x]);
	    BattleMaster.addItem(options[x]);
	}
	p.openInventory(BattleMaster);
	lore0.clear();

    }
    @EventHandler
    public void menuOption(InventoryClickEvent e)
    {
	if (e.getInventory().getName().contains("Battle Menu"))
	{
	    Player p = (Player) e.getWhoClicked();
	    e.setCancelled(true);
	    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Join 1v1 Arena Number: 1"))
	    {
		p.performCommand("arena join arena1");
	    }
	    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Join 1v1 Arena Number: 2"))
	    {
		p.performCommand("arena join arena2");
	    }
	    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Join 1v1 Arena Number: 3"))
	    {
		p.performCommand("arena join arena3");
	    }
	    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Join 1v1 Arena Number: 4"))
	    {
		p.performCommand("arena join arena4");
	    }
	    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Join 1v1 Arena Number: 5"))
	    {
		p.performCommand("arena join arena5");
	    }
	    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Join 1v1 Arena Number: 6"))
	    {
		p.performCommand("arena join arena6");
	    }
	}
    }
}
