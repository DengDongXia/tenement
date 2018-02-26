package servlet;
import java.io.File;  
import java.io.*;  
import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import utils.FileUploadUtil;
import net.coobird.thumbnailator.Thumbnails;
public class FileUploadServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		doPost(request,response);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw=response.getWriter();
        
        //1.��ñ����ϴ��ļ���Ŀ¼��·���Լ������ļ���Ŀ¼
        String savaPath=this.getServletContext().getRealPath("upload");
        //System.out.println(savaPath);
        
        String tempPath=this.getServletContext().getRealPath("temp");
        //System.out.println(tempPath);
        //2.���ô����ļ���Ŀ�������ʵ�������û���Ŀ¼�Լ��ļ���С����
        DiskFileItemFactory dfif=new DiskFileItemFactory();
        dfif.setRepository(new File(tempPath));
        dfif.setSizeThreshold(1024*10);
        
        //3.���ServletFileUpload��ʵ��
        ServletFileUpload sfu=new ServletFileUpload(dfif);
        sfu.setSizeMax(1024*1024*5);//�����ϴ����ܴ�СΪ5MB
        sfu.setFileSizeMax(1024*1024*5);//����ÿ���ϴ����ļ���С���Ϊ5MB
        
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Map<String, Object> res = new HashMap<String, Object>();
        String result;
        
        //4.�����ϴ��б�
        try {
            List<FileItem> list = sfu.parseRequest(request);
            Iterator<FileItem> it = list.iterator();
            System.out.println("testssssssssssss");
            ArrayList<String> pas = new ArrayList<String>();
            while(it.hasNext())
            {
            	System.out.println("testssssssssssss");
                FileItem file=it.next();
                String fileName=file.getName();
                fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
                String fileType=file.getContentType();
                InputStream is=file.getInputStream();
                
                int hashCode=fileName.hashCode();
                String dir1=Integer.toHexString(hashCode&0XF);//�����һ��Ŀ¼
                String dir2=Integer.toHexString((hashCode>>4)&0XF);//����ڶ���Ŀ¼
                
                String aimDir=savaPath+"/"+dir1;
                if(!new File(aimDir).exists())
                {
                    new File(aimDir).mkdir();
                }
                aimDir=aimDir+"/"+dir2;
                if(!new File(aimDir).exists())
                {
                    new File(aimDir).mkdir();
                }
//                String extName=fileName.substring(fileName.lastIndexOf("."));
                String savaFileName=aimDir+"/";
                String extName=fileName.substring(fileName.lastIndexOf("."));
                fileName=UUID.randomUUID().toString().replaceAll("-", "")+extName;
                savaFileName=savaFileName+fileName;
                FileOutputStream fos=new FileOutputStream(new File(savaFileName));
                long fileSize=is.available();
                int length=-1;
                byte []buff=new byte[1024*1024];
                while((length=is.read(buff))!=-1){
                    fos.write(buff, 0, length);
                }
                fos.close();
                is.close();
                file.delete();//��ջ����ļ�
                /*
                pw.println("�ļ��ϴ��ɹ���");
                pw.println("<br/>�ļ�����"+fileName);
                pw.println("<br/>�ļ���С��"+fileSize);
                pw.println("<br/>�ļ����ͣ�"+fileType);
                if(fileType.contains("image"))
                {
                    pw.println("<br/>ͼƬ��");
                    String temp=getServletContext().getContextPath()+"/upload/"+dir1+"/"+dir2+"/"+fileName;
                    pw.println("<br/><img width='500' src='"+temp+"'/>");
                }
                pw.println("<hr/>");*/
                //String binPath=System.getProperty("user.dir");
                //String pt=binPath.substring(0, binPath.lastIndexOf("\\"))+File.separator+"webapps"+File.separator+"FleaMarket"+File.separator+"upload"+File.separator+dir1+File.separator+dir2+File.separator+fileName;

                String pn = getServletContext().getContextPath()+"/upload/"+dir1+"/"+dir2+"/"+fileName;
                String pt = this.getServletContext().getRealPath("upload")+"/"+dir1+"/"+dir2+"/"+fileName;
                
                System.out.println(pt);
                Thumbnails.of(pt).size(1000, 750).toFile(pt);
               // FileUploadUtil.zoomImage(pt, pt, 1000, 750);
                
                pas.add(pn);
                System.out.println(pn);
            }
            res.put("ret", "true");
            res.put("path", pas);
            
        } catch (FileUploadException e) {
            e.printStackTrace();
            res.put("ret", "false");
            res.put("res", "�ļ�����");
           
        }catch(Exception e){
        	  e.printStackTrace();
              res.put("ret", "false");
              res.put("res", "�������쳣");
        }finally {
        	result = gson.toJson(res);
    		System.out.println(result);
    		pw.print(result);
    		pw.flush();
    		pw.close();	
        }
    }

          
        
	
	

}
