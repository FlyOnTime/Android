package org.flyontime.android.ui.adapter

/**
 * Created by jossi on 18.06.2017.
 */

enum class CardType {
    CHECKIN {
        override fun toString(): String {
            return "Check-in online"
        }
    },
    PREPARE {
        override fun toString(): String {
            return "Prepare for the trip"
        }
    },
    COMMUTE {
        override fun toString(): String {
            return "Travel to the airport"
        }
    },
    BAGGAGEDROP {
        override fun toString(): String {
            return "Baggage drop-off"
        }
    },
    SECURITY {
        override fun toString(): String {
            return "Pass security check"
        }
    },
    SHOP {
        override fun toString(): String {
            return "Shop and relax"
        }
    },
    BOARD {
        override fun toString(): String {
            return "Board on the plane"
        }
    }
}
