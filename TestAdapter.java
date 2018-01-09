public class TestAdapter extends HKBaseRecyclerAdapter<TestItem> {

    private final int NAME_VIEW = R.id.name_view;
    private final int AGE_VIEW = R.id.age_view;
    
    @Override
    protected int[] setClickableChilds() {
        return new int[]{NAME_VIEW};
    }

    @Override
    protected int[] setChildsToInflate() {
        return new int[]{NAME_VIEW, AGE_VIEW};
    }

    @Override
    protected int onCreateLayout() {
        return R.layout.row_item;
    }

    @Override
    protected void onBind(TestItem item, HKViewHolder viewHolder) {
        TextView nameView = (TextView) viewHolder.getView(NAME_VIEW);
        TextView ageView = (TextView) viewHolder.getView(AGE_VIEW);

        if (item != null) {
            nameView.setText(item.getName());
            ageView.setText(String.valueOf(item.getAge()));
        }
    }
}
