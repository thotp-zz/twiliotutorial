/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thotp.twiliotutorial.servlet;

import com.thotp.twiliotutorial.entity.InboundMessage;
import com.thotp.twiliotutorial.util.DbUtils;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tho
 */
public class InboundHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sid = req.getParameter("MessageSid");
        if (sid != null && !sid.isEmpty()) {
            String from = req.getParameter("From");
            String body = req.getParameter("Body");
            InboundMessage in = new InboundMessage();
            in.setSid(sid);
            in.setFrom(from);
            in.setBody(body);
            try {
                DbUtils.insert(in);
            } catch (SQLException e) {
                // do nothing
            }
        }
    }
}
