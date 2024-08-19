package caojinyan.examples.db;

import caojinyan.examples.common.DatabaseEnum;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DataSourceManager {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceManager.class);


    private static final Gson gson = new Gson();


    public Map<String, DatabaseConf> loadApolloDbConfig() {
        Map<String, String> config = Maps.newHashMap();//todo to implement
        Map<String, DatabaseConf> result = Maps.newHashMap();
        for (Map.Entry<String, String> entry : config.entrySet()) {
            DatabaseConf item = gson.fromJson(entry.getValue(), DatabaseConf.class);
            item.markKey = entry.getKey();
            if (result.get(item.markKey) == null || !result.get(item.markKey).equals(item)) {
                result.put(item.markKey, item);
            }
        }
        return result;
    }

    public static String getDatabaseName(DatabaseEnum databaseEnum) {
        return "realDataBaseName";//todo to implement
    }


}
