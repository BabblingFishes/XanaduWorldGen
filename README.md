Xanadu World Gen is a mod for Minecraft 1.14.4 for use with [Forge](https://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.14.4.html). It raises the world generation and sea level from the default altitude (64) to 200 to make more room for caves and underground builds.

This mod is safe for server-side use; clients can connect to it even if they don't have the mod. It is designed to be compatible with [Yung's Better Caves](https://www.curseforge.com/minecraft/mc-mods/yungs-better-caves).

To use on singleplayer, make a new world and change the world type to "Xanadu".

To use on a server, add the following line to server.properties:
`use-modded-worldtype=xanadu`

##Known issues:
* Biome amplitude is still a little weird. Flatter biomes like forests may seem more hilly than normal, while more extreme biomes like mountains are now much flatter.
* Caves still generate at the default altitude (with the exception of Yung's Better Caves, which spawn at whatever altitude you configure them to).
* Some mobs (mostly squids & slimes, possibly some others) still spawn at the default altitude or not at all.
* Some structures (ocean monuments & desert temples) still spawn at the default altitude.
* Desert underground consists mostly of sandstone
* Swamp shores are not mottled at sea level.
* Lilypads spawn significantly less frequently.
* Biomes that freeze at high altitudes (like mountains) are _always_ frozen.
* The vanilla fog and horizon levels are still at the default altitude for any unmodded client connecting to a modded server. (There is no way to fix this unless the client also installs the mod.)
* Ore generation is increased on all worldtypes.