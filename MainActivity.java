public class MainActivity extends AppCompatActivity implements HKBaseRecyclerAdapter.OnRowClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<TestItem> items = new ArrayList<>();
        for (int i = 0; i< 100;i++){
            TestItem item1 = new TestItem();
            item1.setAge(i);
            item1.setName("Mihi");
            items.add(item1);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        TestAdapter adapter = new TestAdapter();
        adapter.setItems(items);
        HKBaseRecyclerAdapter.OnRowClickListener onRowClickListener = this;
        adapter.setOnRowClickListener(onRowClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
    
    @Override
    public void onRowClicked(View view, int position, Object item, boolean isChildView) {
     
    }
}
