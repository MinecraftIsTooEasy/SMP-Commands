package btw.community.SMPMod;

import net.minecraft.src.*;

import java.util.Optional;

public class SMPCommandTpAccept extends CommandBase
{
    public String getCommandName()
    {
        return "tpaccept";
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
        if (arguments.length < 1)
        {
            throw new WrongUsageException("Try /tpaccept [playername]", new Object[0]);
        }
        else if (!SMPMod.getInstance().getTpaEnabled())
        {
            throw new WrongUsageException("This command is disabled.", new Object[0]);
        }
        else
        {
            EntityPlayerMP acceptingPlayer;
            //gets the person accepting the request (the person sending the message) as a EntityPlayerMP object
            acceptingPlayer = getCommandSenderAsPlayer(sender);

            if (acceptingPlayer == null)
            {
                throw new PlayerNotFoundException();
            }

            EntityPlayerMP teleportingPlayer = func_82359_c(sender, arguments[arguments.length - 1]);

            if (teleportingPlayer == null)
            {
                throw new PlayerNotFoundException();
            }

            if (teleportingPlayer.worldObj != teleportingPlayer.worldObj)
            {
                notifyAdmins(sender, "commands.tp.notSameDimension", new Object[0]);
                return;
            }

            String teleportingPlayerName = teleportingPlayer.getEntityName();
            String acceptingPlayerName = acceptingPlayer.getEntityName();

            //Optional String must be converted to a pure String, otherwise the game won't read the name properly
            Optional<String> optionalName = ((EntityPlayerMPAccessor)teleportingPlayer).getTpaRequestName();
            String tpaRequestName = optionalName.orElse("");

            //TESTERS VVV
//            acceptingPlayer.addChatMessage(""+tpaRequestName);
//            teleportingPlayer.addChatMessage(""+tpaRequestName);

            if (tpaRequestName.equals(acceptingPlayerName))
            {
                acceptingPlayer.addChatMessage("Teleported "+teleportingPlayerName+" to you.");
                teleportingPlayer.addChatMessage("Teleported you to "+acceptingPlayerName+".");

                teleportingPlayer.mountEntity((Entity)null);
                teleportingPlayer.playerNetServerHandler.setPlayerLocation(acceptingPlayer.posX, acceptingPlayer.posY, acceptingPlayer.posZ, acceptingPlayer.rotationYaw, acceptingPlayer.rotationPitch);
                ((EntityPlayerMPAccessor)teleportingPlayer).setTpaRequestName(""); //prevents the accepter from spam-teleporting
            }
            else
            {
                acceptingPlayer.addChatMessage("No active teleport requests found from "+teleportingPlayerName+".");
            }

        }

    }
}
