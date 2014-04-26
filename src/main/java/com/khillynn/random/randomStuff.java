package com.khillynn.random;

import com.khillynn.Troll;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class randomStuff extends JavaPlugin implements Listener
{
    public HashMap<String, ItemStack[]> invsave = new HashMap<>();

    public HashMap<String, ItemStack[]> armorsave = new HashMap<>();

    //Player Spawn Event
    @EventHandler
    public void onPlayerSpawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        p.getInventory().setContents(invsave.get(p.getName()));
        p.getInventory().setArmorContents(armorsave.get(p.getName()));
        invsave.remove(p.getName());
        armorsave.remove(p.getName());
    }

    //Player Death Event
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setKeepLevel(true);
        Player p = event.getEntity();
        if(event.getDrops() != null) {
            invsave.put(p.getName(), p.getInventory().getContents());
            if(p.getInventory().getHelmet()!=null){
                p.getInventory().getHelmet().setDurability((short) + 50);
            }
            if(p.getInventory().getChestplate()!=null){
                p.getInventory().getChestplate().setDurability((short) + 70);
            }
            if(p.getInventory().getLeggings()!=null){
                p.getInventory().getLeggings().setDurability((short) + 70);
            }
            if(p.getInventory().getBoots()!=null){
                p.getInventory().getBoots().setDurability((short) + 50);
            }
            armorsave.put(p.getName(), p.getInventory().getArmorContents());
            event.getDrops().clear();
            Bukkit.broadcastMessage("[" + ChatColor.GOLD + "Server:" + ChatColor.WHITE + "] " + ChatColor.RED + p.getName() + ChatColor.GRAY + " Has been Killed!");
        }
        else
        {
            Bukkit.broadcastMessage("[" + ChatColor.GOLD + "Server:" + ChatColor.WHITE + "] " + ChatColor.RED + p.getName() + ChatColor.GRAY + " Has been Killed!");
        }
    }

    @EventHandler
    public void PlayerRightClick(PlayerInteractEntityEvent event)
    {
        if(event.getRightClicked() instanceof Villager)
        {
            Player p = event.getPlayer();
            if(p.getInventory().getHelmet() != null) {
                p.getInventory().getHelmet().setDurability((short) 0);
            }
            if(p.getInventory().getChestplate() != null){
                p.getInventory().getChestplate().setDurability((short) 0);
            }
            if(p.getInventory().getLeggings() != null) {
                p.getInventory().getLeggings().setDurability((short) 0);
            }
            if(p.getInventory().getLeggings() != null) {
                p.getInventory().getBoots().setDurability((short) 0);
            }
        }
    }

    @EventHandler
    public void PlayerRightClick(PlayerInteractEvent event)
    {
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getPlayer().getItemInHand()!=null && event.getPlayer().getItemInHand().getType()!=null && event.getPlayer().getItemInHand().getType().equals(Material.EMERALD)){
                Player p = event.getPlayer();
                Inventory inv = Bukkit.createInventory(null, 43, "Jackpot!"); //format: null, size of inventory (must be divisible by 4), "GUI name"
                for(int x = 0; x < 43; x++)
                {
                    inv.setItem(x, new ItemStack(Material.DIAMOND));
                }
                p.openInventory(inv);
                p.setItemInHand(null);
                }
            }
    }

    @Override
    public void onEnable() {
        getLogger().info("Enabled! :D");
        Bukkit.getPluginManager().registerEvents(this, this);
        Troll troll = new Troll();
        getCommand("troll").setExecutor(troll);
        //no es troll but
        getCommand("healer").setExecutor(troll);
    }


}
