<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <link href="<c:url value='/resources/css/bootstrap.min.css'></c:url>" rel="stylesheet"/>
          <script src="<c:url value='/resources/js/jquery.min.js'></c:url>"></script>
          <script src="<c:url value='/resources/js/bootstrap.min.js'></c:url>"></script>
          <script>
              $(document).ready(function(){
                  ClearData();
                  FetchStudents();
                  
                  $("#btnsubmit").click(function(){
                      AddStudent();
                  })
                   
                  $("#btnupdate").click(function(){
                      UpdateStudent();
                  })
                   $("#btnclear").click(function(){
                      ClearData();
                  })
              })
              function FetchStudents(){
                  $.ajax({
                      url:'/SpringMVCWithJDBC/studentlist',
                      method:'get',
                      contentType:'application/json',
                      success:function(resp){
//                          console.log(resp);
                            $("#tbldata").empty();
                            $.each(resp,function(i,d){
                                $("#tbldata").append("<tr><td>"+d.rno+"</td><td>"+d.name+"</td><td>"+d.qualification+"</td><td>"+d.percentage+"</td><td><input type='button' value='View' onclick='ViewStudent("+d.rno+")' /> &nbsp;<input type='button' value='Delete' onclick='DeleteStudent("+d.rno+")' /></td></tr>")
                            })
                    }
                  })
              }
              function AddStudent(){
                  var st={"rno":$("#txtrno").val(),"name":$("#txtname").val(),"qualification":$("#txtqualification").val(),"percentage":$("#txtpercentage").val()}
                  $.ajax({
                      url:'/SpringMVCWithJDBC/addstudent',
                      method:'POST',
                      data: JSON.stringify(st),
                      contentType:'application/json',
                      success:function(resp){
                          console.log(resp);
                          ClearData();
                         FetchStudents();
                    }
                  })
              }
              
              function  ViewStudent(s){
                   $.ajax({
                      url:'/SpringMVCWithJDBC/studentbyrno/'+s,
                      method:'get',
                      contentType:'application/json',
                      success:function(resp){
                          console.log(resp);
                           $("#txtrno").val(resp.rno);
                            $("#txtname").val(resp.name);
                            $("#txtqualification").val(resp.qualification);
                            $("#txtpercentage").val(resp.percentage);
                                $("#btnsubmit").hide();
                                $("#btnupdate").show();

                    }
                  })

                }
                
                 function UpdateStudent(){
                  var st={"rno":$("#txtrno").val(),"name":$("#txtname").val(),"qualification":$("#txtqualification").val(),"percentage":$("#txtpercentage").val()}
                  $.ajax({
                      url:'/SpringMVCWithJDBC/updatestudent',
                      method:'POST',
                      data: JSON.stringify(st),
                      contentType:'application/json',
                      success:function(resp){
                          console.log(resp);
                          ClearData();
                         FetchStudents();
                    }
                  })
              }
              
              function  DeleteStudent(s){
                   if(confirm("Really wants to Delete?"))
                   {
                       $.ajax({
                      url:'/SpringMVCWithJDBC/deletestudent/'+s,
                      method:'get',
                      contentType:'application/json',
                      success:function(resp){
                              console.log(resp);
//                          ClearData();
                         FetchStudents();
                      }
                  })
                   }
               }
              function ClearData(){
                  $("#txtrno").val("");
                  $("#txtname").val("");
                  $("#txtqualification").val("");
                  $("#txtpercentage").val("");
                  
                  $("#btnsubmit").show();
                  $("#btnupdate").hide();
              }
          </script>
    </head>
    <body>
        <h1 class="text-center" style="font-family: serif">Student Data!</h1><hr/>
        <div class="container">
            <div class="col-md-3">
                <h2>Enter the Student data</h2>
            
                <table class="table table-responsive">
                    <tr>
                        <td>Roll No</td>
                        <td><input type="text" id="txtrno" /></td>
                    </tr>
                    <tr>
                        <td>Student Name</td>
                        <td><input type="text" id="txtname" /></td>
                    </tr>
                    <tr>
                        <td>Qualification</td>
                        <td><input type="text" id="txtqualification" /></td>
                    </tr>
                    <tr>
                        <td>Percentage</td>
                        <td><input type="text" id="txtpercentage" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                              <input type="button" id="btnsubmit" value="Submit" class="btn btn-primary"/>
                        
                              <input type="button" id="btnupdate" value="Update" class="btn btn-info"/>
                          
                              <input type="button" id="btnclear" value="Clear" class="btn btn-danger"/>
                        </td>
                    </tr>

                </table>
        <h4>${msg}</h4>
            </div>
            <div class="col-md-1"  style="border-right:1px solid black; height:500px"></div>
            
            <div class="col-md-8">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Roll No</th>
                            <th>Student Name</th>
                            <th>Qualification</th>
                            <th>Percentage</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="tbldata">
                    
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
