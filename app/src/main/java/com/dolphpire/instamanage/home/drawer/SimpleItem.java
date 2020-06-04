package com.dolphpire.instamanage.home.drawer;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dolphpire.instamanage.R;

public class SimpleItem extends DrawerItem<SimpleItem.ViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private int selectedTextSize;
    private int textSize;

    private int selectedIconSize;
    private int iconSize;

    private Drawable icon;
    private String title;

    public SimpleItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_home_drawer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);

//        holder.title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
//        holder.title.setTextSize(isChecked ? selectedTextSize : textSize);
//        holder.icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);
//        holder.icon.getLayoutParams().height = isChecked ? selectedIconSize : iconSize;
//        holder.icon.getLayoutParams().width = isChecked ? selectedIconSize : iconSize;
        holder.llItemBackground.setBackgroundResource(getBackground(isChecked));
    }

    private int getBackground(boolean isChecked) {
        int background;
        if(isChecked) {
            background = R.drawable.background_item_drawer_selected;
        } else {
            background = R.drawable.background_item_drawer;
        }
        return background;
    }

    public SimpleItem withSelectedIconTint(int selectedItemIconTint) {
        this.selectedItemIconTint = selectedItemIconTint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int selectedItemTextTint) {
        this.selectedItemTextTint = selectedItemTextTint;
        return this;
    }

    public SimpleItem withSelectedTextSize(int selectedTextSize) {
        this.selectedTextSize = selectedTextSize;
        return this;
    }

    public SimpleItem withSelectedIconSize(int selectedIconSize) {
        this.selectedIconSize = selectedIconSize;
        return this;
    }

    public SimpleItem withIconTint(int normalItemIconTint) {
        this.normalItemIconTint = normalItemIconTint;
        return this;
    }

    public SimpleItem withTextTint(int normalItemTextTint) {
        this.normalItemTextTint = normalItemTextTint;
        return this;
    }

    public SimpleItem withTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public SimpleItem withIconSize(int iconSize) {
        this.iconSize = iconSize;
        return this;
    }

    static class ViewHolder extends DrawerAdapter.ViewHolder {

        private ImageView icon;
        private TextView title;
        private LinearLayout llItemBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            llItemBackground = itemView.findViewById(R.id.llItemBackground);
        }
    }
}
