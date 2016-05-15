package net.torocraft.fishing.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.torocraft.fishing.FishingMod;

public class EventHooks {

	@SubscribeEvent
	public void dropWorms(HarvestDropsEvent event) {
		
		if (isGrassBlock(event)) {
			if (event.isSilkTouching()) {
				return;
			}
			
			BiomeGenBase biome = event.getWorld().getBiomeGenForCoords(event.getPos());
			if (isBiomeOfType(biome, Type.PLAINS)) {
				dropWormWithOdds(event, 100);
			} else if (isBiomeOfType(biome, Type.FOREST)) {
				dropWormWithOdds(event, 50);
			} else if (isBiomeOfType(biome, Type.JUNGLE)) {
				dropWormWithOdds(event, 10);
			}
		}
	}

	@SubscribeEvent
	public void hookWormOnPole(PlayerEvent event) {
	}
	
	private boolean isBiomeOfType(BiomeGenBase biome, Type type) {
		return BiomeDictionary.isBiomeOfType(biome, type);
	}

	private void dropWormWithOdds(HarvestDropsEvent event, int maxOdds) {
		EntityPlayer player = event.getHarvester();
		
		if(player == null){
			return;
		}
		
		if (!isDay(getWorldTime(event))) {
			maxOdds = doubleSpawnRate(maxOdds);
		}
		
		if (event.getWorld().rand.nextInt(maxOdds) == 0) {
			event.getDrops().add(newWorm(event));
		}
	}

	private int doubleSpawnRate(int maxOdds) {		
		return (int)(maxOdds * 0.5);
	}

	private long getWorldTime(HarvestDropsEvent event) {
		return event.getWorld().getWorldTime();
	}

	private boolean isDay(long time) {
		return time > 0 && time < 12000;
	}

	private ItemStack newWorm(HarvestDropsEvent event) {
		ItemStack worm = new ItemStack(FishingMod.worms);
		if (event.getWorld().rand.nextInt(300) == 0) {
			worm.addEnchantment(FishingMod.juicy, 1);
		}
		return worm;
	}

	private boolean isGrassBlock(HarvestDropsEvent event) {
		return event.getState().getBlock().getUnlocalizedName().equals("tile.grass");
	}
	
}
