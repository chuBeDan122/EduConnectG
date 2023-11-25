/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ENTITY.ThongBao;
import UTILS.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Hân Mai
 */
public class ThongBaoDAO extends EduConnectDAO<ThongBao, String> {

    String INSERT_SQL = "INSERT INTO ThongBao VALUES (?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ThongBao set TieuDe = ?, MaQuanTriVien = ?, NoiDung = ?, NgayGui = ? WHERE MaThongBao = ?";
    String DELETE_SQL = "DELETE FROM ThongBao Where MaThongBao = ?";
    String SELECT_ALL_SQL = "SELECT * FROM ThongBao";
    String SELECT_BY_ID_SQL = "SELECT * FROM ThongBao WHERE MaThongBao = ?";

    @Override
    public void insert(ThongBao entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getMaThongBao(),
                entity.getTieuDe(),
                entity.getMaQuanTriVien(),
                entity.getNoiDung(),
                entity.getNgayGui());
    }

    @Override
    public void update(ThongBao entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getTieuDe(),
                entity.getMaQuanTriVien(),
                entity.getNoiDung(),
                entity.getNgayGui(),
                entity.getMaThongBao());
    }

    @Override
    public void delete(String maThongBao) {
        JDBCHelper.update(DELETE_SQL, maThongBao);
    }

    @Override
    public ThongBao selectByid(String id) {
        List<ThongBao> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ThongBao> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ThongBao> selectBySql(String sql, Object... args) {
        List<ThongBao> list = new ArrayList<ThongBao>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                ThongBao entity = new ThongBao();
                entity.setMaThongBao(rs.getInt("MaThongBao"));
                entity.setTieuDe(rs.getString("TieuDe"));
                entity.setMaQuanTriVien(rs.getString("MaQuanTriVien"));
                entity.setNoiDung(rs.getString("NoiDung"));
                entity.setNgayGui(rs.getDate("NgayGui"));


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
