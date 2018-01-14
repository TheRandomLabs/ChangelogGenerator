# ChangelogGenerator
Uses [CurseAPI-Minecraft](https://github.com/TheRandomLabs/CurseAPI-Minecraft) (which uses [CurseAPI](https://github.com/TheRandomLabs/CurseAPI)) to generate changelogs.

It takes two files as input - `old.json` and `new.json`. `old.json` is the `manifest.json` of the old modpack version, and `new.json` is the `manifest.json` of the new modpack version.

If you want to use different files as input, you can do something like this:
	java -jar ChangelogGenerator.jar oldmanifest.json manifest.json

Obviously, it isn't perfect and might need some user input, but I tried to add as much mod compatibility as possible in CurseAPI-Minecraft, and it even supports TeamCoFH's GitHub changelogs.

Example changelog for FTB Revelation:

	FTB Revelation 1.2.0 to FTB Revelation 1.3.0

	Updated:
		Baubles (went from Baubles-1.12-1.5.1.jar to Baubles-1.12-1.5.2.jar):
			Baubles-1.12-1.5.2.jar:
				- API: added isBaubleEquipped helper method to BaublesAPI

				- fixed player bauble syncing (Thanks pau101) closes #235

		BdLib (went from BDLib 1.14.3.10 (MC 1.12.2) to BDLib 1.14.3.12 (MC 1.12.2)):
			BDLib 1.14.3.11 (MC 1.12.2):
				bdew: Prevent HasTE from corrupting TE's of unrelated blocks if passed an
			BDLib 1.14.3.12 (MC 1.12.2):
				bdew: partially reverted the check in HasTE - causes issues in e.g. blockBreak

		Binnie's Mods (went from binnie-mods-1.12.2-2.5.0.110.jar to binnie-mods-1.12.2-2.5.0.111.jar):
			binnie-mods-1.12.2-2.5.0.111.jar:
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

		Chisel (went from Chisel - MC1.12-0.1.1.24 to Chisel - MC1.12-0.1.1.26):
			Chisel - MC1.12-0.1.1.26:
				- Fix issue where certain storage blocks (iron, lapis, etc) did not have an oredict name.

		CoFH Core (went from CoFHCore-1.12.2-4.3.9.2-universal.jar to CoFHCore-1.12.2-4.3.10.5-universal.jar):
			Changelog retrieved from GitHub:
				4.3.10:
				GENERAL:
					-Internal refactors and optimizations.

		Compact Machines (went from compactmachines3-1.12.2-3.0.4-b182.jar to compactmachines3-1.12.2-3.0.5-b184.jar):
			compactmachines3-1.12.2-3.0.5-b184.jar:
				Bugfix-Release for versions >= 3.0.3:

				Fix client crash when placing TEs near already set up Compact Machines with a Tunnel bound to the side the TE is placed on.

		Draconic Evolution (went from Draconic Evolution 1.12-2.3.7.278-universal to Draconic Evolution 1.12-2.3.8.279-universal):
			Draconic Evolution 1.12-2.3.8.279-universal:
				-Re added toggle flight key binding
				-Fixed entity path finding crash related to placed items.

		Exchangers (went from Exchangers-1.12.2-2.6.jar to Exchangers-1.12.2-2.6.2.jar):
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

		Forestry (went from forestry_1.12.2-5.7.0.219.jar to forestry_1.12.2-5.7.0.232.jar):
			forestry_1.12.2-5.7.0.232.jar:
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

		MalisisCore (went from malisiscore-1.12.2-6.3.0.jar to malisiscore-1.12.2-6.3.1.jar):
			malisiscore-1.12.2-6.3.1.jar:
				No changelog provided

		MalisisDoors (went from malisisdoors-1.12.2-7.2.2.jar to malisisdoors-1.12.2-7.2.3.jar):
			malisisdoors-1.12.2-7.2.3.jar:
				No changelog provided

		MineTogether (went from minetogether-1.10.2-1.8.6.jar to minetogether-1.10.2-1.8.7.jar):
			minetogether-1.10.2-1.8.7.jar:
				Fixed localisation on friends list key bind.

				Changed default key combination to Ctrl+M to avoid reported conflict with JEI.

		Natura (went from natura-1.12-4.3.1.35.jar to natura-1.12.2-4.3.2.42.jar):
			natura-1.12.2-4.3.2.39.jar:
				Bugfixes:

				 * Imp should spawn in any biome that is listed as "hell"
				 * BlockLeavesBase now extends BlockLeaves
				 * Sagurao's should generate more often now.
			natura-1.12.2-4.3.2.42.jar:
				Bugfixes:

				 * Leaves should now render correctly

		Pam's HarvestCraft (went from Pam's HarvestCraft 1.12.2b - Re-Balance Update.jar to Pam's HarvestCraft 1.12.2g.jar):
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

		Quark (went from Quark-r1.4-120.jar to Quark-r1.4-121.jar):
			Quark-r1.4-121.jar:
				- API: Added a way for IDropoffManager implementations to provide their own IItemHandler.
				- API: Increased API version to 2.
				- Automation: Fixed pistons not updating the amount of players viewing a chest's interface, leaving it to be open if it's moved while a player is looking at it.
				- General: Quark will no longer break Random Things' dyeing machine. (lumien231)
				- Management: Fixed the extract and restock buttons being dependant on Dropoff being enabled instead of Chest Buttons.
				- Tweaks: Added a config setting to Automatic Recipe Unlock to disable the recipe book entirely.
				- World: Fixed a crash if a type of world stone is enabled but the rarity set to 0.

		Redstone Arsenal (went from RedstoneArsenal-1.12.2-2.3.9.2-universal.jar to RedstoneArsenal-1.12.2-2.3.10.4-universal.jar):
			Changelog retrieved from GitHub:
				2.3.10:
				GENERAL:
					-Internal refactors and optimizations.

				FIXED:
					-Quiver can now be disabled.

		Steve's Carts Reborn (went from StevesCarts-1.12.2-2.4.13.69.jar to StevesCarts-1.12.2-2.4.14.72.jar):
			StevesCarts-1.12.2-2.4.14.72.jar:
				(gorymoon) #releaseBuild
				(gorymoon) A lot of system changes
				(github) Merge pull request #114 from SonarSonic/patch-1
				(github) Fixes NullPointerException in PacketFluidSync

		Tinkers Construct (went from TConstruct-1.12-2.8.1.49.jar to TConstruct-1.12.2-2.9.0.55.jar):
			TConstruct-1.12.2-2.9.0.55.jar:
				Requires Forge 2577
				Requires JEI 4.8 (if JEI is present)

				Changelog:

				* Seared Channel Recipe gives 3 now
				* Tools can be renamed anytime again!
				* Gasses now render upside down
				* TiC Slime Blocks now work with pistons

		Thermal Cultivation (went from ThermalCultivation-1.12.2-0.1.4.2-universal.jar to ThermalCultivation-1.12.2-0.1.5.3-universal.jar):
			Changelog retrieved from GitHub:
				0.1.5:
				GENERAL:
					-Internal refactors and optimizations.

		Thermal Dynamics (went from ThermalDynamics-1.12.2-2.3.9.3-universal.jar to ThermalDynamics-1.12.2-2.3.10.4-universal.jar):
			Changelog retrieved from GitHub:
				2.3.10:
				GENERAL:
					-Internal refactors and optimizations.

		Thermal Expansion (went from ThermalExpansion-1.12.2-5.3.9.6-universal.jar to ThermalExpansion-1.12.2-5.3.10.14-universal.jar):
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

		Thermal Foundation (went from ThermalFoundation-1.12.2-2.3.9.4-universal.jar to ThermalFoundation-1.12.2-2.3.10.6-universal.jar):
			Changelog retrieved from GitHub:
				2.3.10:
				GENERAL:
					-Internal refactors and optimizations.

				CHANGED:
					-Tome of Insight now accepts "Essence" and converts it to experience.

		ThutCore (went from thutcore-1.12.2-5.14.3.jar to thutcore-1.12.2-5.15.2.jar):
			thutcore-1.12.2-5.15.0.jar:
				overhaul of animation API.
			thutcore-1.12.2-5.15.1.jar:
				fixes some issues with teleporting to non-existant dimensions
			thutcore-1.12.2-5.15.2.jar:
				fixes some issues with limb based animations in animation api.

		Thut's Elevators (went from thuttech-1.12.2-6.0.11.jar to thuttech-1.12.2-6.0.12.jar):
			thuttech-1.12.2-6.0.12.jar:
				Adds an experimental "hackyRender" option which "fixes" the invisible elevator issue when it is on a sub-chunk boundary. This is false by default, set to true if you wish to use it in thuttech.cfg, and may cause undue frame drops if too many large elevators are used at once.

		XNet (went from xnet-1.12-1.6.4.jar to xnet-1.12-1.6.7.jar):
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

	Went from Forge 1.12.2-14.23.1.2573 to 1.12.2-14.23.1.2587.
