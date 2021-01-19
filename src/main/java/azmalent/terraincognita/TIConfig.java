package azmalent.terraincognita;

import azmalent.cuneiform.lib.config.Category;
import azmalent.cuneiform.lib.config.CommonConfigFile;
import azmalent.cuneiform.lib.config.annotations.Comment;
import azmalent.cuneiform.lib.config.annotations.Name;
import azmalent.cuneiform.lib.config.options.BooleanOption;
import azmalent.cuneiform.lib.config.options.DoubleOption;
import azmalent.cuneiform.lib.config.options.IntOption;

import java.util.Random;

public class TIConfig extends CommonConfigFile {
    protected TIConfig() {
        super(TerraIncognita.MODID);
    }

    public static void init() {
        new TIConfig().register();
    }

    public static class Food extends Category {
        @Name("Fern Fiddlehead Enabled")
        @Comment({"Fern fiddleheads can be found when breaking ferns. You can eat them raw or add them to suspicious stews.",
            "Duration of suspicious stew's effect is doubled when a fiddlehead is added during or after cooking.",
            "If the effect is negative, duration is halved instead."
        })
        public static BooleanOption fiddlehead = new BooleanOption(true, "fiddlehead");

        @Name("Taffy Enabled")
        @Comment("Taffy is a food item that can only be found in dungeons. " +
                "It restores some health, but also makes your mouth sticky, reducing eating speed with every taffy eaten.")
        public static BooleanOption taffy = new BooleanOption(true);

        @Name("Taffy Healing Amount")
        @Comment("How much half hearts are healed when eating a taffy.")
        public static IntOption taffyHealing = new IntOption(3, 2, 8);

        @Name("Notch Carrot Enabled")
        @Comment("Enchanted golden carrot grants 2 minutes of Absorption IV and Speed II, and 10 minutes of Night Vision when eaten.")
        public static BooleanOption notchCarrot = new BooleanOption(true);

        @Name("Berry Sorbet Enabled")
        @Comment("Berry Sorbet is a new food item made with sweet berries, sugar and a snowball in a bowl.")
        public static BooleanOption berrySorbet = new BooleanOption(true, "sorbet");
    }

    public static class Flora extends Category {
        @Name("Dandelion Puff Enabled")
        @Comment("Dandelion puffs spawn among vanilla dandelions. You can blow on a dandelion puff by using it.")
        public static BooleanOption dandelionPuff = new BooleanOption(true, "dandelion_puff");

        @Name("Dandelion Puff Ratio")
        @Comment("Chance to replace a vanilla dandelion with a dandelion puff during generation.")
        public static DoubleOption dandelionPuffChance = new DoubleOption(0.5, 0, 1);

        @Name("Forget-me-not Enabled")
        @Comment("Forget-me-not is a light blue flower found in warm and temperate plains, swamps and forests.")
        public static BooleanOption forgetMeNot = new BooleanOption(true, "forget_me_not");

        @Name("Marigold Enabled")
        @Comment({"Marigold is a large orange flower found in biomes such as savannas, deserts and mesas.",
            "It is unique in that it can grow on sand, but only if there is water nearby."})
        public static BooleanOption marigold = new BooleanOption(true, "marigold");

        @Name("Edelweiss Enabled")
        @Comment("Edelweiss is a small white flower found high in temperate and cold mountains. It is so hardy it can grow on gravel.")
        public static BooleanOption edelweiss = new BooleanOption(true, "edelweiss");

        @Name("Minimum Altitude for Edelweiss")
        @Comment("Minimum Y level where edelweiss can generate in the mountains.")
        public static IntOption edelweissMinimumY = new IntOption(80, 64, 128);

        @Name("Iris Enabled")
        @Comment("Iris is a majestic flower found in jungles. It comes in blue and purple colors.")
        public static BooleanOption iris = new BooleanOption(true, "iris");

        @Name("Small Lilypads Enabled")
        @Comment("Small lilypads can be found in swamps. They can be stacked up to 4, like sea pickles.")
        public static BooleanOption smallLilypad = new BooleanOption(true);

        @Name("Lotus Enabled")
        @Comment("Lotus is a beautiful aquatic flower found in jungles. It comes in yellow, pink and white colors.")
        public static BooleanOption lotus = new BooleanOption(true, "lotus");

        @Name("Fireweed Enabled")
        @Comment("Fireweed is a purple flower found in cold biomes.")
        public static BooleanOption fireweed = new BooleanOption(true, "fireweed");

        @Name("Arctic Poppy Enabled")
        @Comment("Arctic poppy is a yellow variant of poppy that replaces vanilla poppies in tundra biomes.")
        public static BooleanOption arcticPoppy = new BooleanOption(true, "arctic_poppy");

        @Name("Reeds and Wicker Enabled")
        @Comment({"Reeds are a sugarcane-like plant that can be found in swamps. They can be grown in the same way as sugar cane, or, alternatively, in one block deep water.",
            "Reeds can be crafted into a variety of decorative wicker blocks and baskets.",
            "Flower baskets can store 9 stacks of flowers and will automatically collect them if you hold it while picking up flowers."})
        public static BooleanOption reeds = new BooleanOption(true, "reeds");

        @Name("Wreaths Enabled")
        @Comment({"Wreath is a cosmetic headdress crafted with 4 small flowers of any kind in any shape.",
            "The color of the wreath depends on the flowers you used to craft it."})
        public static BooleanOption flowerBand = new BooleanOption(true, "flower_band");
    }

    public static class Tools extends Category {
        @Name("Caltrops Enabled")
        @Comment({"Caltrops are metal spikes that can be placed on the ground. Dropped caltrops will also place themselves if possible.",
            "Caltrops deal one heart of damage and inflict slowness for 10 seconds when touched.",
            "They break with 1 in 5 chance after dealing damage, so they are not suitable as a permanent trap.",
            "Caltrops will be flushed away by flowing water.",
            "You can right click caltrops with an empty hand to pick them up."})
        public static BooleanOption caltrops = new BooleanOption(true, "caltrops");
    }

    public static class Biomes extends Category {
        @Name("Tundra Variants")
        @Comment("Adds snowless and rocky tundra biomes.")
        public static BooleanOption tundraVariants = new BooleanOption(true);
    }

    public static class Tweaks extends Category {
        @Name("Better Tundras")
        @Comment("Adds foxes, sheep, berry bushes, birches and shrubs to tundras to make them less lackluster.")
        public static BooleanOption betterTundras = new BooleanOption(true);

        @Name("Additional Compostables")
        @Comment("Adds composting recipes for dead bushes, bamboo, poisonous potatoes and chorus fruits/flowers.")
        public static BooleanOption additionalCompostables = new BooleanOption(true);

        @Name("Bonemeal Lilypad Growing")
        @Comment("Allows growing lilypads by using bonemeal in shallow water pools.")
        public static BooleanOption bonemealLilypadGrowing = new BooleanOption(true);

        @Name("Wither Rose Generation")
        @Comment("If enabled, wither roses will generate naturally in Soul Sand Valleys.")
        public static BooleanOption witherRoseGeneration = new BooleanOption(true);
    }
}