package StrongShop;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BloodListener implements Listener
{
    @EventHandler
    public void showBlood(EntityDamageByEntityEvent e)
    {
	 e.getEntity().getWorld().playEffect(e.getEntity().getLocation().add(0, .5, 0), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
    }
}
