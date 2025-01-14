package com.j256.ormlite.misc;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseFieldConfig;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collection;

/* loaded from: classes2.dex */
public class JavaxPersistence {
    public static DatabaseFieldConfig createFieldConfig(DatabaseType databaseType, Field field) throws SQLException {
        Annotation annotation;
        Annotation annotation2 = null;
        Annotation annotation3 = null;
        Annotation annotation4 = null;
        Annotation annotation5 = null;
        Annotation annotation6 = null;
        Annotation annotation7 = null;
        Annotation annotation8 = null;
        Annotation annotation9 = null;
        for (Annotation annotation10 : field.getAnnotations()) {
            Class<? extends Annotation> annotationType = annotation10.annotationType();
            if (annotationType.getName().equals("javax.persistence.Column")) {
                annotation2 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.Id")) {
                annotation3 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.GeneratedValue")) {
                annotation8 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.OneToOne")) {
                annotation4 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.ManyToOne")) {
                annotation5 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.JoinColumn")) {
                annotation9 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.Enumerated")) {
                annotation6 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.Version")) {
                annotation7 = annotation10;
            }
        }
        if (annotation2 == null && annotation3 == null && annotation4 == null && annotation5 == null && annotation6 == null && annotation7 == null) {
            return null;
        }
        DatabaseFieldConfig databaseFieldConfig = new DatabaseFieldConfig();
        String name = field.getName();
        if (databaseType.isEntityNamesMustBeUpCase()) {
            name = name.toUpperCase();
        }
        databaseFieldConfig.setFieldName(name);
        if (annotation2 != null) {
            try {
                String str = (String) annotation2.getClass().getMethod("name", new Class[0]).invoke(annotation2, new Object[0]);
                if (str != null && str.length() > 0) {
                    databaseFieldConfig.setColumnName(str);
                }
                annotation = annotation7;
                String str2 = (String) annotation2.getClass().getMethod("columnDefinition", new Class[0]).invoke(annotation2, new Object[0]);
                if (str2 != null && str2.length() > 0) {
                    databaseFieldConfig.setColumnDefinition(str2);
                }
                databaseFieldConfig.setWidth(((Integer) annotation2.getClass().getMethod("length", new Class[0]).invoke(annotation2, new Object[0])).intValue());
                Boolean bool = (Boolean) annotation2.getClass().getMethod("nullable", new Class[0]).invoke(annotation2, new Object[0]);
                if (bool != null) {
                    databaseFieldConfig.setCanBeNull(bool.booleanValue());
                }
                Boolean bool2 = (Boolean) annotation2.getClass().getMethod("unique", new Class[0]).invoke(annotation2, new Object[0]);
                if (bool2 != null) {
                    databaseFieldConfig.setUnique(bool2.booleanValue());
                }
            } catch (Exception e) {
                throw SqlExceptionUtil.create("Problem accessing fields from the @Column annotation for field " + field, e);
            }
        } else {
            annotation = annotation7;
        }
        if (annotation3 != null) {
            if (annotation8 == null) {
                databaseFieldConfig.setId(true);
            } else {
                databaseFieldConfig.setGeneratedId(true);
            }
        }
        if (annotation4 != null || annotation5 != null) {
            if (Collection.class.isAssignableFrom(field.getType()) || ForeignCollection.class.isAssignableFrom(field.getType())) {
                databaseFieldConfig.setForeignCollection(true);
                if (annotation9 != null) {
                    try {
                        String str3 = (String) annotation9.getClass().getMethod("name", new Class[0]).invoke(annotation9, new Object[0]);
                        if (str3 != null && str3.length() > 0) {
                            databaseFieldConfig.setForeignCollectionColumnName(str3);
                        }
                        Object invoke = annotation9.getClass().getMethod("fetch", new Class[0]).invoke(annotation9, new Object[0]);
                        if (invoke != null && invoke.toString().equals("EAGER")) {
                            databaseFieldConfig.setForeignCollectionEager(true);
                        }
                    } catch (Exception e2) {
                        throw SqlExceptionUtil.create("Problem accessing fields from the @JoinColumn annotation for field " + field, e2);
                    }
                }
            } else {
                databaseFieldConfig.setForeign(true);
                if (annotation9 != null) {
                    try {
                        String str4 = (String) annotation9.getClass().getMethod("name", new Class[0]).invoke(annotation9, new Object[0]);
                        if (str4 != null && str4.length() > 0) {
                            databaseFieldConfig.setColumnName(str4);
                        }
                        Boolean bool3 = (Boolean) annotation9.getClass().getMethod("nullable", new Class[0]).invoke(annotation9, new Object[0]);
                        if (bool3 != null) {
                            databaseFieldConfig.setCanBeNull(bool3.booleanValue());
                        }
                        Boolean bool4 = (Boolean) annotation9.getClass().getMethod("unique", new Class[0]).invoke(annotation9, new Object[0]);
                        if (bool4 != null) {
                            databaseFieldConfig.setUnique(bool4.booleanValue());
                        }
                    } catch (Exception e3) {
                        throw SqlExceptionUtil.create("Problem accessing fields from the @JoinColumn annotation for field " + field, e3);
                    }
                }
            }
        }
        if (annotation6 != null) {
            try {
                Object invoke2 = annotation6.getClass().getMethod("value", new Class[0]).invoke(annotation6, new Object[0]);
                if (invoke2 != null && invoke2.toString().equals("STRING")) {
                    databaseFieldConfig.setDataType(DataType.ENUM_STRING);
                } else {
                    databaseFieldConfig.setDataType(DataType.ENUM_INTEGER);
                }
            } catch (Exception e4) {
                throw SqlExceptionUtil.create("Problem accessing fields from the @Enumerated annotation for field " + field, e4);
            }
        }
        if (annotation != null) {
            databaseFieldConfig.setVersion(true);
        }
        if (databaseFieldConfig.getDataPersister() == null) {
            databaseFieldConfig.setDataPersister(DataPersisterManager.lookupForField(field));
        }
        databaseFieldConfig.setUseGetSet((DatabaseFieldConfig.findGetMethod(field, false) == null || DatabaseFieldConfig.findSetMethod(field, false) == null) ? false : true);
        return databaseFieldConfig;
    }

    public static String getEntityName(Class<?> cls) {
        Annotation annotation = null;
        for (Annotation annotation2 : cls.getAnnotations()) {
            if (annotation2.annotationType().getName().equals("javax.persistence.Entity")) {
                annotation = annotation2;
            }
        }
        if (annotation == null) {
            return null;
        }
        try {
            String str = (String) annotation.getClass().getMethod("name", new Class[0]).invoke(annotation, new Object[0]);
            if (str != null) {
                if (str.length() > 0) {
                    return str;
                }
            }
            return null;
        } catch (Exception e) {
            throw new IllegalStateException("Could not get entity name from class " + cls, e);
        }
    }
}
