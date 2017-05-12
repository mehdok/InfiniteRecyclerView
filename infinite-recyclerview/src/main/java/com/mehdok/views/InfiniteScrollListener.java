package com.mehdok.views;

/**
 * @author mehdok (mehdok@gmail.com) on 5/12/2017.
 */

public interface InfiniteScrollListener {
    /**
     * When the RecyclerView getting scrolled to the triggerAmount this method is getting called, so the end of the
     * list is near and you need to add more content
     */
    void loadMoreContent();
}
