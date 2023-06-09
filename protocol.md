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
    PACKETLOGGER_LOGSTATE,
    MC_PACKET_RECEIVED,
    MC_PACKET_SENT,
    REQUEST_MC_PACKET_INFO,
    MC_PACKET_INFO,
    REQUEST_CLEAR,
    REQUEST_EXPORT,
    WHITE_BLACK_LIST_CONFIRM,
    WHITE_BLACK_LIST_CHANGE
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

## ``mc_packet_received``

[//]: # (Lets go for some raw byte data in the future. See Blob#arrayBuffer and Uint8Array)

``[packet id, unix milli-timestamp, unique index, networkstate, direction]``

- Unique index will be used for later referencing through the ``request_mc_packet_info`` packet
- NetworkState is the network state of the packet. See enum below
- Direction is the direction of the packet. 0 = client -> server, 1 = server -> client

````json
{
  "id": 1,
  "data": [[0, 1234567890, 9, 0, 0], [1, 123456790, 10, 0, 0]]
}
````

Networkstate Enum:

````java
public enum NetworkState {
    HANDSHAKING,
    PLAY,
    STATUS,
    LOGIN
}
````

## ``mc_packet_sent``

``[packet id, unix milli-timestamp, unique index, networkstate, direction]``

- Unique index will be used for later referencing through the ``request_mc_packet_info`` packet
- NetworkState is the network state of the packet. See enum below
- Direction is the direction of the packet. 0 = client -> server, 1 = server -> client

````json
{
  "id": 2,
  "data": [[0, 1234567890, 9, 0, 0], [1, 123456790, 10, 0, 0]]
}
````

Networkstate Enum:

````java
public enum NetworkState {
    HANDSHAKING,
    PLAY,
    STATUS,
    LOGIN
}
````

## ``mc_packet_info``

````json
{
  "id": 4,
  "data": {
    "index": 0,
    "packetData": {
      "reset": true,
      "advancementMapping": [
        "..."
      ],
      "toRemove": [
        "..."
      ],
      "...": "..."
    }
  }
}
````

## ``white_black_list_confirm``

Confirms the state of the white/blacklist. Will be sent after each white_black_list_change packet.
White/Blacklist entry is given in the following format: ``(cbound|sbound)-(play|login|status|handshake)-0x[0-9a-f]{2}``


````json
{
  "id": 7,
  "data": {
    "whitelist": ["cbound-play-0x01", "sbound-play-0x02", "..."],
    "blacklist": ["cbound-play-0x03", "sbound-play-0x04", "..."]
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

## ``request_mc_packet_info``

*Data specifies an index given by mc_packet_received or mc_packet_sent packet*

````json
{
  "id": 3,
  "data": 1
}
````

## ``request_clear``

*Tells the client to clear all stored packets*

`````json
{
  "id": 5,
  "data": null
}
`````

## ``request_export``

*Tells the client to export all stored packets. If whitelist is not empty, only packets in the whitelist shall be included. If blacklist is not empty, all packets in the blacklist shall be excluded*

`````json
{
  "id": 6
}
`````

## ``white_black_list_change``

Will be sent whenever the frontend changes the whitelist/blacklist. MC Client responds with a ``white_black_list_confirm`` packet containing updated white/blacklist data.

````json
{
  "id": 8,
  "data": {
    "whitelist": ["cbound-play-0x01", "sbound-play-0x02", "..."],
    "blacklist": ["cbound-play-0x03", "sbound-play-0x04", "..."]
  }
}
````