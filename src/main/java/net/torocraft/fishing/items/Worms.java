package net.torocraft.fishing.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Worms extends ItemFood {
	
	public static String NAME = "worms";
	public static final int healAmount = 1;
	public static final int saturation = 1;
	public static final boolean isWolfFood = false;

	public Worms() {
		this(64, CreativeTabs.MISC, NAME);
	}
	
	public Worms(int maxStackSize, CreativeTabs tab, String name) {
		super(healAmount, saturation, isWolfFood);
		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
	}
	
	@Override
	public int getItemEnchantability() {
		return 10;
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return true;
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn,
			World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (ItemDye.applyBonemeal(stack, worldIn, pos, playerIn)) {
			return EnumActionResult.SUCCESS;
		}
		
		return EnumActionResult.PASS;
	}
}
