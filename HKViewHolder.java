public class HKViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public enum Clickables {
        ALL, NONE, ONLY_CHILDS, ONLY_ROOT
    }

    public interface HKViewHolderClickListener {
        void onViewHolderClick(View view, int position, boolean isChildView);
    }

    private HKViewHolder(View view, HKViewHolderClickListener viewHolderClickListener, int[] childViewsToInflate, int[] clickableChilds, Clickables clickable) {
        super(view);
        views = new SparseArray<>();
        views.put(0, view);
        initViewList(childViewsToInflate);
        createClickListeners(view, viewHolderClickListener, clickableChilds, clickable);
    }

    private void createClickListeners(View view, AXViewHolderClickListener viewHolderClickListener, int[] clickableChilds, Clickables clickable) {
        switch (clickable) {
            case ALL:
                setClickListenerToViewHolder(view, viewHolderClickListener);
                setClickListenerToChilds(clickableChilds, viewHolderClickListener);
                break;
            case ONLY_CHILDS:
                setClickListenerToChilds(clickableChilds, viewHolderClickListener);
                break;
            case ONLY_ROOT:
                setClickListenerToViewHolder(view, viewHolderClickListener);
                break;
            default:
                break;
        }
    }

    public static HKViewHolder constructWithParameters(View view, HKViewHolderClickListener viewHolderClickListener, int[] childViewsToInflate, int[] clickableChilds, Clickables clickables) {
        return new HKViewHolder(view, viewHolderClickListener, childViewsToInflate, clickableChilds, clickables);
    }

    private void setClickListenerToViewHolder(View view, final HKViewHolderClickListener viewHolderClickListener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolderClickListener.onViewHolderClick(v, getAdapterPosition(), false);
            }
        });
    }

    private void setClickListenerToChilds(@IdRes int[] childViewIds, final HKViewHolderClickListener viewHolderClickListener) {
        if (childViewIds == null) return;

        for (int id : childViewIds) {
            View view = getView(id);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderClickListener.onViewHolderClick(v, getAdapterPosition(), true);
                }
            });
        }
    }

    private void initViewList(int[] idList) {
        if (idList == null) return;

        for (int id : idList) {
            initViewById(id);
        }
    }

    private void initViewById(int id) {
        View rootView = getRootView();
        View childView = null;
        if (rootView != null) {
            childView = rootView.findViewById(id);
        }
        if (childView != null)
            views.put(id, childView);
    }

    public View getRootView() {
        return getView(0);
    }

    public View getView(int id) {
        if (views.indexOfKey(id) >= 0) {
            return views.get(id);
        }
        return null;
    }
}
