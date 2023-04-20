package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.SetTradeOffersS2CPacket;
import net.minecraft.village.TradeOfferList;

import java.util.ArrayList;
import java.util.List;

public class SetTradeOffersS2CPacketHandler implements BasePacketHandler<SetTradeOffersS2CPacket> {


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
