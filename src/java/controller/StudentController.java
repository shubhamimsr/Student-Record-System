package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import model.Student;
import dao.StudentDao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class StudentController {
    @Autowired
    StudentDao sdao;
    @RequestMapping("student")
 
 public ModelAndView StudentView(){
     
     int rno=sdao.GetNextRollNo();
     Student st=new Student(rno,"","",0);
     ModelAndView m =new ModelAndView("student/index","command",st);
     m.addObject("students",sdao.GetStudents());
     return m;
 }
 
  @RequestMapping(value ="student", method = RequestMethod.POST)
    public ModelAndView StudentView(@ModelAttribute("st")Student st){
     sdao.AddStudent(st);
     int rno=sdao.GetNextRollNo();
     Student std=new Student(rno,"","",0);
     ModelAndView m =new ModelAndView("student/index","command",std);
     m.addObject("students",sdao.GetStudents());
     m.addObject("msg","Student Added Succesfully");
     return m;
 }
 
 @RequestMapping("Edit/{id}")
 public ModelAndView EditStudentView(@PathVariable("id")int id){
     
     Student st= sdao.GetStudent(id);
     ModelAndView m =new ModelAndView("student/edit","command",st);
     
     m.addObject("students",sdao.GetStudents());
     return m;
 }
 
   @RequestMapping(value ="Edit", method = RequestMethod.POST)
 public RedirectView UpdateStudentView(@ModelAttribute("st")Student st){
     
    System.out.println(st.getRno()+" "+st.getName()+" "+st.getQualification()+" "+st.getPercentage());
    sdao.UpdateStudent(st);
    RedirectView r=new  RedirectView("/SpringMVCWithJDBC/student");
    return r;
 }
 
 @RequestMapping("Delete/{id}")
 public RedirectView DeleteStudentView(@PathVariable("id")int rno){
     sdao.DeleteStudent(rno);
    RedirectView r=new  RedirectView("/SpringMVCWithJDBC/student");
    return r;
 }
}