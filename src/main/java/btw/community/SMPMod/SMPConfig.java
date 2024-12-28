package btw.community.SMPMod;

import net.xiaoyu233.fml.config.ConfigCategory;
import net.xiaoyu233.fml.config.ConfigEntry;
import net.xiaoyu233.fml.config.ConfigRoot;
import net.xiaoyu233.fml.util.FieldReference;

import java.io.File;

public class SMPConfig {
    public static final File CONFIG_FILE = new File("smp-commands.json");
    public static final ConfigRoot ROOT = ConfigRoot.create(SMPMod.CONFIG_VERSION).
            addEntry(ConfigEntry.of("Enable-TPA-commands", configItems.Enable_TPA_commands).withComment("Allows players to use the /tpa [playername], /tpaccept [playername], and /tpcancel commands.")).
            addEntry(ConfigEntry.of("Enable-TPA-Externalities", configItems.Enable_TPA_Externalities).withComment("Do you accept the consequences? Wipe inventory on /tpaccept and other punishments..."));


    public static class configItems {
        public static final FieldReference<Boolean> Enable_TPA_commands = new FieldReference<>(true);
        public static final FieldReference<Boolean> Enable_TPA_Externalities = new FieldReference<>(false);
    }
}
