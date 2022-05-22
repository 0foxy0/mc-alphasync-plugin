package foxy.main.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

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

            new DBConnection().MySQL(cT);
            new DBConnection().MySQL(iT);
        }
    }
}