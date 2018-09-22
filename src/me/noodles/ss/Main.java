package me.noodles.ss;
import me.noodles.ss.freezecommand.FreezeCommand;
import me.noodles.ss.freezecommand.FreezeEvents;
import me.noodles.ss.updatechecker.JoinEvent;
import me.noodles.ss.updatechecker.UpdateChecker;
import org.bukkit.plugin.java.*;
import org.bukkit.plugin.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    public static Main plugin;
    private UpdateChecker checker;
    public static Main getPlugin() {
        return (Main)JavaPlugin.getPlugin((Class)Main.class);
    }


    public void onEnable() {
        Main.plugin = this;
        PluginDescriptionFile VarUtilType = this.getDescription();
        this.getLogger().info("Ultimate Freeze V" + VarUtilType.getVersion() + " loading commands...");
        this.getLogger().info("Ultimate Freeze V" + VarUtilType.getVersion() + " loading config...");
        this.getLogger().info("Ultimate Freeze V" + VarUtilType.getVersion() + " loading events...");
        this.registerCommands();
        this.createFiles();
        MetricsLite metrics = new MetricsLite(this);
        getServer().getPluginManager().registerEvents(new FreezeEvents(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        setEnabled(true);
        getLogger().info("Ultimate Freeze V" + VarUtilType.getVersion() + " started!");
        this.getLogger().info("Ultimate Freeze V" + VarUtilType.getVersion() + " checking for updates...");
        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
                getServer().getConsoleSender().sendMessage("------------------------");
                getServer().getConsoleSender().sendMessage("Ultimate Freeze is outdated!");
                getServer().getConsoleSender().sendMessage("Newest version: " + this.checker.getLatestVersion());
                getServer().getConsoleSender().sendMessage("Your version: " + Main.plugin.getDescription().getVersion());
                getServer().getConsoleSender().sendMessage("Please Update Here: https://www.spigotmc.org/resources/44518/");
                getServer().getConsoleSender().sendMessage("------------------------");
            } else {
                getServer().getConsoleSender().sendMessage("------------------------");
                getServer().getConsoleSender().sendMessage("Ultimate Freeze is up to date!");
                getServer().getConsoleSender().sendMessage("------------------------");
            }
        }
    }

    public void registerCommands() {
        this.getCommand("freeze").setExecutor(new FreezeCommand());

    }

    private File configf, configmessages2, configgui2;
    private FileConfiguration config, configmessages, configgui;

    public FileConfiguration getmessagesConfig() {
        return this.configmessages;
    }

    public FileConfiguration getguiConfig() {
        return this.configgui;
    }
    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");
        configmessages2 = new File(getDataFolder(), "messages.yml");
        configgui2 = new File(getDataFolder(), "gui.yml");

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        if (!configmessages2.exists()) {
            configmessages2.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        if (!configgui2.exists()) {
            configgui2.getParentFile().mkdirs();
            saveResource("gui.yml", false);
        }
        config = new YamlConfiguration();
        configmessages = new YamlConfiguration();
        configgui = new YamlConfiguration();
        try {
            config.load(configf);
            configmessages.load(configmessages2);
            configgui.load(configgui2);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}