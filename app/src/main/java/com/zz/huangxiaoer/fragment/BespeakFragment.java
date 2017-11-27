package com.zz.huangxiaoer.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.huangxiaoer.R;
import com.zz.huangxiaoer.activity.AddressActivity;
import com.zz.huangxiaoer.base.BaseFragment;
import com.zz.huangxiaoer.utils.CommonUtils;
import com.zz.huangxiaoer.views.ShowingPage;

/**
 * Created by ${韩永光} on on 2017/11/17 0017 15:44..
 */

public class BespeakFragment extends BaseFragment {

    private TextView tv_bespeakfragment;


    @Override
    protected void onload() {
        CommonUtils.runOnUIthread(new Runnable() {
            @Override
            public void run() {
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);

                tv_bespeakfragment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                          CommonUtils.startActivity(getActivity(), AddressActivity.class);
                    }
                });
            }
        });
    }

    @Override
    public View CreateSuccessView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_bespeak, null);
        tv_bespeakfragment = (TextView) view.findViewById(R.id.tv_bespeakfragment);
        return view;
    }


}
