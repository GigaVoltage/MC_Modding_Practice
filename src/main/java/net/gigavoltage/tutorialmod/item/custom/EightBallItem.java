package net.gigavoltage.tutorialmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class EightBallItem extends Item {
    public EightBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            // Output a Random Number
            outputRandomAnswer(player);
            //Set a Cooldown
            player.getCooldowns().addCooldown(this, 20);
        }


        return super.use(level, player, hand);
    }

    private void outputRandomAnswer(Player player) {
        String answer = "";
        int randomInt = getRandomNumber();

        switch (randomInt) {
            case 0:
                answer += "It is certain";
                break;
            case 1:
                answer += "It is decidedly so";
                break;
            case 2:
                answer += "Without a doubt";
                break;
            case 3:
                answer += "Reply hazy, try again";
                break;
            case 4:
                answer += "Ask again later";
                break;
            case 5:
                answer += "Better not tell you now";
                break;
            case 6:
                answer += "Don't count on it";
                break;
            case 7:
                answer += "My reply is no";
                break;
            case 8:
                answer += "My sources say no";
                break;

        }

        player.sendSystemMessage(Component.literal(answer));
    }

    private int getRandomNumber() {
        return RandomSource.createNewThreadLocalInstance().nextInt(9);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Right click to get an yes/no/maybe answer").withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.literal("Press Shift for more info").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
