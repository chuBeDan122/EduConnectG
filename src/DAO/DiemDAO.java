/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ENTITY.Diem;
import UTILS.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Hân Mai
 */
public class DiemDAO extends EduConnectDAO<Diem, String> {

    String INSERT_SQL = "INSERT INTO Diem VALUES (?,?,?,?)";
    String UPDATE_SQL = "UPDATE Diem set MaBaiTap = ?, MaHocSinh = ?, Diem = ? WHERE MaDiem = ?";
    String DELETE_SQL = "DELETE FROM Diem Where MaDiem = ?";
    String SELECT_ALL_SQL = "SELECT * FROM Diem";
    String SELECT_BY_ID_SQL = "SELECT * FROM Diem WHERE MaDiem = ?";

    @Override
    public void insert(Diem entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getDiem(),
                entity.getMaBaiTap(),
                entity.getMaHocSinh(),
                entity.getDiem());
    }

    @Override
    public void update(Diem entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getMaBaiTap(),
                entity.getMaHocSinh(),
                entity.getDiem(),
                entity.getMaDiem());
    }

    @Override
    public void delete(String maDiem) {
        JDBCHelper.update(DELETE_SQL, maDiem);
    }

    @Override
    public Diem selectByid(String id) {
        List<Diem> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Diem> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<Diem> selectBySql(String sql, Object... args) {
        List<Diem> list = new ArrayList<Diem>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                Diem entity = new Diem();
                entity.setMaDiem(rs.getInt("MaDiem"));
                entity.setMaBaiTap(rs.getInt("MaBaiTap"));
                entity.setMaHocSinh(rs.getInt("MaHocSinh"));
                entity.setDiem(rs.getInt("Diem"));

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
