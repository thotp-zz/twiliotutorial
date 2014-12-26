<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Home</title>
        <style>
            body { font-family: arial; font-size: 14px; }
            thead { font-weight: bold; }
            td { padding: 5px; }
        </style>
    </head>
    <body>
        <h2>Sending and Receiving SMSs with Twilio</h2>
        <form action="/home" method="post">
            <input type="hidden" name="_action" value="sendMessage" />
            <span>To:</span> <input type="text" name="to"/> <br/>
            <span>Message body:</span><br />
            <textarea name="body"></textarea>
            <br /> <input type="submit" value="Send" />
        </form>
        <h3>Outbound Messages:</h3>
        <c:if test="${ empty outs }">
            There is no outbound message yet!
        </c:if>
        <c:if test="${ !empty outs}">
            <table border="1">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Sid</td>
                        <td>To</td>
                        <td>Body</td>
                        <td>Status</td>
                        <td>Created</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ outs }" var="item">
                        <tr>
                            <td>${ item.id }</td>
                            <td>${ item.sid }</td>
                            <td>${ item.to }</td>
                            <td>${ item.body }</td>
                            <td>${ item.status }</td>
                            <td>${ item.createdDate }</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <h3>Inbound Messages:</h3>
        <c:if test="${ empty ins }">
            There is no inbound message yet!
        </c:if>
        <c:if test="${ !empty ins}">
            <table border="1">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Sid</td>
                        <td>From</td>
                        <td>Body</td>
                        <td>Received</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ ins }" var="item">
                        <tr>
                            <td>${ item.id }</td>
                            <td>${ item.sid }</td>
                            <td>${ item.from }</td>
                            <td>${ item.body }</td>
                            <td>${ item.receivedDate }</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>