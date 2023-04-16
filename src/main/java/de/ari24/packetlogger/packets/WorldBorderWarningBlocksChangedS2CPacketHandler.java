package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.WorldBorderWarningBlocksChangedS2CPacket;

public class WorldBorderWarningBlocksChangedS2CPacketHandler implements BasePacketHandler<WorldBorderWarningBlocksChangedS2CPacket> {

    @Override
    public String name() {
        return "SetBorderWarningDistance";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Warning_Distance";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sets the border warning distance from the border. If the player is inside that distance, the client will display a red vignette in the hud.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("warningBlocks", "The distance from the border at which the client will display a red vignette in the hud.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(WorldBorderWarningBlocksChangedS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("warningBlocks", packet.getWarningBlocks());
        return jsonObject;
    }
}
