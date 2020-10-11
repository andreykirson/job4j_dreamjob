<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Candidate" %>
<%@ page import="store.PsqlStore" %>
<%@ page import="store.Store" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>



    <title>Работа мечты</title>
</head>
<body>

<%
    String id = request.getParameter("id");
    Candidate candidate = new Candidate(0, "");
    if (id != null) {
        Store store = PsqlStore.instOf();
        candidate = store.findCandidateById(Integer.valueOf(id));
    }
%>

<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                Новое резюме.
                <% } else { %>
                Редактирование резюме.
                <% } %>
            </div>
            <div class="card-body">
                        <form action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>" method="post">
                            <div class="form-group">
                                <label>Имя</label>
                                <input type="text" class="form-control" name="name" value="<%=candidate.getName()%>">
                            </div>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </form>
            </br>
                        <form action="<с:url value='/upload'>" method="post" enctype="multipart/form-data">
                            <label for="img">Select photo:</label>
                            <input type="file" value="Upload File" id="img" name="img" accept="image/*">
                            <input type="submit">
                        </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>