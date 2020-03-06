package io.github.babblingfishes.xanaduworldgen.world.gen;

import java.util.List;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.VillageSiege;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.CatSpawner;
import net.minecraft.world.spawner.PatrolSpawner;
import net.minecraft.world.spawner.PhantomSpawner;
import net.minecraft.world.spawner.WorldEntitySpawner;

public class XanaduWGChunkGenerator extends NoiseChunkGenerator<OverworldGenSettings> {
   private static final float[] field_222576_h = Util.make(new float[25], (p_222575_0_) -> {
      for(int i = -2; i <= 2; ++i) {
         for(int j = -2; j <= 2; ++j) {
            float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
            p_222575_0_[i + 2 + (j + 2) * 5] = f;
         }
      }

   });
   private final OctavesNoiseGenerator depthNoise;
   private final boolean isAmplified;
   private final PhantomSpawner phantomSpawner = new PhantomSpawner();
   private final PatrolSpawner patrolSpawner = new PatrolSpawner();
   private final CatSpawner catSpawner = new CatSpawner();
   private final VillageSiege villageSiege = new VillageSiege();

   public XanaduWGChunkGenerator(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
      super(worldIn, provider, 4, 8, 256, settingsIn, true);
      this.randomSeed.skip(2620);
      this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 16);
      this.isAmplified = worldIn.getWorldInfo().getGenerator() == WorldType.AMPLIFIED;
   }

   public void spawnMobs(WorldGenRegion region) {
      int i = region.getMainChunkX();
      int j = region.getMainChunkZ();
      Biome biome = region.getChunk(i, j).getBiomes()[0];
      SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
      sharedseedrandom.setDecorationSeed(region.getSeed(), i << 4, j << 4);
      WorldEntitySpawner.performWorldGenSpawning(region, biome, i, j, sharedseedrandom);
   }

   protected void func_222548_a(double[] p_222548_1_, int p_222548_2_, int p_222548_3_) {
      double d0 = (double)684.412F;
      double d1 = (double)684.412F;
      double d2 = 8.555149841308594D;
      double d3 = 4.277574920654297D;
      int i = -10;
      int j = 3;
      this.func_222546_a(p_222548_1_, p_222548_2_, p_222548_3_, d0, d1, d2, d3, i, j);
   }

   protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
      double d0 = 8.5D;
      double d1 = ((double)p_222545_5_ - (d0 + p_222545_1_ * d0 / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / p_222545_3_;
      if (d1 < 0.0D) {
         d1 *= 4.0D;
      }

      return d1;
   }

   protected double[] func_222549_a(int p_222549_1_, int p_222549_2_) {
      double[] adouble = new double[2];
      float f = 0.0F;
      float f1 = 0.0F;
      float f2 = 0.0F;
      //int i = 2;
      float f3 = this.biomeProvider.func_222366_b(p_222549_1_, p_222549_2_).getDepth();

      for(int j = -2; j <= 2; ++j) {
         for(int k = -2; k <= 2; ++k) {
            Biome biome = this.biomeProvider.func_222366_b(p_222549_1_ + j, p_222549_2_ + k);
            float f4 = biome.getDepth() + 8.0F; //T
            float f5 = biome.getScale() * 0.5F; //T
            if (this.isAmplified && f4 > 0.0F) {
               f4 = 1.0F + f4 * 2.0F;
               f5 = 1.0F + f5 * 4.0F;
            }

            float f6 = field_222576_h[j + 2 + (k + 2) * 5] / (f4 + 2.0F);
            if (biome.getDepth() + 8.0F > f3) { //T
               f6 /= 2.0F;
            }

            f += f5 * f6;
            f1 += f4 * f6;
            f2 += f6;
         }
      }

      f = f / f2;
      f1 = f1 / f2;
      f = f * 0.9F + 0.1F;
      f1 = (f1 * 4.0F - 1.0F) / 8.0F;
      adouble[0] = (double)f1 + this.func_222574_c(p_222549_1_, p_222549_2_);
      adouble[1] = (double)f;
      return adouble;
   }

   private double func_222574_c(int p_222574_1_, int p_222574_2_) {
      double d0 = this.depthNoise.func_215462_a((double)(p_222574_1_ * 200), 10.0D, (double)(p_222574_2_ * 200), 1.0D, 0.0D, true) / 8000.0D;
      if (d0 < 0.0D) {
         d0 = -d0 * 0.3D;
      }

      d0 = d0 * 3.0D - 2.0D;
      if (d0 < 0.0D) {
         d0 = d0 / 28.0D;
      } else {
         if (d0 > 1.0D) {
            d0 = 1.0D;
         }

         d0 = d0 / 40.0D;
      }

      return d0;
   }

   public List<Biome.SpawnListEntry> getPossibleCreatures(EntityClassification creatureType, BlockPos pos) {
      if (Feature.SWAMP_HUT.func_202383_b(this.world, pos)) {
         if (creatureType == EntityClassification.MONSTER) {
            return Feature.SWAMP_HUT.getSpawnList();
         }

         if (creatureType == EntityClassification.CREATURE) {
            return Feature.SWAMP_HUT.getCreatureSpawnList();
         }
      } else if (creatureType == EntityClassification.MONSTER) {
         if (Feature.PILLAGER_OUTPOST.isPositionInStructure(this.world, pos)) {
            return Feature.PILLAGER_OUTPOST.getSpawnList();
         }

         if (Feature.OCEAN_MONUMENT.isPositionInStructure(this.world, pos)) {
            return Feature.OCEAN_MONUMENT.getSpawnList();
         }
      }

      return super.getPossibleCreatures(creatureType, pos);
   }

   public void spawnMobs(ServerWorld worldIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {
      this.phantomSpawner.tick(worldIn, spawnHostileMobs, spawnPeacefulMobs);
      this.patrolSpawner.tick(worldIn, spawnHostileMobs, spawnPeacefulMobs);
      this.catSpawner.tick(worldIn, spawnHostileMobs, spawnPeacefulMobs);
      this.villageSiege.func_225477_a(worldIn, spawnHostileMobs, spawnPeacefulMobs);
   }

   public int getGroundHeight() {
      return this.world.getSeaLevel() + 1;
   }

   public int getSeaLevel() {
      return 199;
   }
}