package com.my.greendao_search.dbmanager;

import android.content.Context;
import android.util.Log;

import com.my.greendao_search.bean.Case;
import com.my.greendao_search.dao.CaseDao;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 完成对某一张表的具体操作，操作的是对象，这里具体指Case
 * <p/>
 * Created by YJH on 2016/5/22.
 */
public class CommonUtils {

    private static final String TAG = "CommonUtils------>";
    private DaoManager manager;

    public CommonUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }

    /**
     * 完成对数据库中case表的插入操作
     *
     * @param c
     * @return
     */
    public boolean insertCase(Case c) {
        boolean flag = false;

        flag = manager.getDaoSession().insert(c) != -1 ? true : false;
        Log.d(TAG, "插入case的结果是------>" + flag);
        return flag;
    }

    /**
     * 插入多条数据,需要开线程
     *
     * @param cases
     * @return
     */
    public boolean insertMultCase(final List<Case> cases) {
        boolean flag = false;

        try {

            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Case c : cases) {
                        manager.getDaoSession().insertOrReplace(c);
                    }
                }
            });
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 完成对Case的某一条记录的修改
     *
     * @param c
     * @return
     */
    public boolean updateCase(Case c) {
        boolean flag = false;

        try {
            manager.getDaoSession().update(c);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 按照指定id进行删除
     *
     * @param c
     * @return
     */
    public boolean deleteCase(Case c) {
        boolean flag = false;

        try {
//            按照指定id进行删除delete from case where id = ?
            manager.getDaoSession().delete(c);
            //删除所有的记录
//            manager.getDaoSession().deleteAll(Case.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 删除表内所有数据
     *
     * @param cla
     * @return
     */
    public boolean deleteAllData(Class cla) {
        boolean flag = false;
        try {
            manager.getDaoSession().deleteAll(cla);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 返回多行记录
     *
     * @return
     */
    public List<Case> listAll() {
        return manager.getDaoSession().loadAll(Case.class);
    }

    /**
     * 按照主键返回单行记录
     *
     * @param key
     * @return
     */
    public Case listOneCase(long key) {
        return manager.getDaoSession().load(Case.class, key);
    }

    /**
     * 模糊查询(用SQL语句)
     */
    public void query1() {
        //使用native sql进行查询操作
        List<Case> list = manager.getDaoSession().queryRaw(Case.class, " where name like ? and _id > ?", new String[]{"%郭%", "1002"});
        Log.d(TAG, "" + list.toString() + "");
        for (int i = 0; i < list.size(); i++) {
            Log.d(TAG, list.get(i).getName());
        }
    }

    /**
     * select * form case where name like ? or name = ? or
     * < <= >= != in between and
     */
    public void query2() {
        //查询构建器
        QueryBuilder<Case> builder = manager.getDaoSession().queryBuilder(Case.class);

        //这条语句相当于select * from case where age > 25 and name like "%案%"      (where默认是and，whereOr是or)
        List<Case> list = builder.where(CaseDao.Properties.Age.ge(25)).where(CaseDao.Properties.Name.like("%案%")).list();
        Log.d(TAG, "" + list.size());
        for (int i = 0; i < list.size(); i++) {
            Log.d(TAG, list.get(i).getName());
        }
    }

    public void query3(){
        QueryBuilder<Case> builder=manager.getDaoSession().queryBuilder(Case.class);
        //相当于select * from case where age >50 or name like "%伦%"
        builder.whereOr(CaseDao.Properties.Age.ge(50), CaseDao.Properties.Name.like("%伦%"));
        List<Case> list=builder.list();
        //builder.limit(3);     //去前3条
        builder.count();        //一共有多少条
        Log.d(TAG,list.size()+"");
        for (int i = 0; i < list.size(); i++) {
            Log.d(TAG,list.get(i).getName());
        }
    }


}
