/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ENTITY.KhoaHoc;
import UTILS.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hân Mai
 */
public class KhoaHocDAO extends EduConnectDAO<KhoaHoc, String> {

    String INSERT_SQL = "INSERT INTO KhoaHoc VALUES (?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhoaHoc set TenKhoaHoc = ?, MoTa = ?, MaGiangVien = ?, LichHoc = ?, Gia = ?, HinhAnh = ? WHERE MaKhoaHoc = ?";
    String DELETE_SQL = "DELETE FROM KhoaHoc Where MaKhoaHoc = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKhoaHoc = ?";

    @Override
    public void insert(KhoaHoc entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getMaKhoaHoc(),
                entity.getTenKhoaHoc(),
                entity.getMoTa(),
                entity.getMaGiangVien(),
                entity.getLichHoc(),
                entity.getGia(),
                entity.getHinh());
    }

    @Override
    public void update(KhoaHoc entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getTenKhoaHoc(),
                entity.getMoTa(),
                entity.getMaGiangVien(),
                entity.getLichHoc(),
                entity.getGia(),
                entity.getHinh(),
                entity.getMaKhoaHoc());
    }

    @Override
    public void delete(String maKhoaHoc) {
        JDBCHelper.update(DELETE_SQL, maKhoaHoc);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKhoaHoc(rs.getString("MaKhoaHoc"));
                entity.setTenKhoaHoc(rs.getString("TenKhoaHoc"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setMaGiangVien(rs.getInt("MaGiangVien"));
                entity.setLichHoc(rs.getString("LichHoc"));
                entity.setGia(rs.getFloat("Gia"));
                entity.setHinh(rs.getString("HinhAnh"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log chi tiết về ngoại lệ
            throw new RuntimeException("Lỗi khi thực hiện truy vấn SQL", e);
        }
    }

    @Override
    public KhoaHoc selectByid(String id) {
        List<KhoaHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<KhoaHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhoaHoc WHERE TenKhoaHoc LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

}
