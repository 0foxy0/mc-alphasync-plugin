package foxy.main.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

public class DiscordEvent extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (content.equalsIgnoreCase("!sync")) {
            String pName = message.getAuthor().getName().toUpperCase();
            String cT = "CREATE TABLE " + pName +
                    "(dcid INTEGER not NULL, " +
                    " rank STRING, " +
                    " mcname STRING)";

            String iT = "INSERT INTO " + pName + " values(" + message.getAuthor().getId() + ", " + ")";

            Error err;
            try {
                err = new MySQL().Create(cT);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            if (err.equals("No Data!")) {
                System.out.println("None or wrong MySQL Data! Cannot create the Table!");
            }

            Error err2;
            try {
                err2 = new MySQL().Create(iT);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            if (err2.equals("No Data!")) {
                System.out.println("None or wrong MySQL Data! Cannot insert Data in the Table!");
            }

        }
    }
}