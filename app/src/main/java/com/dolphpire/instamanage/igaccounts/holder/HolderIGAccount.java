package com.dolphpire.instamanage.igaccounts.holder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.igaccounts.adapter.AdapterIGAccount;
import com.dolphpire.instamanage.igaccounts.model.ModelIGAccount;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderIGAccount extends RecyclerView.ViewHolder {

    @BindView(R.id.rlIGAccount)
    RelativeLayout rlIGAccount;
    @BindView(R.id.rlAddAccount)
    RelativeLayout rlAddAccount;
    @BindView(R.id.rlTermsPrivacy)
    RelativeLayout rlTermsPrivacy;
    @BindView(R.id.imvIGUser)
    CircleImageView imvIGUser;
    @BindView(R.id.txtIGUsername)
    TextView txtIGUsername;
    @BindView(R.id.imvDelete)
    ImageView imvDelete;
    @BindView(R.id.imvSelected)
    ImageView imvSelected;
    private ModelIGAccount mModelIGAccount;
    private AdapterIGAccount.OnItem listener;
    private int position;

    public HolderIGAccount(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void setContent(ModelIGAccount mModelIGAccount, AdapterIGAccount.OnItem listener, int position) {

        this.mModelIGAccount = mModelIGAccount;
        this.listener = listener;
        this.position = position;

        rlIGAccount.setVisibility(View.GONE);
        rlAddAccount.setVisibility(View.GONE);
        rlTermsPrivacy.setVisibility(View.GONE);
        if (mModelIGAccount.getType() == 0) {
            loadIGAccount();
        } else if (mModelIGAccount.getType() == 1) {
            loadAddAccount();
        } else if (mModelIGAccount.getType() == 2) {
            loadTermsPrivacy();
        }

    }

    private void loadTermsPrivacy() {

        rlTermsPrivacy.setVisibility(View.VISIBLE);

    }

    private void loadAddAccount() {

        rlAddAccount.setVisibility(View.VISIBLE);

    }

    private void loadIGAccount() {

        rlIGAccount.setVisibility(View.VISIBLE);

        rlIGAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSelected(position);
                }
            }
        });

        imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}