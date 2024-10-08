package me.monst.particleguides.command.breadcrumbs;

import me.monst.particleguides.particle.BreadcrumbsTrail;
import me.monst.particleguides.particle.ParticleService;
import me.monst.pluginutil.command.Arguments;
import me.monst.pluginutil.command.Command;
import me.monst.pluginutil.command.exception.CommandExecutionException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
class BreadcrumbsPause implements Command {
    
    private final ParticleService particleService;
    
    BreadcrumbsPause(ParticleService particleService) {
        this.particleService = particleService;
    }
    
    @Override
    public String getName() {
        return "pause";
    }
    
    @Override
    public String getDescription() {
        return "Pauses dropping breadcrumbs.";
    }
    
    @Override
    public String getUsage() {
        return "/breadcrumbs pause";
    }
    
    @Override
    public void execute(CommandSender sender, Arguments args) throws CommandExecutionException {
        Player player = Command.playerOnly(sender);
        BreadcrumbsTrail breadcrumbs = particleService.getBreadcrumbsTrail(player);
        if (breadcrumbs == null)
            Command.fail("You are not currently dropping breadcrumbs.");
        if (breadcrumbs.isPaused())
            Command.fail("Your breadcrumbs are already paused. Did you mean /breadcrumbs resume?");
        breadcrumbs.setPauseState(BreadcrumbsTrail.PauseState.PAUSED);
        player.sendMessage(ChatColor.YELLOW + "Breadcrumbs paused.");
    }
    
}
