package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);


        final private int nDays;

        private Month(final int nDays) {
            this.nDays = nDays;
        }

        public static Month fromString(String s) {
            Objects.requireNonNull(s);
            s = s.toUpperCase(Locale.ROOT);
            int match = 0;
            Month matchMonth = null;
            for (final var month : Month.values()) {
                if (month.name().startsWith(s)) {
                    match++;
                    matchMonth = month;
                }
                if (month.name().equals(s)) {
                    return month;
                }
            }
            if (match == 1) {
                return matchMonth;
            } else {
                throw new IllegalArgumentException();
            }            
        }
    }

    public static class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2); 
            return m1.ordinal() - m2.ordinal();           
        }
    }

    public static class SortByDate implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);
            return m1.nDays - m2.nDays;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
