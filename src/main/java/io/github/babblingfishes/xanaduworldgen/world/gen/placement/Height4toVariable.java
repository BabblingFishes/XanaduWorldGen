package io.github.babblingfishes.xanaduworldgen.world.gen.placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.SimplePlacement;

public class Height4toVariable extends SimplePlacement<NoPlacementConfig> {
   public Height4toVariable(Function<Dynamic<?>, ? extends NoPlacementConfig> noPlacementConfig) {
      super(noPlacementConfig);
   }

   public Stream<BlockPos> getPositions(Random random, NoPlacementConfig configIn, BlockPos pos) {
	   int variable = 100;
	   
      int i = 3 + random.nextInt(6); //I *think* this is rarity
      return IntStream.range(0, i).mapToObj((p_215060_2_) -> {
         int x = random.nextInt(variable / 2);
         int y = random.nextInt(variable - 4) + 4;
         int z = random.nextInt(variable / 2);
         return pos.add(x, y, z);
      });
   }
}
