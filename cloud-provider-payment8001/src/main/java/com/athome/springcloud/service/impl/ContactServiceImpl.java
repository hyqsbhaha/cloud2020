package com.athome.springcloud.service.impl;

import com.athome.springcloud.entities.Contact;
import com.athome.springcloud.service.ContactService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Override
    public Contact save(Contact contact) {
        String[] ts = contact.getBirthday().split("T");
        contact.setBirthday(ts[0]);
        return null;
    }

    @Override
    public List<Contact> list() {
        return null;
    }

    @Override
    public Contact findById(Integer id) {
        return null;
    }

    @Override
    public String deleteById(Integer id) {
        return "删除成功";
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        System.out.println(file);
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        String newfileName = (new Date()).getTime()+suffixName;
        // 文件上传后的路径
        String filePath = "E://scratch//";
        File dest = new File(filePath + newfileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        //保存在本地
        file.transferTo(dest);
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAI4FyPrQNHzvofkAfuNYrS";
        String accessKeySecret = "lMtBgcu1GQp83DFuM2Lwuoan2c5RHE";

        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
//        InputStream inputStream = new FileInputStream(dest);
//        ossClient.putObject("hyq-first-one",newfileName , inputStream);
//        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
//        String url = ossClient.generatePresignedUrl("hyq-first-one", newfileName, expiration).toString();
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        dest.deleteOnExit();
        String res = "http://hyq-first-one.oss-cn-hangzhou.aliyuncs.com/"+newfileName;
        return res;
    }

}
