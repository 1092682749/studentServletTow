package upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UploadFile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//        Part part = req.getPart("file");
//        BufferedInputStream in = new BufferedInputStream(part.getInputStream());
//        byte[] bb = new byte[1024];
//        while ((in.read(bb))!= -1){
//            System.out.println(bb);
//        }
//        System.out.println(part.getName());
        List<String> fileName = new ArrayList<>();
        boolean ismultipartContent = ServletFileUpload.isMultipartContent(req);
        //判断是否是文件上传
        if (ismultipartContent) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            try {
                List<FileItem> fileItems = fileUpload.parseRequest(req);
                for (FileItem fileItem : fileItems) {
                    //判断字段的类型
                    if (fileItem.isFormField()) {
                        //处理普通字段
                    } else {
                        //文件字段,处理上传字段，这里就不把文件保存到本地了
                        System.out.println(fileItem.getName());
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            //将list转化为json
//            int index = 1;
//            JSONArray jsonArray = new JSONArray();
//            for (String s : fileName){
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("file",s);
//                jsonArray.put(jsonObject);
//            }
//            resp.getWriter().print(jsonArray);

        }}
}
