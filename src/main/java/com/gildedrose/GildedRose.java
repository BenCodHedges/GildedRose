package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemType realItem = getItemType(item);
            realItem.updateItem(item);
        }
    }

    private ItemType getItemType(Item item) { // Belongs in Item Class but the Goblin won't let us 👺
        if (item.name.contains("Aged Brie")) {
            return new AgedBrieItem();
        }

        if (item.name.contains("Backstage passes")) {
            return new BackstagePassesItem();
        }

        if (item.name.contains("Conjured")) {
            return new ConjuredItem();
        }

        if (item.name.contains("Sulfuras")) {
            return new SulfurasItem();
        }

        return new NormalItem();
    }
}
