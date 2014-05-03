package com.khillynn;

import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class Troll implements CommandExecutor {
    private int dist = 1;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length == 0) {
            Player p = (Player)sender;
            p.sendMessage(ChatColor.DARK_BLUE + "---------------------" + ChatColor.BOLD +
                    "TrollMaster" + ChatColor.RESET + ChatColor.DARK_BLUE + "--------------------");
            p.sendMessage(ChatColor.YELLOW + " Type '/troll web' to create a WEB CAGE around the target\n Type '/troll creeper' to surround the target in CREEPERS\n Type '/troll strike' to strike the target with LIGHTNING\n Type '/troll drop' to drop LAVA onto the target\n ***** Happy Hunting =D *****");
            p.sendMessage(ChatColor.DARK_BLUE +
                    "-----------------------------------------------------");
            return true;
        }
        Player p = (Player)sender;
        if (args[0].equalsIgnoreCase("creeper")) {
            if (args.length != 2) {
                p.sendMessage(ChatColor.RED + " Please specify a player.");
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null) {
                p.sendMessage(ChatColor.RED + " Impossible to find : " + args[1] + " !");
                return true;
            }
            p.sendMessage(ChatColor.GOLD + " Troll " + ChatColor.BOLD + "CREEPER" + ChatColor.RESET + ChatColor.GOLD + " on : " + ChatColor.GREEN + target.getName() + ChatColor.GOLD + " done!");
            Location loc = target.getLocation();
            loc.setX(loc.getX() + 2.0D);
            Location loc1 = target.getLocation();
            loc1.setZ(loc1.getZ() + 2.0D);
            Location loc3 = target.getLocation();
            loc3.setZ(loc3.getZ() - 2.0D);
            Location loc2 = target.getLocation();
            loc2.setX(loc2.getX() - 2.0D);
            Location loc4 = target.getLocation();

            for (int i = 0; i < 10; i++)
            {
                World world = target.getWorld();
                world.spawn(loc, Creeper.class);
                world.spawn(loc1, Creeper.class);
                world.spawn(loc2, Creeper.class);
                world.spawn(loc3, Creeper.class);
                world.spawn(loc4, Creeper.class);
            }
        }

        if (args[0].equalsIgnoreCase("web")) {
            if (args.length != 2) {
                p.sendMessage(ChatColor.RED + " Please specify a player.");
                return true;
            }

            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null) {
                p.sendMessage(ChatColor.RED + " Impossible to find : " + args[1] + " !");
                return true;
            }

            else
            {
                Location loc = target.getLocation();

                int i=0;
                double total = Math.pow((dist*2)+1, 3);
                for(int x=(0-dist); x<=dist; x++){
                    for(int y=(0-dist); y<=dist; y++){
                        for(int z=(0-dist); z<=dist; z++){
                            Location l = new Location(loc.getWorld(), loc.getX()+x, loc.getY()+y, loc.getZ()+z);
                            l.getBlock().setType(Material.WEB);
                            i++;
                            if(i%100==0){
                                p.sendMessage(ChatColor.GREEN.toString()+(Math.round((i/total)*10000))/100+"% done!");
                            }
                        }
                    }
                }
                p.sendMessage(ChatColor.GOLD + " Troll " + ChatColor.BOLD + "WEB CAGE" + ChatColor.RESET + ChatColor.GOLD + " on : " + ChatColor.GREEN + target.getName() + ChatColor.GOLD + " done!");
            }

        }
        else if(args[0].equalsIgnoreCase("set")){
            Integer i;
            try{
                i = Integer.parseInt(args[1]);
                if(i>16){
                    sender.sendMessage(ChatColor.RED.toString()+i+" is too large of a number!");
                    return true;
                }
            }catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED+"Incorrect number '"+args[1]+"'!");
                return true;
            }
            dist = i;
            sender.sendMessage(ChatColor.GREEN+"Set range.");
            return true;
        }

        else if (args[0].equalsIgnoreCase("strike"))
        {
            if (args.length != 2)
            {
                p.sendMessage(ChatColor.RED + " Please specify a player.");
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null)
            {
                p.sendMessage(ChatColor.RED + " Impossible to find : " + args[1] + " !");
                return true;
            }
            p.sendMessage(ChatColor.GOLD + " Troll " + ChatColor.BOLD + "LIGHTNING STRIKE" + ChatColor.RESET + ChatColor.GOLD + " on : " + ChatColor.GREEN + target.getName() + ChatColor.GOLD + " done!");
            Location loc = target.getLocation();
            loc.getWorld().strikeLightning(loc);

        }

        else if (args[0].equalsIgnoreCase("drop"))
        {
            if (args.length != 2) {
                p.sendMessage(ChatColor.RED + " Please specify a player.");
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null) {
                p.sendMessage(ChatColor.RED + " Impossible to find : " + args[1] + " !");
                return true;
            }
            p.sendMessage(ChatColor.GOLD + " Troll " + ChatColor.BOLD + "LAVA DROP" + ChatColor.RESET + ChatColor.GOLD + " on : " + ChatColor.GREEN + target.getName() + ChatColor.GOLD + " done!");
            Location loc = target.getLocation();
            Location l = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
            l.getBlock().getRelative(BlockFace.EAST).setType(Material.NETHER_FENCE);
            l.getBlock().getRelative(BlockFace.WEST).setType(Material.NETHER_FENCE);
            l.getBlock().getRelative(BlockFace.NORTH).setType(Material.NETHER_FENCE);
            l.getBlock().getRelative(BlockFace.SOUTH).setType(Material.NETHER_FENCE);
            l.getBlock().getRelative(BlockFace.NORTH_EAST).setType(Material.NETHER_FENCE);
            l.getBlock().getRelative(BlockFace.NORTH_WEST).setType(Material.NETHER_FENCE);
            l.getBlock().getRelative(BlockFace.SOUTH_EAST).setType(Material.NETHER_FENCE);
            l.getBlock().getRelative(BlockFace.SOUTH_WEST).setType(Material.NETHER_FENCE);
            loc.getWorld().spawnFallingBlock(loc.add(0.0, 15.0, 0.0), Material.LAVA, (byte) 0);
            loc.getWorld().playSound(loc, Sound.FIREWORK_LAUNCH, 100, 0);
        }

        else if (args[0].equalsIgnoreCase("healer"))
        {
            final Location loc = p.getLocation();
            final Villager healer = (Villager)loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
            healer.setProfession(Villager.Profession.PRIEST);
            healer.setCustomName("Healer");
            healer.setCustomNameVisible(true);
            healer.teleport(loc);

            Runnable runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    healer.teleport(loc);
                }
            };

            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("RandomStuff"), runnable, 1L, 0L);
        }

        return false;
    }
}


