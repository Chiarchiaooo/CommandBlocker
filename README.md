[licenseImg]: https://img.shields.io/badge/License-MIT-important
[license]: https://github.com/Chiarchiaooo/CommandBlocker/blob/master/LICENSE
[mcversionImg]: https://img.shields.io/badge/MC%20Version-1.19x-success
[mcversion]: https://www.spigotmc.org/resources/command-blocker.99602/
[releaseImg]: https://img.shields.io/badge/Version-2.0-blue
[release]: https://github.com/Chiarchiaooo/CommandBlocker/releases/latest

# CommandBlocker
Simple Command Blocking plugin<br>
Made for 1.19x servers
<br>

[![releaseImg]][release] ![mcversionImg] [![licenseImg]][license]


<br>

## Support

To get support open a issue on my <a href=https://github.com/Chiarchiaooo/CommandBlocker/issues> GitHub issue page </a> or join my <a href=https://dsc.gg/cliffycommunity>discord</a><br><br>

## Features

* Command whitelist: admins can define what commands other users can execute in the config file

* Permission blocked commands: admins can define commands that can be executed if the user has a specific permission: cmdblock.bypass.< command >"

* Permission groups blocked commands: admins can define groups with specific permissions and commands executable and viewable only by those beloning to that group (Ex:
cmdblock.bypass.group.admin)


* Anti-TabComplete: non-whitelisted commands are removed from the player commands suggestions list
(need to rejoin to apply)

* Customizability: admins can define in the config file:
- Message prefix: (like MCServer * )
- Blocked command msg: message sent to player when he tries to execute a blocked command
- Avaiable placeholders (with papi support): %prefix% (message prefix), %player% (player name), %command% (blocked command)

* 1.19 support

<br>

## Commands
<br>

### Commands
| Command | Description | Permission | Aliases |
| --------------- | ---------------- | ---------------- | ---------------- |
| /cmdblock | Shows plugin info | None | None |
| /cmdblock help | Shows plugin help msg | None | None |
| /cmdblock reload | Reload Plugin configs | cmdblock.reload | None |
| /cmdblock restart | Force restart the plugin | cmdblock.restart | None |

<br><br>
### Permissions

| Permission | Description |
| --------------- | ---------------- |
| cmdblock.bypass.group.< group-name > | Group specific permission
| cmdblock.bypass.* | General bypass permission
| cmdblock.bypass.< command > | Single command bypass permission


## Config File
| File Name  | Link |
| ---------- | ---- |
| config.yml | https://github.com/Chiarchiaooo/CommandBlocker/blob/master/src/main/resources/config.yml |

## Donations

If you like my content and you want to support what I do, consider donating to <a href='https://ko-fi.com/U7U59S2LZ'>my ko-fi page</a>. <br>
#### Thank you❤️
<a href='https://ko-fi.com/U7U59S2LZ' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://cdn.ko-fi.com/cdn/kofi1.png?v=3' border='0' alt='Donate at ko-fi.com' /></a>
<br><br>

## Rating

You can also check out the plugin on <a href=https://www.spigotmc.org/resources/command-blocker.99602//>spigotmc.org</a><br><br>
