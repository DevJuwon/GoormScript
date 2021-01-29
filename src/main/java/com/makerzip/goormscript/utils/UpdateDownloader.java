//package com.makerzip.goormscript.utils;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.util.concurrent.CompletableFuture;
//
///**
// * @author MakerZip (juwon@makerzip.com)
// */
//public abstract class UpdateDownloader {
//
//    private static final File RELEASESFOLDER = new File(TabList.getInstance().getFolder(), "releases");
//
//    public static void checkFromGithub(org.bukkit.command.CommandSender sender) {
//
//        if (!TabList.getInstance().getConfig().get("check-update", false)) {
//            deleteDirectory();
//            return;
//        }
//
//        CompletableFuture.supplyAsync(() -> {
//            try {
//                URL githubUrl = new URL(
//                        "https://raw.githubusercontent.com/DevJuwon/GoormScript/master/src/main/resources/config.yml");
//                BufferedReader br = new BufferedReader(new InputStreamReader(githubUrl.openStream()));
//                String s;
//                String lineWithVersion = "";
//                while ((s = br.readLine()) != null) {
//                    String line = s;
//                    if (line.toLowerCase().contains("version")) {
//                        lineWithVersion = line;
//                        break;
//                    }
//                }
//
//                String versionString = lineWithVersion.split(": ")[1],
//                        nVersion = versionString.replaceAll("[^0-9]", ""),
//                        cVersion = TabList.getInstance().getDescription().getVersion().replaceAll("[^0-9]", "");
//
//                int newVersion = Integer.parseInt(nVersion);
//                int currentVersion = Integer.parseInt(cVersion);
//
//                if (newVersion <= currentVersion || currentVersion >= newVersion) {
//                    deleteDirectory();
//                    return false;
//                }
//
//                String msg = "";
//                if (sender instanceof Player) {
//                    msg = Util.colorMsg("&aA new update for TabList is available!&4 Version:&7 " + versionString
//                            + (TabList.getInstance().getConfig().get("download-updates", false) ? ""
//                            : "\n&6Download:&c &nhttps://www.spigotmc.org/resources/46229/"));
//                } else {
//                    msg = "New version (" + versionString
//                            + ") is available at https://www.spigotmc.org/resources/46229/";
//                }
//
//                Util.sendMsg(sender, msg);
//
//                if (!TabList.getInstance().getConfig().get("download-updates", false)) {
//                    deleteDirectory();
//                    return false;
//                }
//
//                final String name = "TabList-v" + versionString;
//
//                if (!RELEASESFOLDER.exists()) {
//                    RELEASESFOLDER.mkdir();
//                }
//
//                // Do not attempt to download the file again, when it is already downloaded
//                final File jar = new File(RELEASESFOLDER, name + ".jar");
//                if (jar.exists()) {
//                    return false;
//                }
//
//                Util.logConsole("Downloading new version of TabList...");
//
//                final URL download = new URL(
//                        "https://github.com/montlikadani/TabList/releases/latest/download/" + name + ".jar");
//
//                InputStream in = download.openStream();
//                try {
//                    Files.copy(in, jar.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                } finally {
//                    in.close();
//                }
//
//                return true;
//            } catch (FileNotFoundException f) {
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return false;
//        }).thenAccept(success -> {
//            if (success) {
//                Util.logConsole("The new TabList has been downloaded to releases folder.");
//            }
//        });
//    }
//
//    private static void deleteDirectory() {
//        if (!RELEASESFOLDER.exists()) {
//            return;
//        }
//
//        for (File file : RELEASESFOLDER.listFiles()) {
//            try {
//                file.delete();
//            } catch (SecurityException e) {
//            }
//        }
//
//        try {
//            RELEASESFOLDER.delete();
//        } catch (SecurityException e) {
//        }
//    }
//}
