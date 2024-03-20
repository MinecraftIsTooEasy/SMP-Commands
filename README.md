This is an addon programmed for BTW CE 2.1.4 that adds useful multiplayer commands.

Changelog v1.0:

   - Added the /spawn command that sends the user back to the original world spawn coordinates (only for admins right now)
    NOTE: doesn't transcend dimensions yet. Don't use in the nether if you don't want to get lost!

   - Added three new player-to-player teleportation commands: /tpa [playername], /tpaccept [playername], and /tpcancel
    These can be used without op permissions, allowing players to manage their own teleports without needing to call an admin

   - Added the /deaths command that shows players how many times they have hardcore spawned in a given world

   - Added the /smite [playername] command that allows admins to bring divine punishment down upon players

   - Added the /tpabove [playername] [# of blocks] command that allows admins to teleport above or below players (stealth spectating)
    Defaults to 20 blocks above target player

   - Added a new config file, SMPMod.properties, that lets server hosts disable the tpa commands

   - Added an "externalities" config that hosts can enable to punish players for using /tpaccept.
    CAUTION: Teleporting players will have their inventories wiped if Externalities is enabled!

