package com.example.droiddaemon.lcaldev.DataBase;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import com.example.droiddaemon.lcaldev.listeners.OnModelSavedListener;
import com.example.droiddaemon.lcaldev.listeners.OnQueryModelListener;
import com.example.droiddaemon.lcaldev.model.Item;
import com.example.droiddaemon.lcaldev.util.CachingConstants;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class CachingService {
    private static final String TAG = CachingService.class.getSimpleName();
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;


    public CachingService(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.db = dbHelper.getWritableDatabase();
    }



    /**
     * @param info
     */

    public void saveUserInfo(Item info, OnModelSavedListener listener) {
        Log.d(TAG, "Saving UserInfo with details:");
        saveModelAsync(info, listener);
    }

    public void fetchCachedItems(OnQueryModelListener listener) {
        queryModelAsync(Item.class, listener);
    }

    /**
     * @param items
     * @param listener
     * @Des: Responsible to update Items
     */

    public void updateItems(Item items, OnModelSavedListener listener) {
        String selection = CachingConstants.ITEM_ID + " AND " + CachingConstants.ITEM_TYPE + "=?";
        String selectionArgs[] = {String.valueOf(items.getText()), items.getColor()};
        ContentValues cv = new ContentValues();
        updateModelAsync(items, cv, selection, selectionArgs, listener);
    }



    private void saveModelAsync(Object object, OnModelSavedListener listener) {
        new SaveModelTask(object, listener).execute();
    }

    private void updateModelAsync(Object object, ContentValues contentValues, String selection, String selectionArgs[], OnModelSavedListener listener) {
        new UpdateModelTask(object, contentValues, selection, selectionArgs, listener).execute();
    }

    private void queryModelAsync(Class clazz, OnQueryModelListener listener) {
        new QueryModelTask(clazz, listener).execute();
    }


    //AsyncTasks
    private class SaveModelTask extends AsyncTask<Void, Void, Long> {
        private Object object;
        private OnModelSavedListener listener;
        private ProgressDialog pd;

        SaveModelTask(Object object, OnModelSavedListener listener) {
            this.object = object;
            this.listener = listener;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(Void... params) {
            return cupboard().withDatabase(db).put(object);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            if (listener != null) {
                listener.onModelSavedSuccess(aLong);
            }
        }
    }


    /**
     * @Description: This class is responsible to update model db
     */

    private class UpdateModelTask extends AsyncTask<Void, Void, Integer> {
        private Object object;
        private OnModelSavedListener listener;
        private ContentValues contentValues;
        private String selection;
        private String[] selectionArgs;

        UpdateModelTask(Object object, ContentValues cv, String selection, String[] selectionArg, OnModelSavedListener listener) {
            this.object = object;
            this.listener = listener;
            this.contentValues = cv;
            this.selection = selection;
            this.selectionArgs = selectionArg;

        }

        @Override
        protected Integer doInBackground(Void... params) {
            return cupboard().withDatabase(db).update(object.getClass(), contentValues, selection, selectionArgs);
        }

        @Override
        protected void onPostExecute(Integer aLong) {
            super.onPostExecute(aLong);
            if (listener != null) {
                listener.onModelSavedSuccess(aLong);
            }
        }
    }


    private class QueryModelTask extends AsyncTask<Void, Void, List<Object>> {
        private Class clazz;
        private OnQueryModelListener listener;

        QueryModelTask(Class clazz, OnQueryModelListener listener) {
            this.clazz = clazz;
            this.listener = listener;
        }

        @Override
        protected List<Object> doInBackground(Void... params) {
            List<Object> objectList = new ArrayList<>();
            Cursor cursor = cupboard().withDatabase(db).query(clazz).getCursor();
            try {
                QueryResultIterable<Object> itr = cupboard().withCursor(cursor).iterate(clazz);
                for (Object o : itr) {
                    objectList.add(o);
                }
            } finally {
                cursor.close();
            }

            return objectList;
        }

        @Override
        protected void onPostExecute(List<Object> objectList) {
            super.onPostExecute(objectList);
            listener.onQueryModelSuccess(objectList);
        }
    }
}
