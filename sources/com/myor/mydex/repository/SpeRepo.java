package com.myor.mydex.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import androidx.exifinterface.media.ExifInterface;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.myor.mydex.database.DatabaseHelper;
import com.myor.mydex.entity.BranchDto;
import com.myor.mydex.entity.DriverDto;
import com.myor.mydex.entity.MonitoringD;
import com.myor.mydex.entity.MonitoringDto;
import com.myor.mydex.entity.MonitoringH;
import com.myor.mydex.entity.MonitoringPhoto;
import com.myor.mydex.entity.Reason;
import com.myor.mydex.entity.Xkey;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SpeRepo {
    private Context context;
    private Dao<DriverDto, String> daoSpe;

    public SpeRepo(Context context) {
        this.context = context;
    }

    private ConnectionSource getConnectionSource() {
        return DatabaseHelper.getInstance(this.context).getConnectionSource();
    }

    public List<MonitoringH> getDataMonitoring() {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery("SELECT spe_no, driver, police_no, driver_phone, status, time_input, destination_id, destination_name, destination_type,          COALESCE(flag_route,'1') as flag_route, COALESCE(time_checkout,'-') as time_checkout, COALESCE(time_arrive,'-') as time_arrive,           COALESCE(time_start_bongkar,'-') as time_start_bongkar,  COALESCE(time_finish_bongkar,'-') as time_finish_bongkar,            COALESCE(time_checkout_subdist,'-') as time_checkout_subdist    FROM t_monitoring_h ", null);
            while (rawQuery.moveToNext()) {
                MonitoringH monitoringH = new MonitoringH();
                monitoringH.setSpeNo(rawQuery.getString(0));
                monitoringH.setDriver(rawQuery.getString(1));
                monitoringH.setPoliceNo(rawQuery.getString(2));
                monitoringH.setDriverPhone(rawQuery.getString(3));
                monitoringH.setStatus(rawQuery.getString(4));
                monitoringH.setTimeInput(rawQuery.getString(5));
                monitoringH.setDestinationId(rawQuery.getString(6));
                monitoringH.setDestinationName(rawQuery.getString(7));
                monitoringH.setDestinationType(rawQuery.getString(8));
                monitoringH.setFlagRoute(rawQuery.getString(9));
                monitoringH.setTimeCheckout(rawQuery.getString(10));
                monitoringH.setTimeArrive(rawQuery.getString(11));
                monitoringH.setTimeStartBongkar(rawQuery.getString(12));
                monitoringH.setTimeFinishBongkar(rawQuery.getString(13));
                monitoringH.setTimeCheckoutSubdist(rawQuery.getString(14));
                arrayList.add(monitoringH);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public int countDataMonitoring(String str) {
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery("SELECT count(distinct do_no) FROM t_monitoring_d WHERE spe_no = '" + str + "' ", null);
            rawQuery.moveToFirst();
            return rawQuery.getInt(0);
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    public boolean inputMonitoring(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            String str8 = "   insert into t_monitoring_h (spe_no, driver, police_no, driver_phone, serial_phone, status, time_input)    VALUES    ('" + str + "','" + str2 + "','" + str4 + "','" + str3 + "','" + str5 + "','" + str6 + "','" + str7 + "') ";
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw(str8, new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean deleteMonitoring(String str) {
        try {
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw("DELETE FROM " + str + " ", new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean insertMonitoringH(MonitoringDto monitoringDto) {
        try {
            String str = "insert into t_monitoring_h (spe_no, driver, police_no, driver_phone, status,           serial_phone, origin_id, origin_name, destination_id, destination_name, destination_type,           flag_route, time_input, time_checkout, time_arrive,           time_start_bongkar, time_finish_bongkar, time_checkout_subdist)  VALUES ('" + monitoringDto.getSpeNo() + "','" + monitoringDto.getDriver() + "','" + monitoringDto.getPoliceNo() + "','" + monitoringDto.getDriverPhone() + "','" + monitoringDto.getStatus() + "',  '" + monitoringDto.getSerialPhone() + "','" + monitoringDto.getOriginId() + "','" + monitoringDto.getOriginName() + "','" + monitoringDto.getDestinationId() + "','" + monitoringDto.getDestinationName() + "','" + monitoringDto.getDestinationType() + "', '" + monitoringDto.getFlagRoute() + "','" + monitoringDto.getTimeInput() + "','" + monitoringDto.getTimeCheckout() + "','" + monitoringDto.getTimeArrive() + "',  '" + monitoringDto.getTimeStartBongkar() + "','" + monitoringDto.getTimeFinishBongkar() + "','" + monitoringDto.getTimeCheckoutSubdist() + "') ";
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw(str, new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public void insertMonitoringD(List<MonitoringD> list) {
        SQLiteDatabase readableDatabase = DatabaseHelper.getInstance(this.context).getReadableDatabase();
        SQLiteStatement compileStatement = readableDatabase.compileStatement("insert into t_monitoring_d (spe_no, do_no, pcode, pcodename, xqty) VALUES (?,?,?,?,?)");
        readableDatabase.beginTransaction();
        try {
            for (MonitoringD monitoringD : list) {
                compileStatement.clearBindings();
                compileStatement.bindString(1, monitoringD.getSpeNo());
                compileStatement.bindString(2, monitoringD.getDoNo());
                compileStatement.bindString(3, monitoringD.getPcode());
                compileStatement.bindString(4, monitoringD.getPcodeName());
                compileStatement.bindString(5, monitoringD.getXqty());
                compileStatement.executeInsert();
            }
            readableDatabase.setTransactionSuccessful();
        } finally {
            readableDatabase.endTransaction();
        }
    }

    public List<MonitoringD> getDataMonitoringDetail(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery("SELECT distinct spe_no, do_no   FROM t_monitoring_d WHERE spe_no = '" + str + "' ", null);
            while (rawQuery.moveToNext()) {
                MonitoringD monitoringD = new MonitoringD();
                monitoringD.setSpeNo(rawQuery.getString(0));
                monitoringD.setDoNo(rawQuery.getString(1));
                arrayList.add(monitoringD);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public List<Xkey> getXkey(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery("SELECT memonama, memoint FROM m_xkey WHERE memonama = '" + str + "' ", null);
            while (rawQuery.moveToNext()) {
                Xkey xkey = new Xkey();
                xkey.setMemonama(rawQuery.getString(0));
                xkey.setMemoint(rawQuery.getString(1));
                arrayList.add(xkey);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public List<Reason> getReason(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery("SELECT reason_type, reason_id, reason FROM m_reason where reason_type='" + str + "' ", null);
            while (rawQuery.moveToNext()) {
                Reason reason = new Reason();
                reason.setTypeId(rawQuery.getString(0));
                reason.setReasonId(rawQuery.getString(1));
                reason.setReasonName(rawQuery.getString(2));
                arrayList.add(reason);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public List<BranchDto> getMasterBranch(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT branch_id, branch_nm,    CASE WHEN latitude = '' or latitude is null then 0.0 ELSE latitude END as latitude,     CASE WHEN longitude = '' or longitude is null then 0.0 ELSE longitude END as longitude,    barcode, 0.0 as radius  from m_branch where branch_type = '" + str + "' ", null);
            while (rawQuery.moveToNext()) {
                BranchDto branchDto = new BranchDto();
                branchDto.setBranchId(rawQuery.getString(0));
                branchDto.setBranchName(rawQuery.getString(1));
                branchDto.setLatitude(rawQuery.getString(2));
                branchDto.setLongitude(rawQuery.getString(3));
                branchDto.setBarcode(rawQuery.getString(4));
                branchDto.setRadius(rawQuery.getDouble(5));
                arrayList.add(branchDto);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public List<BranchDto> getMasterBranchById(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT branch_id, branch_nm,    CASE WHEN latitude = '' or latitude is null then 0.0 ELSE latitude END as latitude,     CASE WHEN longitude = '' or longitude is null then 0.0 ELSE longitude END as longitude,    barcode, 0.0 as radius  from m_branch where branch_id = '" + str + "' ", null);
            while (rawQuery.moveToNext()) {
                BranchDto branchDto = new BranchDto();
                branchDto.setBranchId(rawQuery.getString(0));
                branchDto.setBranchName(rawQuery.getString(1));
                branchDto.setLatitude(rawQuery.getString(2));
                branchDto.setLongitude(rawQuery.getString(3));
                branchDto.setBarcode(rawQuery.getString(4));
                branchDto.setRadius(rawQuery.getDouble(5));
                arrayList.add(branchDto);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public List<BranchDto> getBranchLocation(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT branch_id, branch_nm,    CASE WHEN latitude = '' or latitude is null then 0.0 ELSE latitude END as latitude,     CASE WHEN longitude = '' or longitude is null then 0.0 ELSE longitude END as longitude,    barcode, 0.0 as radius  from m_branch where barcode = '" + str + "' ", null);
            while (rawQuery.moveToNext()) {
                BranchDto branchDto = new BranchDto();
                branchDto.setBranchId(rawQuery.getString(0));
                branchDto.setBranchName(rawQuery.getString(1));
                branchDto.setLatitude(rawQuery.getString(2));
                branchDto.setLongitude(rawQuery.getString(3));
                branchDto.setBarcode(rawQuery.getString(4));
                branchDto.setRadius(rawQuery.getDouble(5));
                arrayList.add(branchDto);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public List<BranchDto> getBranchLocationBySubdist(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery(" SELECT branch_id, branch_nm,    CASE WHEN latitude = '' or latitude is null then 0.0 ELSE latitude END as latitude,     CASE WHEN longitude = '' or longitude is null then 0.0 ELSE longitude END as longitude,    barcode, 0.0 as radius  from m_branch where barcode = '" + str + "' and branch_id = '" + str2 + "' ", null);
            while (rawQuery.moveToNext()) {
                BranchDto branchDto = new BranchDto();
                branchDto.setBranchId(rawQuery.getString(0));
                branchDto.setBranchName(rawQuery.getString(1));
                branchDto.setLatitude(rawQuery.getString(2));
                branchDto.setLongitude(rawQuery.getString(3));
                branchDto.setBarcode(rawQuery.getString(4));
                branchDto.setRadius(rawQuery.getDouble(5));
                arrayList.add(branchDto);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public boolean updateReasonNotScan(String str, String str2, String str3) {
        String str4;
        if (str3.equalsIgnoreCase("0")) {
            str4 = "reason_scan";
        } else if (str3.equalsIgnoreCase("1")) {
            str4 = "reason_scan_subdist";
        } else {
            str4 = str3.equalsIgnoreCase("5") ? "reason_scanout" : "";
        }
        try {
            String str5 = "UPDATE t_monitoring_h SET " + str4 + " = '" + str + "' WHERE spe_no = '" + str2 + "' ";
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw(str5, new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean updateReasonOutRadius(String str, String str2, String str3) {
        String str4;
        if (str.equalsIgnoreCase("0")) {
            str4 = "reason_outradius";
        } else if (str.equalsIgnoreCase("1")) {
            str4 = "reason_outradius_subdist";
        } else {
            str4 = str.equalsIgnoreCase("5") ? "reason_outradius_outsubdist" : "";
        }
        try {
            String str5 = "UPDATE t_monitoring_h SET " + str4 + " = '" + str2 + "' WHERE spe_no = '" + str3 + "' ";
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw(str5, new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean updateWhIdScanOut(String str, String str2, String str3, String str4) {
        try {
            String str5 = "   UPDATE t_monitoring_h           SET status = '1', origin_id = '" + str + "', origin_name = '" + str2 + "', time_checkout = '" + str3 + "'   WHERE spe_no = '" + str4 + "' ";
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw(str5, new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean updateStatus(String str, String str2, String str3) {
        String str4;
        if (str.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
            str4 = "time_in_transit";
        } else if (str.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
            str4 = "time_arrive";
        } else if (str.equalsIgnoreCase("4")) {
            str4 = "time_start_bongkar";
        } else if (str.equalsIgnoreCase("5")) {
            str4 = "time_finish_bongkar";
        } else {
            str4 = str.equalsIgnoreCase("6") ? "time_checkout_subdist" : "";
        }
        try {
            String str5 = "   UPDATE t_monitoring_h               SET status = '" + str + "', '" + str4 + "' = '" + str2 + "'   WHERE spe_no = '" + str3 + "' ";
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw(str5, new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public List<MonitoringD> getDataDetail(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery("SELECT spe_no, do_no, pcode, pcodename, xqty    FROM t_monitoring_d WHERE do_no = '" + str + "' ", null);
            while (rawQuery.moveToNext()) {
                MonitoringD monitoringD = new MonitoringD();
                monitoringD.setSpeNo(rawQuery.getString(0));
                monitoringD.setDoNo(rawQuery.getString(1));
                monitoringD.setPcode(rawQuery.getString(2));
                monitoringD.setPcodeName(rawQuery.getString(3));
                monitoringD.setXqty(rawQuery.getString(4));
                arrayList.add(monitoringD);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public boolean updatePhoto(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8;
        String str9 = str4 + "|" + str5 + "|" + str6;
        try {
            if (str7.equalsIgnoreCase("tiba")) {
                str8 = "   UPDATE t_monitoring_photo SET   photo_arrive = '" + str2 + "', time_arrive = '" + str3 + "' , address_arrive = '" + str9 + "' ";
            } else {
                str8 = "   UPDATE t_monitoring_photo SET   photo_start_bongkar = '" + str2 + "', time_start_bongkar = '" + str3 + "' , address_start_bongkar = '" + str9 + "' ";
            }
            String str10 = str8 + "  WHERE spe_no = '" + str + "' ";
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw(str10, new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean insertPhoto(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8 = str4 + "|" + str5 + "|" + str6;
        try {
            String str9 = (str7.equalsIgnoreCase("tiba") ? "INSERT INTO t_monitoring_photo  (spe_no, photo_arrive, time_arrive, address_arrive) " : "INSERT INTO t_monitoring_photo  (spe_no, photo_start_bongkar, time_start_bongkar, address_start_bongkar) ") + " VALUES   ('" + str + "','" + str2 + "','" + str3 + "','" + str8 + "') ";
            Dao<DriverDto, String> createDao = DaoManager.createDao(getConnectionSource(), DriverDto.class);
            this.daoSpe = createDao;
            createDao.executeRaw(str9, new String[0]);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public List<MonitoringPhoto> getDataPhoto(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = DatabaseHelper.getInstance(this.context).getReadableDatabase().rawQuery("SELECT spe_no, photo_arrive, time_arrive, address_arrive, photo_start_bongkar, time_start_bongkar, address_start_bongkar    FROM t_monitoring_photo WHERE spe_no = '" + str + "' ", null);
            while (rawQuery.moveToNext()) {
                MonitoringPhoto monitoringPhoto = new MonitoringPhoto();
                monitoringPhoto.setSpeNo(rawQuery.getString(0));
                monitoringPhoto.setPhotoArrive(rawQuery.getString(1));
                monitoringPhoto.setTimeArrive(rawQuery.getString(2));
                monitoringPhoto.setAddressArrive(rawQuery.getString(3));
                monitoringPhoto.setPhotoStartBongkar(rawQuery.getString(4));
                monitoringPhoto.setTimeStartBongkar(rawQuery.getString(5));
                monitoringPhoto.setAddressStartBongkar(rawQuery.getString(6));
                arrayList.add(monitoringPhoto);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }
}
