package upload.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import uploadfile.upload.dbutil.DbConnect;


/**
 * Servlet implementation class upload
 */
//@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int file_id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//文件上传的目录
				String savepath=this.getServletContext().getRealPath("/WEB-INF/upload");
				//判断upload文件夹是否存在，如果不存在，则自动创建
				File file=new File(savepath);
				if(!file.exists()){
					file.mkdir();
				}
				System.out.println(savepath);
				//使用Apache文件上传组件
				try{
					//创建一个DiskFileItemFactory对象
					DiskFileItemFactory factory=new DiskFileItemFactory();
					//创建文件上传解析器
					ServletFileUpload upload=new ServletFileUpload(factory);
					//解决乱码的问题
					upload.setHeaderEncoding("utf-8");
					//判断上传上来的是否是表单数据，如果不是则什么都不做
					if(!ServletFileUpload.isMultipartContent(request)){
						return;
					}
					//使用集合把数据保存起来List<FileItem>
					List<FileItem> list=upload.parseRequest(request);
					for(FileItem item:list){
						//如果封装的是非文件数据（文字部分）
						if(item.isFormField()){
						     String value=item.getString("utf-8");
						     System.out.print(value);
						    //文字信息保存到数据库（下次在写）
						     saveTextToDb("admin",value);
					     }
						else{
							//得到上传的文件名
							String filename=item.getName();
							System.out.println(filename);
							//得到文件的类型
							String filenamelast=filename.substring(filename.lastIndexOf(".")+1);
							//防止文件重名，随机产生文件名，使用UUID
							String myname=UUID.randomUUID().toString()+"."+filenamelast;
							if(filename==null || filename.trim().equals("")){
								continue;
							}
							InputStream in=item.getInputStream();	
							FileOutputStream out=new FileOutputStream(savepath+"\\"+myname);
							byte buffer[]=new byte[1024];
							int len=0;
							while((len=in.read(buffer))>0){
								out.write(buffer,0 , len);
								
							}
							String url="/upload"+myname;
							saveUrlToDb(url,file_id);
							in.close();
							out.close();
							}
						}
						
					
				}
				catch(Exception e){
					e.getStackTrace();
					
				}
				
			}

	private void saveUrlToDb(String url, int file_id2) {
		// TODO Auto-generated method stub
		Connection conn=DbConnect.getConnection();
		String sql="insert into file_url(file_url,file_id) value(?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, url);
			ps.setInt(2,file_id2);
			int n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void saveTextToDb(String string, String value) {
		// TODO Auto-generated method stub
		Connection conn=DbConnect.getConnection();
		String sql="insert into file_main(username,demo) value(?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, string);
			ps.setString(2, value);
			int n=ps.executeUpdate();
			if(n>0){
				ResultSet rs=ps.getGeneratedKeys();
				if(rs.next()){
					file_id=rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
