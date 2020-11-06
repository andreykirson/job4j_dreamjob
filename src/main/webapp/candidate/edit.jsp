<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Candidate" %>
<%@ page import="store.PsqlStore" %>
<%@ page import="store.Store" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Работа мечты</title>

    <script>

    function validate() {
            var x = document.forms["edit-candidate"]["candidate-name"].value;
            if (x == "") {
                alert("Please enter the candidate name");
                return false;
            }
            return true;
        }

    $(document).ready(function () {
        $.ajax({
            url: 'http://localhost:8080/dreamjob/city.do',
            type: "GET",
            dataType: "json",
            success: function(data){
                var data = JSON.parse(JSON.stringify(data))
                var options = $("#select-city");
                $.each(data, function(index) {
                    options.append($("<option />").val(data[index].id).text(data[index].name));
                });
            }
        });
    })

    </script>

</head>
<body>

<%
    String id = (String) request.getSession().getAttribute("id");
    Candidate candidate = new Candidate(0, "");
    if (id != null) {
        Store store = PsqlStore.instOf();
        candidate = store.findCandidateById(Integer.valueOf(id));
        System.out.println(candidate.getId());
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
                        <form action="<%=request.getContextPath()%>/candidates.do" method="post">
                            <div class="form-group">
                                <label>Имя</label>
                                <input type="number" class="form-control" hidden name="id" value="<%=candidate.getId()%>">
                                <input type="text" class="form-control" name="candidate-name" value="<%=candidate.getName()%>">
                                <input type="text" class="form-control" name="name-photo" value="<%=request.getSession().getAttribute("photoSource")%>">
                                <select name="select" id="select-city">
                                    <option>Please select your City</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary" onclick="return validate()";>Save</button>
                        </form>
            </div>
        </div>
    </div>
</div>
</body>



</html>