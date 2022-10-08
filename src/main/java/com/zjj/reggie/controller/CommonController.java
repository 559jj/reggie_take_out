package com.zjj.reggie.controller;

import com.zjj.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

//文件的上传下载等处理
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会自动删除
        log.info(file.toString());
        //获得上传文件的名字
        String originalFilename=file.getOriginalFilename();//abc.jpg
        //截取上传文件名的后缀名
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        //通过UUID重新生成文件名防止文件名重复造成为文件覆盖
        String filename = UUID.randomUUID()+suffix;
        //创建一个目录对象
        File dir=new File(basePath);
        if (!dir.exists()){
           //若目标目录不存在,自动生成
            dir.mkdirs();
        }
        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(filename);
    }

    @GetMapping("/download")
    //文件下载的方式有两种： 两种方式都是将文件从服务器传输到本地计算机的过程
    // 1以附件形式下载，弹出保存对话框，将文件保存到磁盘目录
    // 2直接在浏览器中打开
    public void download(String name, HttpServletResponse response){
        try {
            //通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(basePath+name);
            //通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            //设置给浏览器响应回去的文件类型
            response.setContentType("image/jpeg");
            int len=0;
            byte[] bytes=new byte[1024];
            //通过输入流的read方法将文件中的内容读取到byte数组中 当len=-1说明文件已读取完毕
            while ((len=fileInputStream.read(bytes))!=-1){
                //通过输出流的write方法向浏览器写数据
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
