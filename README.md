# ⚠️ Project no longer maintained (and what happens with the frontend)

Since I don't have the time to work on the frontend and the backend, I will discontinue **this** PacketLogger project. But don't worry, the frontend won't be for nothing.  
Developing on both sides takes up a lot of time, especially if you want to implement cool new features on the frontend while the backend contains bugs that must be fixed as to aid users. It's been tough doing that at the same time and I do not wan't to do that anymore. I am mainly a web-developer and I want to continue this passion.  
That's why I will stop developing the mod.  
**But:** The frontend will be moved to [wisp-forest/gadget](https://github.com/wisp-forest/gadget/). At least that is the current idea. It might take up some weeks before everything is set in place, so I highly encourage you to switch over to gadget if you need an alternative atm.  
  
It has been a pleasent journey working on packetlogger. I learned much about minecraft modding in itself, minecraft packet structure and met wonderful people on the way. If you have some questions, hmu on [Twitter](https://twitter.com/@AriOnIce24) or [Discord](https://aridevelopment.de/dc)

# Fabric Packet Logger

I use this for development at [thejocraft.net](https://thejocraft.net). Yes, the code may not be the best. If you want to improve it, do it and open a pull request.

## Features

- Web-UI (Default Port 8080)
- Websocket Connection (Default Port 1337)
- InGame config

## Supported Minecraft Versions

| Minecraft Version | Status                       | Notes |
|-------------------|------------------------------| ----- |
| 1.19.3 | ⚠️ | A rather scuffed version, since 1.19.4 support existed before 1.19.3, so 1.19.3 was created out of 1.19.4 |
| 1.19.4 | ✔️                           | |

⚠️ = Packets implemented but not validated  
✔️ = Supported

## Linked repositories

- Web-UI: [aridevelopment-de/fabric-packet-logger-frontend](https://github.com/aridevelopment-de/fabric-packet-logger-frontend)
- Metadata: [aridevelopment-de/fabric-packet-logger-metadata](https://github.com/aridevelopment-de/fabric-packet-logger-metadata)

## Commands

- ``/packetlogger`` is the base command for all subcommands. You should be able to auto-complete them
- ``/packetlogger logging on/off`` - Toggles the logging state
- ``/packetlogger open`` - Opens the web-ui in your browser
- ``/packetlogger export`` - Exports the current stored data in a json format

## Supported packets

**Clientbound**  
![100%](https://progress-bar.dev/100?title=Play%20Packets%20(110/110))  
![100%](https://progress-bar.dev/100?title=Status%20Packets%20(2/2))  
![100%](https://progress-bar.dev/100?title=Login%20Packets%20(5/5))

**Serverbound**  
![100%](https://progress-bar.dev/100?title=Play%20Packets%20(51/51))  
![100%](https://progress-bar.dev/100?title=Status%20Packets%20(2/2))  
![100%](https://progress-bar.dev/100?title=Login%20Packets%20(3/3))  
![100%](https://progress-bar.dev/100?title=Handshaking%20Packets%20(1/1))

As developing serializers for each packet takes a lot of time, not every packet is supported. I will add more packets
over time. If you want to help out, feel free to open a pull request.

<!-- console.log(a.map((data) => `<li><code>${data.value} (${data.label})</code></li>`).sort().join("\n")) -->

## Why do you not use Reflection to convert the packet data?

There are some reasons why I didn't use Reflection. Here are the main reasons:

- Reflection is slow
    - I like live-streaming data instead of analyzing dumps
- My approach with reflection was horrible (see reflection-rewrite/dev branch)
- I may be able to do a lot more with custom serializers
- And I have my own juicy mappings

If you still prefer a reflection-based approach, check out [wisp-forest/gadget](https://github.com/wisp-forest/gadget/)

## Websocket

By default, the web-ui connects to the websocket and receives the packet information via json data. You can also connect
to this websocket as well and receive the same data as the web-ui. I'd recommend
using [insomnia](https://insomnia.rest/) or [weasel](https://addons.mozilla.org/de/firefox/addon/websocket-weasel/) on
firefox.  
A protocol has been defined for the websocket. It uses data chunking and lazy loading of packet information to reduce
traffic. See protocol.md for more information.

## Previews

Video:
<div align="left">
      <a href="https://www.youtube.com/watch?v=gqOpIm2WfJg">
         <img src="https://img.youtube.com/vi/gqOpIm2WfJg/0.jpg" style="width:100%;">
      </a>
</div>

MapUpdateAdapter:
![https://cdn.discordapp.com/attachments/598256161212596235/1093635306244280431/image.png](https://cdn.discordapp.com/attachments/598256161212596235/1093635306244280431/image.png)
![https://cdn.discordapp.com/attachments/598256161212596235/1093668040752762920/image.png](https://cdn.discordapp.com/attachments/598256161212596235/1093668040752762920/image.png)
