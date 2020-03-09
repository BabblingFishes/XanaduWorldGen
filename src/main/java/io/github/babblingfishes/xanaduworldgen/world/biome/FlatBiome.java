package io.github.babblingfishes.xanaduworldgen.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class FlatBiome extends Biome {
	public FlatBiome() {
		super(new Biome.Builder()
			.surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
				SurfaceBuilder.DEFAULT,
				SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
			)
			.precipitation(RainType.RAIN)
			.category(Category.FOREST)
			.scale(-0.1f)
			.depth(0.0f)
			.temperature(0.7f)
			.downfall(0.8f)
			.waterColor(0xFF00FF)
			.waterFogColor(0xFFFF00)
			.parent((String)null)
		);
	}
}
