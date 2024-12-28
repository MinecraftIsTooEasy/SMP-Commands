package btw.community.SMPMod;

import net.minecraft.*;

public class SMPCommandDeaths extends CommandBase
{
    public String getCommandName()
    {
        return "deaths";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "";
    }

    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    /**
     * Returns true if the given command sender is allowed to use this command.
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return true;
    }

    public void processCommand(ICommandSender sender, String[] arguments)
    {
        if (arguments.length > 1)
        {
            throw new WrongUsageException("Try /deaths", new Object[0]);
        }
        else
        {
            ServerPlayer senderPlayer = getCommandSenderAsPlayer(sender);

            senderPlayer.addChatMessage("You have HC spawned " + senderPlayer.deathTime + " times!");

        }
    }
}
