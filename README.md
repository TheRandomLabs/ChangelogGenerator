# ChangelogGenerator
Uses [CurseAPI-Minecraft](https://github.com/TheRandomLabs/CurseAPI-Minecraft) (which uses [CurseAPI](https://github.com/TheRandomLabs/CurseAPI)) to generate changelogs.

It takes two files as input - `old.json` and `new.json`. `old.json` is the `manifest.json` of the old modpack version, and `new.json` is the `manifest.json` of the new modpack version.

ChangelogGenerator generates two files - `changelog.txt` and `shortchangelog.txt`. `shortchangelog.txt` only contains URLs to changelogs, while `changelog.txt` incorporates full changelogs.

If you want to use different files as input, you can do something like this:
	java -jar ChangelogGenerator.jar oldmanifest.json manifest.json

Obviously, it isn't perfect and might need some user input, but I tried to add as much mod compatibility as possible in CurseAPI-Minecraft, and it even supports TeamCoFH's GitHub changelogs.

To use this in Gradle:

	compile "com.github.TheRandomLabs:ChangelogGenerator:master-SNAPSHOT"

Example changelogs can be found at changelog.txt and shortchangelog.txt in this repository.
