package btw.community.SMPMod;

import java.util.Optional;

public interface EntityPlayerMPAccessor
{


    public default void setTpaRequestName(String name)
    {
    }
    public default Optional<String> getTpaRequestName()
    {
        return Optional.empty();
    }
}
