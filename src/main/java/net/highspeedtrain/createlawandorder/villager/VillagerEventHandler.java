package net.highspeedtrain.createlawandorder.villager;

import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CreateLawAndOrder.MOD_ID)
public class VillagerEventHandler {
    @SubscribeEvent
    public static void onVillagerDeath(LivingDeathEvent event) {
        CreateLawAndOrder.LOGGER.info("thing ded");
        if (!(event.getEntity() instanceof Villager)) {
            return;
        }
        CreateLawAndOrder.LOGGER.info("that thing was a villager");
        
        Villager villager = (Villager) event.getEntity();
        Level level = villager.level();
        BlockPos deathPos = villager.blockPosition();

        List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(
            LivingEntity.class,
            new AABB(deathPos).inflate(30),
            foundEn -> foundEn.getType() == EntityType.VILLAGER && !foundEn.equals(villager)
        );

        nearbyEntities.removeIf(villagerToRemove -> 
            !(villagerToRemove instanceof Villager) || ((Villager) villagerToRemove).getVillagerData().getProfession() != VillagerProfessions.ATTORNEY_GENERAL.get()
        );

        if(!nearbyEntities.isEmpty()) {
            CreateLawAndOrder.LOGGER.info("OMG CRIME!!");
            Entity trueSource = event.getSource().getEntity();
            if (!(trueSource instanceof ServerPlayer player)) {
                return;
            }
            if (player.gameMode.getGameModeForPlayer() == GameType.CREATIVE) {
                //return;
            }
            villager.getNavigation().moveTo(
                deathPos.getX(),
                deathPos.getY(),
                deathPos.getZ(),
                1
            );
            player.sendSystemMessage(Component.literal("He saw that."));
        }
    }

    @SubscribeEvent
    public static void onVillagerAttack(LivingAttackEvent event) {
        if(!(event.getEntity() instanceof Villager attackedVillager)) {
            return;
        }
        if (!(attackedVillager.getVillagerData().getProfession() == VillagerProfessions.ATTORNEY_GENERAL.get())) {
            return;
        }

        Entity trueSource = event.getSource().getEntity();
        if(!(trueSource instanceof ServerPlayer player)) {
            return;
        }
        if(player.gameMode.getGameModeForPlayer() == GameType.CREATIVE) {
            //return;
        }
        player.sendSystemMessage(Component.literal("why are u attacking the attorney general"));
        player.kill();
    }
}