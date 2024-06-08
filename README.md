![Icon-Bild](resources/images/Title.png)

![Latest Release](https://img.shields.io/github/v/release/Dev7ex/FacilisCommon) ![SpigotMC Downloads](https://img.shields.io/spiget/downloads/107198?label=Downloads) ![Spiget Rating](https://img.shields.io/spiget/rating/107198?label=Rating&style=flat-square) ![Java](https://img.shields.io/badge/Java-17+-orange) ![Spigot](https://img.shields.io/badge/Spigot-1.16--1.20-red) [![CodeFactor](https://www.codefactor.io/repository/github/dev7ex/faciliscommon/badge)](https://www.codefactor.io/repository/github/dev7ex/faciliscommon) ![Last Commit](https://img.shields.io/github/last-commit/Dev7ex/FacilisCommon) ![GitHub](https://img.shields.io/github/license/dev7ex/faciliscommon) ![Discord](https://img.shields.io/discord/834580308543668264) ![Modrinth Followers](https://img.shields.io/modrinth/followers/multiworld-bukkit)

---

# Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Installation](#installation)
4. [Requirements](#requirements)
5. [Configuration](#configuration)
6. [Contributing](#contributing)
7. [License](#license)
8. [Contact](#contact)

---

# Overview

- Facilis comes from Latin and stands for ‘simple’. And that's exactly what this library is supposed to be.
- FacilisCommon should add as many things as possible that are missing in the BukkitAPI. The focus is on clean code and
  logical structure.
- Everyone is free to add things. But that doesn't mean that I accept everything blindly. I usually edit small things
  and then accept them.

# Features

- Own command system with annotations
- An extension of JavaPlugin.class called BukkitPlugin.class is available which adds useful methods
- Utility Classes like Worlds.java or Inventorys.java

# Installation

1. Download the latest version of `FacilisCommon`
   from [GitHub Releases](https://github.com/Dev7ex/FacilisCommon/releases).
2. Copy the downloaded `.jar` file into the `plugins` directory of your Spigot server.
3. Restart the server to activate the plugin.

# Requirements

- Minecraft Version: 1.16 - 1.20
- Java Version: 17 or higher
- Spigot Server

# Configuration

- After installation, a configuration file will be created in the `plugins/FacilisCommon` directory. Here, you can make
  various settings.

<details>
<summary>config.yml</summary>

```yaml
#  ______         _ _ _      _____
# |  ____|       (_) (_)    / ____|
# | |__ __ _  ___ _| |_ ___| |     ___  _ __ ___  _ __ ___   ___  _ __
# |  __/ _` |/ __| | | / __| |    / _ \| '_ ` _ \| '_ ` _ \ / _ \| '_ \
# | | | (_| | (__| | | \__ \ |___| (_) | | | | | | | | | | | (_) | | | |
# |_|  \__,_|\___|_|_|_|___/\_____\___/|_| |_| |_|_| |_| |_|\___/|_| |_|
#
# Copyright (c) 2023 by Dev7ex
# Version: ${project.version}
# Software: Your Software
config-version: ${project.version}
# General
prefix: '§8[§cFacilisCommon§8]§r'
no-permission: '§cIm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that is in error.'
```

</details>

# Contributing

We welcome contributions to FacilisCommon! If you'd like to contribute, please follow these guidelines:

1. Fork the repository and clone it to your local machine.
2. Create a new branch for your feature or bug fix.
3. Make your changes and ensure the code passes any existing tests.
4. Commit your changes and push them to your fork.
5. Submit a pull request, explaining the changes you've made and why they should be merged.
6. Ensure your pull request adheres to the code style and guidelines of the project.

Thank you for contributing to FacilisCommon!

# License

The FacilisCommon project is licensed under the GNU General Public License v3.0. See the [LICENSE](LICENSE) file for
details.

# Contact

If you have any questions or need support, you can reach out to Dev7ex via:

- Twitter: [@Dev7ex](https://twitter.com/Dev7ex)
- Discord: [Dev7ex's Discord Server](https://discord.gg/ta33bbA8eF)
