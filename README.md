# Fabric Packet Logger

There weren't really any good fabric packet loggers out on Modrinth, so I decided to create my own one. I use this for development at [thejocraft.net](thejocraft.net)

## Features

- Web-UI (Port 8080)
- Websocket Connection (Port 1337)

## Websocket

By default, the web-ui connects to the websocket and receives the packet information via json data. You can also connect to this websocket as well and receive the same data as the web-ui. I'd recommend using [insomnia](https://insomnia.rest/) or [weasel](https://addons.mozilla.org/de/firefox/addon/websocket-weasel/) on firefox.

## Web-UI

I have a different React project locally which contains the code for the web-ui. I will probably port it to a single html file at some point as otherwise a http server is running parallel with minecraft. In the end, it's mainly a tool I developed for my own purpose, and it was never meant to be used by others.

Previews:

[![Preview](https://cdn.discordapp.com/attachments/598256161212596235/1092214455854694410/image.png)](https://google.com/)