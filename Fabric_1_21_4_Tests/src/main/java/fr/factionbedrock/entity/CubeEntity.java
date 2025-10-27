package fr.factionbedrock.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CubeEntity extends HostileEntity
{
	public CubeEntity(EntityType<? extends HostileEntity> type, World world) {super(type, world);}

	@Override protected void initGoals()
	{
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));
	}

	public static DefaultAttributeContainer.Builder registerAttributes()
	{
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 40.0D)
				.add(EntityAttributes.ARMOR, 3.0D)
				.add(EntityAttributes.ATTACK_DAMAGE, 5.0D)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.23D);
	}
}
