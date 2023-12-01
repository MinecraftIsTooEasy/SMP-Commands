package btw.community.SMPMod;

import btw.AddonHandler;
import net.minecraft.src.ICommand;
import net.minecraft.src.PropertyManager;
import btw.BTWAddon;
import java.io.File;
import java.util.Map;

public class SMPMod extends BTWAddon
{

    //to initialize and read a settings file
    private PropertyManager settings;
    public static boolean isTpaEnabled;
    private static SMPMod instance;

    private SMPMod()
    {
        super("SMP Commands", "1.0 :D", "SMPMod");
    }

    public static SMPMod getInstance()
    {
        if (instance == null)
            instance = new SMPMod();
        return instance;
    }

    @Override
    public void initialize()
    {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
//        this.initializeServerProperties();
        registerAddonCommand(new SMPCommandTpRequest());
        registerAddonCommand(new SMPCommandTpAccept());
        registerAddonCommand(new SMPCommandTpCancel());
    }

    @Override
    public void preInitialize() {
        initializeServerProperties();
    }

    //Methods relating to the properties file
    public void initializeServerProperties()
    {
        AddonHandler.logMessage("Loading SMP Mod Server properties");

        this.registerProperty("Enable-TPA-commands", "True", "Allows players to use the /tpa [playername], /tpaccept [playername], and /tpcancel commands.");

    }
    private Map<String, String> propertyValues;
    @Override
    public void handleConfigProperties(Map<String, String> propertyValues)
    {
        this.propertyValues = propertyValues;

        //TESTER VVV
//        System.out.println("IS TPA ENABLED? "+this.propertyValues.get("Enable-TPA-commands"));
//        System.out.println("IS TPA ENABLED? "+this.propertyValues.get("Enable-TPA-commands"));
//        System.out.println("IS TPA ENABLED? "+this.propertyValues.get("Enable-TPA-commands"));

        this.setTpaEnabled(Boolean.parseBoolean(this.propertyValues.get("Enable-TPA-commands")));

        //TESTER VVVV
//        System.out.println("IS TPA ENABLED after setting? "+this.getTpaEnabled());
    }

    public void setTpaEnabled(boolean b)
    {
        this.isTpaEnabled = b;
    }
    public boolean getTpaEnabled()
    {
        return isTpaEnabled;
    }

}
