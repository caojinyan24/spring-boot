package caojinyan.examples.db;


import caojinyan.examples.common.DatabaseEnum;
import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class RoutingDataSourceStrategy {


    private static final Logger logger = LoggerFactory.getLogger(RoutingDataSourceStrategy.class);
    static DataSourceManager dataSourceConf = new DataSourceManager();
    private static final Map<String, DatabaseConf> confs;
    private static final String DATABASEURL = "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false";

    synchronized static Map<Object, Object> getTargetDataSources(DatabaseEnum databaseEnum) {
        Map<Object, Object> targetDataSources = Maps.newHashMap();
        for (Map.Entry<String, DatabaseConf> entry : confs.entrySet()) {
            try {
                if (entry.getKey().startsWith(databaseEnum.getDbName())) {
                    HikariConfig configuration = new HikariConfig();
                    configuration.setUsername(entry.getValue().user);
                    configuration.setPassword(entry.getValue().password);
                    configuration.setJdbcUrl(String.format(DATABASEURL, entry.getValue().host, entry.getValue().port, entry.getValue().dbname));
                    HikariDataSource dataSource = new HikariDataSource(configuration);
                    dataSource.setMaxLifetime(60000);
                    targetDataSources.put(entry.getValue().markKey, dataSource);
                }
            } catch (Exception e) {
                logger.warn("init dataSource error:{}", e.getMessage());
            }
        }
        logger.info("loadTargetDatasource:{}", targetDataSources);
        return targetDataSources;
    }

    static {
        confs = dataSourceConf.loadApolloDbConfig();
        logger.info("fetch datasource config:{}", confs);
    }


}
