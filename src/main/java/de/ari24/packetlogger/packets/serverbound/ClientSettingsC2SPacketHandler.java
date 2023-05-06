package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.ClientSettingsC2SPacket;

public class ClientSettingsC2SPacketHandler implements BasePacketHandler<ClientSettingsC2SPacket> {
    private JsonObject serializePlayerModelBitMask(int bitMask) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("cape", (bitMask & 0x01) != 0);
        jsonObject.addProperty("jacket", (bitMask & 0x02) != 0);
        jsonObject.addProperty("leftSleeve", (bitMask & 0x04) != 0);
        jsonObject.addProperty("rightSleeve", (bitMask & 0x08) != 0);
        jsonObject.addProperty("leftPantsLeg", (bitMask & 0x10) != 0);
        jsonObject.addProperty("rightPantsLeg", (bitMask & 0x20) != 0);
        jsonObject.addProperty("hat", (bitMask & 0x40) != 0);

        return jsonObject;
    }

    @Override
    public JsonObject serialize(ClientSettingsC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("locale", packet.language());
        jsonObject.addProperty("viewDistance", packet.viewDistance());
        jsonObject.addProperty("chatModeId", packet.chatVisibility().getId());
        jsonObject.addProperty("chatModeTranslation", packet.chatVisibility().getTranslationKey());
        jsonObject.addProperty("chatColors", packet.chatColors());
        jsonObject.addProperty("displayedSkinPartsMask", packet.playerModelBitMask());
        jsonObject.add("displayedSkinParts", serializePlayerModelBitMask(packet.playerModelBitMask()));
        jsonObject.addProperty("mainHandId", packet.mainArm().getId());
        jsonObject.addProperty("mainHandTranslation", packet.mainArm().getTranslationKey());
        jsonObject.addProperty("enableTextFiltering", packet.filterText());
        jsonObject.addProperty("allowServerListings", packet.allowsListing());
        return jsonObject;
    }
}
