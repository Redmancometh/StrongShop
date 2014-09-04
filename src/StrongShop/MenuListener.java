package StrongShop;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.earth2me.essentials.User;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class MenuListener implements Listener
{
    static FileConfiguration fc;
    ItemStack i;

    @EventHandler
    public void tradeItem(InventoryClickEvent e) throws NoLoanPermittedException, UserDoesNotExistException
    {
	fc = StrongShop.getFC();
	if (e.getInventory().getName().equals("Spawn Shop"))
	{
	    e.setCancelled(true);
	    // Buy
	    if (e.isLeftClick() && e.getCurrentItem().getType() != Material.AIR&&e.getClickedInventory().getName().equals("Spawn Shop"))
	    {
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() != null)
		{
		    int price = fc.getInt("WORTH." + e.getCurrentItem().getType().toString());
		    i = new ItemStack(e.getCurrentItem().getType());
		    if (e.isShiftClick())
		    {
			// Stack of Items
			try
			{
			    Economy.subtract(p.getName(), price * i.getMaxStackSize());
			    for (int x = 0; x < i.getMaxStackSize(); x++)
			    {
				p.getInventory().addItem(i);
			    }
			    p.sendMessage(ChatColor.GOLD+"You bought "+getItemName(i)+"x"+i.getMaxStackSize()+" for $"+price*i.getMaxStackSize());
			}
			catch (NoLoanPermittedException e1)
			{
			    if (i.getMaxStackSize() != 1)
			    {
				try
				{
				    int amount = (int) (Economy.getMoney(p.getName()) / price);
				    if (Economy.getMoney(p.getName()) > price)
				    {
					Economy.subtract(p.getName(), price * amount);
					for (int x = 0; x < amount; x++)
					{
					    p.getInventory().addItem(i);
					}
					p.sendMessage(ChatColor.GOLD+"You bought "+getItemName(i)+"x"+amount+" for $"+price*amount);
				    }
				    else
				    {
					throw e1;
				    }
				}
				catch (NoLoanPermittedException e3)
				{
				    p.sendMessage(ChatColor.RED + "You do not have enough money!");
				}

			    }
			    else
			    {
				try
				{
				    Economy.subtract(p.getName(), price);
				    p.getInventory().addItem(i);
				}
				catch (NoLoanPermittedException e2)
				{
				    p.sendMessage(ChatColor.RED + "You do not have enough money!");
				}
			    }
			}
		    }
		    // Single Item
		    else
		    {
			try
			{
			    Economy.subtract(p.getName(), price);
			    p.getInventory().addItem(i);
			    p.sendMessage(ChatColor.GOLD+"You bought "+getItemName(i)+"x1 for: "+price);
			}
			catch (NoLoanPermittedException e1)
			{
			    p.sendMessage(ChatColor.RED + "You do not have enough money!");
			}
		    }
		}
	    }
	    // Sell Item
	    if ((e.isRightClick()) && (e.getCurrentItem() != null) && (e.getCurrentItem().getType() != Material.AIR))
	    {
		Player p = (Player) e.getWhoClicked();
		this.i = e.getCurrentItem();
		if (e.isShiftClick())
		{
		    int amount = getAmountInInventory(p, i.getType());
		    if (amount > 0)
		    {
			int price = fc.getInt("WORTH." + i.getType().toString());
			if (amount >= 64)
			{
			    p.getInventory().removeItem(new ItemStack(i.getType(), 64));
			    Economy.add(p.getName(), 64 * price / 6);
			    p.sendMessage(ChatColor.GOLD+"You sold "+getItemName(i)+"x"+amount+" for: "+(64 * price / 6));
			}
			else
			{
			    p.getInventory().removeItem(new ItemStack(i.getType(), amount));
			    Economy.add(p.getName(), (amount * price / 6));
			    p.sendMessage(ChatColor.GOLD+"You sold "+getItemName(i)+"x"+amount+" for: "+(64 * price / 6));
			}
		    }
		    else
		    {
			p.sendMessage(ChatColor.DARK_RED + "You do not have this item or have an enchanted/damaged (low durability) version!");
		    }
		}
		else
		{
		    if (getAmountInInventory(p, i.getType()) >= 1)
		    {
			int price = fc.getInt("WORTH." + e.getCurrentItem().getType().toString());
			takeOne(p, new ItemStack(this.i.getType()));
			Economy.add(p.getName(), price / 6);
			p.sendMessage(ChatColor.GOLD+"You sold "+getItemName(i)+"x1 for: "+price/6);
		    }
		    else
		    {
			p.sendMessage(ChatColor.DARK_RED + "You do not have this item or have an enchanted/damaged (low durability) version!");
		    }
		}
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

    public int getAmountInInventory(Player player, Material type)
    {
	int total = 0;
	for (ItemStack is : player.getInventory())
	{
	    if (is != null)
	    {
		if (is.getType() == type&&is.getEnchantments().size()<1&&is.getDurability()==0)
		{
		    if(!is.getItemMeta().hasLore()&&!is.getItemMeta().hasDisplayName())
		    {
			total += is.getAmount();
		    }
		}
	    }
	}
	return total;
    }
    public String getItemName(ItemStack i)
    {
	String itemname;
	itemname = i.getType().toString();
	itemname = itemname.replace('_', ' ');
	return itemname;
	
    }
}