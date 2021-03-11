package fr.factionbedrock.newdim.Commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import fr.factionbedrock.newdim.NewDimension;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ModCommands
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
        LiteralCommandNode<CommandSource> cmdNewDim = dispatcher.register(
                Commands.literal(NewDimension.MODID)
                        .then(TpNewDim.register(dispatcher))
        );

        dispatcher.register(Commands.literal("newdim").redirect(cmdNewDim));
    }
}
