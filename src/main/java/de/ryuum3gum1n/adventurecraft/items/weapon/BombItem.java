package de.ryuum3gum1n.adventurecraft.items.weapon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

import de.ryuum3gum1n.adventurecraft.entity.projectile.EntityBomb;

public class BombItem extends ACWeaponItem {

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!player.capabilities.isCreativeMode) {
			stack.shrink(1);
		}

		worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW,
				SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote) {
			EntityBomb bomb = new EntityBomb(worldIn, player);
			bomb.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.spawnEntity(bomb);
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
		super.addInformation(itemstack, world, list, flag);
		list.add("Can destroy broken walls");
	}


}