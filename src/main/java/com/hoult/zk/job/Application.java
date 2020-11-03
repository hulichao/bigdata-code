package com.hoult.zk.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.ToString;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {
    //数据库配置连接
    private static HikariDataSource hikariDataSource;

    //Zk客户端
    private static ZkClient zkClient;

    //数据操作对象
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 启动服务
     *
     * 1. 启动 web 容器
     * 2. 初始化 zookeeper
     * 3. 配置数据库连接池
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        initZk();

        configHikariSource();
    }

    //初始化zk
    private static void initZk() {

        zkClient = new ZkClient("centos:8521");

        zkClient.setZkSerializer(new ZkStrSerializer());

        zkClient.subscribeDataChanges("/jdbc", new IZkDataListener() {

            public void handleDataChange(String path, Object data) {

                System.out.println(path + " data is changed, new data " + data);

                hikariDataSource.close();

                configHikariSource();
            }

            public void handleDataDeleted(String path) {

                System.out.println(path + " is deleted!!");

                hikariDataSource.close();
            }
        });
    }

    /**
     * 配置数据库连接池
     *
     * 1. 从 zookeeper 中获取配置信息
     * 2. 更新 hikari 配置
     * 3. 执行测试 sql
     */
    private static void configHikariSource(){

        JDBCConfig myConfig = getJDBCConfig();

        updateHikariConfig(myConfig);

        try {

            executeTestSQL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //执行测试sql
    private static void executeTestSQL() throws SQLException {

        Connection connection = hikariDataSource.getConnection();

        PreparedStatement pst = connection.prepareStatement( "SELECT id, username FROM user;" );

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            System.out.println("id : " + rs.getString(1) + " , username : " + rs.getString(2)+ " , password : " + rs.getString(3));
        }
    }

    //更新数据库信息
    private static void updateHikariConfig(JDBCConfig myConfig) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(myConfig.getUrl());
        config.setUsername(myConfig.getUsername());
        config.setPassword(myConfig.getPassword());
        config.addDataSourceProperty( "driverClassName" , myConfig.getDriver());
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        hikariDataSource = new HikariDataSource(config);
    }

    private static JDBCConfig getJDBCConfig() {

        Object data = zkClient.readData("/jdbc");

        try {
            JDBCConfig myConfig = mapper.readValue(data.toString(), JDBCConfig.class);

            System.out.println(myConfig.toString());

            return myConfig;

        } catch (JsonProcessingException e) {

            return new JDBCConfig();
        }
    }
}

class ZkStrSerializer implements ZkSerializer {

    @Override
    public byte[] serialize(Object o) throws ZkMarshallingError {
        return String.valueOf(o).getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes);
    }
}

@Data
@ToString
class JDBCConfig {

    private String url;

    private String driver = "com.mysql.jdbc.Driver";

    private String username;

    private String password;
}
