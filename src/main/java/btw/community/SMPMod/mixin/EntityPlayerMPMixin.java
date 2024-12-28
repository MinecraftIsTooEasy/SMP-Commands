package btw.community.SMPMod.mixin;

import btw.community.SMPMod.EntityPlayerMPAccessor;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ServerPlayer.class)
public abstract class EntityPlayerMPMixin extends EntityPlayer implements EntityPlayerMPAccessor
{
    //AARON ADDED these variables in order to keep track of teleportation requests
    public String tpaRequestPlayerName;
    private boolean hasBeenTeleported;

    public EntityPlayerMPMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }


    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void writeEntityToNBTMixin(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setBoolean("hasBeenTeleported", hasBeenTeleported);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void readEntityFromNBTMixin(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        hasBeenTeleported = par1NBTTagCompound.getBoolean("hasBeenTeleported");
    }

//    @Inject(method = "customModDrops", at = @At("HEAD"), cancellable = true, remap = false)
//    private void customModDropsMixin(DamageSource source, CallbackInfo ci) {
//        if (this.getHasBeenTeleported()) {
//            ci.cancel();
//        }
//    }

    //AARON added this method to keep track of teleport requests
    @Override
    public void setTpaRequestName(String name)
    {
        tpaRequestPlayerName = name;
    }

    @Override
    public Optional<String> getTpaRequestName()
    {
        return Optional.ofNullable(tpaRequestPlayerName);
    }

    @Override
    public void setHasBeenTeleported(boolean hasBeenTeleported)
    {
        this.hasBeenTeleported = hasBeenTeleported;
    }

    @Override
    public boolean getHasBeenTeleported()
    {
        return hasBeenTeleported;
    }
}
