package io.github.babblingfishes.xanaduworldgen.init;

import io.github.babblingfishes.xanaduworldgen.XanaduWorldGen;
import io.github.babblingfishes.xanaduworldgen.world.biome.TempTrashBiome;
import io.github.babblingfishes.xanaduworldgen.world.gen.XanaduWGChunkGenerator;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = XanaduWorldGen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class XanaduWGRegistry {
	public static Biome tempTrashBiome;
	public static ChunkGeneratorType<OverworldGenSettings, XanaduWGChunkGenerator> xanaduChunkGen;
	
	/*@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		IForgeRegistry<Biome> registry = event.getRegistry();
		tempTrashBiome = new TempTrashBiome();
		
		tempTrashBiome.setRegistryName(XanaduWorldGen.MOD_ID, "temptrashbiome");
		registry.register(tempTrashBiome);
		
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(tempTrashBiome, 10));
		BiomeManager.addSpawnBiome(tempTrashBiome);
		BiomeDictionary.addTypes(tempTrashBiome, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH);	
	}*/
	
	@SubscribeEvent
	public static void registerChunkGenerators(RegistryEvent.Register<ChunkGeneratorType<?, ?>> event) {
		
		xanaduChunkGen = new ChunkGeneratorType<>(XanaduWGChunkGenerator::new, true, OverworldGenSettings::new);
		
		xanaduChunkGen.setRegistryName(XanaduWorldGen.MOD_ID, "xanaduchunkgen");
		
		IForgeRegistry<ChunkGeneratorType<?, ?>> registry = event.getRegistry();
		registry.register(xanaduChunkGen);
	}
}
