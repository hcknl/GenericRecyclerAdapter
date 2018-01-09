public class TestAdapter extends HKBaseRecyclerAdapter<TestItem> {

    @Override
    protected int[] setClickableChilds() {
        return new int[]{R.id.name_view};
    }

    @Override
    protected int[] setChildsToInflate() {
        return new int[]{R.id.age_view, R.id.name_view};
    }

    @Override
    protected int onCreateLayout() {
        return R.layout.row_item;
    }

    @Override
    protected void onBind(TestItem item, HKViewHolder viewHolder) {
        TextView nameView = (TextView) viewHolder.getView(R.id.name_view);
        TextView ageView = (TextView) viewHolder.getView(R.id.age_view);

        if (item != null) {
            nameView.setText(item.getName());
            ageView.setText(String.valueOf(item.getAge()));
        }
    }
}
