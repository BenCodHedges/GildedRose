package com.gildedrose;

import static com.gildedrose.ItemUtils.*;

public class BackstagePassesItem implements ItemType {

    @Override
    public void updateItem(Item item) {
        increaseQuality(item);

        if (item.sellIn < 11) {
            increaseQuality(item);
        }

        if (item.sellIn < 6) {
            increaseQuality(item);
        }

        decreaseSellIn(item);

        if (item.sellIn < 0) {
            setQualityToZero(item);

        }

        checkQualityLimits(item);
    }
}
