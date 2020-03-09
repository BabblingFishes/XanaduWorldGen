/* NOTE: THIS IS A MODIFIED/ANNOTATED VERSION OF `net.minecraft.world.gen.OverworldChunkGenerator.class` (C) MOJANG.
 * Removed: Amplified world type calculations.
 * Added: Extra depth. */

package io.github.babblingfishes.xanaduworldgen.world.gen;

import java.util.List;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.VillageSiege;
import net.minecraft.world.IWorld;
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

public class XanaduChunkGenerator extends NoiseChunkGenerator<OverworldGenSettings> {
	private static final int smoothingRadius = 2; //don't touch this it breaks things
	
	//generates a 5x5 smoothing kernel (not gaussian, pointy)
   private static final float[] kernel = Util.make(new float[25], (p_222575_0_) -> {
      for(int i = -smoothingRadius; i <= smoothingRadius; ++i) {
         for(int j = -smoothingRadius; j <= smoothingRadius; ++j) {
            float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
            p_222575_0_[i + smoothingRadius + (j + smoothingRadius) * 5] = f;
         }
      }
   });
   
   private final OctavesNoiseGenerator depthNoise;
   private final PhantomSpawner phantomSpawner = new PhantomSpawner();
   private final PatrolSpawner patrolSpawner = new PatrolSpawner();
   private final CatSpawner catSpawner = new CatSpawner();
   private final VillageSiege villageSiege = new VillageSiege();

   public XanaduChunkGenerator(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
      super(worldIn, provider, 4, 8, 256, settingsIn, true);
      this.randomSeed.skip(2620);
      this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 16);
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
   
   //this is called with i iterating between value 0-64
   protected double func_222545_a(double depth, double scale, int i) {
      double d0 = 8.5D; //default 8.5D, 7.552264808D is close
      double d1 = ((double)i - (d0 + depth * d0 / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / scale;
      if (d1 < 0.0D) {
         d1 *= 4.0D;
      }

      return d1;
   }
   
   //depth & scale smoothing
   protected double[] func_222549_a(int x, int z) {
      double[] out = new double[2];
      
      float extraDepth = 8.0F; //this is what raises the world to 200 (4.0)
      /* Depth Values: 
       *  4.0 200
       *  3.0 168
       *  2.0 135
       *  1.0 101
       *  0.0  64
       * -1.0  32
       * 
       * Don't ask me why these aren't quite linear.
       * */
      float scaleAdjust = 0.3F; //raising depth also makes scale go kinda wild so tone that down
      
      float totalScale = 0.0F;
      float totalDepth = 0.0F;
      float totalWeight = 0.0F;
      
      float biomeDepth = this.biomeProvider.func_222366_b(x, z).getDepth() + extraDepth;
      
      //kernel-based smoothing
      for(int i = -smoothingRadius; i <= smoothingRadius; ++i) {
         for(int j = -smoothingRadius; j <= smoothingRadius; ++j) {
            Biome biome = this.biomeProvider.func_222366_b(x + i, z + j); //get biome for this block
            float depth = biome.getDepth() + extraDepth;
            float scale = biome.getScale() * scaleAdjust;

            float weight = kernel[i + smoothingRadius + (j + smoothingRadius) * 5] / (depth + 2.0F);
            // smooth less on biome edges (keeps biomes coherent)
            if (depth > biomeDepth) {
               weight /= 2.0F;
            }

            totalScale += scale * weight;
            totalDepth += depth * weight;
            totalWeight += weight;
         }
      }

      totalScale = totalScale / totalWeight;
      totalDepth = totalDepth / totalWeight;
      totalScale = totalScale * 0.9F + 0.1F;
      totalDepth = totalDepth * 0.5F - 0.125F;
      out[0] = (double)totalDepth + this.getDepthNoise(x, z); //noise
      out[1] = (double)totalScale;
      return out;
   }
   
   //depth noise generator - note that additional depthnoise is also added
   private double getDepthNoise(int x, int z) {
      double noise = this.depthNoise.func_215462_a(
    	  (double)(x * 200), 10.0D, 
    	  (double)(z * 200), 1.0D, 
    	  0.0D, true)
    	  / 8000.0D;
      
      //deep valleys are turned into low hills
      if (noise < 0.0D) {
         noise = -noise * 0.3D;
      }
      
      noise = noise * 3.0D - 2.0D;
      
      // lower min taper (bottom possible -0.07143)
      if (noise < 0.0D) {
          noise = noise / 28.0D;
      }
      // sharp max cutoff at 1
      else {
         if (noise > 1.0D) {
            noise = 1.0D;
         }

         noise = noise / 40.0D;
      }
      
      return noise;
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
      return 199; //default 63
   }
}