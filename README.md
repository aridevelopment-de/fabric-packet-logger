# Fabric Packet Logger

I use this for development at [thejocraft.net](https://thejocraft.net). Yes, the code may not be the best. If you want to improve it, do it and open a pull request.

## Features

- Web-UI (Default Port 8080)
- Websocket Connection (Default Port 1337)
- InGame config

## Supported Minecraft Versions

| Minecraft Version | Status |
|-------------------|------|
| 1.19.3 | ⚠️   |
| 1.19.4 | ✔️     |

⚠️ = Packets implemented but not validated  
✔️ = Supported

## Linked repositories

- Web-UI: [aridevelopment-de/fabric-packet-logger-frontend](https://github.com/aridevelopment-de/fabric-packet-logger-frontend)
- Metadata: [aridevelopment-de/fabric-packet-logger-metadata](https://github.com/aridevelopment-de/fabric-packet-logger-metadata)

## Commands

- ``/packetlogger`` is the base command for all subcommands. You should be able to auto-complete them
- ``/packetlogger logging on/off`` - Toggles the logging state
- ``/packetlogger open`` - Opens the web-ui in your browser

## Supported packets

![100%](https://progress-bar.dev/100?title=All%20Play%20Packets%20(110/110))  
![100%](https://progress-bar.dev/100?title=All%20Status%20Packets%20(2/2))  
![100%](https://progress-bar.dev/100?title=All%20Login%20Packets%20(5/5))

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
traffic. See metadata.md for more information.

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
