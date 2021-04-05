package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Entity.NewDimGolem;
import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = NewDimension.MODID, bus = Bus.MOD)
public class RegisterEntityAttributes
{
	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event)
	{
		/*
		Do not forget to register the attributes ! If you forget and try to summon the entity, this exception will pop :
		java.lang.NullPointerException: Cannot invoke "net.minecraft.entity.ai.attributes.AttributeModifierMap.getAttributeValue(net.minecraft.entity.ai.attributes.Attribute)" because "this.attributeMap" is null
		Entity with no attributes registered and be summoned 
		*/
		event.put(Registration.NEWDIM_GOLEM.get(), NewDimGolem.registerAttributes().create());
	}
}
