package com.yigong.faceverify.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.yigong.faceverify.config.QiNiuKeyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * @author tianyi
 * @date 2018-02-20 20:03
 */
@RestController
@Slf4j
public class QiNiuFileUpUtil {

    /**基本配置-从七牛管理后台拿到*/
    //设置好账号的ACCESS_KEY和SECRET_KEY
    @Value("${qiniu.AK}")
    private String ACCESS_KEY;
    @Value("${qiniu.SK}")
    private static String SECRET_KEY;
    //要上传的空间名--
    @Value("${qiniu.spacename}")
    private String spacename;

    @Value("${qiniu.openUrl}")
    private String openUrl;

    public static void main(String[] args){
        System.out.println(SECRET_KEY);
    }


    @Autowired
    private QiNiuKeyConfig qiNiuKeyConfig;

    /**指定保存到七牛的文件名--同名上传会报错  {"error":"file exists"}*/
    /** {"hash":"FrQF5eX_kNsNKwgGNeJ4TbBA0Xzr","key":"aa1.jpg"} 正常返回 key为七牛空间地址 http:/xxxx.com/aa1.jpg */
    //上传文件的路径
    String FilePath ="F:\\Tencent Files\\326640559\\FileRecv\\MobileFile\\IMG_0196.JPG";
    //上传到七牛后保存的文件名    访问为：http://oswj11a86.bkt.clouddn.com/daimo6.png
    String key = "dinner.JPG";


    /*//密钥配置
    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);*/
    //创建上传对象
    UploadManager uploadManager =new UploadManager(new Configuration());


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return auth.uploadToken(spacename);
    }

    @GetMapping(value = "abcde")  //很乱的一个测试api，无重要作用
    @ResponseBody
    public String qiniuTest(){
        String kind=FilePath.substring(FilePath.lastIndexOf("."));
        log.warn(ACCESS_KEY+"  "+SECRET_KEY+"  "+spacename);
        log.info(kind);
        System.out.println(ACCESS_KEY+"  "+SECRET_KEY+"  "+spacename+"\n"+kind);
        return ACCESS_KEY+"  "+SECRET_KEY+"  "+spacename+"\n"+kind;
    }

    @PostMapping(value = "qiniuTEST")
    public String upload(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 1、获得文件：
        MultipartFile file = multipartRequest.getFile("testFile");

        //2、通过IO流将MultipartFile转为java.io.File
        InputStream input = file.getInputStream();
        byte[] byt = new byte[input.available()];
        input.read(byt);

        //3、获取文件名称：testKey
        String originalFilename = file.getOriginalFilename();
        String kind=originalFilename.substring(originalFilename.lastIndexOf("."));
        String testKey=(Math.random()*100)+kind;

        //4、将byte数组转换为IOFile：
        File iofile = new File("D:\\"+testKey);
        OutputStream output = new FileOutputStream(iofile);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
        bufferedOutput.write(byt);

        //5、关闭用完的流  【不然后面无法删除本地缓存】
        bufferedOutput.close();
        output.close();
        input.close();

        try {
            //调用put方法上传
            //uploadManager.put({文件路径}, {上传后的名称}, getUpToken());
            Response res = uploadManager.put(iofile, testKey, getUpToken());
            //打印返回的信息
            log.info(res.bodyString());
            log.info(String.valueOf(res.statusCode));//200为上传成功
            log.info("originalFilename={},testKey={},isDeleted={}",
                    originalFilename, testKey, iofile.delete());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            log.warn(r.toString());
        }
        String returnUrl=openUrl+"/"+testKey;
        log.info("returnUrl={}",returnUrl);
        return "redirect:"+returnUrl;
    }

    @PostMapping(value = "manyQiniuTEST")
    public String manyupload(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 1、获得文件：
        List<MultipartFile> manyfiles = multipartRequest.getFiles("manyTestFile");

        for (int i=0;i<manyfiles.size();i++) {
            MultipartFile file=manyfiles.get(i);
            //2、通过IO流将MultipartFile转为java.io.File
            InputStream input = file.getInputStream();
            byte[] byt = new byte[input.available()];
            input.read(byt);

            //3、获取文件名称：testKey
            String originalFilename = file.getOriginalFilename();
            String kind = originalFilename.substring(originalFilename.lastIndexOf("."));
            String testKey = (Math.random() * 100) + kind;

            //4、将byte数组转换为IOFile：
            File iofile = new File("D:\\" + testKey);
            OutputStream output = new FileOutputStream(iofile);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(byt);

            //5、关闭用完的流  【不然后面无法删除本地缓存】
            bufferedOutput.close();
            output.close();
            input.close();

            try {
                //调用put方法上传
                //uploadManager.put({文件路径}, {上传后的名称}, getUpToken());
                Response res = uploadManager.put(iofile, testKey, getUpToken());
                //打印返回的信息
                log.info("【第"+(i+1)+"个文件】"+"originalFilename={},testKey={},isDeleted={}",
                        originalFilename, testKey, iofile.delete());//200为上传成功
                log.info(res.bodyString());
            } catch (QiniuException e) {
                Response r = e.response;
                // 请求失败时打印的异常的信息
                log.warn(r.toString());
            }
            String returnUrl = openUrl + "/" + testKey;
            log.info("returnUrl={}", returnUrl);
        }
        return "all success";
    }

}