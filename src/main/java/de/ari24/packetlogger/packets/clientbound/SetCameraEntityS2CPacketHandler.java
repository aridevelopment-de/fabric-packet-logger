package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.SetCameraEntityS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.SetCameraEntityS2CPacket;

public class SetCameraEntityS2CPacketHandler implements BasePacketHandler<SetCameraEntityS2CPacket> {
    @Override
    public String name() {
        return "SetCamera";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Camera";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", """
                Sets the entity that the player renders from. This is normally used when the player left-clicks an entity while in spectator mode.
                The player's camera will move with the entity and look where it is looking. The entity is often another player, but can be any type of entity. The player is unable to move this entity (move packets will act as if they are coming from the other entity).
                If the given entity is not loaded by the player, this packet is ignored. To return control to the player, send this packet with their entity ID.
                The Notchian server resets this (sends it back to the default entity) whenever the spectated entity is killed or the player sneaks, but only if they were spectating an entity. It also sends this packet whenever the player switches out of spectator mode (even if they weren't spectating an entity).\s
                """);
        jsonObject.addProperty("wikiVgNotes", """
                The notchian client also loads certain shaders for given entities:
                    Creeper -> shaders/post/creeper.json
                    Spider (and cave spider) -> shaders/post/spider.json
                    Enderman -> shaders/post/invert.json
                    Anything else -> the current shader is unloaded
                """);
        jsonObject.addProperty("entityId", "ID of the entity to spectate");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(SetCameraEntityS2CPacket packet) {
        SetCameraEntityS2CPacketAccessor accessor = (SetCameraEntityS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, accessor.getEntityId());
        return jsonObject;
    }
}
