package com.shahrdari;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 15;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "خساپا", "1398-12-13", "2000 دلار", "", "(0.48%)");
    }

    public static class DummyItem {
        public final String id;
        public final String subject;
        public final String date;
        public final String price;
        public final String unit;
        public final String change;

        public DummyItem(String id, String subject, String date, String price, String unit, String change) {
            this.id = id;
            this.subject = subject;
            this.date = date;
            this.price = price;
            this.unit = unit;
            this.change = change;
        }

        @Override
        public String toString() {
            return subject;
        }
    }
}