package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void shouldLowerANormalItemsSellInAndQualityValues() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 20) };
        GildedRose app = new GildedRose(items);
        passDays(app, 2);
        assertEquals(8, items[0].sellIn);
        assertEquals(18, items[0].quality);

        passDays(app, 8);
        assertEquals(0, items[0].sellIn);
        assertEquals(10, items[0].quality);

        passDays(app, 1);
        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    void shouldLowerQualityTwiceAsFastAsNormalItemsIfConjured() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 12) };
        GildedRose app = new GildedRose(items);
        passDays(app, 1);
        assertEquals(2, items[0].sellIn);
        assertEquals(10, items[0].quality);

        passDays(app, 2);
        assertEquals(0, items[0].sellIn);
        assertEquals(6, items[0].quality);

        passDays(app, 1);
        assertEquals(-1, items[0].sellIn);
        assertEquals(2, items[0].quality);

        passDays(app, 1);
        assertEquals(-2, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void shouldNotDecreaseInQualityAndSellInIfLegendary() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        passDays(app, 3);
        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void shouldIncreaseInQualityUntilItReaches50IfAgedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 0) };
        GildedRose app = new GildedRose(items);

        passDays(app, 2);
        assertEquals(0, items[0].sellIn);
        assertEquals(2, items[0].quality);

        passDays(app, 50);
        assertEquals(-50, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @ParameterizedTest
    @CsvSource({
        "15,1,3,12,4",
        "12,4,5,7,12",
        "7,12,6,1,28",
        "1,28,1,0,31",
        "0,31,1,-1,0",
    })
    void shouldGraduallyIncreaseInQualityUntilConcertDateIfBackStagePass(int Sellin, int Quality, int days, int expectedSellinValue, int expectedQualityValue){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", Sellin, Quality) };
        GildedRose app = new GildedRose(items);

        passDays(app, days);
        assertEquals(expectedSellinValue, items[0].sellIn);
        assertEquals(expectedQualityValue, items[0].quality);

        // days till concert    | quality
        // 15                   | 1 (+1)
        // 14                   | 2 (+1)
        // 13                   | 3 (+1)
        // 12                   | 4 (+1)
        // 11                   | 5 (+1)
        // 10                   | 6 (+2)
        // 9                    | 8 (+2)
        // 8                    | 10
        // 7                    | 12
        // 6                    | 14
        // 5                    | 16
        // 4                    | 19
        // 3                    | 22
        // 2                    | 25
        // 1                    | 28
        // 0                    | 31
        // -1                    | 0
    }

    private void passDays(GildedRose app, int days) {
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
    }
}
