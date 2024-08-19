package caojinyan.examples.common;



public enum DatabaseEnum {

    DB1("test"),

    ;

    DatabaseEnum(String dbName) {
        this.dbName = dbName;
    }

    private String dbName;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DatabaseEnum{");
        sb.append("dbName='").append(dbName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
