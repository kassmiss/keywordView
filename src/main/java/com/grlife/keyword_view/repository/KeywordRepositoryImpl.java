package com.grlife.keyword_view.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class KeywordRepositoryImpl implements KeywordRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public KeywordRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void create(HttpServletRequest req) {

        String sql = "SELECT * FROM KEYWORD WHERE BLOG_CATE_NO = '0000' AND MB_CNT <> '< 10' AND MB_CNT > 1000 AND DOC_CNT > 0 AND DOC_CNT < 300 AND DATE_FORMAT(I_DATE, '%Y%m%d') = DATE_FORMAT(SYSDATE(), '%Y%m%d')";

        MapSqlParameterSource params = new MapSqlParameterSource();

        List<Map<String, Object>> list = jdbcTemplate.query(sql, params, listMapper());

        String fileName = UUID.randomUUID().toString();
        File file = new File("/keyword_file/" + fileName);

        //File file = new File("F:\test.html");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fOut, "UTF-8");
            BufferedWriter out = new BufferedWriter(osw);

            out.append("<!DOCTYPE html><html lang='ko'><head><title>키워드 뷰어</title><meta http-equiv='Content-Type' content='text/html'; charset='utf-8'/><link rel='stylesheet' type='text/css' href='/css/view_css.css'/></head><body class='view_css'");
            out.append("<table border='1'>");
            out.append("<thead><tr>" +
                    "<th>키워드</th>" +
                    "<th>위치</th>" +
                    "<th>타입</th>" +
                    "<th>문서수</th>" +
                    "<th>Mobile 조회수</th>" +
                    "<th>PC 조회수</th>" +
                    "</tr></thead>");
            out.append("<tbody>");

            for(Map<String, Object> map : list) {
                out.append("<tr>");

                out.append("<td>" + map.get("keyword") + "</td>");
                out.append("<td>" + map.get("blogLoc") + "</td>");
                out.append("<td>" + map.get("blogCate") + "</td>");
                out.append("<td>" + map.get("docCnt") + "</td>");
                out.append("<td>" + map.get("mbCnt") + "</td>");
                out.append("<td>" + map.get("pcCnt") + "</td>");

                out.append("</tr>");
            }

            out.append("</tbody>");
            out.append("</table>");
            out.append("</body></html>");

            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private RowMapper<Map<String, Object>> listMapper() {
        return (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("keyword", rs.getString("KEYWORD"));
            map.put("blogCate", rs.getString("BLOG_CATE"));
            map.put("blogCateNo", rs.getString("BLOG_CATE_NO"));
            map.put("blogLoc", rs.getString("BLOG_LOC"));
            map.put("docCnt", rs.getString("DOC_CNT"));
            map.put("mbVal", rs.getString("MB_VAL"));
            map.put("pcVal", rs.getString("PC_VAL"));
            map.put("mbCnt", rs.getString("MB_CNT"));
            map.put("pcCnt", rs.getString("PC_CNT"));
            map.put("iDate", rs.getString("I_DATE"));

            return map;
        };
    }

}
