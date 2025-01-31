package dev.geco.gsit.event.features;

import dev.geco.gsit.GSitMain;
import dev.geco.gsit.api.event.PlayerGetUpPoseEvent;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpinConfusionEvent implements Listener {

    private final GSitMain gSitMain;

    public SpinConfusionEvent(GSitMain gSitMain) {
        this.gSitMain = gSitMain;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void playerGetUpPoseEvent(PlayerGetUpPoseEvent event) {
        if(event.getPose().getPose() != Pose.SPIN_ATTACK) return;
        if(!gSitMain.getConfigService().FEATUREFLAGS.contains("SPIN_CONFUSION")) return;
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.getByName(gSitMain.getVersionManager().isNewerOrVersion(20, 5) ? "NAUSEA" : "CONFUSION"), 120, 2));
    }

}