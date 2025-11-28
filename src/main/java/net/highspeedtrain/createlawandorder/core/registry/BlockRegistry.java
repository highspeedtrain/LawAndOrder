package net.highspeedtrain.createlawandorder.core.registry;

import java.util.function.Supplier;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.content.block.CourtCoreBlock;
import net.highspeedtrain.createlawandorder.content.block.CourtTerminalBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, CreateLawAndOrder.MOD_ID);

    //
    public static final RegistryObject<Block> BLUE_BLOCK = registerBlock("blue_block", () -> new Block(
        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
    ));

    public static final RegistryObject<Block> COURT_TERMINAL = registerBlock("court_terminal", () -> new CourtTerminalBlock(
        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()
    ));

    public static final RegistryObject<Block> COURT_CORE = registerBlock("court_core", () -> new CourtCoreBlock(
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()
    ));
    //
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
