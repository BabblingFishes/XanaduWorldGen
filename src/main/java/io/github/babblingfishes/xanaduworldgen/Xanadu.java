package io.github.babblingfishes.xanaduworldgen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.babblingfishes.xanaduworldgen.world.OreGenerationFix;
import io.github.babblingfishes.xanaduworldgen.world.XanaduWorldType;
import net.minecraft.server.dedicated.ServerProperties;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("xanaduworldgen")
public class Xanadu {
	
	public static Xanadu instance;
	public static final String MOD_ID = "xanaduworldgen";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final WorldType WORLD_TYPE = new XanaduWorldType("xanadu");
	
	public Xanadu() {
		instance = this;
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dedicatedServerSetup);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	//preinitialization
	private void setup(final FMLCommonSetupEvent event) {
		OreGenerationFix.setupOreGeneration(); //TODO only trigger this on xanadu world type
	}
	
	//client-only setup = FMLClientSetupEvent
	
	public void dedicatedServerSetup(FMLDedicatedServerSetupEvent event) {
		ServerProperties serverProperties = event.getServerSupplier().get().getServerProperties();
		
		if(serverProperties != null) {
			String entryValue = serverProperties.serverProperties.getProperty("use-modded-worldtype");
			
			if(entryValue != null && entryValue.contentEquals("xanadu")) {
				serverProperties.worldType = WORLD_TYPE;
				LOGGER.info("Using Xanadu world type.");
			}
			else {
				LOGGER.info("Using default world type. (View README for Xanadu worldtype.)");
			}
		}
	}
}
