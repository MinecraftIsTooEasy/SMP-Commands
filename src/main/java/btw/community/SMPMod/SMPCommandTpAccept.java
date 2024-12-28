package btw.community.SMPMod;

import net.minecraft.server.MinecraftServer;
import net.minecraft.*;

import java.util.List;
import java.util.Optional;

public class SMPCommandTpAccept extends CommandBase
{
    public String getCommandName()
    {
        return "tpaccept";
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
            //gets the person accepting the request (the person sending the message) as a ServerPlayer object
            ServerPlayer acceptingPlayer = getCommandSenderAsPlayer(sender);

            if (acceptingPlayer == null)
            {
                throw new PlayerNotFoundException();
            }

            ServerPlayer teleportingPlayer = getPlayer(sender, arguments[arguments.length - 1]);

            if (teleportingPlayer == null)
            {
                throw new PlayerNotFoundException();
            }

            if (teleportingPlayer.worldObj != acceptingPlayer.worldObj)
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
//                teleportingPlayer.foodStats.addExhaustion(1000);
//                teleportingPlayer.foodStats.addStats(-65,1);

                if (SMPMod.getInstance().getTpaExternalitiesEnabled())
                {
//                    if (!(teleportingPlayer.foodStats.getFoodLevel() <= 3))
//                    {
//                        teleportingPlayer.foodStats.setFoodLevel(3);
//                    }

//                    teleportingPlayer.performHurtAnimation();
//                    acceptingPlayer.performHurtAnimation();

                    acceptingPlayer.worldObj.playSoundAtEntity( acceptingPlayer,
                            "random.classic_hurt", 0.5F,
                            1F + .3F * 0.1F);

//                    acceptingPlayer.setEntityHealth(acceptingPlayer.getHealth()-10);
                    //acceptingPlayer.attackEntityFrom(SMPMod.tpaDamageSource, 10);

                    teleportingPlayer.attackEntityFrom(new Damage(DamageSource.generic, teleportingPlayer.getHealth() - 1));
                    if (acceptingPlayer.getHealth() > 10)
                    {
                        acceptingPlayer.attackEntityFrom(new Damage(DamageSource.generic, teleportingPlayer.getHealth() - 10));
                    }

                    ((EntityPlayerMPAccessor) teleportingPlayer).setHasBeenTeleported(true);

                    //uh oh, something got out...
//                    EntityCreature.attemptToPossessCreaturesAroundBlock(acceptingPlayer.worldObj, (int)acceptingPlayer.posX, (int)acceptingPlayer.posY, (int)acceptingPlayer.posZ, 1, 16);

                    //teleporting player drops all items
                    teleportingPlayer.inventory.dropAllItems();
                }

                acceptingPlayer.addChatMessage("Teleported "+teleportingPlayerName+" to you.");
                teleportingPlayer.addChatMessage("Teleported you to "+acceptingPlayerName+".");

                teleportingPlayer.mountEntity((Entity)null);
                teleportingPlayer.playerNetServerHandler.setPlayerLocation(acceptingPlayer.posX, acceptingPlayer.posY, acceptingPlayer.posZ, acceptingPlayer.rotationYaw, acceptingPlayer.rotationPitch);
                ((EntityPlayerMPAccessor)teleportingPlayer).setTpaRequestName(""); //prevents the accepter from spam-teleporting

                if (SMPMod.getInstance().getTpaExternalitiesEnabled())
                {
                    //a soul escapes, plays this sound
                    acceptingPlayer.worldObj.playAuxSFX(EnumParticle.explode.ordinal(),
                            MathHelper.floor_double( acceptingPlayer.posX ), MathHelper.floor_double( acceptingPlayer.posY ), MathHelper.floor_double( acceptingPlayer.posZ ), 0 );
                }
            }
            else
            {
                acceptingPlayer.addChatMessage("No active teleport requests found from "+teleportingPlayerName+".");
            }
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
