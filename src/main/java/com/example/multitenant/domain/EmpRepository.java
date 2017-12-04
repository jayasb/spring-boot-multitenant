package com.example.multitenant.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmpRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Emp> findAll() {
        return jdbcTemplate.query("select * from emp", new EmpRowMapper());
    }
}

class EmpRowMapper implements RowMapper<Emp>
{
    @Override
    public Emp mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Emp emp = new Emp();
        emp.setEmpno(rs.getInt("empno"));
        emp.setEname(rs.getString("ename"));
        return emp;
    }
}