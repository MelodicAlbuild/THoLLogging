package com.melodicalbuild.thollogging.commands;

import com.melodicalbuild.thollogging.THoLLogging;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ICommand {
    void run(@NotNull final THoLLogging pl, @NotNull final CommandSender sender, @NotNull final String[] args);

    @NotNull
    List<String> getTabCompletition(@NotNull final String[] args);
}
