package utilty.means.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import utilty.means.Application.MeansApplications;
import utilty.means.R;
import utilty.means.model.ListModel;
import utilty.means.model.WordBean;
import utilty.means.network.Network;
import utilty.means.network.NetworkImageListner;
import utilty.means.presenter.ListPresenter;
import utilty.means.utils.Utils;

/**
 * Created by mahesh on 24/3/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder>
{
    private List<WordBean> mItemList;
    private Context mContext;
    private ListPresenter mPresenter;


    public RecyclerAdapter(Context context, List<WordBean> listItems, ListPresenter presenter)
    {
        this.mItemList = listItems;
        this.mContext = context;
        this.mPresenter=presenter;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_card_view, null);
        Utils.configureCardView((CardView)view);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    public List<WordBean> getItemList()
    {
        return mItemList;
    }

    public void setItemList(List<WordBean> mListItem)
    {
        this.mItemList = mListItem;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, int i)
    {
        final WordBean word = mItemList.get(i);

        //Download image using picasso library
       /* */

        Picasso.with(mContext).load(mPresenter.getImageUrl(word.getId()))
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(customViewHolder.imageView);

        customViewHolder.textWord.setText(word.getWord());
        customViewHolder.textMeaning.setText("Means:"+word.getMeaning());

    }

    @Override
    public int getItemCount()
    {
        return (null != mItemList ? mItemList.size() : 0);
    }




}
