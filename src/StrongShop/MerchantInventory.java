package StrongShop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MerchantInventory
{
    static FileConfiguration fc;
    public static Inventory getInventory(String mobname)
    {
	fc = StrongShop.getFC();
	System.out.println(mobname);
	Set<String> names = fc.getConfigurationSection(mobname).getKeys(false);
	int size = names.size();
	Inventory SpawnShop = (Inventory) Bukkit.createInventory(null, 54, "Spawn Shop");
	ItemStack[] options = new ItemStack[size];
	ItemMeta[] meta = new ItemMeta[size];
	Iterator<String> i = names.iterator();
	String[] ItemName = new String[size];
	int  x = 0;
	while(i.hasNext()){ItemName[x]=i.next();x++;}
	for(x = 0; x<ItemName.length; x++)
	{
	  
	    ArrayList<String> lists = new ArrayList();
	    options[x] = new ItemStack(Material.matchMaterial(ItemName[x]));

	    meta[x] = options[x].getItemMeta();
	    if(ItemName[x].contains("_"))
	    {
		ItemName[x].replace('_', ' ');
	    }
	    meta[x].setDisplayName(ItemName[x]);
	    int price = fc.getInt(mobname+"."+ItemName[x]);
	    lists.add(ChatColor.GREEN+"Buy: "+price);
	    lists.add(ChatColor.RED+"Sell: "+price/6);
	    lists.add(ChatColor.GOLD+"Left click to buy, Right Click to sell");
	    lists.add(ChatColor.GOLD+"Hold shift to buy/sell an entire stack!");
	    meta[x].setLore(lists);
	    options[x].setItemMeta(meta[x]);
	    SpawnShop.setItem(x,options[x]);
	    lists.clear();
	}
	
	return SpawnShop;
    }
}
