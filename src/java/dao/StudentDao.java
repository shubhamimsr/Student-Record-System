
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class StudentDao {

    JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    
    public void AddStudent(Student st){
        int rno=GetNextRollNo();
        template.execute("insert into tblstudents(rno,name,qualification,percentage)values("+st.getRno()+",'"+st.getName()+"','"+st.getQualification()+"',"+st.getPercentage()+")");
    }
    public void UpdateStudent(Student st){
        template.execute("update tblstudents set name='"+st.getName()+"', qualification= '"+st.getQualification()+"', percentage="+st.getPercentage()+" where rno="+st.getRno());
    }

    public void DeleteStudent(int rno){
        template.execute("delete from tblstudents where rno="+rno);
    }

    public List<Student>GetStudents(){
        return template.query("select * from tblstudents order by rno", new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
            
                Student st=new Student(rs.getInt("rno"), rs.getString("name"), rs.getString("qualification"), rs.getFloat("percentage"));
                return st;
            }
        });
    }
    
     public Student GetStudent(int rno){
        return template.query("select * from tblstudents where rno="+rno, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
            
                Student st=new Student(rs.getInt("rno"), rs.getString("name"), rs.getString("qualification"), rs.getFloat("percentage"));
                return st;
            }
        }).get(0);
    }
     
    public int GetNextRollNo(){
        List<Student>lst=null;
        lst=template.query("select max(rno)rno from tblstudents", new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
            
                Student st=new Student(rs.getInt("rno"), "","",0);
                return st;
            }
        });
        int rno=0;
        if(lst==null)
        {
            rno=1;
        }
        else
        {
            rno=lst.get(0).getRno()+1;
        }
        return rno;
    }


}
