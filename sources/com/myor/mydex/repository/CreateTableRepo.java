package com.myor.mydex.repository;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.myor.mydex.database.DatabaseHelper;
import com.myor.mydex.entity.DriverDto;

/* loaded from: classes2.dex */
public class CreateTableRepo {
    private Context context;
    private Dao<DriverDto, String> daoSpe;
    private String sqlSelect = "";

    public CreateTableRepo(Context context) {
        this.context = context;
    }

    private ConnectionSource getConnectionSource() {
        return DatabaseHelper.getInstance(this.context).getConnectionSource();
    }

    public void updateTable() {
        try {
            DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT * from t_monitoring_h limit 1 ", null);
        } catch (Exception unused) {
            try {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("   CREATE TABLE t_monitoring_h (       spe_no TEXT,       driver TEXT,       police_no TEXT,       driver_phone TEXT,       status TEXT,       serial_phone TEXT,       origin_id TEXT,       origin_name TEXT,       destination_id TEXT,       destination_name TEXT,       destination_type TEXT,       flag_route TEXT,       time_input TEXT,       time_checkout TEXT,       time_arrive TEXT,       time_start_bongkar TEXT,       time_finish_bongkar TEXT,       time_checkout_subdist TEXT,       reason_scan TEXT,       reason_outradius TEXT,       reason_scan_subdist TEXT,       reason_outradius_subdist TEXT,       PRIMARY KEY (spe_no)) ", new String[0]);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        this.sqlSelect = "SELECT reason_scan FROM t_monitoring_h limit 1";
        try {
            try {
                DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(this.sqlSelect, null);
            } catch (Exception unused2) {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("ALTER TABLE t_monitoring_h ADD COLUMN reason_scan TEXT", new String[0]);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
        this.sqlSelect = "SELECT reason_outradius FROM t_monitoring_h limit 1";
        try {
            try {
                DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(this.sqlSelect, null);
            } catch (Exception unused3) {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("ALTER TABLE t_monitoring_h ADD COLUMN reason_outradius TEXT", new String[0]);
            }
        } catch (Exception e3) {
            e3.getMessage();
        }
        this.sqlSelect = "SELECT reason_outradius_subdist FROM t_monitoring_h limit 1";
        try {
            try {
                DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(this.sqlSelect, null);
            } catch (Exception unused4) {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("ALTER TABLE t_monitoring_h ADD COLUMN reason_outradius_subdist TEXT", new String[0]);
            }
        } catch (Exception e4) {
            e4.getMessage();
        }
        this.sqlSelect = "SELECT reason_scan_subdist FROM t_monitoring_h limit 1";
        try {
            try {
                DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(this.sqlSelect, null);
            } catch (Exception unused5) {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("ALTER TABLE t_monitoring_h ADD COLUMN reason_scan_subdist TEXT", new String[0]);
            }
        } catch (Exception e5) {
            e5.getMessage();
        }
        this.sqlSelect = "SELECT time_in_transit FROM t_monitoring_h limit 1";
        try {
            try {
                DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(this.sqlSelect, null);
            } catch (Exception unused6) {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("ALTER TABLE t_monitoring_h ADD COLUMN time_in_transit TEXT", new String[0]);
            }
        } catch (Exception e6) {
            e6.getMessage();
        }
        try {
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw("  ALTER TABLE t_monitoring_d RENAME TO t_monitoring_d_old ", new String[0]);
        } catch (Exception e7) {
            e7.getMessage();
        }
        try {
            DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT * from t_monitoring_d limit 1 ", null);
        } catch (Exception unused7) {
            try {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("   CREATE TABLE t_monitoring_d (       spe_no TEXT,       do_no TEXT,       pcode TEXT,       pcodename TEXT,       xqty TEXT,      PRIMARY KEY (spe_no,do_no,pcode)) ", new String[0]);
            } catch (Exception e8) {
                e8.getMessage();
            }
        }
        try {
            Dao<DriverDto, String> createDao2 = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao2;
            createDao2.executeRaw("  INSERT INTO t_monitoring_d SELECT * FROM t_monitoring_d_old ", new String[0]);
        } catch (Exception e9) {
            e9.getMessage();
        }
        try {
            Dao<DriverDto, String> createDao3 = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao3;
            createDao3.executeRaw("  DROP TABLE IF EXISTS t_monitoring_d_old ", new String[0]);
        } catch (Exception e10) {
            e10.getMessage();
        }
        try {
            DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT * from t_monitoring_location limit 1 ", null);
        } catch (Exception unused8) {
            try {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("   CREATE TABLE t_monitoring_location (       spe_no TEXT,       seq INTEGER PRIMARY KEY AUTOINCREMENT,       latitude TEXT,       longitude TEXT       ) ", new String[0]);
            } catch (Exception e11) {
                e11.getMessage();
            }
        }
        try {
            DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT * from m_driver limit 1 ", null);
        } catch (Exception unused9) {
            try {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("   CREATE TABLE m_driver (       driver_name TEXT,       driver_phone TEXT,       serial_phone TEXT,       police_no TEXT       ) ", new String[0]);
            } catch (Exception e12) {
                e12.getMessage();
            }
        }
        try {
            DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT * from m_reason limit 1 ", null);
        } catch (Exception unused10) {
            try {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("   CREATE TABLE m_reason (       reason_type TEXT,       reason_id TEXT,       reason TEXT       ) ", new String[0]);
            } catch (Exception e13) {
                e13.getMessage();
            }
        }
        try {
            DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT * from m_xkey limit 1 ", null);
        } catch (Exception unused11) {
            try {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("   CREATE TABLE m_xkey (       memonama TEXT,       memoint TEXT,       memostring TEXT,       memoint2 TEXT,       memostring2 TEXT       ) ", new String[0]);
            } catch (Exception e14) {
                e14.getMessage();
            }
        }
        try {
            DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT * from m_branch limit 1 ", null);
        } catch (Exception unused12) {
            try {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("   CREATE TABLE m_branch (       branch_id TEXT,       branch_nm TEXT,       longitude TEXT,       latitude TEXT,       barcode TEXT,       flag_route TEXT,       branch_type TEXT,       branch_id_m1 TEXT,       branch_id_m245 TEXT,       branch_id_m3 TEXT,   PRIMARY KEY (branch_id)       ) ", new String[0]);
            } catch (Exception e15) {
                e15.getMessage();
            }
        }
        try {
            DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT * from t_monitoring_photo limit 1 ", null);
        } catch (Exception unused13) {
            try {
                DaoManager.createDao(getConnectionSource(), DriverDto.class).updateRaw("   CREATE TABLE t_monitoring_photo (       spe_no TEXT,       photo_arrive TEXT,       time_arrive TEXT,       address_arrive TEXT,       photo_start_bongkar TEXT,       time_start_bongkar TEXT,       address_start_bongkar TEXT,   PRIMARY KEY (spe_no)       ) ", new String[0]);
            } catch (Exception e16) {
                e16.getMessage();
            }
        }
    }
}
