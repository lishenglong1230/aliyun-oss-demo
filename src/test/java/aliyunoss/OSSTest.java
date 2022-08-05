package aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.AccessControlList;
import com.aliyun.oss.model.CannedAccessControlList;
import org.junit.Test;

/**
 * @Author: Lishenglong
 * @Date: 2022/8/5 14:25
 */

public class OSSTest {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = "LTAI5t6u9Q1DBFwYQkRVm4Fc";
    String accessKeySecret = "h5hb220jYEQNGsU9dE70Yohxmsw3mr";
    String bucketName = "finance-file-bigdragon-1";

    @Test
    public void testCreateBucket() {


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.createBucket(bucketName);//代码创建默认private

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void test(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 设置存储空间的读写权限。例如将examplebucket的读写权限ACL设置为私有Private。
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

        // 获取存储空间的读写权限。
        AccessControlList acl = ossClient.getBucketAcl(bucketName);
        System.out.println(acl.toString());

        // 判断存储空间examplebucket是否存在。如果返回值为true，则存储空间存在，如果返回值为false，则存储空间不存在。
        boolean exists = ossClient.doesBucketExist(bucketName);
        System.out.println(exists);

        ossClient.shutdown();
    }
}
