package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NewDimPOI
{
    public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, NewDimension.MODID);

    public static final RegistryObject<PointOfInterestType> NEWDIM_PORTAL_POI = POI.register("newdim_portal",
            () -> new PointOfInterestType("newdim_portal", PointOfInterestType.getAllStates(Registration.NEWDIM_PORTAL.get()), 0, 1));
}