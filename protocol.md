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
    MC_PACKET_RECEIVED = 2,
    MC_PACKET_SENT = 3,
    REQUEST_MC_PACKET_INFO = 5,
    MC_PACKET_INFO = 6
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

Field packetIds include packet id, unix timestamp (milliseconds) and a unique index for later referencing

````json
{
  "id": 2,
  "data": {
    "packetIds": [[0, 1234567890, 9], [1, 123456790, 10]]
  }
}
````

## ``mc_packet_sent``

Field packetIds include packet id, unix timestamp (milliseconds) and a unique index for later referencing

````json
{
  "id": 3,
  "data": {
    "packetIds": [[0, 1234567890, 9], [1, 123456790, 10]]
  }
}
````

## ``mc_packet_info``

````json
{
  "id": 6,
  "data": {
    "reset": true,
    "advancementMapping": ["..."],
    "toRemove": ["..."],
    "...": "..."
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
  "id": 5,
  "data": 1
}
````