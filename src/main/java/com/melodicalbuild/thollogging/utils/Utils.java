package com.melodicalbuild.thollogging.utils;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

public final class Utils {
    public static final char[] forbiddenFileNameChar = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };

    public static final String[] eventPackages = new String[] {
            // Bukkit
            "org.bukkit.event.block.", "org.bukkit.event.enchantment.", "org.bukkit.event.entity.",
            "org.bukkit.event.hanging.", "org.bukkit.event.inventory." , "org.bukkit.event.player.",
            "org.bukkit.event.server.", "org.bukkit.event.vehicle.", "org.bukkit.event.weather.",
            "org.bukkit.event.world.",
            // Spigot
            "org.spigotmc.event.entity.", "org.spigotmc.event.player.",
            // Paper
            "com.destroystokyo.paper.event.block.", "com.destroystokyo.paper.event.entity.",
            "com.destroystokyo.paper.event.player.", "com.destroystokyo.paper.event.profile.",
            "com.destroystokyo.paper.event.server."
    };

    /**
     * Translate color codes
     * @param text text to translate
     * @return translated text
     */
    @Nullable
    public static String color(@Nullable final String text) {
        return text == null ? null : ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Trying to get the event class from the event name
     * @param event event name
     * @return the event class, null if not found
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public static Class<? extends Event> getEventClass(@NotNull final String event) {
        for (final String p : eventPackages) {
            try {
                return (Class<? extends Event>) Class.forName(p + event);
            } catch (final Exception ignored) {
                /* ignored */
            }
        }
        return null;
    }

    /**
     * Getting all fields from a class
     * @param clazz the class
     * @return all fields in a class
     */
    @NotNull
    public static List<Field> getAllFields(@NotNull Class<?> clazz) {
        final Set<Field> fields = new HashSet<>();
        while (clazz != null) {
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        return new ArrayList<>(fields);
    }

}
