package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.SetTradeOffersS2CPacket;
import net.minecraft.village.TradeOfferList;

import java.util.ArrayList;
import java.util.List;

public class SetTradeOffersS2CPacketHandler implements BasePacketHandler<SetTradeOffersS2CPacket> {
    @Override
    public String name() {
        return "MerchantOffers";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Merchant_Offers";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "The list of trades a villager NPC is offering. ");
        jsonObject.addProperty("wikiVgNotes", "Modifiers can increase or decrease the number of items for the first input slot. The second input slot and the output slot never change the nubmer of items. The number of items may never be less than 1, and never more than the stack size. If special price and demand are both zero, only the default price is displayed. If either is non-zero, then the adjusted price is displayed next to the crossed-out default price. The adjusted prices is calculated as follows: Adjusted price = default price + floor(default price x multiplier x demand) + special price ");
        jsonObject.addProperty("windowId", "Id of the opened window");
        jsonObject.addProperty("size", "The number of trades");
        jsonObject.addProperty("trades.inputItem1", "The first item the player has to supply for this villager trade. The count of the item stack is the default \"price\" of this trade");
        jsonObject.addProperty("trades.inputItem2", "The second item the player has to supply for this villager trade. May be an empty slot. ");
        jsonObject.addProperty("trades.outputItem", "The item the player will receive from this villager trade.");
        jsonObject.addProperty("trades.tradeDisabled", "");
        jsonObject.addProperty("trades.numTradeUses", "Number of times the trade has been used so far. If equal to the maximum number of trades, the client will display a red X");
        jsonObject.addProperty("trades.maxTradeUses", "");
        jsonObject.addProperty("trades.xp", "Amount of XP the villager will earn each time the trade is used");
        jsonObject.addProperty("trades.specialPrice", "Can be zero or negative. The number is added to the price when an item is discounted due to player reputation or other effects");
        jsonObject.addProperty("trades.priceMultiplier", "Can be low (0.05) or high (0.2). Determines how much demand, player reputation, and temporary effects will adjust the price.");
        jsonObject.addProperty("trades.demand", "If positive, causes the price to increase. Negative values seem to be treated the same as zero"); // TODO: Are they really treated as 0?
        jsonObject.addProperty("villagerLevel", "Appears on the trade GUI; meaning comes from the translation key merchant.level.<level>");
        jsonObject.addProperty("totalExperience", "Total experience for this villager (always 0 for the wandering trader)");
        jsonObject.addProperty("isRegularVillager", "True if this is a regular villager; false for the wandering trader. When false, hides the villager level and some other GUI elements");  // TODO: Which gui elements?
        jsonObject.addProperty("canRestock", "True for regular villagers and false for the wandering trader. If true, the \"Villagers restock up to two times per day.\" message is displayed when hovering over disabled trades.");
        return jsonObject;
    }

    private List<JsonObject> serializeTradeOffers(TradeOfferList recipes) {
        List<JsonObject> list = new ArrayList<>();

        recipes.forEach(offer -> {
            JsonObject child = new JsonObject();
            child.add("inputItem1", ConvertUtils.serializeItemStack(offer.getAdjustedFirstBuyItem()));
            child.add("inputItem2", ConvertUtils.serializeItemStack(offer.getSecondBuyItem()));
            child.add("outputItem", ConvertUtils.serializeItemStack(offer.getSellItem()));
            child.addProperty("tradeDisabled", offer.isDisabled());
            child.addProperty("numTradeUses", offer.getUses());
            child.addProperty("maxTradeUses", offer.getMaxUses());
            child.addProperty("xp", offer.getMerchantExperience());
            child.addProperty("specialPrice", offer.getSpecialPrice());
            child.addProperty("priceMultiplier", offer.getPriceMultiplier());
            child.addProperty("demand", offer.getDemandBonus());
            list.add(child);
        });

        return list;
    }

    @Override
    public JsonObject serialize(SetTradeOffersS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("size", packet.getOffers().size());
        jsonObject.add("trades", ConvertUtils.GSON_INSTANCE.toJsonTree(serializeTradeOffers(packet.getOffers())));
        jsonObject.addProperty("villagerLevel", packet.getLevelProgress());
        jsonObject.addProperty("totalExperience", packet.getExperience());
        jsonObject.addProperty("isRegularVillager", packet.isLeveled());
        jsonObject.addProperty("canRestock", packet.isRefreshable());
        return jsonObject;
    }
}
