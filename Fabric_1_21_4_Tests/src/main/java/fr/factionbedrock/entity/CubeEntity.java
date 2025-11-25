package fr.factionbedrock.entity;

import fr.factionbedrock.registry.TestEntities;
import net.minecraft.entity.Entity;
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
	private PartEntity head;
	private PartEntity leftArm;
	private PartEntity rightArm;

	public CubeEntity(EntityType<? extends HostileEntity> type, World world)
	{
		super(type, world);
	}

	@Override @Nullable public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData)
	{
		this.above = this.summonNewPart();
		this.above.setInvulnerable(true);
		this.head = this.summonNewPart();
		this.head.setHead(true);
		this.leftArm = this.summonNewPart();
		this.rightArm = this.summonNewPart();
		return entityData;
	}

	@Override public void pushAwayFrom(Entity other)
	{
		if (other.isPartOf(this)) {return;}
		super.pushAwayFrom(other);
	}

	private PartEntity summonNewPart()
	{
		float x = 0.0F;
		float z = 0.0F;
		PartEntity newPart = TestEntities.PART.create(this.getWorld(), SpawnReason.NATURAL);
		if (newPart != null)
		{
			if (this.isPersistent()) {newPart.setPersistent();}
			newPart.setCustomName(this.getCustomName());
			newPart.setInvulnerable(this.isInvulnerable());
			newPart.setOwner(this);
			newPart.refreshPositionAndAngles(this.getX() + (double) x, this.getY(), this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
			this.getWorld().spawnEntity(newPart);
		}
		return newPart;
	}

	@Override public void tickMovement()
	{
		super.tickMovement();
		this.tickAboveMovement();
		this.tickHeadMovement();
		this.tickLeftArmMovement();
		this.tickRightArmMovement();
	}

	private void tickAboveMovement()
	{
		if (this.above == null) {return;}
		this.above.setPos(this.getX(), this.getY() + 0.5F, this.getZ());
		this.above.bodyYaw = this.bodyYaw;
		this.above.headYaw = this.headYaw;
		this.above.setPitch(this.getPitch());
		this.above.setYaw(this.getYaw());
	}

	private void tickHeadMovement()
	{
		if (this.head == null) {return;}
		this.head.setPos(this.getX(), this.getY() + 1.0F, this.getZ());
		this.head.bodyYaw = this.bodyYaw;
		this.head.headYaw = this.headYaw;
		this.head.setPitch(this.getPitch());
		this.head.setYaw(this.getYaw());
	}

	private void tickLeftArmMovement()
	{
		if (this.leftArm == null) {return;}
		this.leftArm.setPitch(this.getPitch());
		this.leftArm.setYaw(this.getYaw());
		this.leftArm.bodyYaw = this.bodyYaw;
		this.leftArm.headYaw = this.headYaw;

		Vec3d leftArmOffset = new Vec3d(-0.5F, 0.5F, 0.0F);
		float yawRad = (float) Math.toRadians(this.getBodyYaw());
		Vec3d rotatedLeft  = leftArmOffset.rotateY(-yawRad);
		this.leftArm.setPos(this.getX() + rotatedLeft.x, this.getY() + rotatedLeft.y, this.getZ() + rotatedLeft.z);
	}

	private void tickRightArmMovement()
	{
		if (this.rightArm == null) {return;}
		this.rightArm.setPitch(this.getPitch());
		this.rightArm.setYaw(this.getYaw());
		this.rightArm.bodyYaw = this.bodyYaw;
		this.rightArm.headYaw = this.headYaw;

		Vec3d rightArmOffset = new Vec3d(0.5F, 0.5F, 0.0F);
		float yawRad = (float) Math.toRadians(this.getBodyYaw());
		Vec3d rotatedRight = rightArmOffset.rotateY(-yawRad);
		this.rightArm.setPos(this.getX() + rotatedRight.x, this.getY() + rotatedRight.y, this.getZ() + rotatedRight.z);
	}

	@Override public final boolean damage(ServerWorld world, DamageSource source, float amount)
	{
		boolean damaged = super.damage(world, source, amount);
		if (damaged)
		{
			//attacking other parts just for attack animation (red overlay)
			this.above.damagePart(world, source, 0.5F, true);
			this.above.heal(0.5F);
			this.head.damagePart(world, source, 0.5F, true);
			this.head.heal(0.5F);
			this.leftArm.damagePart(world, source, 0.5F, true);
			this.leftArm.heal(0.5F);
			this.rightArm.damagePart(world, source, 0.5F, true);
			this.rightArm.heal(0.5F);
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


	@Override public double getEyeY()
	{
		return this.getPos().y + 1.25F;
	}
}
