package fr.factionbedrock.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class PartEntity extends HostileEntity
{
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

    @Override protected void initDataTracker(DataTracker.Builder builder) {super.initDataTracker(builder);}

    @Override public boolean canHit() {return true;}

    public final boolean damagePart(ServerWorld world, DamageSource source, float amount, boolean forceLocalDamage)
    {
        if (forceLocalDamage) {return super.damage(world, source, amount);}
        else {return this.damage(world, source, amount);}
    }

    @Override public final boolean damage(ServerWorld world, DamageSource source, float amount)
    {
        if (this.owner == null || this.owner.isDead()) {return super.damage(world, source, amount);}
        boolean damaged = this.isAlwaysInvulnerableTo(source) ? false : this.owner.damage(world, source, amount);
        return damaged;
    }

    @Override public boolean isPartOf(Entity entity) {return this == entity || this.owner == entity;}

    //@Override public boolean isCollidable() {return true;} block-like collision

    @Override public boolean isAttackable() {return true;}
}
