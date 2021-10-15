package net.mcreator.fantasticalfruits.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.fantasticalfruits.item.RedGrapesItem;
import net.mcreator.fantasticalfruits.item.PurpleGrapesItem;
import net.mcreator.fantasticalfruits.item.GreenGrapesItem;
import net.mcreator.fantasticalfruits.block.RedGrapeVineTipBlock;
import net.mcreator.fantasticalfruits.block.PurpleGrapeVineTipBlock;
import net.mcreator.fantasticalfruits.block.GreenGrapeVineTipBlock;
import net.mcreator.fantasticalfruits.FantasticalFruitsMod;

import java.util.Map;
import java.util.HashMap;

public class GrapesPlaceProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
			PlayerEntity entity = event.getPlayer();
			if (event.getHand() != entity.getActiveHand()) {
				return;
			}
			double i = event.getPos().getX();
			double j = event.getPos().getY();
			double k = event.getPos().getZ();
			IWorld world = event.getWorld();
			BlockState state = world.getBlockState(event.getPos());
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("direction", event.getFace());
			dependencies.put("blockstate", state);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency entity for procedure GrapesPlace!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency x for procedure GrapesPlace!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency y for procedure GrapesPlace!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency z for procedure GrapesPlace!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				FantasticalFruitsMod.LOGGER.warn("Failed to load dependency world for procedure GrapesPlace!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == PurpleGrapesItem.block)) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR)) {
				if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == PurpleGrapeVineTipBlock.block)
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.OAK_LEAVES))
						|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).isSolid()))) {
					if ((!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity)))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(PurpleGrapesItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), PurpleGrapeVineTipBlock.block.getDefaultState(), 3);
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
				}
			}
		} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == GreenGrapesItem.block)) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR)) {
				if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GreenGrapeVineTipBlock.block)
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.OAK_LEAVES))
						|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).isSolid()))) {
					if ((!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity)))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(GreenGrapesItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), GreenGrapeVineTipBlock.block.getDefaultState(), 3);
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
				}
			}
		} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == RedGrapesItem.block)) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR)) {
				if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RedGrapeVineTipBlock.block)
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.OAK_LEAVES))
						|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).isSolid()))) {
					if ((!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity)))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(RedGrapesItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), RedGrapeVineTipBlock.block.getDefaultState(), 3);
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
				}
			}
		}
	}
}
