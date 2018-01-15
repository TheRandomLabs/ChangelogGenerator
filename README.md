# ChangelogGenerator
Uses [CurseAPI-Minecraft](https://github.com/TheRandomLabs/CurseAPI-Minecraft) (which uses [CurseAPI](https://github.com/TheRandomLabs/CurseAPI)) to generate changelogs.

It takes two files as input - `old.json` and `new.json`. `old.json` is the `manifest.json` of the old modpack version, and `new.json` is the `manifest.json` of the new modpack version.

If you want to use different files as input, you can do something like this:
	java -jar ChangelogGenerator.jar oldmanifest.json manifest.json

Obviously, it isn't perfect and might need some user input, but I tried to add as much mod compatibility as possible in CurseAPI-Minecraft, and it even supports TeamCoFH's GitHub changelogs.

Example changelog (a bit larger than usual, and looks even larger because of GitHub's formatting):

	FTB Presents Direwolf20 1.12 1.4.0 to FTB Presents Direwolf20 1.12 1.6.0

	Added:
		- Inventory Tweaks
		- Item Scroller
		- Placebo
		- Simply Jetpacks 2
		- TipTheScales

	Updated:
		Actually Additions (went from ActuallyAdditions-1.12.2-r125.jar to ActuallyAdditions-1.12.2-r126.jar):
			Changlog retrieved from GitHub:
				# 1.12.2-r126
				* New feature!  The atomic reconstructor can now split enchanted books with multiple enchantments!
				* Leomil72: Added it_IT.lang

		Applied Energistics 2 (went from appliedenergistics2-rv5-stable-3.jar to appliedenergistics2-rv5-stable-4.jar):
			appliedenergistics2-rv5-stable-4.jar:
				Feature #3259: Added a debug generator for ForgeEnergy. - yueh

				Feature #3284: Reworked IO Port to handle StorageChannels better. - yueh

				Fixes #3217: Allow caps to be used as P2P attunements. - yueh

				Fixes #3255: Cache invalid patterns instead computing them every frame. - yueh

				Fixes #3260: Only match damaged items when selected and set to 99%. - yueh

				Fixes #3265: Do not strip special chars when extracting recipes. - yueh

				Fixes #3282: Stacksizes > 64 prevent applicator from cycling. - yueh

				Fixes #3286: Incorrectly inverted hasSkyLight causes crashes on spatial transfer. - yueh

				Fixes #3293: Only update light level if the panel-facing block changes. - fscan

				Fixes #3296: Prevent invalid TileEntites from updating their block. - yueh

		Aroma1997Core (went from Aroma1997Core-1.12.2-2.0.0.0.b129.jar to Aroma1997Core-1.12.2-2.0.0.0.b135.jar):
			Aroma1997Core-1.12.2-2.0.0.0.b130.jar:
				N/A
			Aroma1997Core-1.12.2-2.0.0.0.b131.jar:
				N/A
			Aroma1997Core-1.12.2-2.0.0.0.b132.jar:
				N/A
			Aroma1997Core-1.12.2-2.0.0.0.b133.jar:
				N/A
			Aroma1997Core-1.12.2-2.0.0.0.b134.jar:
				N/A
			Aroma1997Core-1.12.2-2.0.0.0.b135.jar:
				N/A

		Baubles (went from Baubles-1.12-1.5.1.jar to Baubles-1.12-1.5.2.jar):
			Baubles-1.12-1.5.2.jar:
				- API: added isBaubleEquipped helper method to BaublesAPI

				- fixed player bauble syncing (Thanks pau101) closes #235

		BdLib (went from BDLib 1.14.3.10 (MC 1.12.2) to BDLib 1.14.3.12 (MC 1.12.2)):
			BDLib 1.14.3.11 (MC 1.12.2):
				bdew: Prevent HasTE from corrupting TE's of unrelated blocks if passed an
			BDLib 1.14.3.12 (MC 1.12.2):
				bdew: partially reverted the check in HasTE - causes issues in e.g. blockBreak

		Binnie's Mods (went from binnie-mods-1.12.2-2.5.0.106.jar to binnie-mods-1.12.2-2.5.0.111.jar):
			Changelog retrieved from binnie-mods-1.12.2-2.5.0.111.jar:
				Alpha version. Do not create a world you care about with this version, it may break things severely.
				Please help the project by trying it out and [reporting issues here.](https://github.com/ForestryMC/Binnie/issues)

				Current release 2.5.0.111

				 * Fix #331 Crash on launching when butterflies are disabled in forestry. - Nedelosk
				 * Validate ItemFieldKit to avoid metadata manipulations with items. (#375) - mezz
				 * Create & Update zh_CN.lang(s) (#376) - SihenZhang
				 * #347 Various issues with the brewery - mezz
				 * Fix #359 remove oredict "gearWood" for scented and proven gears - mezz
				 * Create ru_RU.lang for Binnie Genetics (#358) - Mr-Krab
				 * Create ru_RU.lang for Binnie Extrabees (#355) - Mr-Krab
				 * Create ru_RU.lang for Binnie Botany (#354) - Mr-Krab
				 * Create ru_RU.lang for Binnie Extratrees (#357) - Mr-Krab
				 * Create ru_RU.lang for Binnie Core (#353) - Mr-Krab
				 * Create ru_RU.lang for Binnie Design (#356) - Mr-Krab
				 * Make Binnie's stained glass non-flammable - mezz
				 * Update dependencies - mezz
				 * Fix #344 Networking issue with latest Forge - mezz
				 * Fix Ceramic Brick pick block - DrManganese
				 * Fix #350 Crash on dedicated server - mezz
				 * Fix localization error - mezz
				 * Remove unused imports - mezz
				 * Fix #345 Prevent shift-clicking into recipe slots - mezz
				 * Improve tooltips on machines, show some basic help Close #216 make the help key configurable - mezz
				 * Fix #343 TOPAddons shows 6 tanks for each real tank - mezz
				 * Fix ArrayIndexOutOfBoundsException on invalid soil metadata (#317) - Gamebuster
				 * Add Item Capability support to Alveary Frame Housing (#325) - mcenderdragon
				 * Fix #298 Most trees support apples when they shouldn't. - mezz
				 * Fix #294 Nerf fruit saturation - mezz
				 * Fix #340 Inoculate and Splicer cannot apply bee effect alleles - mezz
				 * Fix #341 Analyst turns tree pollen into sapling - mezz
				 * Fix #335 Fix lab stand, render items over some machines - mezz
				 * Fix #337 Breeding System Crash - mezz
				 * Create zh_CN.lang(s) (#334) - SihenZhang
				 * Expand accepted blocks for Redstone, Leaves, and Wood flower type bees - mezz
				 * Fix #336 Ecstatic Bee effect crashes - mezz
				 * Prevent 2-tall flowers from breaking blocks when grown - mezz
				 * Enable JEI lookup of items and fluids in all guis - mezz
				 * Fix automated extraction from machines pulling all items out - mezz
				 * Fix machine particles - mezz
				 * Fix crash from CraftTweaker ASM version issue - mezz

		Biomes O' Plenty (went from BiomesOPlenty-1.12.2-7.0.1.2310-universal.jar to BiomesOPlenty-1.12.2-7.0.1.2312-universal.jar):
			Changelog retrieved from BiomesOPlenty-1.12.2-7.0.1.2312-universal.jar:
				Build 1.12.2-7.0.1.2312:
				github:
				Revised and fixed version (pt-BR.lang)
				And, if the mod itself don't have any actualization, should be the final
				version

				Build 1.12.2-7.0.1.2311:
				github: Added pt-BR.lang File for 1.12.2

		Bookshelf (went from Bookshelf-1.12.2-2.2.500.jar to Bookshelf-1.12.2-2.3.518.jar):
			Bookshelf-1.12.2-2.2.501.jar:
				 * Added a util to check if two blocks are in the same chunk. - Tyler Hancock
				 * Added a hook for when tiles are removed. - Tyler Hancock
			Bookshelf-1.12.2-2.3.502.jar:
				 * Added some missing javadocs for IColorfulItem and ICustomMesh - Tyler Hancock
				 * Added a class for handling metadata food. - Tyler Hancock
				 * Ticked major version up. Backwards compat is no longer assured. - Tyler Hancock
				 * Added a default meta check for all variant items and blocks. - Tyler Hancock
			Bookshelf-1.12.2-2.3.503.jar:
				 * Fixed onTileRemoved implementation being mandatory. - Tyler Hancock
			Bookshelf-1.12.2-2.3.504.jar:
				No changelog provided
			Bookshelf-1.12.2-2.3.505.jar:
				 * Added support for code based recipes. - Tyler Hancock
			Bookshelf-1.12.2-2.3.506.jar:
				 * Fixed custom models hook not firing. - Tyler Hancock
			Bookshelf-1.12.2-2.3.507.jar:
				 * Actually fixed the custom model hook. - Tyler Hancock
			Bookshelf-1.12.2-2.3.508.jar:
				 * Added access transformers for some chunk generator things. - Tyler Hancock
			Bookshelf-1.12.2-2.3.509.jar:
				 * Fixed incorrect access transformer for world info. - Tyler Hancock
			Bookshelf-1.12.2-2.3.510.jar:
				 * Added a bunch of new world utils. - Tyler Hancock
			Bookshelf-1.12.2-2.3.511.jar:
				 * Fixed some duplicate items being added to the variants list. - Tyler Hancock
			Bookshelf-1.12.2-2.3.512.jar:
				 * Made fake player check more friendly. - Tyler Hancock
			Bookshelf-1.12.2-2.3.513.jar:
				 * Fixed biome change util using dirty scala arrays. Ew. - Tyler Hancock
			Bookshelf-1.12.2-2.3.514.jar:
				 * Added a reusable basic 16x16x16 layered json model. - Tyler Hancock
			Bookshelf-1.12.2-2.3.515.jar:
				 * Undo the changes to real player checks. - Tyler Hancock
			Bookshelf-1.12.2-2.3.516.jar:
				 * Removed uneeded sideonly annotations. - Tyler Hancock
			Bookshelf-1.12.2-2.3.518.jar:
				No changelog provided

		Brandon's Core (went from Brandon's Core 1.12-2.3.4.134-universal to Brandon's Core 1.12-2.3.7.140-universal):
			Brandon's Core 1.12-2.3.6.139-universal:
				No changelog provided
			Brandon's Core 1.12-2.3.7.140-universal:
				No changelog provided

		Chicken Chunks 1.8.+ (went from Chicken Chunks 1.12-2.4.0.70-universal to Chicken Chunks 1.12.2-2.4.1.71-universal):
			Chicken Chunks 1.12.2-2.4.1.71-universal:
				No changelog provided

		Chisel (went from Chisel - MC1.12-0.1.0.23 to Chisel - MC1.12-0.1.1.26):
			Chisel - MC1.12-0.1.1.24:
				Chisel MC1.12-0.1.1.24
				New

				- Default chisel durabilities were slightly buffed, to make them even multiples of 64. To get these new values, remove the durability configs from your config file.

				Fixes

				- [#594](https://github.com/Chisel-Team/Chisel/issues/594) Fix factory block recipe output being an empty stack
				- [#598](https://github.com/Chisel-Team/Chisel/issues/598) Fix oredict conversion groups only working one-way, it should now actually be possible to create chisel's ingot blocks!
				- [#606](https://github.com/Chisel-Team/Chisel/issues/606) Fix crash when in-world chiseling oredict variants
				- [#605](https://github.com/Chisel-Team/Chisel/issues/605) Fix factory blocks having the same recipe as Tyrian (Tyrian recipe was corrected)
				- Fix iron/diamond chisel incorrectly handling a chisel breaking partially through crafting a stack
				- Fix chisel sound playing twice in iChisel
			Chisel - MC1.12-0.1.1.26:
				- Fix issue where certain storage blocks (iron, lapis, etc) did not have an oredict name.

		CodeChicken Lib 1.8.+ (went from CodeChicken Lib 1.12-3.1.3.313-universal to CodeChicken Lib 1.12.2-3.1.5.331-universal):
			CodeChicken Lib 1.12.2-3.1.5.331-universal:
				No changelog provided

		CoFH Core (went from CoFHCore-1.12.2-4.3.7.25-universal.jar to CoFHCore-1.12.2-4.3.10.5-universal.jar):
			Changelog retrieved from GitHub:
				4.3.10:
				GENERAL:
					-Internal refactors and optimizations.

				4.3.9:
				FIXED:
					-Addressed a few potential but highly unlikely NPEs with raytracing.

				4.3.8:
				CHANGED:
					-The killall command no longer kills Item Frame Entities when "item" or "items" is provided as
						a parameter.

				GENERAL:
					-Backend work to support AOE Block Breaking and Filters.

		CoFH World (went from CoFHWorld-1.12.2-1.0.2.10-universal.jar to CoFHWorld-1.12.2-1.1.1.12-universal.jar):
			CoFHWorld-1.12.2-1.1.1.12-universal.jar:
				No changelog provided

		Compact Machines (went from compactmachines3-1.12.2-3.0.3-b169.jar to compactmachines3-1.12.2-3.0.5-b184.jar):
			compactmachines3-1.12.2-3.0.4-b182.jar:
				- Feature: Render TESRs and blocks with extended Blockstates in the machine view

				- Feature: Render Entities in the machine view

				- Feature: Let the player rotate the machine contents with drag n drop

				- Feature: Added OP command to generate recipes in world

				- Feature: Added visual feedback for OpenComputers connections

				- Fix: Properly block machine interaction after being placed inside itself

				- Fix: Do not mirror the contents in the machine view (phew! nobody noticed 😌 )

				- Fix: Multiblock Recipes not being detected properly

				- Fix: Redstone Tunnels changing in/output mode inadvertently when changing their face

				If you are having problems with the TESR/Entity rendering, this can be disabled client-side in the compact machines config file.
			compactmachines3-1.12.2-3.0.5-b184.jar:
				Bugfix-Release for versions >= 3.0.3:

				Fix client crash when placing TEs near already set up Compact Machines with a Tunnel bound to the side the TE is placed on.

		CraftTweaker (went from CraftTweaker2-1.12-4.0.10 to CraftTweaker2-1.12-4.0.12):
			CraftTweaker2-1.12-4.0.11:
				added recipe name to /ct recipes handAdded pow for Math.pow

				Made transformerRecipes public

				Improve performance of ActionRemoveRecipesNoIngredients (#384)

				Entity API refactor (#354)

				Global variables and Brewing Recipe Addition (#383)

				added recipe name to /ct recipes hand

				Changed how Globals work and DataMaps addition/substraction (#393)

				Added owner to get the modid of the mod that adds the item

				Made the recipenames command it's own command and actually work

				Test Project, string functions and array addition (#399)

				ZenNativeCasters and Better ArrayAdd (#406)

				Fixed oredict removal (#404)
			CraftTweaker2-1.12-4.0.12:
				Fixed oredict issues

		Cyclops Core (went from CyclopsCore-1.12.2-0.10.24.jar to CyclopsCore-1.12.2-0.11.0.jar):
			CyclopsCore-1.12.2-0.11.0.jar:
				As always, don't forget to backup your world before updating!

				Additions:
				* Add main menu button to create a dev world when in dev environment
				* Add support for doors

				Changes:
				* Break out getItemInstance() so not everything has to use Item.getItemFromBlock()
				* Let blocks' items be any Item, not just ItemBlock
				* Render fluid tanks in guis using the fluid's color

				Fixes:
				* Fix XML recipe oredict ingredients not adhering to stacksizes

		/dank/null (went from DankNull-1.12-1.0.14.jar to DankNull-1.12-1.3.29.jar):
			DankNull-1.12-1.2.18.jar:
				Implemented:

				[#18 - Sneak+Right-Click in SMP to place block](https://github.com/p455w0rd/DankNull/issues/18)

				[#20 - Added filtering on a per-item basis for the /dank/null Docking Station](https://github.com/p455w0rd/DankNull/issues/20)

				[#21- Added ability to access /dank/null GUI while there is a /dank/null in the Docking Station](https://github.com/p455w0rd/DankNull/issues/21)

				[#27 - Added ability to place blocks via /dank/null when it is in off hand](https://github.com/p455w0rd/DankNull/issues/27)

				[#34 - Made previous/next item selection keys configurable (used to be pg fwd/pg backward mouse buttons)](https://github.com/p455w0rd/DankNull/issues/34)

				[#37 - Got rid of chat spam and replaced with custom text display](https://github.com/p455w0rd/DankNull/issues/37)

				Fixed:

				[#25 - Fixed /dank/null items not having texture when used with certain Resource Packs/mods (Optifine and CTM specifically)](https://github.com/p455w0rd/DankNull/issues/25)

				[#28 - Fixed dupe bug](https://github.com/p455w0rd/DankNull/issues/28)

				[#29 - Fixed fluid buckets bug](https://github.com/p455w0rd/DankNull/issues/29)

				[#30 - Fixed placement issue when used in packs with FTBUtils](https://github.com/p455w0rd/DankNull/issues/30)

				[#35 - Fixed bug when /dank/null is in same pack as SwingThroughGlass](https://github.com/p455w0rd/DankNull/issues/35)

				[#36 - Added ability to remove /dank/null from docking station using either hand even if the other hand has an item](https://github.com/p455w0rd/DankNull/issues/36)
			DankNull-1.12-1.2.19.jar:
				Fixed [#41](https://github.com/p455w0rd/DankNull/issues/41) (Since I also moved recipes around a bit, forgot to move event registration to preinit)
			DankNull-1.12-1.3.23.jar:
				Fixed [#43](https://github.com/p455w0rd/DankNull/issues/43), [#50](https://github.com/p455w0rd/DankNull/issues/50), [#51](https://github.com/p455w0rd/DankNull/issues/51), [#52](https://github.com/p455w0rd/DankNull/issues/52)

				Removed FakePlayer ([#44](https://github.com/p455w0rd/DankNull/issues/44))
			DankNull-1.12-1.3.24.jar:
				Fixed server crash ([#54](https://github.com/p455w0rd/DankNull/issues/54))
			DankNull-1.12-1.3.27.jar:
				Fixed [#40](https://github.com/p455w0rd/DankNull/issues/40), [#48](https://github.com/p455w0rd/DankNull/issues/48), [#55](https://github.com/p455w0rd/DankNull/issues/55)

				Implemented [#56](https://github.com/p455w0rd/DankNull/issues/56), [#61](https://github.com/p455w0rd/DankNull/issues/61)

				Hopefully fixed [#57](https://github.com/p455w0rd/DankNull/issues/57) ..couldn't reproduce and couldn't find anyone to test, but made some adjustments...hope it works
			DankNull-1.12-1.3.29.jar:
				Fixed [#62](https://github.com/p455w0rd/DankNull/issues/62) - Creative /dank/null showing on wrong creative tabs

				Fixed [#63](https://github.com/p455w0rd/DankNull/issues/63) - No sound when placing blocks

		Dark Utilities (went from DarkUtils-1.12.2-1.7.181.jar to DarkUtils-1.12.2-1.8.190.jar):
			DarkUtils-1.12.2-1.7.182.jar:
				 * zh_CN update (#123) - Technician;Freak
			DarkUtils-1.12.2-1.8.185.jar:
				This is the first alpha for the 1.8 content update. Things may change, and more content is still planned.

				New

				 * Maim trap. Permanently lowers the max health of mobs on contact and will continue to do so until they have 1 heart left. Players and bosses are excluded from this and will take damage instead.
				 * Player trap. Similar to the damage trap, but all damage is from a player source.
				 * Monolith of Spawning. Overrides spawn checks in the same chunk, to allways allow the mob to spawn. Spawned mobs have 20% more health and attack damage.
				 * Monolith of Experience. Absorbs experience orbs in the same chunk as it. EXP can be pulled from the reclaimed from the monolith.
				 * Using wither dust on a skeleton will turn it into a wither skeleton.
				 * Dispensing wither dust will turn skeletons into wither skeletons.
				 * Pearl Ender Hopper. An upgrade to the old ender hopper that is instant, and doesn't spawn particles.
				Changes

				 * Fire trap now does 2 fire damage instead of 1.
				 * Items and blocks with sub types (like traps) now have specific information pages in JEI.
				 * The null charm now has 27 slots rather than 5.
				 * Charms will now work in Baubles again.
				 * Ender tether crafting now requires an unstable ender pearl. Normal pearl recipe removed.
				 * Removed the recipe for the block update detector. Added a recipe to replace existing ones with Vanilla's BUD block.
				 * Changed the recipes for lore tags, and added recipes for previously uncraftable lore tag colours.
				 * Rewrote the recipe system to work better with ore dictionary and metadata.
				Fixes

				 * The wither trap would not do damage to mobs if they stand on the plate.
				 * Removed duplicate JEI information recipe registrations.
				 * Ender Tethers are now significantly more performant.
				 * Shulkers would not drop their pearls if you kill them.
				 * Fixed a client side freeze when shift clicking items into the null charm.
				 * Fixed being able to move the null charm item while accessing it's inventory.
			DarkUtils-1.12.2-1.8.186.jar:
				 * Fixed dyed slime recipe causing serverside crash. Closes #127 - Tyler Hancock
			DarkUtils-1.12.2-1.8.187.jar:
				 * Fixed maim trap not causing damage to players and bosses. - Tyler Hancock
				 * Fixed JEI description for the EXP monolith and player trap. #128 - Tyler Hancock
			DarkUtils-1.12.2-1.8.188.jar:
				 * Fixed null charm crashing when clicking on an invalid slot. #130 - Tyler Hancock
			DarkUtils-1.12.2-1.8.190.jar:
				No changelog provided

		Deep Resonance (went from deepresonance-1.12-1.4.9.jar to deepresonance-1.12-1.6.0.jar):
			deepresonance-1.12-1.5.0.jar:
				1.5.0:

				 * Updated to latest ElecCore (1.8.434)
			deepresonance-1.12-1.6.0.jar:
				1.6.0:

				 * This version requires McJtyLib 2.6.0!
				 * Joseph C. Sible fixed the overworld variant of resonating ore appearing twice in the creative inventory
				 * Added obsidian to the recipe of the tank to avoid people (ab)using them as cheap liquid conduits
				 * Fixed a problem with the resonating plate recipe being broken
				 * Fixed an OC dependency

		Default Options (went from DefaultOptions_1.12.1-9.2.4.jar to DefaultOptions_1.12.2-9.2.5.jar):
			DefaultOptions_1.12.2-9.2.5.jar:
				Fixed JourneyMap compatibility

		Draconic Evolution (went from Draconic Evolution 1.12-2.3.5.269-universal to Draconic Evolution 1.12-2.3.8.279-universal):
			Draconic Evolution 1.12-2.3.7.278-universal:
				-Fixed console spam from placed items due to incorrect implementation of a safety check.
			Draconic Evolution 1.12-2.3.8.279-universal:
				-Re added toggle flight key binding
				-Fixed entity path finding crash related to placed items.

		ElecCore (went from ElecCore-1.12.2-1.8.433.jar to ElecCore-1.12.2-1.8.434.jar):
			ElecCore-1.12.2-1.8.434.jar:
				-Fixed air tooltips

				-Fixed NPE when unloading world

				-Fixed chunk loading issue

				-Some minor tweaks in the grid system

		Ender Storage 1.8.+ (went from Ender Storage 1.12-2.4.0.122-universal to Ender Storage 1.12.2-2.4.2.126-universal):
			Ender Storage 1.12.2-2.4.2.126-universal:
				No changelog provided

		Environmental Tech (went from environmentaltech-1.12.2-2.0.5a.jar to environmentaltech-1.12.2-2.0.6b.jar):
			environmentaltech-1.12.2-2.0.6a.jar:
				No changelog provided
			environmentaltech-1.12.2-2.0.6b.jar:
				Version 2.0.6b
				- Added Crystal Lens for all the Tier Crystals(REGEN JSONS for Void Ore Miner!!!)
				- Added next tier block requirements for Assembler left click functionality.
				Version 2.0.6a
				- Fixes
				- Updated to latest ValkyrieLib

		Exchangers (went from Exchangers-1.12-2.5.2.jar to Exchangers-1.12.2-2.6.2.jar):
			Exchangers-1.12.2-2.6.jar:
				Exchangers has hit 1 million total downloads on CurseForge! Thank you everyone for the support! <3

				1.12 Exclusive Changes

				 * Rewritten everything again, just to make porting changes from 1.10 and 1.11 easier
				 * Fixed error messages not showing up on servers, closes [#35](https://github.com/JackyyTV/Exchangers/issues/35)
				 * Added Blockcraftery blocks to global whitelist, closes [#38](https://github.com/JackyyTV/Exchangers/issues/38)
				 * Moved error messages to use the vanilla status messages, so they don't spam the ingame chat
			Exchangers-1.12.2-2.6.1.jar:
				 *
				Added a Silk Touch mode config option, if Silk Touch mode is disabled, players may enchant the Exchangers with Silk Touch to achieve Silk Touching on Exchangers or Fortune to get additional drops

				 *
				Added a PlaceEvent for when Exchangers tries to replace blocks, allows other mods to cancel the event, which also fixes an issue with Faction Mod, closes [#41](https://github.com/JackyyTV/Exchangers/issues/41)

				 *
				Migrated over to use string block IDs instead of integers

				 *
				Rewritten lots of translation stuff

				 *
				Changed all the camelCase translation keys to be lowercase with underscores

				 *
				Added a special blocks list for internal use

				 *
				Fixed a major problem with Exchangers not being able to replace blocks with metadata, closes [#42](https://github.com/JackyyTV/Exchangers/issues/42)

				 *
				Internal code optimizations to greatly reduce redundancy

				 *
				Added a config option to allow Unbreaking enchant reducing chance of using power for Powered Exchangers, currently tested and works on all MC versions, enabled by default (Part of [#37](https://github.com/JackyyTV/Exchangers/issues/37))

				 *
				Added a way to decrease range for Exchangers (by holding sneak and pressing the mode key)
			Exchangers-1.12.2-2.6.2.jar:
				1.12 Exclusive Changes

				 * Fixed MV Exchanger requiring Tier 3 core instead of Tier 2 in hard mode, closes [#43](https://github.com/JackyyTV/Exchangers/issues/43)

		Extra Utilities (went from Extra Utilities 2 - 1.12 - 1.6.8 to Extra Utilities 2 - 1.12 - 1.7.2):
			Extra Utilities 2 - 1.12 - 1.7.0:
				- Add Unstable ingots and an unstable ingot recipe for moon stone.
				- Add "Progressive efficiency loss" mechanic to GP mills/generators. Mills become less effective when there are many of the same type.
				- Fix Manual issues
				- Fix Villager localization issues
				- Add config option to enable end and nether biomes working in the Quantum Quarry (off by default)
				- Fix serialization issue with blockstates
				- Move save file initiation to occur after the server has been started.
			Extra Utilities 2 - 1.12 - 1.7.1:
				- Tweak some GP loss values
				- Fix incorrect GP being shown
				- Fix crash when using other languages
				- Fix missing localization
			Extra Utilities 2 - 1.12 - 1.7.2:
				- Fix null itemstack crash in 1.10.2
				- Reduce efficiency loss in generators
				- Improve overlay to show GP before and after efficiency loss.

		Extreme Reactors (went from ExtremeReactors-1.12-0.4.5.44.jar to ExtremeReactors-1.12.2-0.4.5.45.jar):
			ExtremeReactors-1.12.2-0.4.5.45.jar:
				- Added a couple of reactor moderators if Draconic Evolution is around
				- Made fluid pipes happy on restart by deceiving them. Close #148
				- The reactor will now take in the correct amount of fuel, no more dupes thank you. Close #127
				- Minimum Minecraft version changed to 1.12.2
				. No more 1.12 / 1.12.1: sorry guys
				- Updated Forge to version 14.23.1.2555
				- Bumped version to 0.4.5.45

		Forestry (went from forestry_1.12.2-5.7.0.215.jar to forestry_1.12.2-5.7.0.232.jar):
			Changelog retrieved from forestry_1.12.2-5.7.0.232.jar:
				Please help the Forestry project by [reporting issues here.](https://github.com/ForestryMC/ForestryMC/issues)

				Current release 5.7.0.232

				 * Adds dimension blacklist for hive generation. - phillipasmith1995
				 * Adds dimension blacklist for hive generation. - phillipasmith1995
				 * Adds dimension blacklist for hive generation. - phillipasmith1995
				 * Move the worktable into a separate module - Nedelosk
				 * Should be all of them (hopefully) - temp1011
				 * Add power config for analyzer - temp1011
				 * Fix #1061 Squeezer is very "liberal" regarding oredict in Recipes - Nedelosk
				 * Update Lang Git-Module - Nedelosk
				 * Fix #1937 Backpacks do not hold previously defined items - Nedelosk
				 * Fix #1399 Add Hive Generation Blacklist - Nedelosk
				 * Fix #1884 Tin can / wax capsule cannot pick up fluid blocks in world - Nedelosk
				 * Fix Crate Registration - Nedelosk
				 * Fix #1916 Client Crash with new version - Nedelosk
				 * Fix #1874 Crash when adding bee to BQM quest / chestloot - Nedelosk
				 * Fix #1920 Crate registration only on client side - Nedelosk
				 * No biome check - temp1011
				 * Fix #1919 Butterfly Model throws NullPointerException - Nedelosk
				 * Improve Database Recipe - Nedelosk
				 * Fix hive flammability Hive placed in the overworld burned whereas hive placed in nether did not in my tests. Hopefully this works better. - temp1011
				 * Fix #1932 Client Tile Updates Depending On Apiculture Module - Nedelosk
				 * Woops - temp1011
				 * add damage option to hives based on config - temp1011
				 * update config in apiculture - temp1011
				 * make non hellish hives flammable - temp1011
				 * Fix "Invalid property value detected" error logs from Buildcraft - mezz
				 * Fix #1918 Fix #1912 packet handler issue with latest Forge (14.23.1.2574+) - Nedelosk
				 * Fix Broken Sockets - Nedelosk
				 * Fix #1907 Electric engine doesn't update configuration properly - Nedelosk
				 * Fix #1901 Custom Backpack Model Registration - Nedelosk
				 * Fix #1906 Willow leaves can be used to create an x-ray - Nedelosk
				 * Fix resurrected ender dragons having no AI (#1908) - Remy A
				 * Revert "Slightly improve render performance of genetic items (bee, trees, etc)" This reverts commit 3df3d9121e51fca16e8a02b19ddb8e1a2b1d5bc6. - mezz
				 * Fix #1893 False Module Container ID - Nedelosk
				 * Slightly improve render performance of genetic items (bee, trees, etc) - mezz
				 * Stop viscous fluids from making water flowing sounds - mezz
				 * Fix #1890 NPE with Quark - Nedelosk
				 * Fix backward compatibility - Nedelosk
				 * Clean Up Module System - Nedelosk
				 * Fix Module System - Nedelosk
				 * Fix Api - Nedelosk
				 * Improve Module System - Nedelosk
				 * Fix GreenhouseEventHandler - Nedelosk
				 * Change the plugin system to a module system and split up modules -Changed plugin system to a module -Renamed forestry plugins to modules -Mod integration plugins are still called plugins -Moved the woodpile from the arboriculture module to the charcoal module -Moved the backpacks from the storage module to the backpacks module -Moved the crates from the storage module to the creates module -Added the database module with a new storage block for individuals -Added a species plugin interface to handle the database gui -Added some alyzer methods to the species plugin to move the alyzer integration later to it - Nedelosk
				 * Update Lang - Nedelosk
				 * Move some classes to the api and fix broken api classes - Nedelosk
				 * Remove experimental code - Nedelosk
				 * Remove unused resources - Nedelosk
				 * Change the plugin system to a module system and slit up modules -Moved the woodpile from the arboriculture module to the charcoal module -Moved the backpacks from the storage module to the backpacks module -Moved the crates from the storage module to the creates module -Added the database module with a new storage for individuals -Added a gui element system to improve the alyzer api -Added a species plugin interface to handle the database gui -Added some alyzer methods to the species plugin to move the alyzer integration later to it - Nedelosk
				 * Fix #1866 Block State Interaction Issues With Pokecube and Forestry - Nedelosk
				 * Fix Mystical Agriculture Inferium Seeds and update Integration - Nedelosk
				 * Fix #1860 Mystical Agriculture Integration (Could not find item) - Nedelosk
				 * Fix #1856 Non Glass Fluids in Thermionic Fabricator are not getting used up. - Nedelosk
				 * Fix #1863 Bottler does not empty bottles - Nedelosk

		Gendustry (went from Gendustry 1.6.5.6 (MC 1.12.2) to Gendustry 1.6.5.8 (MC 1.12.2)):
			Gendustry 1.6.5.8 (MC 1.12.2):
				Fixed interaction with fluid containers when clicking a block (closes #250)
				Prevent getting TE on incorrect block in apiary getLightValue (closes #254)
				Added BR translation (thanks InterPlay)

		Advanced Generators (went from Advanced Generators 0.9.20.9 (MC 1.12.2) to Advanced Generators 0.9.20.12 (MC 1.12.2)):
			Advanced Generators 0.9.20.10 (MC 1.12.2):
				568248266: Update zh_CN language file
				noreply: fix a typo
			Advanced Generators 0.9.20.12 (MC 1.12.2):
				Added BR translation (thanks InterPlay)

		iChunUtil (went from iChunUtil-1.12.2-7.1.3.jar to iChunUtil-1.12.2-7.1.4.jar):
			iChunUtil-1.12.2-7.1.4.jar:
				- Fix the aggressive head tracking config being locked to 0 or 1 despite there being a 2 option.
				- Fix crashes by shatter and morph when killing a skeleton because of Optifine.
				- Fix client crashes when using the recipe book on a client with iChunUtil installed, on a server that doesn't have it installed.
				- Fix crashes with block entities that have been summoned/loaded with an invalid NBT data.
				- Made latched entity invisible to hide the second bounding box found in the player.
				- Update localization for the keybind settings to reflect on how keybinds are accessed now.
				- Add a way for non-iChunUtil mods to use iChunUtil's update tracker.

		Immersive Engineering (went from Immersive Engineering 0.12 - 75 to Immersive Engineering 0.12 - 76):
			Immersive Engineering 0.12 - 76:
				- added various config options for the Belljar, to adjust growthspeeds and fertilizer effects
				- re-added partivles and animation for the Arc Furnace (thanks Malte)
				- added TOP compat for the Sheetmetal Tank (thanks SirWindfield)
				- added Metal Ladders and Scaffold covered versions
				- fixed wires connected to razorwire not rendering (thanks Malte)
				- fixed missing comparator output on the Current Transformer (thanks Malte)
				- fixed the drill accidentally modifying its NBT on sharing (thanks Malte)
				- fixed the Bucket Wheel having rotation issues (thanks Malte)
				- fixed crashes related to newer Forge versions (thanks Malte)
				- fixed rendering issues with the Floodlight and improved its performance (thanks Malte)
				- fixed Carrots and Potoes not working in the Cloche (thanks SirWindfield)
				- fixed crash when the Teslacoil destroys a piece of Faraday armor
				- fixed Chisel compat
				- fixed AA compat, Canola in the squeezer
				- fixed TCon compat, mixup of Constantan and Uranium (thanks tgstyle)
				- fixed TCon compat, Arc Furnace for alloying
				- fixed a crash with the Slippery potion
				- fixed derpyness with Mineral Veins (at least for the most part), regarding CraftTweaker
				- fixed links to Biodiesel in the manual pointing to a nonexistant page
				- fixed a minor texture warning in the console
				- Translations Added/Updated: zh_CN (DYColdWind)

		Industrial Foregoing (went from industrialforegoing-1.12.2-1.6.3-107.jar to industrialforegoing-1.12.2-1.6.6-113.jar):
			industrialforegoing-1.12.2-1.6.4-111.jar:
				 * Added config options to disable machines from the game
				 * Added config option to not show Book entries in JEI
				 * More safe fluid crafting for the Fluid Crafter and making the tank fluid the default fluid for the recipe.
				 * Improved leaf breaking so it doesn't drop items on the floor, I hope.
				 * Added chorus support for the Plant Gatherer
				 * Improved tooltips, Plant Gatherer shows what it can gather.
				 * Fixed Forestry Saplings not being planted with the Plant Sower
				 * Changed all the machines that had a 5x5 radius and didn't accept Range addons to a 7x7
				 * Added the range of the machines in the tooltip
				 * Fixed Item Splitter only pushing the first slot
			industrialforegoing-1.12.2-1.6.5-112.jar:
				 * Added the mob in the Mob Imprisonment Tool name for easy searching
				 * Fixed Fluid Crafter not crafting with only 1 fluid
				 * Added config options to disable machines from the game
				 * Added config option to not show Book entries in JEI
				 * More safe fluid crafting for the Fluid Crafter and making the tank fluid the default fluid for the recipe.
				 * Improved leaf breaking so it doesn't drop items on the floor, I hope.
				 * Added chorus support for the Plant Gatherer
				 * Improved tooltips, Plant Gatherer shows what it can gather.
				 * Fixed Forestry Saplings not being planted with the Plant Sower
				 * Changed all the machines that had a 5x5 radius and didn't accept Range addons to a 7x7
				 * Added the range of the machines in the tooltip
				 * Fixed Item Splitter only pushing the first slot
				 *
			industrialforegoing-1.12.2-1.6.6-113.jar:
				 * Made fake players not mounting entities
				 * Added a safety check for entities in the Mob Imprisonment Tool
				 * Improved Log rendering
				 * Fixed Item Splitter Facing
				 * Tooltips are now translatable
				 * Fixed Block Breaker and Block Placer accepting range addons
				 * Book entries are now translatable

		Integrated Dynamics (went from IntegratedDynamics-1.12.2-0.9.8.jar to IntegratedDynamics-1.12.2-0.10.0.jar):
			IntegratedDynamics-1.12.2-0.9.9.jar:
				As always, don't forget to backup your world before updating!
				Requires CyclopsCore version 0.10.24 or higher.

				Additions:
				* Add convenience aspect to fluid reader to read fluid blocks, Closes #405

				Fixes:
				* Fix crash on comparator reading on certain blocks, Closes #450
				* Fix incorrect LP dropdown rendering order
				The dropdown of the operators field was sometimes being rendered in front of the LP element type name box.
				* Fix incorrect operator value signatures, Closes #386
				* Fix generator incorrectly internally acting as variable store
				* Fix value updates not being propagated when 1+ steps from a proxy, Closes #374
				* Allow flip operator to be used for operators with 2+ inputs, Closes #387
				* Fix advancement condition logging errors for certain predicate checks
				* Fix strong redstone power not always updating all blocks, #422
				* Fix part states not properly for updating player, Closes #389
				* Fix NBT serialization in ValueTypeListProxyEntityBase not being called, Closes #393
			IntegratedDynamics-1.12.2-0.10.0.jar:
				As always, don't forget to backup your world before updating!
				Requires CyclopsCore version 0.11.0 or higher.

				Additions:
				* Add Mechanical Squeezer, an advanced and automatable version of the Squeezer
				* Add Mechanical Drying Basin, an advanced and automatable version of the Drying Basin
				* Add channels, this is particularly useful for Integrated Tunnels
				* Add network aspect for reading the network consumption rate
				* Add TPS aspect to world and extradimensional readers
				* Add auto-supply mode to batteries
				This allows you to automatically fill held energy containers.
				* Add operators to read items, fluids and energy from entities, Closes #403
				* Add redstone pulse writing aspects, Closes #418
				* Add part setting for changing the effective target side, #278
				* Add index_of, index_of_regex, regex_groups, replace, replace_regex, matches_predicate.
				* Add Signals compat
				* Add molten metal convenience recipes to Drying Basin
				* Add an "Is Minecart" operator
				* Add an entity and machine writer
				Like the inventory writer, these will only be useful with cross-mod compats
				* Add an offset time to the redstone clock
				This lets you have, for example, 4 redstone clocks, each one on for a different
				1/4 of each second. Without this, they'd all be on for the same 1/4 of each
				second. This partially solves #317; it will fully solve it once #197 is solved.
				* Add a recipe to squeeze menril resin out of planks
				* Add menril doors
				* Allow squeezing of poisonous materials

				Changes:
				* Balance default energy capacity of batteries
				This multiplies the default capacity by a factor of 10, in order to have a better balance compared to other RF storage mods.
				* Make battery transfer limit in relation to its capacity, #377
				* Show outline of variables in variable slots
				* Show battery energy level toast when right clicking with an empty hand
				* Add warning about certain parts not having aspects, Closes #214
				* Give 3 omni-directional connectors instead of 2 when consuming one
				* Don't show empty fluid squeezer slots in infobook
				* Modify several Squeezer recipe outputs to be probabilistic

				Fixes:
				* Fix missing texture on the bottom of the Materializer
				* Fix part settings button hovering appearing incorrectly, Closes #453
				* Fix crash due to casting ANY to NUMBER
				* Fix JEI recipe clicking in LP overwriting last inventory slot, Closes CyclopsMC/IntegratedDynamics#441
				* Fix incorrect JEI LP recipe fill in target
				* Fix machine deactivation after finishing each recipe

		Integrated Tunnels (went from IntegratedTunnels-1.12.2-1.3.6.jar to IntegratedTunnels-1.12.2-1.4.0.jar):
			IntegratedTunnels-1.12.2-1.4.0.jar:
				As always, don't forget to backup your world before updating!
				Requires CyclopsCore version 0.11.0 or higher.

				Additions:
				* Add energy, fluid and item channels
				* Add option to shift click in Player Simulator, Closes #73

		Just Enough Items (JEI) (went from jei_1.12.2-4.8.5.132.jar to jei_1.12.2-4.8.5.138.jar):
			Changelog retrieved from jei_1.12.2-4.8.5.138.jar:
				Current release 4.8.5.138

				 * Update zh_cn.lang (#1073)
				 * Improve tooltip message when item can't be crafted in player inventory
				 * Fix #1077 packet handler issue with latest Forge (14.23.1.2574+)
				 * Update sv_se.lang (#1070)
				 * Fix #1066 Item Hiding Mode not working with mouse clicks
				 * Use ClientConnectedToServerEvent instead of EntityJoinWorldEvent
				 * Check for disabled brewing recipes using a BrewingRecipeRegistry#hasOutput (#1065)
				 * Close #953 Hide item from client if it doesn't exist in the server
				 * Only subscribe to ClientTickEvent when needed
				 * Update Polish translation (#1062)
				 * Fix #1055 Crash when searching using OR(|) operator

		Magic Bees (went from MagicBees-1.12-3.1.7.jar to MagicBees-1.12.2-3.1.9.jar):
			MagicBees-1.12.2-3.1.8.jar:
				-Fixed botania flower models

				-Fixed module effects loading after the bee is registered, resulting in NPE's

				-Made bee disabling & bee hiding in JEI configurable

				-Set max frame stack size to 1, fixes frames with same durability being able to stack

				-Fixed Essence of Everlasting Durability recipe

				-Fixed hive gen on nether roof (thanks temp1011)

				-Fixed different hive types stacking

				-Fixed null taglines, resulting in NPE's when shift-hovering over BM-botania research

				-Added TE mobtypes now that they are back in
			MagicBees-1.12.2-3.1.9.jar:
				-Fixed botania flower models

				-Fixed module effects loading after the bee is registered, resulting in NPE's

				-Made bee disabling & bee hiding in JEI configurable

				-Set max frame stack size to 1, fixes frames with same durability being able to stack

				-Fixed Essence of Everlasting Durability recipe

				-Fixed hive gen on nether roof (thanks temp1011)

				-Fixed different hive types stacking

				-Fixed null taglines, resulting in NPE's when shift-hovering over BM-botania research

				-Added TE mobtypes now that they are back in

				-Fixed NPE when certain bee were removed

				-Decreased spawn rate of TE mobs

				-Added JEI info about where to find bee hives

				-Added some postgen features to underground hives

		McJtyLib (went from mcjtylib-1.12-2.5.0.jar to mcjtylib-1.12-2.6.2.jar):
			mcjtylib-1.12-2.6.0.jar:
				2.6.0:

				 * Big refactoring and cleanup of a lot of code. Pulled code out of various mods for better code reuse
				 * Joseph C. Sible cleaned up a lot of code
			mcjtylib-1.12-2.6.1.jar:
				2.6.1:

				 * Fix a server side crash
			mcjtylib-1.12-2.6.2.jar:
				2.6.2:

				 * Joseph C. Sible did some fixes to tooltip handling in the gui library
				 * Joseph fixed wrong height of a line with just a single ItemStack in tooltips
				 * Joseph did some internal code changes to the widget library
				 * Rendering stack tooltips for BlockRender widgets is now a core feature of McJtyLib

		MCMultiPart (went from MCMultiPart 2.4.0 to MCMultiPart 2.4.1):
			MCMultiPart 2.4.1:
				 * Fixed TEs not getting their IPartInfo set when turned into a multipart
				 * Implemented entity collision detection support (for BC compat)

		MeeCreeps (went from meecreeps-1.12-1.0.1.jar to meecreeps-1.12-1.1.0.jar):
			meecreeps-1.12-1.1.0.jar:
				1.1.0:

				 * This version requires McJtyLib 2.6.0!
				 * Fixed the two pickup tasks so they properly work again. They somehow got broken

		MineTogether (went from minetogether-1.10.2-1.8.2.jar to minetogether-1.10.2-1.8.7.jar):
			minetogether-1.10.2-1.8.5.jar:
				Reupload to fix minor text issues.

				Added sorting, made Config more resilient - will just disable mod instead of crashing.
				Fixed bug where invite only servers weren't working as I didn't send the hash correctly. I am a dumb.
			minetogether-1.10.2-1.8.6.jar:
				Added flag rendering so you know what country each server is in.

				Added location sorting - will sort your country to the top, then alphabetically afterwards

				Changed how sorting works - no longer needs to refresh every single time sorting is changed

				Now has dropdown list for sorting.
			minetogether-1.10.2-1.8.7.jar:
				Fixed localisation on friends list key bind.

				Changed default key combination to Ctrl+M to avoid reported conflict with JEI.

		ModTweaker (went from modtweaker-4.0.5 to modtweaker-4.0.6):
			modtweaker-4.0.6:
				Added weighted support to TE refinery

				Added botania support

				Add Pyrolysis methods to Redstone Furnace (#564)

				Fixed botania support (closes #569, closes #573, closes #447)

				Added refined storage support (closes #562)

				Added support for Alchemical Imbuer and Arcane Ensorcellator (Closes #570)

				Added Tcomplement support closes #541

		Morph (went from Morph-1.12.2-7.1.1.jar to Morph-1.12.2-7.1.2.jar):
			Morph-1.12.2-7.1.2.jar:
				- Fix crashes with certain mods' mob grinders trying to acquire a mob it's killed.
				- Fix crashes due to checks for a null sound event.

		Not Enough Wands (went from notenoughwands-1.12-1.5.9.jar to notenoughwands-1.12-1.6.0.jar):
			notenoughwands-1.12-1.6.0.jar:
				1.6.0:

				 * This version requires McJtyLib 2.6.0!

		p455w0rd's Library (went from p455w0rdslib-1.12-2.0.24.jar to p455w0rdslib-1.12-2.0.25.jar):
			p455w0rdslib-1.12-2.0.25.jar:
				Fixed rare ConcurrantModificationException

				Updated Inventory logic a bit to work with Enderman Evolution

		Pam's HarvestCraft (went from Pam's HarvestCraft 1.12.2a.jar to Pam's HarvestCraft 1.12.2g.jar):
			Pam's HarvestCraft 1.12.2b - Re-Balance Update.jar:
				HarvestCraft 1.12.2b
				-----------------
				- Added: Config: topTierFood (Default: 10): This new config option defines which foods are considered top tier. Choose between 5, 6, 7, 8, 9, or 10 for minimum number of shanks a food restores that become top tier. Set to 0 to have no top tier foods. Warning: Top Tier foods can ALWAYS BE EATEN so be careful you don't waste them. Example: If you pick 5, any food that restores 5 or more shanks become top tier and give buffs.
				- Added: Top Tier Foods (those who meet the topTierFood threshold) now give multiple buffs upon eating. All foods defined as top tier give the same buffs to allow diet variety. Buffs given are Strength and Resistance.
				- Added: Config: buffTimeForTopTier (Default: 3600) This new config determines how long top tier food buffs last. 3600 is equal to 3 minutes.
				- Added: es_es Lang file by ivandpf!
				- Change: Food Restore: Every food in HarvestCraft has been rebalanced against the new vanilla Minecraft food restore numbers. This 1200+ item change cannot be turned off in the config.
				- Change: Recipe: Toast: toolBakeware, bread, foodButter
				- Change: Recipe: Lemonaide: toolJuicer, cropLemon, listAllsugar
				- Change: Recipe: Toast Sandwich: foodToast, foodToast, foodBlackpepper
				- Change: Recipe: Plain Yogurt: toolPot, listAllmilk
				- Change: Recipe: Grilled Asparagus: toolSkillet, cropAsparagus
				- Change: Recipe: Grilled Eggplant: toolSkillet, cropEggplant
				- Change: Recipe: Grilled Mushroom: toolSkillet, listAllmushroom
				- Change: Recipe: Stuffed Eggplant: toolBakeware, cropEggplant, cropGarlic, cropBellpepper, foodButter, listAllegg
				- Change: Recipe: Pasta x5: toolMixingbowl, foodDough, foodButter
				- Change: Recipe: Noodles x3: toolCuttingboard, foodDough
				- Change: Recipe: Paneer x5: toolPot, listAllmilk, foodVinegar, foodLemonaide
				- Change: Recipe: Chocolate Bar x4: toolSaucepan, foodCocoapowder, foodButter, listAllmilk
				- Change: Recipe: Hot Sauce x6: toolPot, listAllwater, foodVinegar, cropChilipepper, cropGarlic, foodSalt
				- Change: Recipe: Hoisin Sauce x9: toolSaucepan, cropSweetpotato, listAllwater, listAllsugar, cropSoybean, cropSesame, foodVinegar, cropGarlic, cropChilipepper
				- Change: Recipe: Trail Mix: listAllseed/cropSunflower/foodRoastedsunflowerseeds/listAllnut, foodRaisins, foodChocolatebar
				- Change: Recipe: Raw Tofeak x6: toolCuttingboard, foodFirmtofu, listAllmushroom, foodSoysauce, foodBlackpepper, foodOliveoil
				- Change: Recipe: Raw Tofenison x7: toolCuttingboard, foodFirmtofu, foodFlour, foodOliveoil, listAllnut, foodSoysauce, listAllmushroom
				- Bug Fix: Sodas should now give 30 seconds of speed buff instead of 3 seconds.
			Pam's HarvestCraft 1.12.2c.jar:
				HarvestCraft 1.12.2c
				-----------------
				- Added: Items: Candied Pecans, Enchilada, Stuffing, Green Bean Casserole, Ham and Pineapple Pizza, Sauced Lamb Kebab, Cobblestone Cobbler, Crayfish Salad, Ceviche, Deluxe Nachoes, Baked Cactus, Garlic Steak, Mushroom Steak, Hot Dish Casserole, Sausage Bean Melt, Mettbrötchen
			Pam's HarvestCraft 1.12.2d.jar:
				HarvestCraft 1.12.2d
				-----------------
				Note: You will need to regen your Config file in order to get the new config options

				- Fixed: Blocks: Gardens now break instantly again when punched
				- Fixed: Name: Raw Duck and Cooked Duck have correct names
				- Fixed: Name: Three Bean Casserole and Grapefruit Juice names are fixed by Luke616
				- Fixed: WorldGen: Fixed editing of main thread by kashike
				- Fixed: Recipe: Peking Duck finally has its own recipe, allowing you to finally make it and Stuffed Duck
				- Fixed: Recipe: Green Bean Casserole now has a recipe
				- Fixed: Recipe: Fixed recipe grouping by F43nd1r
				- Fixed: Recipe: Soft Pretzels now require listAllwater as well
				- Fixed: Recipe: Tofacon now accepts itemSalt, dustSalt, and foodSalt OreDictionary'd salts
				- Fixed: Recipe: Roasted Chestnut, Popcorn, Raisins, Tea, Coffee, Ricecake, Grilled Asparagus, Baked Sweet Potato now use correct cropX Ore Dictionary listings
				- Fixed: Recipe: All honey recipes now use foodHoneydrop and dropHoney (from Forestry) for all recipes
				- Fixed: Recipe: Plain Yogurt x4 can also be made by putting Plain Yogurt + Leather in crafting grid
				- Fixed: Recipe: Red Velvet Cake now requires foodCocoapowder in place of one of the dyes by ReverseStateMonad
				- Removed: Recipe: Removed Plain Yogurt + listAllmilk = 2 Plain Yogurt recipe
				- Added: Config: cropGrowthSpeed (Default: 0.0): This number is added/subtracted from normal fertile crop growth (3.0) and adjacent fertile crop growth (4.0)
				- Added: Config: fruitGrowthSpeed (Default: 25): Lower is faster
				- Added: Lang: Added zh_tw.lang file by HaeDon
				- Added: Lang: Added pl_pl.lang file by EliogabalusPL
				- Added: Blocks: Beehives can now be sheared
			Pam's HarvestCraft 1.12.2e.jar:
				HarvestCraft 1.12.2e
				-----------------
				ALL RECIPES SHOULD NOW BE WORKING

				Note: You will need to regen your Config file in order to get any changes to the Config file

				- Fixed: Block: Beehives now break when nothing is above them, dropping the Queen Bee
				- Fixed: Block: Fruit blocks now break with nothing is above them, if mature will drop 2 fruits (to make up for losing a renewable resource block)
				- Fixed: Config: Removed useless config options (bait amounts, fresh water amounts, salt amounts, etc). To change the amount a recipe gives you, open the recipe JSON in the JAR file in a text editor and change the count amount. (reported by bushbaby1234)
				- Fixed: Recipe: Grilled Mushroom now uses cropWhitemushroom instead of harvestcraft:whitemushroomitem
				- Fixed: Recipe: All recipes with alternate versions have now been re-named foodname_alternate item (example: friesitem_foodSalt, friesitem_dustSalt, friesitem_itemSalt, etc) -
				- Fixed: Recipe: Sweet and Sour Sauce now uses foodHoneydrop and dropHoney instead of harvestcraft:honeyitem
				- Fixed: Recipe: All salt recipes should have three versions for dustSalt, foodSalt, and itemSalt Ore Dictionary (reported by Chunkey)
				- Fixed: Recipe: Fruit logs (maple, cinnamon, paperbark) should now convert to planks correctly
				- Fixed: Recipe: Fixed recipes for making and unmaking Honey Block, Pressed Wax Block, Honeycomb Block, Waxcomb Block
				- Fixed: Recipe: Recipes to make Pressed Wax with cropCandle fixed (toolPot, 1:1, toolPot, 8:8)
				- Fixed: Recipe: Toasted Coconut now uses cropCoconut correctly
				- Added: Presser: Minecraft Pumpkin Seeds to Cooking Oil, Grain Bait
				- Added: Presser: Harvest Apple to Apple Juice, Fruit Bait
				- Added: Presser: Harvest Carrot to Carrot Juice, Veggie Bait
				- Added: Presser: Raspberry to Raspberry Juice, Fruit Bait
				- Added: Recipe: Added Flour recipes for cropAlmond, cropCoconut, cropChestnut, listAllwheat, cropBean, cropBanana, cropPeas, cropSoybean
				- Added: Recipe: Added Cooking Oil recipes for cropAvocado, listAllseed(2:2 ratio), cropTea
				- Added: Recipe: Added Roasted Root Veggie Medley recipes for cropRutabaga, cropBeet, cropRhubarb
				- Added: Recipe: Added Spicy Greens Recipe for cropSpinach, cropAsparagus
				- Added: Recipe: Fresh Milk recipe alternative (toolJuicer, cropAlmond)
				- Changed: Presser: Coconut to Coconut Milk, Grain Bait
				- Changed: Recipe: Spring Salad now requires toolCuttingboard, cropLettuce, listAllveggie (instead of having a dozen specific recipes)
				- Changed: Recipe: Veggie Stir Fry now requires listAllmushroom or listAllvegie instead of specific recipes
				- Changed: Recipe: Almond Butter, Peanut Butter, Cashew Butter, Pistachio Butter, Chestnut Butter now use toolMortarandpestle
				- Removed: Recipe: Removed double cropSpiceleaf Salad Dressing Recipe (not intended)
				- Removed: Presser: Grape to Cooking Oil
				- Removed: Presser: Coconut to Cooking Oil
				- Removed: Presser: Pumpkin Seeds to Cooking Oil
			Pam's HarvestCraft 1.12.2f.jar:
				HarvestCraft 1.12.2f
				-----------------
				- Added: Fruit Tree: Added a Spider Web 'fruit' tree, that grows in temperate areas with spider web 'fruits' that drop string when mature and right-clicked (sapling crafted with any sapling and three string) Disable in config with spiderwebtreeGeneration
			Pam's HarvestCraft 1.12.2g.jar:
				HarvestCraft 1.12.2g
				-----------------
				NOTE: Automation for other blocks coming next update

				- Fixed: Presser: The Presser can now be automated! Input slot is accessed from top, sides while both Output slots are accessed from the bottom. Tested with hoppers!
				- Fixed some issues with Hunger Overhaul interacting with HarvestCraft (PR by alexbegt)

		Reborn Core (went from RebornCore-1.12.2-3.5.2.171-universal.jar to RebornCore-1.12.2-3.6.2.187-universal.jar):
			RebornCore-1.12.2-3.5.3.174-universal.jar:
				(modmuss50) #releaseBuild
				(modmuss50) Some improvements to the power system value > string handling
				(drcrazy) Drop from ore should be good now. Part of TechReborn/TechReborn#1365
				(drcrazy) Changed OreDropSet.drop to return NonNullList instead of ArrayList
			RebornCore-1.12.2-3.5.4.176-universal.jar:
				(modmuss50) #releaseBuild
				(modmuss50) Rewrite to use the correct way of logging
			RebornCore-1.12.2-3.6.0.181-universal.jar:
				NOTE: This is a beta test of the new slot configuration system that was added to TechReborn. Please report all issues to the github.

				(modmuss50) #betaBuild
				(modmuss50) Added the ability to filter inputs on a slot so it only accepts valid items
				(modmuss50) Fix [https://github.com/TechReborn/TechReborn/issues/1383](https://github.com/TechReborn/TechReborn/issues/1383)
			RebornCore-1.12.2-3.6.1.183-universal.jar:
				(modmuss50) #releaseBuild
				(modmuss50) Use https for shield connection
			RebornCore-1.12.2-3.6.2.187-universal.jar:
				(modmuss50) #releaseBuild
				(modmuss50) Fix kicking other players when configuring machine slots Closes [https://github.com/TechReborn/TechReborn/issues/1406](https://github.com/TechReborn/TechReborn/issues/1406)
				(drcrazy) Added pt-BR Translation (#76)
				(drcrazy) fix for TechReborn/TechReborn#1402

		Reborn Storage (went from RebornStorage-1.12.2-3.0.2.32.jar to RebornStorage-1.12.2-3.0.3.36.jar):
			RebornStorage-1.12.2-3.0.2.33.jar:
				(modmuss50) Hopefully fix crash, Closes #70 #releaseBuild
			RebornStorage-1.12.2-3.0.3.36.jar:
				(modmuss50) #releaseBuild
				(modmuss50) Only print out invalid multiblock error when hand is empty

		Redstone Arsenal (went from RedstoneArsenal-1.12.2-2.3.7.18-universal.jar to RedstoneArsenal-1.12.2-2.3.10.4-universal.jar):
			Changelog retrieved from GitHub:
				2.3.10:
				GENERAL:
					-Internal refactors and optimizations.

				FIXED:
					-Quiver can now be disabled.

				2.3.9:
				FIXED:
					-Addressed a few potential but highly unlikely NPEs with raytracing.
					-Corrected Unbreaking enchantment energy reduction calculation.

				2.3.8:
				ADDED:
					-Tools with AOE Breaking (Hammer, Shovel, Pickaxe) now display breaking effects on every block.

				FIXED:
					-Empowering the Flux-Infused Bow now correctly increases arrow damage and velocity.
					-Enchants are more consistent with respect to vanilla tool types.

		Refined Storage (went from Refined Storage 1.5.28 to Refined Storage 1.5.31):
			Refined Storage 1.5.29:
				 * Update Forge to 2577 (minimum Forge version required is now 2555 for MC 1.12.2) (raoulvdberge)
				 * Fixed bug where MCMP multiparts were blocking RS network connections (raoulvdberge)
				 * Fixed Reader/Writers for energy extracting energy when not needed (raoulvdberge)
			Refined Storage 1.5.30:
				 * Fixed crashing bug when MCMultiPart is not installed (raoulvdberge)
			Refined Storage 1.5.31:
				 * Improved the "cannot craft! loop in processing..." error message (raoulvdberge)
				 * Fixed error logs when toggling the Pattern Grid from and to processing mode (raoulvdberge)
				 * Fixed pattern slots in Crafters not being accessible (raoulvdberge)
				 * Fixed rare Grid crash (raoulvdberge)
				 * Fixed OpenComputers cable showing up in Grid as air (raoulvdberge)
				 * Storage disk and block stored and capacity counts are formatted now in the tooltip (raoulvdberge)
				 * Made the Disk Manipulator unsided (inserting goes to insert slots and extracting from output slots) (raoulvdberge)

		RFTools (went from rftools-1.12-7.15.jar to rftools-1.12-7.23.jar):
			rftools-1.12-7.20.jar:
				7.20:

				 * This version requires McJtyLib 2.6.0!
				 * Joseph C. Sible fixed dimensional ore registration with the three types. This fixes the nether and end variants of dimensional shard ore dropping as the overworld variant when silk touched
				 * Joseph added a recipe for the shape manual and fixed recipe for block protector
				 * Joseph removed some log spam
				 * Joseph implemented config options to allow disabling some subsystems in RFTools: security system, block protector, network monitor, crafter, coal generator, ...
				 * Joseph fixed a bug where the block protector didn't properly save and restore redstone mode
				 * Joseph fixed a syringe bug in creative
				 * Countless other small changes and fixes
			rftools-1.12-7.21.jar:
				7.21:

				 * Server side fix
			rftools-1.12-7.22.jar:
				7.22:

				 * Fixed crashes with builder and screen when being wrenched or similar circumstances
			rftools-1.12-7.23.jar:
				7.23:

				 * Joseph C. Sible made a change to let creative powercells act as infinite power sinks
				 * Now use the new feature in McJtyLib for drawing stack tooltips
				 * Depends on McJtyLib 2.6.2!

		RFTools Control (went from rftoolsctrl-1.12-1.7.0.jar to rftoolsctrl-1.12-1.8.0.jar):
			rftoolsctrl-1.12-1.8.0.jar:
				1.8.0:

				 * This version requires McJtyLib 2.6.0!

		RFTools Dimensions (went from rftoolsdim-1.12-5.05.jar to rftoolsdim-1.12-5.50.jar):
			rftoolsdim-1.12-5.10.jar:
				5.10:

				 * This version requires McJtyLib 2.6.0!
			rftoolsdim-1.12-5.50.jar:
				5.50:

				 * Requires McJtyLib 2.6.2
				 * Fixes a double icon bug in the dimlet workbench
				 * All changes below by Joseph. C Sible
				 * New features:
				 * Made dimensions be completely determined by the world seed and dimlets used (randomizeSeed is available if you don’t like this)
				 * Brought back the seed dimlet
				 * Made the dimlet workbench show full names and keys, and all possible variants
				 * Made the dimension editor work instantly and for free for cheater dimensions
				 * Made dimension editor dimlet injections take effect without having to reload the dimension
				 * Added the ability to turn off a dimlet by injecting it again
				 * Added a dimlet to reset the sky color
				 * Added dimensions' cloud colors to their info dump
				 * Added the ability to specify a descriptor to createdim
				 * Added a setting to control whether rarity factors scale with the number of items in each rarity
				 * Enhancements:
				 * Prevented players from accidentally injecting dimlets that only have an effect when used from the beginning
				 * Stopped low-power effects from flickering on and off
				 * Made colored clouds compatible with Forge’s GPU cloud renderer
				 * Made sure the space outside of the shelter’s door isn’t blocked
				 * Spaced out dimlets when extracting them
				 * Hid the realized dimension tab from the creative inventory
				 * Improved random dimlet generation’s probability calculations
				 * Improved handling of missing mob dimlets
				 * Bug fixes:
				 * Fixed crashes during Lost Cities world generation if the RFTools Crafter was disabled
				 * Fixed chunk loaders not working on server startup until a player enters the dimension
				 * Fixed lots of bugs involving how dimension information was shared between server and client
				 * Fixed createdim being able to create multiple dimensions with the same descriptor
				 * Fixed blacklisted dimlets being silently destroyed by the enscriber
				 * Fixed several resource leaks
				 * Fixed dimension effects not working in cheater dimensions
				 * Fixed most cases of cascading world generation in Lost Cities dimensions
				 * Fixed block display names showing Air instead of what they actually were
				 * Fixed Lost Cities data centers using the library chance to generate instead of their own
				 * Fixed uncraftable dimlets being generated from the wrong distribution
				 * Fixed a crafter and dimensional block variant never generating
				 * Fixed lit redstone ore showing up in place of redstone ore in the dimlet workbench
				 * Fixed cloud colors not properly overwriting each other
				 * Fixed mob descriptors not showing any useful information
				 * Fixed chests in dimlet dungeons facing the wrong way
				 * Fixed dimensions created by commands having the wrong owner
				 * Fixed mob dimlets for non-mob entities appearing in the dimlet workbench
				 * Fixed the “Body none” dimlet not working. Its old, unintended behavior is now available via “Body default”.
				 * And lots of refactoring and code cleanup

		Tinkers Construct (went from TConstruct-1.12-2.7.4.34.jar to TConstruct-1.12.2-2.9.0.55.jar):
			TConstruct-1.12-2.8.0.45.jar:
				New

				- Casting Channels are back, and better than ever! Thanks KnightMiner! (also check out ceramics, where they were tested)
				- Kamas are the little brother of scythes, working as shears as well as mowing through plants and crops
				- Items in the side-inventory are used for shift-clicking recipies via JEI, again! (Yes, technically not new, but.. new! Again!)
				- Chisel blocks should render correctly now

				- New ore preference config. This is a list of mod IDs to prefer for recipe outputs from the ore dictionary, such as casting recipes.

				Material rebalancing

				- Pig-Iron now is a medium-tier material, between iron and steel, and a better handle. It's much cheaper and keeps you topped off with food now. It uses clay instead of emerald now, but more iron. Blood also is easier to get now.
				- Cactus now always returns damage when you're holding a cactus tool. It returns more damage when blocking
				- Flammable now also sets enemies on fire when you're not blocking (aka when getting hit)
				- Prismarine now always has Aquadynamic and a very minor damage buff
				- Electrums trait now works for both combat and mining. You also gain charge on hitting enemies or breaking blocks. When breaking a block while charged you get a short boost of Haste(mining speed), when hitting an enemy you get a short boost of speed in addition to the bonus damage
				- Lead has more durability and has a new "Heavy" trait which prevents knockback
				- Minor nerf to bronze trait
				- Silvers holy trait now applies weakness to undead on hit, and deals more damage
				- Netherracks damage bonus doubled and also applies to tool heads/blades now

				Changes

				- Glowing modifier now also works from the offhand
				- In general traits/modifiers that require no player interaction also work in the offhand
				- Blood is more readily available now
				- Multiblock controllers can now be placed up to 64 blocks above the floor (was 9)
				- As long as a smeltery or tank isn't completely full there will always be an area at the top to check the remaining capacity

				Bugfixes

				- Probably fixed the crash that sometimes happens with blood (and in very rare cases: other fluids) in the smeltery!
				- Obsidian bolts head no head-texture
				- Fix tooltips on the materials when inspecting bolts
				- Fix snow setting on slime fluid (or rather, slime fluid being counted as solid)
				- Fixed a hit (sound) issue with projectiles stuck in blocks
				- Many small code, localization and visual fixes
			TConstruct-1.12-2.8.1.49.jar:
				2.8.0.49 Changes:

				- Kama book page

				- Kama/ and Scythes can now properly harvest Netherwart

				- Broken Kamas don't shear anymore

				- Elecrum Trait description updated
			TConstruct-1.12.2-2.9.0.55.jar:
				Requires Forge 2577
				Requires JEI 4.8 (if JEI is present)

				Changelog:

				* Seared Channel Recipe gives 3 now
				* Tools can be renamed anytime again!
				* Gasses now render upside down
				* TiC Slime Blocks now work with pistons

		Thermal Cultivation (went from ThermalCultivation-1.12.2-0.1.2.8-universal.jar to ThermalCultivation-1.12.2-0.1.5.3-universal.jar):
			Changelog retrieved from GitHub:
				0.1.5:
				GENERAL:
					-Internal refactors and optimizations.

				0.1.4:
				FIXED:
					-Addressed a potential but highly unlikely NPE with raytracing.

				0.1.3:
				GENERAL:
					-Backend refactors.
					-Improved Fake Player detection logic on Watering Can.

		Thermal Dynamics (went from ThermalDynamics-1.12.2-2.3.7.14-universal.jar to ThermalDynamics-1.12.2-2.3.10.4-universal.jar):
			Changelog retrieved from GitHub:
				2.3.10:
				GENERAL:
					-Internal refactors and optimizations.

				2.3.9:
				ADDED:
					-Redstone Relays. These transmit redstone signals along duct networks and have 16 channels.
						(Thanks ImbaKnugel)

				2.3.8:
				CHANGED:
					-Cover recipe is now hidden from the Crafting Book.

				FIXED:
					-Corner case fixes for cover render crashes.
					-Leadstone and Cryo Ducts now properly connect.

		Thermal Expansion (went from ThermalExpansion-1.12.2-5.3.7.31-universal.jar to ThermalExpansion-1.12.2-5.3.10.14-universal.jar):
			Changelog retrieved from GitHub:
				5.3.10:
				GENERAL:
					-Internal refactors and optimizations.

				ADDED:
					-New Machine:
						Sequential Fabricator - It's an autocrafter! :D

					-New Augments:
						Pattern Validation - Enables intelligent auto-input on the Sequential Fabricator.

						Fluidic Fabrication - Adds an internal fluid tank to the Sequential Fabricator.

						Enstabulation Apparatus - Allows for the Centrifuge to "process" Morbs.

					-New machine process recipes.
					-Plugin Support for Ice and Fire.

				CHANGED:
					-All blocks can now be filled/drained by hand, even the Fluid Transposer. Some machines may need
						to be OFF to allow for fluid to be drained.
					-Cache behavior has been changed internally, for better crossmod compatibility.
					-Comparator behavior has been improved for the Cell, Tank, and Cache.
					-Many recipes have been rebalanced. In general, dynamos and augments are all slightly cheaper.

				5.3.9:
				ADDED:
					-New Items:
						Morbs. These allow for mobs to be caught. Basic Morbs have a 25% reuse chance. Adding some
						signalum makes this 100%. (Thanks ImbaKnugel)

						Void Satchel. It's a satchel that voids things. Be careful.

				CHANGED:
					-Dusts now smelt one at a time in the Induction Smelter. Energy cost has been adjusted.
					-Satchels now transfer to inventory on sneak-click, not regular click.

				FIXED:
					-Added logic to the Arcane Ensorcellator to circumvent a miserable failing on Mojang's part.
					-Corrected a corner case where Furnace recipes were not properly retrieved.
					-Fixed a crash when locking a tank in the Fluid Allocator.

				5.3.8:
				ADDED:
					-New Augments:
						Gearworking Die - Allows the Compactor to create Gears from 4 Ingots.

						Parabolic Flux Coupling - Converts the Energetic Infuser into a wireless charger. Only Flux
							Capacitors will be charged. This is very intentional. (Thanks ImbaKnugel)

						Disjunctive Extraction - Allows the Enervation Dynamo to destroy enchanted items for RF.

					-New Item: Reservoir - It's a multi-bucket fluid "capacitor" of sorts.

					-Satchels now have filtering, auto-pickup, and inventory transfer. (Thanks ImbaKnugel)

				CHANGED:
					-Device recipes have been changed - more Iron, less Tin overall.
					-Fluid Transposer behavior has been adjusted to accommodate partial fills/drains.
					-Igneous Extruder and Glacial Precipitator have been rewritten and have new GUIs and backends.

				FIXED:
					-Charger and Transposer should no longer have "orphan" processes in some cases.

				REMOVED:
					-Substrate and Stratum augments.

		Thermal Foundation (went from ThermalFoundation-1.12.2-2.3.7.18-universal.jar to ThermalFoundation-1.12.2-2.3.10.6-universal.jar):
			Changelog retrieved from GitHub:
				2.3.10:
				GENERAL:
					-Internal refactors and optimizations.

				CHANGED:
					-Tome of Insight now accepts "Essence" and converts it to experience.

				2.3.9:
				ADDED:
					-Storage blocks for Charcoal and Coal Coke.

				CHANGED:
					-Tome of Insight operates in single level increments. Bad vanilla logic still accounted for.

				FIXED:
					-Addressed a few potential but highly unlikely NPEs with raytracing.

				2.3.8:
				ADDED:
					-Tools with AOE Breaking (Hammers) now display breaking effects on every block.

				CHANGED:
					-Hardened Glass is now Wither-proof.
					-Horse Armor recipes have been adjusted.
					-More Phyto-Gro is produced using charcoal.
					-Tome of Insight now takes/gives all XP at once, to correct for bad vanilla logic.

				FIXED:
					-Corrected some colors on bow sprites.
					-Enchants are more consistent with respect to vanilla tool types.

		Toast Control (went from Toast Control-1.12-1.2.0.jar to Toast Control-1.12.2-1.3.0.jar):
			Toast Control-1.12.2-1.2.1.jar:
				Changed the defaults to block advancement toasts.
			Toast Control-1.12.2-1.3.0.jar:
				Adds the ability to set a minimum time for toasts to be present on-screen.
				Adds the ability to make the toast background translucent.
				Adds the ability to make the toast background transparent.

				Requires Placebo 1.1.3+

		UniDict (went from UniDict-1.12.2-1.6.jar to UniDict-1.12.2-2.1.jar):
			UniDict-1.12.2-1.7.jar:
				New Integration: [Extreme Energy](https://minecraft.curseforge.com/projects/extreme-energy) (High-Pressure Crusher)
			UniDict-1.12.2-1.8.jar:
				New:

				 * added Black List for Entries/Kinds for KeepOneEntry.
				 * added support for the machine "Assembler" in Extreme Energy Integration.
			UniDict-1.12.2-1.9.jar:
				New: added an option to turn the Kind Blacklists in Whitelists as requested on [Issue#49](https://github.com/WanionCane/UniDict/issues/49)

				Warning: due to Tinker's Construct changes, UniDict needs to override the setting "S:orePreference" from tconstruct.cfg in order to make the "Indirect Integration" to work, this is likely to be temporary. fixes [Issue#59](https://github.com/WanionCane/UniDict/issues/59)
			UniDict-1.12.2-1.9b.jar:
				Fixed: Keep One Entry Blacksets/Whitesets.
			UniDict-1.12.2-2.0.jar:
				CraftTweaker support:
				mods.unidict.api.newShapedRecipeTemplate(String outputKind, int outputSize, String[][] shape); mods.unidict.api.newShapelessRecipeTemplate(String outpuKind, int outputSize, String[] inputs); mods.unidict.removalByKind.crafting(String kindName);
				the first two commands are for creating all the recipes for a given kind, per example, if you want change all you gear recipe to have a different shape, then now you don't need to create one by one.
				the last command will be used to remove recipes for a given kind, if you pass it the argument "block", then it will remove all the recipes for blocks, like iron block, copper block, gold block, and etc... (I will expand this on the future for mod machines)

				for more informations about CraftTweaker support, check out [this](https:/linkout?remoteUrl=https%253a%252f%252fwww.reddit.com%252fr%252ffeedthebeast%252fcomments%252f7n1cg5%252funidict_112220_update%252f).

				New: a config option to blacklist some ItemStacks in Unification (resquested by [lanse505](https://minecraft.curseforge.com/members/lanse505))

				there are several other improvements on the source code, like resource gathering, but including all them here would make this changelog huge =D

				so, I guess this is the last version of UniDict... for 2017! I wish you all a Happy new year!
			UniDict-1.12.2-2.1.jar:
				1.12.2-2.1:

				 * now resource gathering is made on init (this will need some testing in order to see if there are no negative effects.).
				 * now you can pass null values (for the shape)for the CraftTweaker command mods.unidict.api.newShapedRecipeTemplate() without crashing.
				 * added a new "RemoveByKind" CT command, "furnace".
				 * added the method "addItemStackToBlackList" to UniDictAPI, this will allow modders to "blacklist" ItemStacks directly (resquested by [SkySom](https://minecraft.curseforge.com/members/SkySom))
				 *
				added an optional parameter "String[] resourceKindWhiteList" for the CT commands "mods.unidict.removalByKind,get("crafting")" and "mods.unidict.removalByKind,get("furnace")"
				is is basically a filter, that it will remove recipes only when the resource contains the given "kinds".

				 * changed how to use the "RemovalByKind" CT Command

				now it is needed to "get" the wanted "RemovalByKind".
				usage:
				"val removalByKind = mods.unidict.removalByKind.get("removalByKindName")""
				it will return the wanted "RemovalByKind" instance, as long you passed it a valid value.
				currently the valid values are ("crafting", "furnace")
				(probably I will have to create a "wiki" to explain better these things XD)

				added another "RemovalByKind", the "furnace"
				it works exactly as the "crafting" variant, but for the furnace.

				after getting the "RemovalByKind" instance, in order to use, use the "remove" method, it uses the same arguments as before.

				isn't required to store the "instance" in a val, but if you want to use it several times, it is better.

		ValkyrieLib (went from valkyrielib-1.12.2-2.0.5a.jar to valkyrielib-1.12.2-2.0.6b.jar):
			valkyrielib-1.12.2-2.0.6a.jar:
				Fixed multiblock null owner causing people to not be able to dismantle their multiblocks from older versions.
			valkyrielib-1.12.2-2.0.6b.jar:
				No changelog provided

		WanionLib (went from WanionLib-1.12.2-1.3.jar to WanionLib-1.12.2-1.4.jar):
			WanionLib-1.12.2-1.4.jar:
				No changelog provided

		XNet (went from xnet-1.12-1.5.0.jar to xnet-1.12-1.6.7.jar):
			xnet-1.12-1.6.0.jar:
				1.6.0:

				 * This version requires McJtyLib 2.6.0!
			xnet-1.12-1.6.1.jar:
				1.6.1:

				 * Implemented extract mode for item channels: first (like it was in the past), random slot, or round robin
				 * Doubled the speed of item connectors (5 ticks per operation for advanced and 10 ticks per operation for normal)
				 * New version of the redstone proxy block that can do block updates
				 * The controller GUI will sort connected blocks based on block name
				 * Implemented tooltips for the filtered items in item connectors
			xnet-1.12-1.6.2.jar:
				1.6.2:

				 * Fixed a bug in handling of old connectors for XNet causing lots of log spam and loss of TPS
			xnet-1.12-1.6.3.jar:
				1.6.3:

				 * Second attempt to fix the TPS lag. This version should be ok now
			xnet-1.12-1.6.4.jar:
				1.6.4:

				 * Needs McJtyLib 2.6.2
				 * Uses McJtyLib way to render tooltips for items in filter
				 * New API so that other mods can 'connect' their blocks to XNet connectors
				 * Fixed a crash that could occur in item channels
				 * Improved how 'random' works. It will now really select evenly among all non-empty input slots
			xnet-1.12-1.6.5.jar:
				1.6.5:

				 * Fixed another division by zero
			xnet-1.12-1.6.6.jar:
				1.6.6:

				 * Fixed a problem with advanced connectors still being limited to 1000mb instead of 5000mb for liquids
				 * Changes to the IConnectable API
			xnet-1.12-1.6.7.jar:
				1.6.7:

				 * Bug fix with setting integer values in guis
				 * Fixed double tooltips in gui of controller

	Removed:
		- Inventory Sorter

	Went from Forge 1.12.2-14.23.1.2566 to 1.12.2-14.23.1.2587.
