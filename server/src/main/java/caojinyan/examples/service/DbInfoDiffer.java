package caojinyan.examples.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

@Component
public class DbInfoDiffer {
    @Resource
    DataSource dataSource;

    public void diff() {


    }

    List<String> getTableList(DataSource dataSource) {
        List<String> result = Lists.newArrayList();
        try {
            Connection conn = dataSource.getConnection();
            ResultSet query = conn.prepareCall("show tables").executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
