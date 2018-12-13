package com.mic.xsample;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mic.libsqlite.db.DaoFactory;
import com.mic.libsqlite.db.IDao;
import com.mic.xsample.model.Person;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class PersonTest {

    private static final String TAG ="person";

    @Test
    public void insert(){
        IDao dao = DaoFactory.getFactory().getDao(Person.class);
        dao.insert(new Person("hello",25));

    }

    @Test
    public void batchInsert(){
        IDao dao = DaoFactory.getFactory().getDao(Person.class);

        List<Person> list = new ArrayList<Person>();

        for (int i=0;i<30;i++){
            Person person = new Person("AAAA",i);
            list.add(person);
        }

        dao.insert(list);
    }


    @Test
    public void queryAll(){
        IDao<Person> dao = DaoFactory.getFactory().getDao(Person.class);
        List<Person> list = dao.queryAll();

        if(list!=null){
            for (Person p:list){
                Log.d(TAG,p.toString());
            }
        }
    }
}
