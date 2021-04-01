package fr.factionbedrock.newdim.Entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NewDimGolem extends AbstractNewDimEntity
{
	private int attackTimer;
	
    public NewDimGolem(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D);
    }
    
    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer()
    {
       return this.attackTimer;
    }
    

    private float func_226511_et_()
    {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        this.attackTimer+=10;
        this.world.setEntityState(this, (byte)4);
        float f = this.func_226511_et_();
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.rand.nextInt((int)f) : f;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag)
        {
           entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)0.4F, 0.0D));
           this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }
    
    @Override
    public void livingTick()
    {
	      super.livingTick();
	      if (this.attackTimer > 0)
	      {
	         --this.attackTimer;
	      }

	      if (horizontalMag(this.getMotion()) > (double)2.5000003E-7F && this.rand.nextInt(5) == 0)
	      {
	         int i = MathHelper.floor(this.getPosX());
	         int j = MathHelper.floor(this.getPosY() - (double)0.2F);
	         int k = MathHelper.floor(this.getPosZ());
	         BlockPos pos = new BlockPos(i, j, k);
	         BlockState blockstate = this.world.getBlockState(pos);
	         if (!blockstate.isAir(this.world, pos))
	         {
	            this.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos), this.getPosX() + ((double)this.rand.nextFloat() - 0.5D) * (double)this.getWidth(), this.getPosY() + 0.1D, this.getPosZ() + ((double)this.rand.nextFloat() - 0.5D) * (double)this.getWidth(), 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
	         }
	      }
	   }
    
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 0.15F, 0.5F);
    }
}