package org.flyontime.android.ui.adapter;

/**
 * Created by jossi on 18.06.2017.
 */

public enum CardType {
    CHECKIN {
        @Override
        public String toString() {
            return "Check-in online";
        }
    },
    PREPARE {
        @Override
        public String toString() {
            return "Prepare for the trip";
        }
    },
    COMMUTE {
        @Override
        public String toString() {
            return "Travel to the airport";
        }
    },
    BAGGAGEDROP {
        @Override
        public String toString() {
            return "Baggage drop-off";
        }
    },
    SECURITY {
        @Override
        public String toString() {
            return "Pass security check";
        }
    },
    SHOP {
        @Override
        public String toString() {
            return "Shop and relax";
        }
    },
    BOARD {
        @Override
        public String toString() {
            return "Board on the plane";
        }
    }
}
