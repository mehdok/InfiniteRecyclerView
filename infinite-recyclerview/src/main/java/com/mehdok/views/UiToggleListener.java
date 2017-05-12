package com.mehdok.views;

/**
 * @author mehdok (mehdok@gmail.com) on 5/12/2017.
 */

public interface UiToggleListener {
    /**
     * Called when the recycler view scrolled upward
     * you can use it to show some ui element like fab
     */
    void showUI();

    /**
     * Called when the recycler view scrolled downward
     * you can use it to hide some ui element like fab
     */
    void hideUI();
}
