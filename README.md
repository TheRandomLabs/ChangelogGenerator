# ChangelogGenerator

Uses [CurseAPI-Minecraft](https://github.com/TheRandomLabs/CurseAPI-Minecraft) to generate changelogs.

It takes two files as input - `old.json` and `new.json`. `old.json` is the `manifest.json` of the
old modpack version, and `new.json` is the `manifest.json` of the new modpack version.

ChangelogGenerator generates two files - `changelog.txt` and `shortchangelog.txt`.
`shortchangelog.txt` only contains URLs to changelogs, while `changelog.txt` incorporates
full changelogs.

If you want to use different files as input, you can use something like this:

	java -jar ChangelogGenerator.jar oldmanifest.json manifest.json

Obviously it isn't perfect, but I tried to add as much mod compatibility as possible
(CoFH's mods, mezz's mods, etc.).

There is also a changelog history mode, which takes one argument - a project ID or slug.
For example:

	java -jar ChangelogGenerator.jar enigmatica2expert

This will create a full changelog history for Enigmatica 2 Expert and write it to
`changeloghistory.txt`.

Because there's no official API for retrieving changelogs, there can be crashes when things change.
I usually fix these problems quickly. You may also see repeated errors while trying to access file
URLs. This is because there's no way to tell whether a file is archived or if it is just a random
error, so you can usually ignore it.

CurseMeta is faster than retrieving changelogs directly from HTML, however, it is not guaranteed to
work, so it is disabled by default. To enable it, put `curseMeta` somewhere in the arguments.

I still haven't implemented proper command-line argument parsing, and ChangelogGenerator does not yet
work with 1.13+ modpacks.

To use this with Gradle:

	compile "com.github.TheRandomLabs:ChangelogGenerator:master-SNAPSHOT"

Example changelogs can be found at changelog.txt and shortchangelog.txt in this repository.

Planned features:
* Better mezz support
* Proper command-line arguments
