package cheesystuffs.stick;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Stick extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(ChatColor.YELLOW + "Stick plugin enabled.");
        getServer().getPluginManager().registerEvents( this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(ChatColor.YELLOW + "Stick plugin disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("stick")){
            onDisable();
            onEnable();
            Player player = (Player) sender;
            player.sendMessage(ChatColor.YELLOW + "Sticks have been reloaded.");
        }

        return true;
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        int i = e.getNewSlot();
        ItemStack item = e.getPlayer().getInventory().getItem(i); //this get the item in the selected slot (i)
        if (item != null && item.getItemMeta().getLore() != null) { //check if there is an item and if the item has a lore
            if (item.getType() == Material.STICK) {
                PotionEffect luck = new PotionEffect(PotionEffectType.LUCK, 200, 1, false);
                p.addPotionEffect(luck);
            }
        }
    }
}
