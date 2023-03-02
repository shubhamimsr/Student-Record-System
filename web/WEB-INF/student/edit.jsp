<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <h2>Update Student </h2>
        <form:form action="/SpringMVCWithJDBC/Edit" method="post">
            <table>
                <tr>
                    <td>Roll No</td>
                    <td><form:input path="rno" readonly="true" /></td>
                </tr>
                <tr>
                    <td>Student Name</td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td>Qualification</td>
                    <td><form:input path="qualification" /></td>
                </tr>
                <tr>
                    <td>Percentage</td>
                    <td><form:input path="percentage" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit" />   
                    </td>
                </tr>

            </table>
        </form:form>
        <hr/>
        <table border="1">
            <thead>
                <tr>
                    <th>Roll No</th>
                    <th>Student Name</th>
                    <th>Qualification</th>
                    <th>Percentage</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${students}">
                    <tr>
                        <td>${s.rno}</td>
                        <td>${s.name}</td>
                        <td>${s.qualification}</td>
                        <td>${s.percentage}</td>
                        <td>
                            <a href="/SpringMVCWithJDBC/Edit/${s.rno}">Edit </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>