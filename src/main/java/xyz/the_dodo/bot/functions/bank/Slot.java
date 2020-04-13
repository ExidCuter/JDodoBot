package xyz.the_dodo.bot.functions.bank;

import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

import java.util.concurrent.ThreadLocalRandom;

@BotService(command = "slot", description = "Play the slots!!", usage = "slot <AMOUNT>", category = CommandCategoryEnum.BANK)
public class Slot extends IFunction {
    private final String[] slots = {":tangerine:", ":cookie:", ":heart:", ":floppy_disk:", ":minidisc:", ":cd:", ":film_frames:"};

    public Slot(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Double amount;
        BankAccount bankAccount;
        StringBuilder out = new StringBuilder();
        StringBuilder endGame = new StringBuilder();
        MessageChannel messageChannel = messageParams.getTextChannel();
        int[][] slotMachine = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};

        if (messageParams.getParameters().length == 1) {
            try {
                amount = Double.parseDouble(messageParams.getParameters()[0]);
            } catch (Exception e) {
                amount = null;
            }

            if (amount != null && amount > 0) {
                if (BankUtils.bankAccountExists(messageParams.getUser())) {
                    bankAccount = BankUtils.bankService.findByUserDiscordId(messageParams.getUser().getId());

                    if (bankAccount.getBalance() >= amount) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                slotMachine[i][j] = ThreadLocalRandom.current().nextInt(0, slots.length - 1);
                            }

                            endGame.append(slots[slotMachine[i][0]])
                                    .append(" ")
                                    .append(slots[slotMachine[i][1]])
                                    .append(" ")
                                    .append(slots[slotMachine[i][2]])
                                    .append(i == 1 ? " :arrow_left:\n" : "\n");
                        }

                        messageChannel.sendMessage(endGame.toString()).queue();

                        if (slotMachine[1][0] == slotMachine[1][1] && slotMachine[1][1] == slotMachine[1][2]) {
                            amount *= 3;
                            out.append("Jackpot. WOW! +3x!");
                        } else {
                            amount *= -1;
                            out.append("You lost. Better luck next time!");
                        }

                        out.append(" Winnings: ")
                                .append(amount)
                                .append("\n")
                                .append(bankAccount.getBalance())
                                .append("-->")
                                .append(bankAccount.getBalance() + amount)
                                .append(" ₪");

                        messageChannel.sendMessage(out.toString()).queue();

                        bankAccount.setBalance(bankAccount.getBalance() + amount);

                        BankUtils.bankService.save(bankAccount);
                    } else
                        messageChannel.sendMessage("You are too poor!").queue();
                } else {
                    messageChannel.sendMessage("You have to register a bank account!").queue();
                }
            } else {
                messageChannel.sendMessage("Can't slot that!").queue();
            }
        } else {
            messageChannel.sendMessage(getEmbededHelp().build()).queue();
        }
    }
}
