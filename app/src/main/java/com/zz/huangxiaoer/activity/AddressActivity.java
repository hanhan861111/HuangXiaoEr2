package com.zz.huangxiaoer.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zz.huangxiaoer.R;
import com.zz.huangxiaoer.bean.Tinfo;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.R.attr.name;
import static android.R.id.list;


public class AddressActivity extends AutoLayoutActivity implements TencentLocationListener, EasyPermissions.PermissionCallbacks {


    private ListView lv;
    private TencentLocationRequest request;
    private TencentLocationManager mLocationManager;
    private String address;
    private List<TencentPoi> poiList;
    private Button bt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressctivity);
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE

        };

        if (EasyPermissions.hasPermissions(this, permissions)) {

        } else {
            EasyPermissions.requestPermissions(this, "必要的权限", 0, permissions);

        }
        request = TencentLocationRequest.create();
        TencentLocationRequest tencentLocationRequest = request.setRequestLevel(4);
        request.setInterval(50000);//设置定位时间间隔
        request.setAllowGPS(true); //当为false时，设置不启动GPS。默认启动
        request.setAllowIndoorLocation(true);  //设置启动室内定位，默认不启动
        mLocationManager = TencentLocationManager.getInstance(this);
        //这一行执行不了   开启定位失败
       mLocationManager.requestLocationUpdates(request, this);//开启定位，其中的实现函数是TencentLocationListener，
//        int error = mLocationManager.requestLocationUpdates(request, this);
//        Log.e("TTT", error + "");
//        Log.e("TTT", "开始定位: " + request + ", 坐标系=");
//        Log.e("TTT", "注册监听状态： " + (error == 0 ? "成功" : "失败"));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        Log.e("TTT", "获取权限" + list);
    }

    //成功

    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        Log.e("TTT", "获取成功的权限" + list);

    }

    //失败

    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        Log.e("TTT", "获取失败的权限" + list);
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {

        if (tencentLocation.ERROR_OK == i) {
            // 定位成功
            //构建坐标
            Log.e("TTT", "定位成功1111");
            //获取poi商圈,其中tencentpoi对象包含 名字,地址,坐标,方向,距离
            poiList = tencentLocation.getPoiList();
            address = tencentLocation.getAddress();
            Tinfo info = new Tinfo();
            info.setAddress(address);
            Log.e("TTT", poiList.get(1).getAddress());

        } else {
            // 定位失败
            Log.d("TTT", "定位失败");
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        String message = "{name=" + name + ", new status=" + i + ", desc="
                + s1 + "}";

        if (i == STATUS_DENIED) {
            /* 检测到定位权限被内置或第三方的权限管理或安全软件禁用, 导致当前应用**很可能无法定位**
             * 必要时可对这种情况进行特殊处理, 比如弹出提示或引导
			 */
            Log.e("TTT", "定位权限被禁用!");
            Toast.makeText(this, "定位权限被禁用!", Toast.LENGTH_SHORT).show();
        }
        Log.e("TTT", message);
    }


    private void adapterr() {
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return poiList.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView;
                if (view == null) {
                    view = View.inflate(AddressActivity.this, R.layout.item_map, null);
                }
                TextView item_tv = (TextView) view.findViewById(R.id.tv_map);
                item_tv.setText(poiList.get(i).getAddress());
                return view;
            }
        });
    }

}