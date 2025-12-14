package fr.factionbedrock.entity;

import fr.factionbedrock.registry.TestEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CubeEntity extends HostileEntity
{
	private PartEntity bottom;
	private PartEntity body;
	private PartEntity head;
	private PartEntity leftArm;
	private PartEntity rightArm;
	private PartEntity firstYShield;
	private PartEntity secondYShield;
	private PartEntity firstXShield;
	private PartEntity secondXShield;
	private PartEntity firstZShield;
	private PartEntity secondZShield;

	public CubeEntity(EntityType<? extends HostileEntity> type, World world)
	{
		super(type, world);
	}

	@Override @Nullable public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData)
	{
		this.bottom = this.summonNewPart();
		this.body = this.summonNewPart();
		this.head = this.summonNewPart();
		this.head.setHead(true);
		this.leftArm = this.summonNewPart();
		this.leftArm.setLeftArm(true);
		this.rightArm = this.summonNewPart();
		this.rightArm.setRightArm(true);
		this.firstYShield = this.summonNewPart();
		this.firstYShield.setInvulnerable(true);
		this.firstYShield.setShield(true);
		this.secondYShield = this.summonNewPart();
		this.secondYShield.setInvulnerable(true);
		this.secondYShield.setShield(true);
		this.firstXShield = this.summonNewPart();
		this.firstXShield.setInvulnerable(true);
		this.firstXShield.setShield(true);
		this.secondXShield = this.summonNewPart();
		this.secondXShield.setInvulnerable(true);
		this.secondXShield.setShield(true);
		this.firstZShield = this.summonNewPart();
		this.firstZShield.setInvulnerable(true);
		this.firstZShield.setShield(true);
		this.secondZShield = this.summonNewPart();
		this.secondZShield.setInvulnerable(true);
		this.secondZShield.setShield(true);
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
		this.tickBottomMovement();
		this.tickBodyMovement();
		this.tickHeadMovement();
		this.rotateShields();
		this.tickArmsMovement();
	}

	private void tickBottomMovement()
	{
		if (this.bottom == null) {return;}
		this.bottom.setPos(this.getX(), this.getY(), this.getZ());
		this.bottom.bodyYaw = this.bodyYaw;
		this.bottom.headYaw = this.headYaw;
		this.bottom.setPitch(this.getPitch());
		this.bottom.setYaw(this.getYaw());
	}

	private void tickBodyMovement()
	{
		if (this.body == null) {return;}
		this.body.setPos(this.getX(), this.getY() + 0.5F, this.getZ());
		this.body.bodyYaw = this.bodyYaw;
		this.body.headYaw = this.headYaw;
		this.body.setPitch(this.getPitch());
		this.body.setYaw(this.getYaw());
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

	private void tickArmsMovement()
	{
		this.tickLeftArmMovement();
		this.tickRightArmMovement();
	}

	private void tickLeftArmMovement()
	{
		if (this.leftArm == null) {return;}

		Vec3d armOffset = new Vec3d(0.5F, 0.5F, 0.0F);
		float yawRad = (float) Math.toRadians(this.getBodyYaw());
		Vec3d rotatedOffset = armOffset.rotateY(-yawRad);
		this.leftArm.setPos(this.getX() + rotatedOffset.x, this.getY() + rotatedOffset.y, this.getZ() + rotatedOffset.z);

		//forward orientation
		Vec3d center = this.getPos().add(0.0F, 0.5F, 0.0F);
		Vec3d armPos = this.leftArm.getPos();
		Vec3d outwardVector = armPos.subtract(center);
		Vec3d forwardVector = outwardVector.rotateY((float)Math.PI / 2);

		float yaw = (float)(Math.atan2(forwardVector.z, forwardVector.x) * 180.0 / Math.PI) + 90.0F;
		float pitch = 0.0F;

		this.leftArm.setYaw(yaw);
		this.leftArm.setPitch(pitch);
		this.leftArm.bodyYaw = yaw;
		this.leftArm.headYaw = this.leftArm.bodyYaw; //if headYaw is different, the body will follow (interpolate) after being still for some ticks.
	}

	private void tickRightArmMovement()
	{
		if (this.rightArm == null) {return;}

		Vec3d armOffset = new Vec3d(-0.5F, 0.5F, 0.0F);
		float yawRad = (float) Math.toRadians(this.getBodyYaw());
		Vec3d rotatedOffset = armOffset.rotateY(-yawRad);
		this.rightArm.setPos(this.getX() + rotatedOffset.x, this.getY() + rotatedOffset.y, this.getZ() + rotatedOffset.z);

		//forward orientation
		Vec3d center = this.getPos().add(0.0F, 0.5F, 0.0F);
		Vec3d armPos = this.rightArm.getPos();
		Vec3d outwardVector = armPos.subtract(center);
		Vec3d forwardVector = outwardVector.rotateY((float)- Math.PI / 2);

		float yaw = (float)(Math.atan2(forwardVector.z, forwardVector.x) * 180.0 / Math.PI) + 90.0F;
		float pitch = 0.0F;

		this.rightArm.setYaw(yaw);
		this.rightArm.setPitch(pitch);
		this.rightArm.bodyYaw = yaw;
		this.rightArm.headYaw = this.rightArm.bodyYaw; //if headYaw is different, the body will follow (interpolate) after being still for some ticks.
	}

	private void rotateShields()
	{
		Vec3d center = this.getPos().add(0.0F, 0.5F, 0.0F);
		rotatePart(this.firstYShield, center, this.age * 0.1f, 1.2f, 0.0F, Direction.Axis.Y);
		rotatePart(this.secondYShield, center, this.age * 0.1f, 1.2f, (float)Math.PI, Direction.Axis.Y);
		rotatePart(this.firstXShield, center, this.age * 0.1f, 1.2f, 0.0F, Direction.Axis.X);
		rotatePart(this.secondXShield, center, this.age * 0.1f, 1.2f, (float)Math.PI, Direction.Axis.X);
		rotatePart(this.firstZShield, center, this.age * 0.1f, 1.2f, 0.0F, Direction.Axis.Z);
		rotatePart(this.secondZShield, center, this.age * 0.1f, 1.2f, (float)Math.PI, Direction.Axis.Z);
	}

	private static void rotatePart(@Nullable PartEntity part, Vec3d rotationCenter, float angle, float radius, float offset, Direction.Axis axis)
	{
		if (part == null) {return;}
		//rotating position
		float x = 0.0F, y = 0.0F, z = 0.0F;
		switch (axis)
		{
			case X :
			{
				y = (float) Math.sin(angle + offset) * radius; //inverting those two (y = cos and x = sin) will make the shields unsync (nicer effect) for orientation will be offset by pi/2
				z = (float) Math.cos(angle + offset) * radius;
				break;
			}
			case Y :
			{
				x = (float) Math.cos(angle + offset) * radius;
				z = (float) Math.sin(angle + offset) * radius;
				break;
			}
			case Z :
			{
				x = (float) Math.cos(angle + offset) * radius;
				y = (float) Math.sin(angle + offset) * radius;
				break;
			}
		}
		part.setPos(rotationCenter.getX() + x, rotationCenter.getY() + y, rotationCenter.getZ() + z);

		//outward orientation
		Vec3d armPos = part.getPos();
		Vec3d outwardVector = armPos.subtract(rotationCenter).multiply(-1.0F, -1.0F, -1.0F);

		float horizontalLength = (float)Math.sqrt(outwardVector.x * outwardVector.x + outwardVector.z * outwardVector.z);
		float yaw = 0.0F, pitch = 0.0F;
		float pitchOffset = (offset == 0.0F) ? ((float)Math.PI) : 0.0F;
		switch (axis)
		{
			case X :
			{
				yaw = (float)(Math.atan2(outwardVector.z, outwardVector.x) * 180.0 / Math.PI) + 90.0F;
				pitch = (float) Math.sin(angle + pitchOffset) * 90.0F;//(float)(Math.atan2(outwardVector.y, horizontalLength) * 180.0 / Math.PI);
				break;
			}
			case Y :
			{
				yaw = (float)(Math.atan2(outwardVector.z, outwardVector.x) * 180.0 / Math.PI) + 90.0F;
				pitch = 0.0F;
				break;
			}
			case Z :
			{
				yaw = (float)(Math.atan2(outwardVector.z, outwardVector.x) * 180.0 / Math.PI) + 90.0F;
				pitch = (float) Math.sin(angle + pitchOffset) * 90.0F;//(float)(Math.atan2(outwardVector.y, horizontalLength) * 180.0 / Math.PI);
				break;
			}
		}

		part.setYaw(yaw);
		part.setPitch(pitch);
		part.bodyYaw = yaw;
		part.headYaw = part.bodyYaw; //if headYaw is different, the body will follow (interpolate) after being still for some ticks.
	}

	@Override public final boolean damage(ServerWorld world, DamageSource source, float amount)
	{
		boolean damaged = super.damage(world, source, amount);
		if (damaged)
		{
			//attacking other parts just for attack animation (red overlay)
			falseAttackForRedAnimation(this.bottom, world, source);
			falseAttackForRedAnimation(this.body, world, source);
			falseAttackForRedAnimation(this.head, world, source);
			falseAttackForRedAnimation(this.leftArm, world, source);
			falseAttackForRedAnimation(this.rightArm, world, source);
			falseAttackForRedAnimation(this.firstYShield, world, source);
			falseAttackForRedAnimation(this.secondYShield, world, source);
			falseAttackForRedAnimation(this.firstXShield, world, source);
			falseAttackForRedAnimation(this.secondXShield, world, source);
			falseAttackForRedAnimation(this.firstZShield, world, source);
			falseAttackForRedAnimation(this.secondZShield, world, source);
		}
		return damaged;
	}

	private static void falseAttackForRedAnimation(@Nullable PartEntity part, ServerWorld world, DamageSource source)
	{
		if (part != null)
		{
			part.damagePart(world, source, 0.5F, true);
			part.heal(0.5F);
		}
	}

	@Override public boolean isAttackable() {return false;} //makes damage is not called when a player left-clicks on the hitbox, but the left-click hitbox collision still happen
	@Override public boolean canHit() {return false;} //disables left-click hitbox collision

	@Override protected void initGoals()
	{
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
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
