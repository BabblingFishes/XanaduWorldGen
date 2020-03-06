package io.github.babblingfishes.xanaduworldgen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("xanaduworldgen")
public class XanaduWorldGen_OLD {
	public static class ModSetup {
		public void init(FMLCommonSetupEvent event) {}
	}
	
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "xanaduworldgen";
	public static ModSetup setup = new ModSetup();
	
	public XanaduWorldGen_OLD() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::init);
	}
}
