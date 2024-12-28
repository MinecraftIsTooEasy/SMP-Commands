package btw.community.SMPMod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.PropertyManager;
import net.xiaoyu233.fml.config.ConfigRegistry;
import net.xiaoyu233.fml.reload.event.MITEEvents;

import java.util.Optional;

public class SMPMod implements ModInitializer {
    //to initialize and read a settings file
    private PropertyManager settings;
    public static boolean isTpaEnabled = SMPConfig.configItems.Enable_TPA_commands.get();
    public static boolean areTpaExternalitiesEnabled = SMPConfig.configItems.Enable_TPA_Externalities.get();
    private static SMPMod instance;
    public static int CONFIG_VERSION = 1;
    private final ConfigRegistry configRegistry = new ConfigRegistry(SMPConfig.ROOT, SMPConfig.CONFIG_FILE);

    public static SMPMod getInstance() {
        if (instance == null)
            instance = new SMPMod();
        return instance;
    }


    public void setTpaEnabled(boolean b) {
        this.isTpaEnabled = SMPConfig.configItems.Enable_TPA_commands.get();
    }

    public boolean getTpaEnabled() {
        return isTpaEnabled;
    }

    public void setTpaExternalitiesEnabled(boolean b) {
        this.areTpaExternalitiesEnabled = b;
    }

    public boolean getTpaExternalitiesEnabled() {
        return areTpaExternalitiesEnabled;
    }

    @Override
    public void onInitialize() {
        registerAllEvents();
    }

    public static void registerAllEvents() {
        MITEEvents.MITE_EVENT_BUS.register(new SMPEvent());
    }

    public Optional<ConfigRegistry> createConfig() {
        return Optional.of(this.configRegistry);
    }
}
