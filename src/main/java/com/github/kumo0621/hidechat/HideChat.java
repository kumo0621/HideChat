package com.github.kumo0621.hidechat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Locale;

public final class HideChat extends JavaPlugin implements org.bukkit.event.Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    boolean set = false;

    @EventHandler
    public void onPlayerchat(AsyncPlayerChatEvent e) {
        if (set) {
            e.setFormat("<" + name + "> " + "%2$s");
        }
    }

    String name = "匿名さん";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("hidechat")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage("引数を指定してください。");
                } else {
                    switch (args[0]) {
                        case "name":
                            String chat = args[1];
                            name = chat;
                            sender.sendMessage("名前を" + chat + "にしました。");
                            break;
                        case "on":
                            set = true;
                            break;
                        case "off":
                            set = false;
                            break;
                    }

                }
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}
