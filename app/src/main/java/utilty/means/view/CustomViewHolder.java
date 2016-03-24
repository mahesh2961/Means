package utilty.means.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import utilty.means.R;

/**
 * Created by mahesh on 24/3/16.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder
{
    protected ImageView imageView;
    protected TextView textWord;
    protected TextView textMeaning;


    public CustomViewHolder(View view)
    {
        super(view);
        this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
        this.textWord = (TextView) view.findViewById(R.id.txtWord);
        this.textMeaning = (TextView) view.findViewById(R.id.txtMeaning);

    }
}
