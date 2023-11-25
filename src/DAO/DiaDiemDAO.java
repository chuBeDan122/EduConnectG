/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ENTITY.DiaDiem;
import UTILS.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author Hân Mai
 */
public class DiaDiemDAO extends EduConnectDAO<DiaDiem, String> {

    String INSERT_SQL = "INSERT INTO DiaDiem VALUES (?,?)";
    String UPDATE_SQL = "UPDATE DiaDiem set TenDiaDiem = ? WHERE MaDiaDiem = ?";
    String DELETE_SQL = "DELETE FROM DiaDiem Where MaDiaDiem = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DiaDiem";
    String SELECT_BY_ID_SQL = "SELECT * FROM DiaDiem WHERE MaDiaDiem = ?";

    @Override
    public void insert(DiaDiem entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getMaDiaDiem(),
                entity.getTenDiaDiem());
    }

    @Override
    public void update(DiaDiem entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getTenDiaDiem(),
                entity.getMaDiaDiem());
    }

    @Override
    public void delete(String maDiaDiem) {
        JDBCHelper.update(DELETE_SQL, maDiaDiem);
    }

    @Override
    public DiaDiem selectByid(String id) {
        List<DiaDiem> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DiaDiem> selectAll() {
       return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<DiaDiem> selectBySql(String sql, Object... args) {
        List<DiaDiem> list = new ArrayList<DiaDiem>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                DiaDiem entity = new DiaDiem();
                entity.setMaDiaDiem(rs.getInt("MaDiaDiem"));
                entity.setTenDiaDiem(rs.getString("TenDiaDiem"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log chi tiết về ngoại lệ
            throw new RuntimeException("Lỗi khi thực hiện truy vấn SQL", e);
        }
    }
    }


