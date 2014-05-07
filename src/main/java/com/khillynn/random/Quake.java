package com.khillynn.random;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Quake
{
    public class randomStuff extends JavaPlugin implements Listener
    {
        @EventHandler
        public void onPlayerSpawn(PlayerRespawnEvent event)
        {
            Player p = event.getPlayer();
            p.getPlayer().getInventory().setItemInHand(new ItemStack(Material.WOOD_HOE, 1));
        }

        public void onInteract(PlayerInteractEvent e) {
            Location loc = e.getPlayer().getLocation();

            if(e.getAction() == Action.RIGHT_CLICK_AIR) {
                if(e.getPlayer().getItemInHand().getType() == Material.WOOD_HOE) {
                    e.getPlayer().launchProjectile(WitherSkull.class);
                    e.getPlayer().playSound(loc, Sound.GHAST_FIREBALL, 100, 0);

                    WitherSkull bullet = (WitherSkull) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
                    bullet.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.5));
                }
            }
        }

    }
}
