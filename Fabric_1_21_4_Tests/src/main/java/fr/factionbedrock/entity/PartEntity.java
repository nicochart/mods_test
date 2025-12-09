package fr.factionbedrock.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PartEntity extends HostileEntity
{
    private static final TrackedData<Boolean> IS_RIGHT_ARM = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private static final TrackedData<Boolean> IS_LEFT_ARM = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private static final TrackedData<Boolean> IS_SHIELD = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private static final TrackedData<Boolean> IS_HEAD = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private static final TrackedData<Boolean> IS_INVULNERABLE = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);;
    private static final TrackedData<String> OWNER_UUID = DataTracker.registerData(PartEntity.class, TrackedDataHandlerRegistry.STRING);;
    private CubeEntity owner;

    public PartEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);
        this.owner = null;
    }

    public CubeEntity getOwner() {return this.owner;}
    public boolean setOwner(CubeEntity owner)
    {
        boolean canSet = this.owner == null;
        if (canSet)
        {
            this.owner = owner;
            this.setOwnerUUID(this.owner.getUuidAsString());
        }
        return canSet;
    }

    @Override public void pushAwayFrom(Entity other)
    {
        if (other.isPartOf(this.owner)) {return;}
        super.pushAwayFrom(other);
    }

    @Override public void tick()
    {
        if (this.owner == null) {this.owner = this.getOwnerByUUID();}
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
        builder.add(IS_SHIELD, false);
        builder.add(IS_HEAD, false);
        builder.add(IS_INVULNERABLE, false);
        builder.add(OWNER_UUID, "");
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

    @Override public boolean isInsideWall() {return false;}

    @Override public boolean isPartOf(Entity entity) {return this == entity || this.owner == entity;}

    //@Override public boolean isCollidable() {return true;} block-like collision

    @Override public boolean isAttackable() {return true;}

    public boolean isLeftArm() {return this.getDataTracker().get(IS_LEFT_ARM);}
    public void setLeftArm(boolean isArm) {this.getDataTracker().set(IS_LEFT_ARM, isArm);}
    public boolean isRightArm() {return this.getDataTracker().get(IS_RIGHT_ARM);}
    public void setRightArm(boolean isArm) {this.getDataTracker().set(IS_RIGHT_ARM, isArm);}
    public boolean isShield() {return this.getDataTracker().get(IS_SHIELD);}
    public void setShield(boolean isShield) {this.getDataTracker().set(IS_SHIELD, isShield);}
    public boolean isHead() {return this.getDataTracker().get(IS_HEAD);}
    public void setHead(boolean isHead) {this.getDataTracker().set(IS_HEAD, isHead);}
    public boolean isInvulnerable() {return this.getDataTracker().get(IS_INVULNERABLE);}
    public void setInvulnerable(boolean isInvulnerable) {this.getDataTracker().set(IS_INVULNERABLE, isInvulnerable);}
    public CubeEntity getOwnerByUUID() {return this.getOwnerByUUID(this.getOwnerUUID());}
    public String getOwnerUUID() {return this.getDataTracker().get(OWNER_UUID);}
    public void setOwnerUUID(String uuid) {this.getDataTracker().set(OWNER_UUID, uuid);}

    @Nullable public CubeEntity getOwnerByUUID(String stringUUID)
    {
        List<CubeEntity> nearbyEntities = this.getWorld().getEntitiesByClass(CubeEntity.class, this.getBoundingBox().expand(5), EntityPredicates.maxDistance(this.getX(), this.getY(), this.getZ(), 5));
        for (CubeEntity entity : nearbyEntities)
        {
            if (entity.getUuidAsString().equals(stringUUID)) {return entity;}
        }
        return null;
    }

    @Override public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource)
    {
        return false;
    }
}
