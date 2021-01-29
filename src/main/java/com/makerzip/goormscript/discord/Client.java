package com.makerzip.goormscript.discord;

import com.makerzip.goormscript.utils.Config;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author MakerZip (juwon@makerzip.com)
 */
public class Client {
    public static void main(String[] args) throws LoginException {
        String[] messages = {"'/gs make'를 입력", "'/gs help'를 입력"};

        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken(new Config().token(false))
                .setStatus(OnlineStatus.IDLE)
                .build();

        jda.addEventListener(new Commands());

        final int[] currentIndex = {0};
        new Timer().schedule(new TimerTask() {
            public void run() {
                jda.getPresence().setActivity(Activity.playing(messages[currentIndex[0]]));
                currentIndex[0] = (currentIndex[0] + 1) % messages.length;
            }
        }, 0, 10000);
    }
}
