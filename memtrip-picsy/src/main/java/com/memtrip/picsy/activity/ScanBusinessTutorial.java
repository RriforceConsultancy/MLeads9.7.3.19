package com.memtrip.picsy.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.memtrip.picsy.R;

/**
 * Created by Triforceimac3 on 15/05/15.
 */
public class ScanBusinessTutorial extends Activity {


    Button btnCancel = null;

    CustomPagerAdapter mCustomPagerAdapter = null;
    ViewPager mViewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanbusiness_tutorial);

        btnCancel = (Button)findViewById(R.id.btnCancel);

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mCustomPagerAdapter = new CustomPagerAdapter(this);
        mViewPager.setAdapter(mCustomPagerAdapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
	        /*Button btnSkip = (Button)itemView.findViewById(R.id.btnSkip);
	        Button btngetStarted = (Button)itemView.findViewById(R.id.btngetStarted);*/

            if(position == 0){
                imageView.setBackgroundResource(R.drawable.tip_1);
            }else if (position == 1){
                imageView.setBackgroundResource(R.drawable.tip_2);
            }else if (position == 2){
                imageView.setBackgroundResource(R.drawable.tip_3);
            }





            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }
}
