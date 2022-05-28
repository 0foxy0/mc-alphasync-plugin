package foxy.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;

public final class Main extends Plugin {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "Plugin successfully loaded!");

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();

                Configuration config;

                try {
                    config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                config.set("bot-token", "");
                config.set("mysql-conlink", "jdbc:mysql://IP:PORT/DATABASE");
                config.set("mysql-user", "");
                config.set("mysql-password", "");

                try {
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Configuration config;

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String botToken = config.getString("bot-token");
        JDA jda = null;

        if (!config.getString("bot-token").equals("")) {
            try {
                jda = JDABuilder.createDefault(botToken).build().awaitReady();
            } catch (InterruptedException | LoginException e) {
                throw new RuntimeException(e);
            }
        }

        if (jda == null) {
            System.out.println(ChatColor.RED + "Bot cannot connect!\n                check your bot token!");
            return;
        }
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "The Server was shut down!");
    }
}