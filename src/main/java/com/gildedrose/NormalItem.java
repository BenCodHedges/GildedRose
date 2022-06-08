package com.gildedrose;

import static com.gildedrose.ItemUtils.*;

public class NormalItem implements ItemType {
    @Override
    public void updateItem(Item item) {

        decreaseQuality(item);
        decreaseSellIn(item);
        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
        checkQualityLimits(item);

    }
}
