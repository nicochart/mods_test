package fr.factionbedrock.Entity;

import fr.factionbedrock.Registry.AerialHellEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EvilCowEntity extends HostileEntity
{
    public EvilCowEntity(EntityType<? extends EvilCowEntity> type, World world) {super(type, world);}

    public EvilCowEntity(World world) {this(AerialHellEntityTypes.EVIL_COW, world);}

    public static DefaultAttributeContainer.Builder createAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224D)
        		.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    @Override protected void initGoals()
    {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.6D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4F));
    }

    public static boolean canSpawn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random randomIn)
    {
        return randomIn.nextInt(50) == 0 && canSpawnInDark(type, world, reason, pos, randomIn);
    }

    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.ENTITY_COW_HURT;}
    @Nullable @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_COW_DEATH;}
    @Nullable @Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_COW_AMBIENT;}
}