package fr.cyu.depinfo.agp.tahiti.persistence.bde.operators;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.JdbcConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLOperator extends AbstractFinalOperator{

    private ResultSet resultSet;

    public SQLOperator(String query) {
        super(query);
        try {
            PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(getQuery(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
        try {
            resultSet.beforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> next() {
        ResultSetMetaData metaData = null;
        Map<String, Object> map = new HashMap<String, Object>();
        int colCount;

        try {
            if(!resultSet.next()){
                return null;
            }
            metaData = resultSet.getMetaData();
            colCount = metaData.getColumnCount();

            for (int i = 1; i <= colCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                map.put(columnName, columnValue);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return map;
    }
}
