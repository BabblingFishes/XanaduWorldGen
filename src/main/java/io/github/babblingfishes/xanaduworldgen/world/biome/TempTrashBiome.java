package io.github.babblingfishes.xanaduworldgen.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TempTrashBiome extends Biome {
	public TempTrashBiome() {
		super(new Biome.Builder()
			.surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
				SurfaceBuilder.DEFAULT,
				SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
			)
			.precipitation(RainType.RAIN)
			.category(Category.FOREST)
			.scale(0.05f)
			.depth(8.0f)
			.temperature(0.7f)
			.downfall(0.8f)
			.waterColor(0x557077)
			.waterFogColor(0x000000)
			.parent((String)null)
		);
		
		DefaultBiomeFeatures.addCarvers(this); //caves
		//DefaultBiomeFeatures.addOres(biomeIn);
		
		//custom ore and stone variant generation
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.DIRT.getDefaultState(),
					33),
				Placement.COUNT_RANGE,
				new CountRangeConfig(10, 0, 0, 256)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.GRAVEL.getDefaultState(),
					33),
				Placement.COUNT_RANGE,
				new CountRangeConfig(8, 0, 0, 256)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.GRANITE.getDefaultState(),
					33),
				Placement.COUNT_RANGE,
				new CountRangeConfig(10, 0, 0, 256)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.DIORITE.getDefaultState(),
					33),
				Placement.COUNT_RANGE,
				new CountRangeConfig(10, 0, 0, 256)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.ANDESITE.getDefaultState(),
					33),
				Placement.COUNT_RANGE,
				new CountRangeConfig(10, 0, 0, 256)));

		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.COAL_ORE.getDefaultState(),
					17),
				Placement.COUNT_RANGE,
				new CountRangeConfig(20, 0, 0, 256)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.IRON_ORE.getDefaultState(),
					9),
				Placement.COUNT_RANGE,
				new CountRangeConfig(20, 0, 0, 195)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.GOLD_ORE.getDefaultState(),
					9),
				Placement.COUNT_RANGE,
				new CountRangeConfig(2, 0, 0, 100)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.REDSTONE_ORE.getDefaultState(),
					8),
				Placement.COUNT_RANGE,
				new CountRangeConfig(8, 0, 0, 32)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.DIAMOND_ORE.getDefaultState(),
					8),
				Placement.COUNT_RANGE,
				new CountRangeConfig(1, 0, 0, 32)));
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createDecoratedFeature(
				Feature.ORE,
				new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE,
					Blocks.LAPIS_ORE.getDefaultState(),
					7),
				Placement.COUNT_DEPTH_AVERAGE,
				new DepthAverageConfig(1, 32, 32)));

		DefaultBiomeFeatures.addExtraEmeraldOre(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		
		DefaultBiomeFeatures.addStructures(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addFossils(this);
		
		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		
		//DefaultBiomeFeatures.addForestTrees(this);
		//DefaultBiomeFeatures.addJunglePlants(this);
		//DefaultBiomeFeatures.addSavannaTrees(this);
		//DefaultBiomeFeatures.addShatteredSavannaTrees(this);
		//DefaultBiomeFeatures.addSwampVegetation(this);
		//DefaultBiomeFeatures.addTaigaConifers(this);
		
		DefaultBiomeFeatures.addBamboo(this);
		DefaultBiomeFeatures.addBerryBushes(this);
		DefaultBiomeFeatures.addKelp(this);
		DefaultBiomeFeatures.addExtraReedsPumpkinsCactus(this);
		
		DefaultBiomeFeatures.addGrass(this);
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addExtraDefaultFlowers(this);
		DefaultBiomeFeatures.addDoubleFlowers(this);
		DefaultBiomeFeatures.func_222303_T(this); //mushrooms + taiga grass + dead bush
		
		DefaultBiomeFeatures.addFreezeTopLayer(this); //TODO IceAndSnowFeature.class
		
		//sunflowers
		this.addFeature(
			GenerationStage.Decoration.VEGETAL_DECORATION,
			createDecoratedFeature(
				Feature.DOUBLE_PLANT,
				new DoublePlantConfig(Blocks.SUNFLOWER.getDefaultState()),
				Placement.COUNT_HEIGHTMAP_32,
				new FrequencyConfig(1)));
	
		//swamp flowers
		this.addFeature(
			GenerationStage.Decoration.VEGETAL_DECORATION,
			Biome.createDecoratedFeature(
				Feature.SWAMP_FLOWER,
				IFeatureConfig.NO_FEATURE_CONFIG,
				Placement.COUNT_HEIGHTMAP_32,
				new FrequencyConfig(1)));
		
	  //lilypads
		this.addFeature(
			GenerationStage.Decoration.VEGETAL_DECORATION,
			Biome.createDecoratedFeature(
				Feature.WATERLILY,
				IFeatureConfig.NO_FEATURE_CONFIG,
				Placement.COUNT_HEIGHTMAP_DOUBLE,
				new FrequencyConfig(4)));
	    
	    
		//mass amounts of trees
		this.addFeature(
			GenerationStage.Decoration.VEGETAL_DECORATION,
			createDecoratedFeature(
				Feature.RANDOM_SELECTOR,
				new MultipleRandomFeatureConfig(
					new Feature[]{
						Feature.NORMAL_TREE,
						Feature.FANCY_TREE,
						Feature.BIRCH_TREE,
						Feature.SUPER_BIRCH_TREE,
						Feature.JUNGLE_GROUND_BUSH,
						Feature.JUNGLE_TREE,
						Feature.PINE_TREE,
						Feature.DARK_OAK_TREE,
						Feature.SAVANNA_TREE,
						Feature.SPRUCE_TREE,
						Feature.SWAMP_TREE,
						Feature.MEGA_JUNGLE_TREE,
						Feature.MEGA_PINE_TREE,
						Feature.MEGA_SPRUCE_TREE
					},
					new IFeatureConfig[]{
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG,
						IFeatureConfig.NO_FEATURE_CONFIG
					},
					new float[]{0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F, 0.05F},
					Feature.NORMAL_TREE,
					IFeatureConfig.NO_FEATURE_CONFIG
				),
				Placement.DARK_OAK_TREE,
				IPlacementConfig.NO_PLACEMENT_CONFIG));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.FOX, 8, 1, 2));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 8, 2, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.WOLF, 5, 2, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CAT, 5, 1, 20));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.HORSE, 5, 2, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.DONKEY, 1, 2, 3));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PARROT, 5, 1, 10));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		
		//this.setRegistryName(XanaduWGRegistries.location("temp trash biome")); //TODO
		//this.setRegistryName(XanaduWorldGen.MODID, "temp trash biome");
	}
}
