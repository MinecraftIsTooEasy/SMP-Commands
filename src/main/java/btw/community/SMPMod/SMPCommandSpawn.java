package btw.community.SMPMod;

import net.minecraft.*;


public class SMPCommandSpawn extends CommandBase
{
    public String getCommandName()
    {
        return "spawn";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    public void processCommand(ICommandSender sender, String[] arguments)
    {

        if (arguments.length > 1)
        {

            throw new WrongUsageException("Try /spawn]", new Object[0]);

        }

        ServerPlayer teleportingPlayer = getCommandSenderAsPlayer(sender);

        teleportingPlayer.addChatMessage("Teleported you to spawn.");

        if (!(teleportingPlayer.worldObj.getWorldInfo().getDimensionId() == 0))
        {
            //TESTER vvvv
//            System.out.println("HIIIII are you in the overworld?");
            throw new WrongUsageException("You can't do that here :)", new Object[0]);
        }

        boolean searchingForEmptySpace = true;
        int yIncrementer = 0;
        //this loop checks to see if spawn coords aren't solid blocks
        //if solid blocks found
        while (searchingForEmptySpace)
        {
            Material aboveTargetMaterial = teleportingPlayer.worldObj.getBlockMaterial(teleportingPlayer.worldObj.getWorldInfo().getSpawnX(), teleportingPlayer.worldObj.getWorldInfo().getSpawnY()+yIncrementer, teleportingPlayer.worldObj.getWorldInfo().getSpawnZ());
            if (!aboveTargetMaterial.isSolid() && !aboveTargetMaterial.isLiquid())
            {
                searchingForEmptySpace = false;
            }
            else
            {
                yIncrementer++;
                if (yIncrementer > 32)
                {
                    break;
                }
            }
        }

        teleportingPlayer.mountEntity((Entity)null);
        teleportingPlayer.playerNetServerHandler.setPlayerLocation(teleportingPlayer.worldObj.getWorldInfo().getSpawnX(), teleportingPlayer.worldObj.getWorldInfo().getSpawnY()+yIncrementer, teleportingPlayer.worldObj.getWorldInfo().getSpawnZ(), teleportingPlayer.rotationYaw, teleportingPlayer.rotationPitch);

    }

}
