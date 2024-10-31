package com.myor.mydex.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.myor.mydex.database.DatabaseHelper;
import com.myor.mydex.entity.BranchDto;
import com.myor.mydex.entity.DriverDto;
import com.myor.mydex.entity.Reason;
import com.myor.mydex.entity.Xkey;
import java.util.List;

/* loaded from: classes2.dex */
public class SyncRepo {
    private Context context;
    private Dao<DriverDto, String> dao;

    public SyncRepo(Context context) {
        this.context = context;
    }

    private ConnectionSource getConnectionSource() {
        return DatabaseHelper.getInstance(this.context).getConnectionSource();
    }

    public boolean deleteMXkey() {
        try {
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.dao = createDao;
            createDao.executeRaw(" delete from m_xkey", new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public void insertmXkey(List<Xkey> list) {
        SQLiteDatabase readableDatabase = DatabaseHelper.getInstance(this.context).getReadableDatabase();
        SQLiteStatement compileStatement = readableDatabase.compileStatement("insert into m_xkey (memonama, memoint, memostring, memoint2, memostring2)  VALUES (?,?,?,?,?) ");
        readableDatabase.beginTransaction();
        try {
            for (Xkey xkey : list) {
                compileStatement.clearBindings();
                compileStatement.bindString(1, xkey.getMemonama());
                compileStatement.bindString(2, xkey.getMemoint());
                compileStatement.bindString(3, xkey.getMemostring());
                compileStatement.bindString(4, xkey.getMemoint2());
                compileStatement.bindString(5, xkey.getMemostring2());
                compileStatement.executeInsert();
            }
            readableDatabase.setTransactionSuccessful();
        } finally {
            readableDatabase.endTransaction();
        }
    }

    public void insertReason(List<Reason> list) {
        SQLiteDatabase readableDatabase = DatabaseHelper.getInstance(this.context).getReadableDatabase();
        SQLiteStatement compileStatement = readableDatabase.compileStatement("insert into m_reason (reason_type, reason_id, reason)  VALUES (?,?,?) ");
        readableDatabase.beginTransaction();
        try {
            for (Reason reason : list) {
                compileStatement.clearBindings();
                compileStatement.bindString(1, reason.getTypeId());
                compileStatement.bindString(2, reason.getReasonId());
                compileStatement.bindString(3, reason.getReasonName());
                compileStatement.executeInsert();
            }
            readableDatabase.setTransactionSuccessful();
        } finally {
            readableDatabase.endTransaction();
        }
    }

    public boolean deleteMReason() {
        try {
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.dao = createDao;
            createDao.executeRaw(" delete from m_reason", new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean deleteMBranch() {
        try {
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.dao = createDao;
            createDao.executeRaw(" delete from m_branch", new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public void insertBranch(List<BranchDto> list) {
        SQLiteDatabase readableDatabase = DatabaseHelper.getInstance(this.context).getReadableDatabase();
        SQLiteStatement compileStatement = readableDatabase.compileStatement("insert or replace into m_branch (branch_id, branch_nm, latitude, longitude, barcode, flag_route,       branch_type, branch_id_m1, branch_id_m245, branch_id_m3)  VALUES (?,?,?,?,?,?,?,?,?,?) ");
        readableDatabase.beginTransaction();
        try {
            for (BranchDto branchDto : list) {
                compileStatement.clearBindings();
                compileStatement.bindString(1, branchDto.getBranchId());
                compileStatement.bindString(2, branchDto.getBranchName());
                compileStatement.bindString(3, branchDto.getLatitude());
                compileStatement.bindString(4, branchDto.getLongitude());
                compileStatement.bindString(5, branchDto.getBarcode());
                compileStatement.bindString(6, branchDto.getFlagRoute());
                compileStatement.bindString(7, branchDto.getBranchType());
                compileStatement.bindString(8, branchDto.getBranchIdM1());
                compileStatement.bindString(9, branchDto.getBranchIdM2());
                compileStatement.bindString(10, branchDto.getBranchIdM3());
                compileStatement.executeInsert();
            }
            readableDatabase.setTransactionSuccessful();
        } finally {
            readableDatabase.endTransaction();
        }
    }
}
