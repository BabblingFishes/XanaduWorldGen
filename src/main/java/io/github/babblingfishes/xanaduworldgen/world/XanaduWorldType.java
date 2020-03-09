package io.github.babblingfishes.xanaduworldgen.world;

import io.github.babblingfishes.xanaduworldgen.init.XanaduRegistry;
import io.github.babblingfishes.xanaduworldgen.world.gen.XanaduChunkGenerator;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;

public class XanaduWorldType extends WorldType {
	public XanaduWorldType() {
		super("xanadu");
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {
		
		ChunkGeneratorType<OverworldGenSettings, XanaduChunkGenerator> chunkGen = XanaduRegistry.xanaduChunkGenerator;
		//ChunkGeneratorType<OverworldGenSettings, OverworldChunkGenerator> chunkGen = ChunkGeneratorType.SURFACE; //DEBUG
		OverworldGenSettings genSettings = chunkGen.createSettings();

		//BiomeProviderType<OverworldBiomeProviderSettings, XanaduWGBiomeProvider> biomeProviderType = XanaduWGRegistry.xanaduBiomeProvider;
		BiomeProviderType<OverworldBiomeProviderSettings, OverworldBiomeProvider> biomeProviderType = BiomeProviderType.VANILLA_LAYERED; //DEBUG
		OverworldBiomeProviderSettings biomeSettings = biomeProviderType.createSettings().setWorldInfo(world.getWorldInfo()).setGeneratorSettings(genSettings);
		
		//BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeProviderType = BiomeProviderType.FIXED;
		//SingleBiomeProviderSettings biomeSettings = new SingleBiomeProviderSettings().setBiome(XanaduWGRegistry.flatBiome);
		
		return chunkGen.create(world, biomeProviderType.create(biomeSettings), genSettings);
	}
	
	@Override
	public double getHorizon(World world) {
		
		return 199.0D; //default 63.0D
	}
	
	@Override
	public double voidFadeMagnitude() {
		// eyeballed -- 0.004 too low, 0.005 not bad
        return 0.005D; //default 0.03125D
    }
	
	@Override
	public float getCloudHeight(){
        return 250.0F;
    }
	
	//TODO getBiomeLayer?
}
