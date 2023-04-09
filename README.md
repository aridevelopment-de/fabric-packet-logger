# Fabric Packet Logger

I use this for development at [thejocraft.net](https://thejocraft.net). Yes, the code may not be the best. If you want to improve it, do it and open a pull request.

## Features

- Web-UI (Default Port 8080)
- Websocket Connection (Default Port 1337)
- InGame config
- TODO: InGame packet history viewer

## Web-ui

Now located at [aridevelopment-de/fabric-packet-logger-frontend](https://github.com/aridevelopment-de/fabric-packet-logger-frontend).

## Commands

- ``/packetlogger`` is the base command for all subcommands. You should be able to auto-complete them
- ``/packetlogger toggleLogging`` - toggles logging
- ``/packetlogger open`` - opens the web-ui

## Supported packets

![100%](https://progress-bar.dev/100?title=All%20Play%20Packets%20(110/110))  
![100%](https://progress-bar.dev/100?title=All%20Status%20Packets%20(2/2))  
![100%](https://progress-bar.dev/100?title=All%20Login%20Packets%20(5/5))  

As developing serializers for each packet takes a lot of time, not every packet is supported. I will add more packets over time. If you want to help out, feel free to open a pull request.

<!-- console.log(a.map((data) => `<li><code>${data.value} (${data.label})</code></li>`).sort().join("\n")) -->


## Why do you not use Reflections/... to convert the packet data?

Mainly because I want to learn about packets themselves. Also, I think serializing each packet itself can help in the process of filtering the correct data and thus making it better for developers to understand. It also allows me to provide fallback values / filtering if certain data is missing.

I may use Reflections/VarHandles in the future tho. It depends on the features I want to implement.

## What are these ids?

The names you see in the web-ui are the names taken from wiki.vg. This provides much faster navigation to the correct packet data. If you see a question mark after the packet, that means I'm not sure if the wiki.vg entry and the packet match.

## Websocket

By default, the web-ui connects to the websocket and receives the packet information via json data. You can also connect to this websocket as well and receive the same data as the web-ui. I'd recommend using [insomnia](https://insomnia.rest/) or [weasel](https://addons.mozilla.org/de/firefox/addon/websocket-weasel/) on firefox.

## Previews:

Video:  
<div align="left">
      <a href="https://www.youtube.com/watch?v=gqOpIm2WfJg">
         <img src="https://img.youtube.com/vi/gqOpIm2WfJg/0.jpg" style="width:100%;">
      </a>
</div>

MapUpdateAdapter:
![https://cdn.discordapp.com/attachments/598256161212596235/1093635306244280431/image.png](https://cdn.discordapp.com/attachments/598256161212596235/1093635306244280431/image.png)
![https://cdn.discordapp.com/attachments/598256161212596235/1093668040752762920/image.png](https://cdn.discordapp.com/attachments/598256161212596235/1093668040752762920/image.png)
