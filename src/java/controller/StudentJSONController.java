
package controller;
import model.Student;
import dao.StudentDao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentJSONController {
   @Autowired
   StudentDao sdao;
//   @RequestMapping("json")
//
//    public ModelAndView StudentView(){
//     int rno=sdao.GetNextRollNo();
//     Student st=new Student(rno,"","",0);
//     ModelAndView m =new ModelAndView("studentjson/index","command",st);
//     
//     m.addObject("students",sdao.GetStudents());
//     return m;
// }
// 
//  @RequestMapping(value ="student", method = RequestMethod.POST)
//    public ModelAndView StudentView(@ModelAttribute("st")Student st){
//     sdao.AddStudent(st);
//     int rno=sdao.GetNextRollNo();
//     Student std=new Student(rno,"","",0);
//     ModelAndView m =new ModelAndView("studentjson/index","command",std);
//     
//     m.addObject("students",sdao.GetStudents());
//     m.addObject("msg","Student Added Succesfully");
//     return m;
// }
   
    @RequestMapping("json")
    public ModelAndView JsonView(){
        int rno = sdao.GetNextRollNo();
        Student st = new Student(rno, "", "", 0);
        ModelAndView m =new ModelAndView("studentjson/index","command", st);
        return m;
    }
    
    @RequestMapping(value = "studentlist")
    public @ResponseBody List<Student>GetStudents(){
        return sdao.GetStudents();
    }
      @RequestMapping(value = "studentbyrno/{rno}")
    public @ResponseBody Student GetStudentByRno(@PathVariable("rno")int rno){
        return sdao.GetStudent(rno);
    }
    
    @RequestMapping(value = "addstudent" , method = RequestMethod.POST)
    public @ResponseBody Student AddStudent(@RequestBody Student st)
    {
        sdao.AddStudent(st);
        return st;
    }
    
     @RequestMapping(value = "updatestudent" , method = RequestMethod.POST)
    public @ResponseBody Student UpdateStudent(@RequestBody Student st)
    {
        sdao.UpdateStudent(st);
        return st;
    }
     @RequestMapping(value = "deletestudent/{rno}")
    public @ResponseBody Student DeleteStudent(@PathVariable("rno")int rno){
        Student st=sdao.GetStudent(rno);
        sdao.DeleteStudent(rno);
        return  st;
    }
    
    
}
