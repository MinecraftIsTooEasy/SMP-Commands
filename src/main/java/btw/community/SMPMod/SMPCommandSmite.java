package btw.community.SMPMod;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

import java.util.List;

public class SMPCommandSmite extends CommandBase
{
    public String getCommandName()
    {
        return "smite";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    public void processCommand(ICommandSender sender, String[] arguments)
    {
        if (arguments.length < 1)
        {
            throw new WrongUsageException("Try /smite [playername]", new Object[0]);
        }
        else
        {
            EntityPlayerMP smotePlayer = func_82359_c(sender, arguments[arguments.length - 1]);

            if (smotePlayer == null)
            {
                throw new PlayerNotFoundException();
            }

            smotePlayer.worldObj.addWeatherEffect( new EntityLightningBolt( smotePlayer.worldObj, smotePlayer.posX + 0.5D,
                    smotePlayer.posY, smotePlayer.posZ + 0.5D ) );
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
