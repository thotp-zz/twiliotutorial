package com.thotp.twiliotutorial.servlet;

import com.thotp.twiliotutorial.entity.InboundMessage;
import com.thotp.twiliotutorial.entity.OutboundMessage;
import com.thotp.twiliotutorial.util.DbUtils;
import com.thotp.twiliotutorial.util.SmsUtils;
import com.twilio.sdk.TwilioRestException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<OutboundMessage> outs = null;
        try {
            outs = DbUtils.getOutboundMessages(0, 20);
        } catch (SQLException e) {
            throw new IOException(e);
        }
        List<InboundMessage> ins = null;
        try {
            ins = DbUtils.getInboundMessages(0, 20);
        } catch (SQLException e) {
            throw new IOException(e);
        }
        req.setAttribute("outs", outs);
        req.setAttribute("ins", ins);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("_action");
        if ("sendMessage".equals(action)) {
            String toNumber = req.getParameter("to");
            String body = req.getParameter("body");
            OutboundMessage msg;
            try {
                msg = SmsUtils.sendMessage(toNumber, body);
                if (msg != null) {
                    DbUtils.insert(msg);
                }
            } catch (TwilioRestException e) {
                throw new IOException(e);
            } catch (SQLException e) {
                throw new IOException(e);
            }
        }
        resp.sendRedirect("/home");
    }

}
