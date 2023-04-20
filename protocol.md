# Websocket protocol

*The word "packet" refers to the data being sent over the websocket connection and not minecraft packets*  
Format: JSON

## Base Packet Structure

````json
{
  "id": "packet_id (integer)",
  "data": "anything (any type)"
}
````

Where ``packet_id`` is one of the following enum entries

````ts
enum PacketId {
    PACKETLOGGER_LOGSTATE = 0,
    MC_PACKET_METADATA = 1,
    MC_PACKET_RECEIVED = 2,
    MC_PACKET_SENT = 3,
    REQUEST_MC_PACKET_METADATA = 4
}
````

# Client -> Web

## ``packetlogger_logstate``

Confirms / Changes the state of the packetlogger

````json
{
  "id": 0,
  "data": 0
}
````

Enum being used for future uses. Values correspond to the following enum:

````ts
enum LogState {
    OFF = 0,
    LOGGING = 1
}
````

## ``mc_packet_metadata``

*Example data*

````json
{
  "id": 1,
  "data": {
    "clientbound": {
        "0x00": {
          "name": "UpdateAdvancements",
          "url": "https://wiki.vg/Protocol#Update_Advancements",
          "fields": {
            "general": "No description available",
            "wikiVgNotes": null,
            "reset": "...",
            "advancementMapping": "...",
            "toRemove": "...",
            "...": "..."
          }
        }
    },
    "serverbound": [
      "..."
    ]
  }
}
````

## ``mc_packet_received``

Field packetIds include packet id and unix timestamp (in milliseconds)

````json
{
  "id": 2,
  "data": {
    "packetIds": [["0x00", 1234567890], ["0x01", 123456790]]
  }
}
````

## ``mc_packet_sent``

Field packetIds include packet id and unix timestamp (in milliseconds)

````json
{
  "id": 3,
  "data": {
    "packetId": [["0x00", 1234567890], ["0x01", 123456790]]
  }
}
````

# Web -> Client

## ``packetlogger_logstate``

*Uses same packet structure as client -> web packetlogger_logstate packet*

````json
{
  "id": 0,
  "data": 0
}
````

## ``request_mc_packet_metadata``

````json
{
    "id": 4,
    "data": null
}
````