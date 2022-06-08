package com.gildedrose;

import static com.gildedrose.ItemUtils.*;

public class AgedBrieItem implements ItemType {
    @Override
    public void updateItem(Item item) {
        increaseQuality(item);
        decreaseSellIn(item);
        checkQualityLimits(item);
    }
}
