package btw.community.SMPMod.mixin;

import btw.community.SMPMod.EntityPlayerMPAccessor;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(EntityPlayerMP.class)
public abstract class EntityPlayerMPMixin extends EntityPlayer implements EntityPlayerMPAccessor
{
    public EntityPlayerMPMixin(World par1World) {
        super(par1World);
    }

    //AARON ADDED these variables in order to keep track of teleportation requests
    public String tpaRequestPlayerName;

    //AARON added this method to keep track of teleport requests
    public void setTpaRequestName(String name)
    {
        tpaRequestPlayerName = name;
    }
    public Optional<String> getTpaRequestName()
    {
        return Optional.ofNullable(tpaRequestPlayerName);
    }

}
