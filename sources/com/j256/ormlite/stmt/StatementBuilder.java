package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class StatementBuilder<T, ID> {
    private static Logger logger = LoggerFactory.getLogger((Class<?>) StatementBuilder.class);
    protected boolean addTableName;
    protected final Dao<T, ID> dao;
    protected final DatabaseType databaseType;
    protected final TableInfo<T, ID> tableInfo;
    protected final String tableName;
    protected StatementType type;
    protected Where<T, ID> where = null;

    protected abstract void appendStatementEnd(StringBuilder sb, List<ArgumentHolder> list) throws SQLException;

    protected abstract void appendStatementStart(StringBuilder sb, List<ArgumentHolder> list) throws SQLException;

    protected FieldType[] getResultFieldTypes() {
        return null;
    }

    protected boolean shouldPrependTableNameToColumns() {
        return false;
    }

    public StatementBuilder(DatabaseType databaseType, TableInfo<T, ID> tableInfo, Dao<T, ID> dao, StatementType statementType) {
        this.databaseType = databaseType;
        this.tableInfo = tableInfo;
        this.tableName = tableInfo.getTableName();
        this.dao = dao;
        this.type = statementType;
        if (statementType.isOkForStatementBuilder()) {
            return;
        }
        throw new IllegalStateException("Building a statement from a " + statementType + " statement is not allowed");
    }

    public Where<T, ID> where() {
        Where<T, ID> where = new Where<>(this.tableInfo, this, this.databaseType);
        this.where = where;
        return where;
    }

    public void setWhere(Where<T, ID> where) {
        this.where = where;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MappedPreparedStmt<T, ID> prepareStatement(Long l) throws SQLException {
        List<ArgumentHolder> arrayList = new ArrayList<>();
        String buildStatementString = buildStatementString(arrayList);
        ArgumentHolder[] argumentHolderArr = (ArgumentHolder[]) arrayList.toArray(new ArgumentHolder[arrayList.size()]);
        FieldType[] resultFieldTypes = getResultFieldTypes();
        FieldType[] fieldTypeArr = new FieldType[arrayList.size()];
        for (int i = 0; i < argumentHolderArr.length; i++) {
            fieldTypeArr[i] = argumentHolderArr[i].getFieldType();
        }
        if (!this.type.isOkForStatementBuilder()) {
            throw new IllegalStateException("Building a statement from a " + this.type + " statement is not allowed");
        }
        TableInfo<T, ID> tableInfo = this.tableInfo;
        if (this.databaseType.isLimitSqlSupported()) {
            l = null;
        }
        return new MappedPreparedStmt<>(tableInfo, buildStatementString, fieldTypeArr, resultFieldTypes, argumentHolderArr, l, this.type);
    }

    public String prepareStatementString() throws SQLException {
        return buildStatementString(new ArrayList());
    }

    public void clear() {
        this.where = null;
    }

    private String buildStatementString(List<ArgumentHolder> list) throws SQLException {
        StringBuilder sb = new StringBuilder(128);
        appendStatementString(sb, list);
        String sb2 = sb.toString();
        logger.debug("built statement {}", sb2);
        return sb2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendStatementString(StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        appendStatementStart(sb, list);
        appendWhereStatement(sb, list, true);
        appendStatementEnd(sb, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendWhereStatement(StringBuilder sb, List<ArgumentHolder> list, boolean z) throws SQLException {
        if (this.where == null) {
            return;
        }
        if (z) {
            sb.append("WHERE ");
        } else {
            sb.append("AND (");
        }
        this.where.appendSql(this.addTableName ? this.tableName : null, sb, list);
        if (z) {
            return;
        }
        sb.append(") ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FieldType verifyColumnName(String str) {
        return this.tableInfo.getFieldTypeByColumnName(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StatementType getType() {
        return this.type;
    }

    /* loaded from: classes2.dex */
    public enum StatementType {
        SELECT(true, true, false, false),
        SELECT_LONG(true, true, false, false),
        SELECT_RAW(true, true, false, false),
        UPDATE(true, false, true, false),
        DELETE(true, false, true, false),
        EXECUTE(false, false, false, true);

        private final boolean okForExecute;
        private final boolean okForQuery;
        private final boolean okForStatementBuilder;
        private final boolean okForUpdate;

        StatementType(boolean z, boolean z2, boolean z3, boolean z4) {
            this.okForStatementBuilder = z;
            this.okForQuery = z2;
            this.okForUpdate = z3;
            this.okForExecute = z4;
        }

        public boolean isOkForStatementBuilder() {
            return this.okForStatementBuilder;
        }

        public boolean isOkForQuery() {
            return this.okForQuery;
        }

        public boolean isOkForUpdate() {
            return this.okForUpdate;
        }

        public boolean isOkForExecute() {
            return this.okForExecute;
        }
    }
}
