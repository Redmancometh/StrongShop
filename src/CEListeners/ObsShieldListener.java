package CEListeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class ObsShieldListener
{
    public void stopFire(EntityDamageEvent e)
    {
	if(e.getEntity() instanceof Player&&((e.getCause()==DamageCause.FIRE_TICK)||(e.getCause()==DamageCause.LAVA)||e.getCause()==DamageCause.FIRE))
	{
	    Player p = (Player)e.getEntity();
	    for(ItemStack i : p.getEquipment().getArmorContents())
	    {
		if(i.hasItemMeta())
		{
		    if(i.getItemMeta().hasLore())
		    {
			List<String> lore = i.getItemMeta().getLore();
			for(String loretext : lore)
			{
			    if(loretext.contains("Obsidian Shielding"))
			    {
				e.setCancelled(true);
			    }
			}
		    }
		}
	    }
	}
    }
}
