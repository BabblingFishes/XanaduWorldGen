package io.github.babblingfishes.xanaduworldgen.init;

import io.github.babblingfishes.xanaduworldgen.Xanadu;
import io.github.babblingfishes.xanaduworldgen.world.biome.provider.XanaduBiomeProvider;
import io.github.babblingfishes.xanaduworldgen.world.gen.XanaduChunkGenerator;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Xanadu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class XanaduRegistry {
	//public static Biome tempTrashBiome;
	//public static Biome flatBiome;
	public static WorldType xanaduWorldType;
	public static ChunkGeneratorType<OverworldGenSettings, XanaduChunkGenerator> xanaduChunkGenerator;
	public static BiomeProviderType<OverworldBiomeProviderSettings, XanaduBiomeProvider> xanaduBiomeProvider;
	
	/*@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		IForgeRegistry<Biome> registry = event.getRegistry();
		tempTrashBiome = new TempTrashBiome();
		
		tempTrashBiome.setRegistryName(XanaduWorldGen.MOD_ID, "temptrashbiome");
		registry.register(tempTrashBiome);
		
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(tempTrashBiome, 10));
		BiomeManager.addSpawnBiome(tempTrashBiome);
		BiomeDictionary.addTypes(tempTrashBiome, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH);
		
		
		flatBiome = new FlatBiome();
		
		flatBiome.setRegistryName(XanaduWorldGen.MOD_ID, "flatbiome");
		registry.register(flatBiome);
		
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(flatBiome, 10));
		BiomeManager.addSpawnBiome(flatBiome);
		BiomeDictionary.addTypes(flatBiome, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH);
	}*/
	
	@SubscribeEvent
	public static void registerChunkGenerators(RegistryEvent.Register<ChunkGeneratorType<?, ?>> event) {
		
		xanaduChunkGenerator = new ChunkGeneratorType<>(XanaduChunkGenerator::new, true, OverworldGenSettings::new);
		
		xanaduChunkGenerator.setRegistryName(Xanadu.MOD_ID, "xanaduchunkgenerator");
		
		IForgeRegistry<ChunkGeneratorType<?, ?>> registry = event.getRegistry();
		registry.register(xanaduChunkGenerator);
	}
	
	@SubscribeEvent
	public static void registerBiomeProviders(RegistryEvent.Register<BiomeProviderType<?, ?>> event) {
		
		xanaduBiomeProvider = new BiomeProviderType<>(XanaduBiomeProvider::new, OverworldBiomeProviderSettings::new);
		
		xanaduBiomeProvider.setRegistryName(Xanadu.MOD_ID, "xanadubiomeprovider");
		
		IForgeRegistry<BiomeProviderType<?, ?>> registry = event.getRegistry();
		registry.register(xanaduBiomeProvider);
	}
}
