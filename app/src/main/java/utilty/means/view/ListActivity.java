package utilty.means.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.ContextCompatApi24;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.List;

import utilty.means.R;
import utilty.means.model.WordBean;
import utilty.means.presenter.ListPresenter;
import utilty.means.presenter.ListPresenterImpl;

public class ListActivity extends AppCompatActivity implements ListView, View.OnClickListener
{

    ListPresenter mPresenter;
    RecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;
    private Button mRetry;
    private RecyclerView mRecyclerView;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter =new ListPresenterImpl(this,this);
        mPresenter.onCreate();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mPresenter.onStop();

    }

    @Override
    public void initializeView()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mRetry=(Button)findViewById(R.id.btnRetry);
        mRetry.setOnClickListener(this);
        mContext=this;
    }

    @Override
    public void displayProgress(int visibility)
    {
        mProgressBar.setVisibility(visibility);
    }

    @Override
    public void displayRetry(int visibility)
    {
        mRetry.setVisibility(visibility);
    }

    @Override
    public void setListAdapter(List<WordBean> listItems)
    {
        if(mAdapter==null)
        {
            mAdapter = new RecyclerAdapter(mContext, listItems, mPresenter);
            mRecyclerView.setAdapter(mAdapter);
        }
        else
        {
            refereshAdapter(listItems);
        }
    }

    @Override
    public void refereshAdapter(List<WordBean> listItems)
    {
        mAdapter.setItemList(listItems);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view)
    {
        if(view==mRetry)
        {
            mPresenter.onClickRetry();
        }
    }
}
