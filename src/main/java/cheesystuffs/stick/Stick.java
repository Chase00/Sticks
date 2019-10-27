package cheesystuffs.stick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public final class Stick extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Stick plugin enabled.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Stick plugin disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("stick")) {
            onDisable();
            onEnable();
            Player player = (Player) sender;
            player.sendMessage(ChatColor.YELLOW + "Sticks have been reloaded.");
        }

        return true;
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent e) {

        int timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

                Player p = e.getPlayer();

                ItemStack item = e.getPlayer().getInventory().getItemInMainHand(); // Get item in player's hand
                if (item.getItemMeta().getLore() != null) { // Check if there's an item and if it has a lore

                    if (item.getType() == Material.STICK) { // Checks for stick

                        PotionEffect effect = new PotionEffect(PotionEffectType.GLOWING, 200, 1, true);
                        p.addPotionEffect(effect);

                    }
                }
            }
        }, 0, 20);
    }
}
