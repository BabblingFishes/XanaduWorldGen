package io.github.babblingfishes.xanaduworldgen.world;

import io.github.babblingfishes.xanaduworldgen.init.XanaduWGRegistry;
import io.github.babblingfishes.xanaduworldgen.world.gen.XanaduWGChunkGenerator;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.NetherChunkGenerator;
import net.minecraft.world.gen.NetherGenSettings;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class XanaduWGWorldType extends WorldType {
	public XanaduWGWorldType() {
		super("xanadu_type");
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {
		
		ChunkGeneratorType<OverworldGenSettings, XanaduWGChunkGenerator> chunkGen = XanaduWGRegistry.xanaduChunkGen;

		//ChunkGeneratorType<OverworldGenSettings, OverworldChunkGenerator> chunkGen = ChunkGeneratorType.SURFACE;

		BiomeProviderType<OverworldBiomeProviderSettings, OverworldBiomeProvider> biomeProviderType = BiomeProviderType.VANILLA_LAYERED;
		OverworldGenSettings genSettings = chunkGen.createSettings();
		
		OverworldBiomeProviderSettings biomeSettings = biomeProviderType.createSettings().setWorldInfo(world.getWorldInfo()).setGeneratorSettings(genSettings);
		//single.setBiome(Biomes.MOUNTAINS);
		//return new OverworldChunkGenerator(world, new OverworldBiomeProvider(biomeSettings), genSettings);
		return chunkGen.create(world, biomeProviderType.create(biomeSettings), genSettings);
	}
	
	/*@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {
		OverworldGenSettings genSettings = new OverworldGenSettings();
		SingleBiomeProviderSettings biomeSettings = new SingleBiomeProviderSettings();
		biomeSettings.setBiome(XanaduWGBiomeRegistry.tempTrashBiome);
		//single.setBiome(Biomes.MOUNTAINS);
		return new OverworldChunkGenerator(world, new SingleBiomeProvider(biomeSettings), genSettings);
	}*/
	
	@Override
	public double getHorizon(World world) {
		//default 63.0D
		return 195.0D;
	}
	
	@Override
	public double voidFadeMagnitude() {
		//default 0.03125D
		// 0.004 too low
		// 0.005 not bad
        return 0.005D;
    }
	
	@Override
	public float getCloudHeight(){
        return 250.0F;
    }
	
	//TODO getBiomeLayer?
}
