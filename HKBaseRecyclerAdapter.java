public abstract class HKBaseRecyclerAdapter<T> extends RecyclerView.Adapter<HKViewHolder> implements HKViewHolder.HKViewHolderClickListener {

    protected abstract @LayoutRes int onCreateLayout();

    protected abstract @IdRes int[] setChildsToInflate();

    protected abstract @IdRes int[] setClickableChilds();

    protected abstract void onBind(T item, HKViewHolder viewHolder);

    private List<T> items = new ArrayList<>();

    private OnRowClickListener onRowClickListener;
    private HKViewHolder.Clickables clickables = HKViewHolder.Clickables.NONE;

    public interface OnRowClickListener {
        void onRowClicked(View view, int position, Object item, boolean isChildView);
    }

    public HKBaseRecyclerAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public HKViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(onCreateLayout(), viewGroup, false);
        HKViewHolder.HKViewHolderClickListener viewHolderClickListener = this;
        return HKViewHolder.constructWithParameters(view, viewHolderClickListener, setChildsToInflate(), setClickableChilds(), clickables);
    }

    @Override
    public void onBindViewHolder(HKViewHolder holder, int position) {
        onBind(getItem(position), holder);
    }

    @Override
    public void onViewHolderClick(View view, int position, boolean isChildView) {
        if (onRowClickListener != null) {
            onRowClickListener.onRowClicked(view, position, getItem(position), isChildView);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public T getItem(int index) {
        return (index < items.size() ? items.get(index) : null);
    }

    public void setItems(List<T> items) {
        if (items != null) {
            this.items = items;
        }
    }

    public void setOnRowClickListener(OnRowClickListener onRowClickListener, HKViewHolder.Clickables clickables) {
        this.onRowClickListener = onRowClickListener;
        this.clickables = clickables;
    }

    public void setOnRowClickListener(OnRowClickListener onRowClickListener) {
        setOnRowClickListener(onRowClickListener, HKViewHolder.Clickables.NONE);
    }
}