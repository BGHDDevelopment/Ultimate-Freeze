package me.noodles.ultimatefreeze.utilities.updatechecker;

import me.noodles.ultimatefreeze.UltimateFreeze;
import org.bukkit.Bukkit;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker
{
    private UltimateFreeze plugin;
    private int resourceId;

    public UpdateChecker(UltimateFreeze plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
            }
        });
    }
}
