package com.my.greendao_search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.my.greendao_search.bean.Case;
import com.my.greendao_search.dbmanager.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity------>";
    private EditText ed_MainAct;
    private Button btn_addData_MainAct, btn_addMoreData_MainAct,
            btn_upDataOneData_MainAct, btn_delOneData_MainAct,
            btn_queryOneOrMore_MainAct,btn_QueryBuilder_MainAct,
            btn_delAllData_MainAct;

    private CommonUtils commonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_MainAct = (EditText) findViewById(R.id.ed_MainAct);
        //listView_MainAct = (ListView) findViewById(R.id.listView_MainAct);
        btn_addData_MainAct = (Button) findViewById(R.id.btn_addData_MainAct);
        btn_addMoreData_MainAct = (Button) findViewById(R.id.btn_addMoreData_MainAct);
        btn_upDataOneData_MainAct = (Button) findViewById(R.id.btn_upDataOneData_MainAct);
        btn_delOneData_MainAct = (Button) findViewById(R.id.btn_delOneData_MainAct);
        btn_queryOneOrMore_MainAct= (Button) findViewById(R.id.btn_queryOneOrMore_MainAct);
        btn_QueryBuilder_MainAct= (Button) findViewById(R.id.btn_QueryBuilder_MainAct);
        btn_delAllData_MainAct= (Button) findViewById(R.id.btn_delAllData_MainAct);

        btn_delAllData_MainAct.setOnClickListener(this);
        btn_QueryBuilder_MainAct.setOnClickListener(this);
        btn_queryOneOrMore_MainAct.setOnClickListener(this);
        btn_delOneData_MainAct.setOnClickListener(this);
        btn_upDataOneData_MainAct.setOnClickListener(this);
        btn_addMoreData_MainAct.setOnClickListener(this);
        btn_addData_MainAct.setOnClickListener(this);
//        ed_MainAct.addTextChangedListener(this);    //监听EditText的内容的变化

        commonUtils = new CommonUtils(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addData_MainAct:
                //插入数据库的操作
                Log.d(TAG, "插入数据");
                Case c1 = new Case();
                c1.setId(1001L);
                c1.setAge(33);
                c1.setName("刑事案件");
                c1.setTime(20150519L);
                commonUtils.insertCase(c1);
                break;

            case R.id.btn_addMoreData_MainAct:
                //添加多条数据
                Log.d(TAG, "添加多条数据");
                List<Case> list = new ArrayList<>();
//                for (int i = 0; i < 10; i++) {
//                    Case c2 = new Case();
//                    c2.setName("郭美美"+i);
//                    c2.setAge(24 + i);
//                    c2.setTime(20150329L + i);
//                    list.add(c2);
//                }
                Case ca1=new Case(1L,"张学友",20150303L,49);
                Case ca2=new Case(2L,"谢文东",20130222L,51);
                Case ca3=new Case(3L,"乌迪尔",20130523L,33);
                Case ca4=new Case(4L,"盖伦",20131202L,20);
                list.add(ca1);
                list.add(ca2);
                list.add(ca3);
                list.add(ca4);
                commonUtils.insertMultCase(list);
                break;

            case R.id.btn_upDataOneData_MainAct:
                //修改一条数据
                Log.d(TAG, "修改一条数据");
                //updata case set name='jack' where id=1001;
                Case c3 = new Case();
                c3.setId(1002L);
                c3.setAge(10);
                c3.setName("华宇");
                c3.setTime(2013L);
                commonUtils.updateCase(c3);
                break;

            case R.id.btn_delOneData_MainAct:
                //删除单条数据
                Log.d(TAG, "删除单条数据");
                Case c4 = new Case();
//                c4.setId(1002L);
                //delete from case where id = 1002
                commonUtils.deleteCase(c4);
                break;

            case R.id.btn_queryOneOrMore_MainAct:
                //查询单行/多行记录
                Log.d(TAG,"查询单行/多行记录");
//                List<Case> list1=commonUtils.listAll();
////                Log.i(TAG,list1.get(2).getName());
//                for (int i = 0; i <list1.size(); i++) {
//                    Log.d(TAG,list1.get(i).getId()+","+list1.get(i).getName()+","+list1.get(i).getTime()+","+list1.get(i).getAge());
//                }
                Log.d(TAG,commonUtils.listOneCase(1001L).getName()+"");
                break;

            case R.id.btn_QueryBuilder_MainAct:
                //使用符合条件进行查询
                Log.d(TAG,"使用QueryBuilder查询");
                //commonUtils.query1();
                //commonUtils.query2();
                commonUtils.query3();
                break;

            case R.id.btn_delAllData_MainAct:
                //删除表内所有数据
                Log.d(TAG,"删除表内所有数据");
                commonUtils.deleteAllData(Case.class);
                break;
        }
    }

}
