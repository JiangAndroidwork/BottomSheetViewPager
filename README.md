BottomSheetDialogFragment + ViewPager+Fragment+RecyclerView这种样式出现的不多，所以前一阵做这类需求的时候遇到种种问题。

主要的原因就是因为BottomSheetBehavior的findScrollingChild方法并没有有关ViewPager 更新查找子元素view的东西，所以它只能拿到一个页面去滑动，那么就需要对BottomSheetBehavior进行修改，这样的话就需要自己定义BottomSheetDialog。


private View findScrollingChild(View view) {
    if (view instanceof NestedScrollingChild) {
        return view;
    }
    if (view instanceof ViewGroup) {
        ViewGroup group = (ViewGroup) view;
        for (int i = 0, count = group.getChildCount(); i < count; i++) {
            View scrollingChild = findScrollingChild(group.getChildAt(i));
            if (scrollingChild != null) {
                return scrollingChild;
            }
        }
    }
    return null;
}

修改后的结果：


@VisibleForTesting
    View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) view;
            View currentViewPagerChild = viewPager.getChildAt(viewPager.getCurrentItem());
//            View currentViewPagerChild = ViewPagerUtils.getCurrentView(viewPager);
            if (currentViewPagerChild == null) {
                return null;
            }

            View scrollingChild = findScrollingChild(currentViewPagerChild);
            if (scrollingChild != null) {
                return scrollingChild;
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0, count = group.getChildCount(); i < count; i++) {
                View scrollingChild = findScrollingChild(group.getChildAt(i));
                if (scrollingChild != null) {
                    return scrollingChild;
                }
            }
        }
        return null;
    }

还有注意的一点是这种结合 RecyclerView不要在其外层加那种带手势一些事件拦截的ViewGroup，这样会有一些冲突。比如刷新组件SwipeRefreshLayout 和一些上拉加载的组件。如果想要实现上拉加载那就在RecyclerView本身上实现。
