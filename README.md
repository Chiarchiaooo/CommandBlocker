[licenseImg]: https://img.shields.io/badge/License-MIT-important
[license]: https://github.com/Chiarchiaooo/CommandBlocker/blob/master/LICENSE
[mcversionImg]: https://img.shields.io/badge/MC%20Version-1.19x-success
[mcversion]: https://www.spigotmc.org/resources/command-blocker.99602/
[releaseImg]: https://img.shields.io/badge/Version-3.0-blue
[release]: https://github.com/Chiarchiaooo/CommandBlocker/releases/latest

# CommandBlocker
Simple Command Blocking plugin<br>
Made for 1.13x -> 1.19x servers
<br>

[![releaseImg]][release] ![mcversionImg] [![licenseImg]][license]


<br>

## Support

There are 3 possible ways to recieve support:
 * Open a <a href=https://github.com/Chiarchiaooo/CommandBlocker/issues> issue </a>
 * Joining my <a href=https://dsc.gg/cliffycommunity>discord</a> and going on the **help section** after verifying
 * DM Me to **~Luke#7123** or <a href=https://telegram.me/Ciarcia>**@Ciarcia**</a>

## Features

* **Command whitelist**: _admins can define what commands other users can execute in the config file_

* **Permission blocked commands**: _admins can define commands that can be executed if the user has a specific permission: cmdblock.bypass.\<command\>_

* **Permission groups blocked commands**: _admins can define groups with specific permissions and commands executable and viewable only by those beloning to that group (Ex:
cmdblock.bypass.group.admin)_

* **Anti-TabComplete**: _non-whitelisted commands are removed from the player commands suggestions list
(need to rejoin to apply)_

* **Reloadable/Resettable config file**: _possibility to reload and reset the config without the use of plugman, /reload or manual action_

* **Customizability**, _admins can define in the config file, like:_<br>
 \- _Message prefix: (Ex: MCServer >)_<br>
 \- _Blocked command msg: message sent to player when he tries to execute a blocked command_<br>
 \- _Avaiable placeholders (with papi support): %prefix% (message prefix), %player% (player name), %command% (blocked command)_<br>

* **1.13x -> 1.19x support**

<br>

## Commands
<br>

### Commands
| Command | Description | Permission | Aliases |
| --------------- | ---------------- | ---------------- | ---------------- |
| /cmdblock | Shows plugin info | None | None |
| /cmdblock help | Shows plugin help msg | cmdblock.help.command | None |
| /cmdblock reload | Reload Plugin configs | cmdblock.reload.command | None |
| /cmdblock reset | Resets plugin config file | cmdblock.reset.command | None |

<br><br>
### Permissions

| Permission | Description |
| --------------- | ---------------- |
| cmdblock.bypass.group.\<group-name\> | Group specific permission
| cmdblock.bypass.* | General bypass permission
| cmdblock.bypass.\<command\> | Single command bypass permission


## Config File
| File Name  | Link |
| ---------- | ---- |
| config.yml | https://github.com/Chiarchiaooo/CommandBlocker/blob/master/src/main/resources/config.yml |

## Donations

If you like my content and you want to support what I do, consider donating to <a href='https://ko-fi.com/U7U59S2LZ'>my ko-fi page</a>. <br>
#### Thank you❤️
[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/U7U59S2LZ)

## Rating

You can also check out the plugin on <a href=https://www.spigotmc.org/resources/command-blocker.99602//>spigotmc.org</a><br><br>
