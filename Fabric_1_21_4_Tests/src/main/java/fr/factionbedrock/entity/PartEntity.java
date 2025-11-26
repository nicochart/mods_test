package fr.factionbedrock.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class PartEntity extends HostileEntity
{
    private static final TrackedData<Boolean> IS_RIGHT_ARM = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private static final TrackedData<Boolean> IS_LEFT_ARM = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private static final TrackedData<Boolean> IS_HEAD = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private static final TrackedData<Boolean> IS_INVULNERABLE = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private CubeEntity owner;

    public PartEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);
        this.owner = null;
    }

    public boolean setOwner(CubeEntity owner)
    {
        boolean canSet = this.owner == null;
        if (canSet) {this.owner = owner;}
        return canSet;
    }

    @Override public void pushAwayFrom(Entity other)
    {
        if (other.isPartOf(this.owner)) {return;}
        super.pushAwayFrom(other);
    }

    @Override public void tick()
    {
        if (this.owner == null || this.owner.isDead() || this.owner.isRemoved())
        {
            this.serverDamage(this.getDamageSources().outOfWorld(), this.getMaxHealth());
        }
        super.tick();
    }

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(IS_LEFT_ARM, false);
        builder.add(IS_RIGHT_ARM, false);
        builder.add(IS_HEAD, false);
        builder.add(IS_INVULNERABLE, false);
    }

    @Override public boolean canHit() {return true;}

    public final boolean damagePart(ServerWorld world, DamageSource source, float amount, boolean forceLocalDamage)
    {
        if (forceLocalDamage) {return super.damage(world, source, amount);}
        else {return this.damage(world, source, amount);}
    }

    @Override public final boolean damage(ServerWorld world, DamageSource source, float amount)
    {
        if (this.owner == null || this.owner.isDead()) {return super.damage(world, source, amount);}
        if (this.isInvulnerable()) {return false;}
        boolean damaged = this.isAlwaysInvulnerableTo(source) ? false : this.owner.damage(world, source, amount);
        return damaged;
    }

    @Override public boolean isPartOf(Entity entity) {return this == entity || this.owner == entity;}

    //@Override public boolean isCollidable() {return true;} block-like collision

    @Override public boolean isAttackable() {return true;}

    public boolean isLeftArm() {return this.getDataTracker().get(IS_LEFT_ARM);}
    public void setLeftArm(boolean isHead) {this.getDataTracker().set(IS_LEFT_ARM, isHead);}
    public boolean isRightArm() {return this.getDataTracker().get(IS_RIGHT_ARM);}
    public void setRightArm(boolean isHead) {this.getDataTracker().set(IS_RIGHT_ARM, isHead);}
    public boolean isHead() {return this.getDataTracker().get(IS_HEAD);}
    public void setHead(boolean isHead) {this.getDataTracker().set(IS_HEAD, isHead);}
    public boolean isInvulnerable() {return this.getDataTracker().get(IS_INVULNERABLE);}
    public void setInvulnerable(boolean isHead) {this.getDataTracker().set(IS_INVULNERABLE, isHead);}

    @Override public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource)
    {
        return false;
    }
}
