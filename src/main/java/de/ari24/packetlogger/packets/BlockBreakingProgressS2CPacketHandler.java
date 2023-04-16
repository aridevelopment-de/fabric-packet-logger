package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;

public class BlockBreakingProgressS2CPacketHandler implements BasePacketHandler<BlockBreakingProgressS2CPacket> {
    @Override
    public String name() {
        return "SetBlockDestroyStage";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Block_Destroy_Stage";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "0–9 are the displayable destroy stages and each other number means that there is no animation on this coordinate. Block break animations can still be applied on air; the animation will remain visible although there is no block being broken. However, if this is applied to a transparent block, odd graphical effects may happen, including water losing its transparency. (An effect similar to this can be seen in normal gameplay when breaking ice blocks) ");
        jsonObject.addProperty("wikiVgNotes", "If you need to display several break animations at the same time you have to give each of them a unique Entity ID. The entity ID does not need to correspond to an actual entity on the client. It is valid to use a randomly generated number.");
        jsonObject.addProperty("entityId", "The entity ID of the block break animation. This is used to identify the animation on the client. If you need to display several break animations at the same time you have to give each of them a unique Entity ID. The entity ID does not need to correspond to an actual entity on the client. It is valid to use a randomly generated number.");
        jsonObject.addProperty("location", "");
        jsonObject.addProperty("destroyStage", "0–9 to set it, any other value to remove it.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(BlockBreakingProgressS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        jsonObject.addProperty("location", packet.getPos().toString());
        jsonObject.addProperty("destroyStage", packet.getProgress());
        return jsonObject;
    }
}
