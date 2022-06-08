package com.gildedrose;

public class ItemUtils {
    private static final int MAX_NORMAL_ITEM_QUALITY = 50;
    private static final int MIN_NORMAL_ITEM_QUALITY = 0;

    public static void checkQualityLimits(Item item){
        if(item.quality > MAX_NORMAL_ITEM_QUALITY){
            item.quality = MAX_NORMAL_ITEM_QUALITY;
        }

        if(item.quality < MIN_NORMAL_ITEM_QUALITY){
            item.quality = MIN_NORMAL_ITEM_QUALITY;
        }
    }

    public static void decreaseQuality(Item item) {
        item.quality--;
    }

    public static void increaseQuality(Item item) {
        item.quality++;
    }

    public static void decreaseSellIn(Item item) {
        item.sellIn--;
    }

    public static void setQualityToZero(Item item) {
        item.quality = 0;
    }
}
