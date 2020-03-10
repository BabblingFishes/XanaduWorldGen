package io.github.babblingfishes.xanaduworldgen.world;

import io.github.babblingfishes.xanaduworldgen.init.XanaduRegistry;
import io.github.babblingfishes.xanaduworldgen.world.gen.XanaduChunkGenerator;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.common.extensions.IForgeWorldType;

public class XanaduWorldType extends WorldType implements IForgeWorldType {
	public XanaduWorldType(String name) {
		super(name);
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {
		if(world.getDimension().getType() == DimensionType.OVERWORLD) {
			ChunkGeneratorType<OverworldGenSettings, XanaduChunkGenerator> chunkGen = XanaduRegistry.xanaduChunkGenerator;
			//ChunkGeneratorType<OverworldGenSettings, OverworldChunkGenerator> chunkGen = ChunkGeneratorType.SURFACE; //DEBUG
			OverworldGenSettings genSettings = chunkGen.createSettings();
	
			//BiomeProviderType<OverworldBiomeProviderSettings, XanaduWGBiomeProvider> biomeProviderType = XanaduWGRegistry.xanaduBiomeProvider;
			
			BiomeProviderType<OverworldBiomeProviderSettings, OverworldBiomeProvider> biomeProviderType = BiomeProviderType.VANILLA_LAYERED;
			OverworldBiomeProviderSettings biomeSettings = biomeProviderType.createSettings().setWorldInfo(world.getWorldInfo()).setGeneratorSettings(genSettings);
			
			//BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeProviderType = BiomeProviderType.FIXED;
			//SingleBiomeProviderSettings biomeSettings = new SingleBiomeProviderSettings().setBiome(Biomes.DESERT);
			
			return chunkGen.create(world, biomeProviderType.create(biomeSettings), genSettings);
		}
		else return super.createChunkGenerator(world);
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
