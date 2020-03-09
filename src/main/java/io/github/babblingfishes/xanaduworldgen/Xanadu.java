package io.github.babblingfishes.xanaduworldgen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.babblingfishes.xanaduworldgen.world.OreGenerationFix;
import io.github.babblingfishes.xanaduworldgen.world.XanaduWorldType;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("xanaduworldgen")
public class Xanadu {
	
	public static Xanadu instance;
	public static final String MOD_ID = "xanaduworldgen";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final WorldType WORLD_TYPE = new XanaduWorldType();
	
	public Xanadu() {
		instance = this;
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	//preinitialization
	private void setup(final FMLCommonSetupEvent event) {
		OreGenerationFix.setupOreGeneration();
	}
	
	//client-only setup
	private void clientRegistries(final FMLClientSetupEvent event) {}
	
	/*Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {}*/
}
