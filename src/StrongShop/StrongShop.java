package StrongShop;
import java.util.HashSet;
import java.util.List;

import mc.alk.arena.BattleArena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.garbagemule.MobArena.MobArena;

public class StrongShop extends JavaPlugin
{
    static FileConfiguration fc;
    MobArena ma;
    PluginManager pm = getServer().getPluginManager();
    public void onEnable()
    {
	ma = (MobArena)pm.getPlugin("MobArena");
	pm.registerEvents(new MerchantListener(), this);
	pm.registerEvents(new CheatStop(this), this);
	pm.registerEvents(new BattleMaster(), this);
	pm.registerEvents(new InstaSoup(), this);
	pm.registerEvents(new BloodListener(), this);
	pm.registerEvents(new MenuListener(), this);
	addDefault();
	fc = this.getConfig();
    }
    public static FileConfiguration getFC()
    {
	return fc;
    }
    public void addDefault()
    {
	this.getConfig().addDefault("Merchant", "Wood Sword");
    }
    public MobArena getMA()
    {
	return ma;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
	Player p = (Player)sender;
	if(BattleArena.inArena(p)&&(!args[0].contains("arena"))){args=null;}
	return false;
    }
}
