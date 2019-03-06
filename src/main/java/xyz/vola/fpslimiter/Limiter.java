package xyz.vola.fpslimiter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = Limiter.MODID, name = Limiter.NAME, version = Limiter.VERSION)
public class Limiter {
    public static final String MODID = "fpslimiter";
    public static final String NAME = "Faster Chat";
    public static final String VERSION = "1.1";
    private static final Minecraft mc = Minecraft.getMinecraft();

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        log("On load");
    }

    public Limiter() {
        MinecraftForge.EVENT_BUS.register(Limiter.class);
    }

    @SubscribeEvent
    public static void onLimitFPS(GuiOpenEvent event) {
        Gui gui = event.getGui();
        if (gui instanceof GuiChat || gui instanceof GuiNewChat) {
            ChangeFPS(5);
        }
    }

    @SubscribeEvent
    public static void onRestoreFPS(GuiOpenEvent event) {
        if (event.getGui() == null) {
            ChangeFPS(60);
        }
    }

    private static void ChangeFPS(int toFPS) {
        log("Change FPS to " + toFPS);
        mc.gameSettings.limitFramerate = toFPS;
    }

    private static void log(String x) {
        logger.log(Level.INFO, x);
    }
}
