/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thotp.twiliotutorial.servlet;

import com.thotp.twiliotutorial.entity.OutboundMessage;
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
public class StatusCallbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sid = req.getParameter("MessageSid");
        String status = req.getParameter("MessageStatus");
        if (sid != null && !sid.isEmpty()) {
            try {
                OutboundMessage msg = DbUtils.getOutboundMessageBySid(sid);
                if (msg != null) {
                    msg.setStatus(status);
                    DbUtils.update(msg);
                }
            } catch (SQLException e) {
                // do nothing
            }
        }
    }
}
