package btw.community.SMPMod;

import net.minecraft.server.MinecraftServer;
import net.minecraft.*;

import java.util.List;

//a player sends a teleport request by typing /tpa [playername]

public class SMPCommandTpRequest extends CommandBase
{

    public String getCommandName()
    {
        return "tpa";
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

    @Override
    public void processCommand(ICommandSender sender, String[] arguments)
    {
        //TESTER VVV
//        System.out.println("While processing command, tpa enabled? "+SMPMod.isTpaEnabled);
//        System.out.println("While processing command, tpa enabled? "+SMPMod.getInstance().getTpaEnabled());
//        System.out.println("While processing command, tpa enabled? "+SMPMod.getInstance().getTpaEnabled());

        if (arguments.length < 1)
        {
            throw new WrongUsageException("Try /tpa [playername]", new Object[0]);
        }
        else if (!SMPMod.getInstance().getTpaEnabled())
        {
            throw new WrongUsageException("This command is disabled.", new Object[0]);
        }
        else
        {
            ServerPlayer teleportingPlayer;
            //gets the request sender (the person sending the message) as a EntityPlayerMP object
            teleportingPlayer = getCommandSenderAsPlayer(sender);

            if (teleportingPlayer == null)
            {
                throw new PlayerNotFoundException();
            }

            //gets the target of the tp request
            ServerPlayer targetPlayer = getPlayer(sender, arguments[arguments.length - 1]);

            if (targetPlayer == null)
            {
                throw new PlayerNotFoundException();
            }

            if (targetPlayer.worldObj != teleportingPlayer.worldObj)
            {
                notifyAdmins(sender, "commands.tp.notSameDimension", new Object[0]);
                return;
            }

            String targetPlayerName = targetPlayer.getEntityName();

            ((EntityPlayerMPAccessor)teleportingPlayer).setTpaRequestName(targetPlayer.getEntityName());
            teleportingPlayer.addChatMessage("You sent a teleport request to "+targetPlayerName+".");
            teleportingPlayer.addChatMessage("CAUTION: Your inventory will be WIPED!! /tpcancel to prevent teleporting!");
            targetPlayer.addChatMessage(teleportingPlayer.getEntityName()+" sent you a teleport request.");

        }
    }

    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length >= 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames()) : null;
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2)
    {
        return par2 == 0;
    }
}
