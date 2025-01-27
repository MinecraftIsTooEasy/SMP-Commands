package btw.community.SMPMod;

import net.minecraft.*;

public class SMPCommandTpCancel extends CommandBase
{
    public String getCommandName()
    {
        return "tpcancel";
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
            throw new WrongUsageException("Try /tpcancel", new Object[0]);
        }
//        else if (!SuperBTW.instance.getTpaEnabled())
//        {
//            throw new WrongUsageException("This command is disabled.", new Object[0]);
//        }
        else
        {

            ServerPlayer cancelingPlayer;
            //gets the person canceling the request (the person sending the message) as a ServerPlayer object
            cancelingPlayer = getCommandSenderAsPlayer(sender);

//            if (cancelingPlayer == null)
//            {
//                throw new PlayerNotFoundException();
//            }

            cancelingPlayer.addChatMessage("Canceled active teleport request.");

            ((EntityPlayerMPAccessor) cancelingPlayer).setTpaRequestName("");
        }
    }
}
