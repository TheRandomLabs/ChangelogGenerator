# ChangelogGenerator

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

![Build](https://github.com/TheRandomLabs/ChangelogGenerator/workflows/Build/badge.svg?branch=master)
[![Dependabot](https://badgen.net/dependabot/TheRandomLabs/ChangelogGenerator/?icon=dependabot)](https://dependabot.com/)

[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/TheRandomLabs/ChangelogGenerator.svg)](http://isitmaintained.com/project/TheRandomLabs/ChangelogGenerator "Average time to resolve an issue")

<!-- [![Maven Central](https://img.shields.io/maven-central/v/com.therandomlabs.ggenerator/changeloggenerator.svg?style=shield)](https://maven-badges.herokuapp.com/maven-central/com.therandomlabs.changeloggenerator/changeloggenerator/)

[comment]: # [![Javadoc](https://javadoc.io/badge/com.therandomlabs.changeloggenerator/changeloggenerator.svg?color=blue)](https://javadoc.io/doc/com.therandomlabs.changeloggenerator/changeloggenerator)-->

Generates changelogs for CurseForge modpacks.

All public-facing code is documented with Javadoc and (mostly) tested with JUnit.

## How to use?
1. Put the `manifest.json` from your old version in a folder and rename it to `old.json`.
2. Put the `manifest.json` from your new version in the same folder and rename it to `new.json`.
3. Execute the `ChangelogGenerator.jar` by double-clicking it in the same folder.
4. Wait for the `changelog.txt`.
