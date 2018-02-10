package com.wuyou.worker.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "USER_INFO".
 */
public class UserInfoDao extends AbstractDao<UserInfo, Long> {

    public static final String TABLENAME = "USER_INFO";

    /**
     * Properties of entity UserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Mid = new Property(0, long.class, "mid", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "USERNAME");
        public final static Property Phone = new Property(2, String.class, "phone", false, "PHONE");
        public final static Property Uid = new Property(3, String.class, "uid", false, "UID");
        public final static Property Head_image = new Property(4, String.class, "head_image", false, "HEAD");
        public final static Property Token = new Property(5, String.class, "token", false, "TOKEN");
        public final static Property Password = new Property(6, String.class, "password", false, "PWD");
    }


    public UserInfoDao(DaoConfig config) {
        super(config);
    }

    public UserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: mid
                "\"USERNAME\" TEXT," + // 1: name
                "\"PHONE\" TEXT," + // 2: phone
                "\"UID\" TEXT," + // 3: uid
                "\"HEAD\" TEXT," + // 4: head_image
                "\"TOKEN\" TEXT," + // 5: token
                "\"PWD\" TEXT);"); // 6: password
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfo entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getMid());

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }

        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }

        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(4, uid);
        }

        String head_image = entity.getHead_image();
        if (head_image != null) {
            stmt.bindString(5, head_image);
        }

        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(6, token);
        }

        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(7, password);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getMid());

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }

        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }

        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(4, uid);
        }

        String head_image = entity.getHead_image();
        if (head_image != null) {
            stmt.bindString(5, head_image);
        }

        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(6, token);
        }

        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(7, password);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }

    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
                cursor.getLong(offset + 0), // mid
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // phone
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // uid
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // head_image
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // token
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // password
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.setMid(cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhone(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUid(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHead_image(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setToken(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPassword(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
    }

    @Override
    protected final Long updateKeyAfterInsert(UserInfo entity, long rowId) {
        entity.setMid(rowId);
        return rowId;
    }

    @Override
    public Long getKey(UserInfo entity) {
        if(entity != null) {
            return entity.getMid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfo entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

}
