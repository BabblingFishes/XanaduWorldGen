package io.github.babblingfishes.xanaduworldgen.world;

import io.github.babblingfishes.xanaduworldgen.Xanadu;
import io.github.babblingfishes.xanaduworldgen.init.XanaduRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

//For reference: default feature configurations can be found at DefaultBiomeFeatures
public class OreGenerationFix {
	public static void setupOreGeneration() {
		
		Xanadu.LOGGER.debug("Adding ores to biomes...");
		
		for(Biome biome : ForgeRegistries.BIOMES) {
			
			Biome.Category category = biome.getCategory();
			
			//no changes to the end/nether
			if(category == Biome.Category.NETHER ||
			   category == Biome.Category.THEEND) {
				Xanadu.LOGGER.debug("Not adding ores to biome: " + biome.toString());
			}
			else {
				//mountain emerald
				if(category == Biome.Category.EXTREME_HILLS) {
					Xanadu.LOGGER.debug("Adding emeralds to biome: " + biome.toString());
					biome.addFeature(
						GenerationStage.Decoration.UNDERGROUND_ORES,
						Biome.createDecoratedFeature(
							Feature.EMERALD_ORE,
							new ReplaceBlockConfig(
								Blocks.STONE.getDefaultState(),
								Blocks.EMERALD_ORE.getDefaultState()),
							XanaduRegistry.xanaduEmeraldPlacer,
							IPlacementConfig.NO_PLACEMENT_CONFIG));
				}
				
				//badland gold
				if(category == Biome.Category.MESA) {
					Xanadu.LOGGER.debug("Adding extra gold to biome: " + biome.toString());
					biome.addFeature(
						GenerationStage.Decoration.UNDERGROUND_ORES,
						Biome.createDecoratedFeature(
							Feature.ORE,
							new OreFeatureConfig(
								OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.GOLD_ORE.getDefaultState(),
								9),
							Placement.COUNT_RANGE,
							new CountRangeConfig(20, 80, 80, 220)));
				}
				
				//everything:
				//FILLER
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.GRANITE.getDefaultState(),
							33),
						Placement.COUNT_RANGE,
						new CountRangeConfig(10, 80, 80, 230)));
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.DIORITE.getDefaultState(),
							33),
						Placement.COUNT_RANGE,
						new CountRangeConfig(10, 80, 80, 230)));
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.ANDESITE.getDefaultState(),
							33),
						Placement.COUNT_RANGE,
						new CountRangeConfig(10, 80, 80, 230)));
				
				//ORES
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.COAL_ORE.getDefaultState(),
							17),
						Placement.COUNT_RANGE,
						new CountRangeConfig(20, 128, 128, 256)));
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.IRON_ORE.getDefaultState(),
							9),
						Placement.COUNT_RANGE,
						new CountRangeConfig(20, 64, 64, 200)));
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.GOLD_ORE.getDefaultState(),
							9),
						Placement.COUNT_RANGE,
						new CountRangeConfig(2, 32, 32, 100)));
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.REDSTONE_ORE.getDefaultState(),
							8),
						Placement.COUNT_RANGE,
						new CountRangeConfig(8, 16, 16, 32)));
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.DIAMOND_ORE.getDefaultState(),
							8),
						Placement.COUNT_RANGE,
						new CountRangeConfig(1, 16, 16, 32)));
				biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							Blocks.LAPIS_ORE.getDefaultState(),
							7),
						Placement.COUNT_DEPTH_AVERAGE,
						new DepthAverageConfig(1, 32, 16)));
			}
		}
	}
}
