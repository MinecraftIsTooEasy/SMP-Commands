package btw.community.SMPMod;

import com.google.common.eventbus.Subscribe;
import net.xiaoyu233.fml.reload.event.CommandRegisterEvent;

public class SMPEvent {
    @Subscribe
    public void onCommandRegister(CommandRegisterEvent event) {
        event.register(new SMPCommandTpRequest());
        event.register(new SMPCommandTpAccept());
        event.register(new SMPCommandTpCancel());
        event.register(new SMPCommandTpAbove());
        event.register(new SMPCommandSmite());
        event.register(new SMPCommandSpawn());
        event.register(new SMPCommandDeaths());
    }
}
