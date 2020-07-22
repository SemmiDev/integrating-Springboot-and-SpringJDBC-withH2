package com.sammidev.learn.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJdbcRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // retrieve
    class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setName(rs.getString("name"));
            student.setNisn(rs.getString("nisn"));
            return student;
        }
    }
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from student", new StudentRowMapper());
    }


    /**
     JdbcTemplate => untuk mengeksekusi query, konteks ini queryForObject
     new Object[] { id } =>  id kita passing sebagai parameter dalam query
     new BeanPropertyRowMapper<Student>(Student.class) => kita gunakan BeanProperty.... untuk map hasil dari resultSet ke bean Student
     */
    public Student findById(long id) {
        return jdbcTemplate.queryForObject("select * from student where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Student>(Student.class));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from student where id=?", new Object[] { id });
    }

    public int insert(Student student) {
        return jdbcTemplate.update("insert into student (id, name, nisn) " + "values(?,  ?, ?)",
                new Object[] { student.getId(), student.getName(), student.getName() });
    }

    public int update(Student student) {
        return jdbcTemplate.update("update student " + " set name = ?, nisn = ? " + " where id = ?",
                new Object[] { student.getName(), student.getNisn(), student.getId() });
    }
}
