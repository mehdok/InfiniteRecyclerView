A RecyclerView That handle scrolling and calling an interface for loading more item or toggling the ui based on user scroll direction.

## Installation
**Gradle config:** comming soon ...

## Usage
1) Use the View in you xml layout like this:
```xml
<com.mehdok.views.InfiniteRecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:triggerAmount="0.4"
        />
```

* triggerAmount: is the amount of scrolling that after this amount the `loadMoreContent()` will getting called.
it must be between 0 and 1 and 0.75 is preferable.

2) implement `InfiniteScrollListener` and `UiToggleListener` as you need.

*for an example look the app.*
