package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class PlayerListS2CPacketHandler implements BasePacketHandler<PlayerListS2CPacket> {
    @Override
    public String id() {
        return "PlayerInfoUpdate";
    }

    @Override
    public JsonObject serialize(PlayerListS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();;
        jsonObject.add("actions", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getActions()));

        List<JsonObject> entries = new ArrayList<>();
        for (PlayerListS2CPacket.Entry entry : packet.getEntries()) {
            JsonObject child = new JsonObject();
            child.addProperty("uuid", entry.profileId().toString());
            child.add("gameProfile", ConvertUtils.serializeGameProfile(entry.profile()));
            child.addProperty("isListed", entry.listed());
            child.addProperty("latency", entry.latency());
            child.addProperty("gameMode", entry.gameMode().getName());

            if (entry.displayName() != null) {
                child.addProperty("displayName", entry.displayName().toString());
            }

            if (entry.chatSession() != null) {
                JsonObject sessionObject = new JsonObject();
                sessionObject.addProperty("id", entry.chatSession().sessionId().toString());
                sessionObject.addProperty("publicKeyExpiresAt", entry.chatSession().publicKeyData().expiresAt().toString());
                sessionObject.add("publicKey", ConvertUtils.GSON_INSTANCE.toJsonTree(entry.chatSession().publicKeyData().key().getEncoded()));
                sessionObject.add("publicKeySignature", ConvertUtils.GSON_INSTANCE.toJsonTree(entry.chatSession().publicKeyData().keySignature()));
                child.add("chatSession", sessionObject);
            }

            entries.add(child);
        }

        jsonObject.add("entries", ConvertUtils.GSON_INSTANCE.toJsonTree(entries));
        return jsonObject;
    }
}
