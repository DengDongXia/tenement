package utils;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;



public class FileUploadUtil {
	public static final String FILE_SEPARATOR_REG = File.separator.equals("/") ? File.separator  
	        : File.separator + File.separator;  
	// �����ַ��滻  

	//���������ַ����ĺ���
    public static String codeString(String str){
        String s = str;
        try {
            byte[] temp = s.getBytes("utf-8");
            s = new String(temp);
            return s ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return s;
        }
    }
  public static void zoomImage(String src,String dest,int w,int h) throws Exception {
        
        double wr=0,hr=0;
    	//src = src.replaceAll("\\\\", FILE_SEPARATOR_REG).replaceAll("/", FILE_SEPARATOR_REG).replaceAll("\\", FILE_SEPARATOR_REG);  
    	//dest = dest.replaceAll("\\\\", FILE_SEPARATOR_REG).replaceAll("/", FILE_SEPARATOR_REG).replaceAll("\\", FILE_SEPARATOR_REG);  
        File srcFile = new File(FileUploadUtil.class.getResource(src).toURI());
        File destFile = new File(dest);
        System.out.println(srcFile);
        BufferedImage bufImg = ImageIO.read(srcFile); //��ȡͼƬ
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//��������Ŀ��ͼƬģ��
        
        wr=w*1.0/bufImg.getWidth();     //��ȡ���ű���
        hr=h*1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile); //д���������ͼƬ
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
