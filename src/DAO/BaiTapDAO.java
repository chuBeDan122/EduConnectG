/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ENTITY.BaiTap;
import UTILS.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Hân Mai
 */
public class BaiTapDAO extends EduConnectDAO<BaiTap, String> {

    String INSERT_SQL = "INSERT INTO BaiTap VALUES (?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE BaiTap set MaLopHoc = ?, TieuDe = ?, MoTa = ?, HanChot = ?, DiemToiDa = ?, TepDinhKem = ? WHERE MaBaiTap = ?";
    String DELETE_SQL = "DELETE FROM BaiTap Where MaBaiTap = ?";
    String SELECT_ALL_SQL = "SELECT * FROM BaiTap";
    String SELECT_BY_ID_SQL = "SELECT * FROM BaiTap WHERE MaBaiTap = ?";

    @Override
    public void insert(BaiTap entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getMaBaiTap(),
                entity.getMaLopHoc(),
                entity.getTieuDe(),
                entity.getMoTa(),
                entity.getHanChot(),
                entity.getDiemToiDa(),
                entity.getTepDinhKem());
    }

    @Override
    public void update(BaiTap entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getMaLopHoc(),
                entity.getTieuDe(),
                entity.getMoTa(),
                entity.getHanChot(),
                entity.getDiemToiDa(),
                entity.getTepDinhKem(),
                entity.getMaBaiTap());
    }

    @Override
    public void delete(String maBaiTap) {
        JDBCHelper.update(DELETE_SQL, maBaiTap);
    }

    @Override
    public BaiTap selectByid(String id) {
        List<BaiTap> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<BaiTap> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<BaiTap> selectBySql(String sql, Object... args) {
        List<BaiTap> list = new ArrayList<BaiTap>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                BaiTap entity = new BaiTap();
                entity.setMaBaiTap(rs.getInt("MaBaiTap"));
                entity.setMaLopHoc(rs.getInt("MaLopHoc"));
                entity.setTieuDe(rs.getString("TieuDe"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setHanChot(rs.getDate("HanChot"));
                entity.setDiemToiDa(rs.getInt("DiemToiDa"));
                entity.setTepDinhKem(rs.getString("TepDinhKem"));


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
