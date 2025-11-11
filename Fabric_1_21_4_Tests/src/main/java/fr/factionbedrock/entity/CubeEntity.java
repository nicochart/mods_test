package fr.factionbedrock.entity;

import fr.factionbedrock.registry.TestEntities;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CubeEntity extends HostileEntity
{
	private PartEntity above;

	public CubeEntity(EntityType<? extends HostileEntity> type, World world)
	{
		super(type, world);
	}

	@Override @Nullable public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData)
	{
		this.above = this.summonAbovePart();
		return entityData;
	}

	private PartEntity summonAbovePart()
	{
		float x = 0.0F;
		float z = 0.0F;
		PartEntity above = TestEntities.PART.create(this.getWorld(), SpawnReason.NATURAL);
		if (above != null)
		{
			if (this.isPersistent()) {above.setPersistent();}
			above.setCustomName(this.getCustomName());
			above.setInvulnerable(this.isInvulnerable());
			above.setOwner(this);
			above.refreshPositionAndAngles(this.getX() + (double) x, this.getY(), this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
			this.getWorld().spawnEntity(above);
		}
		return above;
	}

	@Override public void tickMovement()
	{
		super.tickMovement();
		this.tickAboveMovement();
	}

	private void tickAboveMovement()
	{
		if (this.above == null) {return;}
		this.above.setPos(this.getX(), this.getY() + 1.0F, this.getZ());
		this.above.bodyYaw = this.bodyYaw;
		this.above.headYaw = this.headYaw;
		this.above.setPitch(this.getPitch());
		this.above.setYaw(this.getYaw());
	}

	@Override public final boolean damage(ServerWorld world, DamageSource source, float amount)
	{
		boolean damaged = super.damage(world, source, amount);
		if (damaged)
		{
			//attacking other parts just for attack animation (red overlay)
			this.above.damagePart(world, source, 0.5F, true);
			this.above.heal(0.5F);
		}
		return damaged;
	}

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
