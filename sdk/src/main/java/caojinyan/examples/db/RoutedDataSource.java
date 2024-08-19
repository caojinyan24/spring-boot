package caojinyan.examples.db;

import caojinyan.examples.common.DatabaseEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;


public class RoutedDataSource extends AbstractRoutingDataSource {
    private final DatabaseEnum databaseEnum;

    public RoutedDataSource(DatabaseEnum databaseEnum, DataSource defaultDataSource) {
        this.databaseEnum = databaseEnum;
        this.setTargetDataSources(RoutingDataSourceStrategy.getTargetDataSources(databaseEnum));
        if (defaultDataSource != null) {
            this.setDefaultTargetDataSource(defaultDataSource);
        }
    }


    @Override
    public Object determineCurrentLookupKey() {

        return DataSourceManager.getDatabaseName(databaseEnum);

    }

    @Override
    public DataSource determineTargetDataSource() {
        try {
            return super.determineTargetDataSource();
        } catch (Exception e) {
            logger.error("determineTargetDataSource error,", e);
            if (this.getResolvedDefaultDataSource() != null) {
                logger.error("determineTargetDataSource error, will use default datasource");
                return this.getResolvedDefaultDataSource();
            }
            throw e;
        }

    }


}
