package com.makerzip.goormscript.discord;

import com.makerzip.goormscript.utils.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Commands extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String ficon = event.getJDA().getUserById("804244563945848893").getAvatarUrl();
        String ftext = event.getJDA().getUserById("804244563945848893").getAsTag();

        String messageLower = event.getMessage().getContentRaw().toLowerCase();
        String message = event.getMessage().getContentRaw();

        if (event.getAuthor().isBot()) {
            return;
        }

        // 구름스크립트
        if (messageLower.startsWith(Config._prefix + " make")) {
            if (messageLower.equalsIgnoreCase(Config._prefix + " make")) {
                event.getChannel().sendMessage(Config._prefix + " make [<filename>]").queue();
            } else {
                event.getChannel().sendMessage(message.substring((Config._prefix + " make ").length()) + ".gs 를 성공적으로 만들지 못했습니다.(25)").queue();
            }
        }
        if (messageLower.startsWith(Config._prefix + " edit")) {
            if (messageLower.equalsIgnoreCase(Config._prefix + " edit")) {
                event.getChannel().sendMessage(Config._prefix + " edit [<filename>] <line> [text]").queue();
            } else {
                event.getChannel().sendMessage(messageLower.substring((Config._prefix + " edit ").length()) + ".gs 를 성공적으로 수정하지 못했습니다.(26)").queue();
            }
        }
        if (messageLower.startsWith(Config._prefix + " view")) {
            if (messageLower.equalsIgnoreCase(Config._prefix + " view")) {
                event.getChannel().sendMessage(Config._prefix + " view [<filename>]").queue();
            } else {
                event.getChannel().sendMessage(messageLower.substring((Config._prefix + " view ").length()) + ".gs 를 성공적으로 불러오지 못했습니다.(28)").queue();
            }
        }
        if (messageLower.startsWith(Config._prefix + " remove")) {
            if (messageLower.equalsIgnoreCase(Config._prefix + " remove")) {
                event.getChannel().sendMessage(Config._prefix + " remove [<filename>]").queue();
            } else {
                event.getChannel().sendMessage(messageLower.substring((Config._prefix + " remove ").length()) + ".gs 를 성공적으로 제거하지 못했습니다.(27)").queue();
            }
        }
    }
}
