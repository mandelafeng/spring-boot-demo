//package com.cfhui.service;
//
//import com.zxl.sdk.ZXLSDK;
//import com.zxl.sdk.bean.KeyPair;
//import com.zxl.sdk.bean.QueryEvData;
//import com.zxl.sdk.bean.SaveEvData;
//
//import java.util.List;
//
///**
// * []
// *
// * @author cfhui
// * @version V1
// * @date 2023/7/28 上午 10:57
// */
//public class BlackChainService {
//    // https://open.zxinchain.com/documentcenter#%E6%8E%A5%E5%85%A5%E6%B5%81%E7%A8%8B
//    public static void main(String[] args) {
//        // 初始化
//        ZXLSDK zxlsdk = new ZXLSDK("200515000110001", "0e4bce1b0ef8471fb9140b849e776f48");
//        // 获取公钥
//        KeyPair keyPair = zxlsdk.generateKeyPair();
//        String publicKey = keyPair.getPublicKey();
//        String privateKey = keyPair.getPrivateKey();
//        // 路径为存证文件在物理机上的地址
//        String evidencePath = "E:\\MyWorkspace\\Data\\msme\\OpenSCENARIO_v0.9.1_examples\\OpenSCENARIO_v0.9.1\\Examples\\DE\\Einscherer\\Einscherer.xosc";
//        // 文件hash计算
//        String hash = zxlsdk.calculateFileHash(evidencePath);
//        // hash存证场景，不对原文进行报送。
//        SaveEvData saveEvDataRes = zxlsdk.evidenceSave(hash, "扩展信息", privateKey, publicKey);
//        // 上传原文信息
//        zxlsdk.evidenceSave(hash, "", "扩展信息", privateKey, publicKey);
//        // 存证Id
//        System.out.println(saveEvDataRes.getEvId());
//        // 交易hash
//        System.out.println(saveEvDataRes.getTxHash());
//        // 区块高度
//        System.out.println(saveEvDataRes.getBlockHeight());
//        // 创建时间
//        System.out.println(saveEvDataRes.getCreateTime());
//        // 拓展信息
//        System.out.println(saveEvDataRes.getExt());
//
//        // ---------------链上信息查询------------------
//        // 根据存证id查询
//        List<QueryEvData> queryEvData = zxlsdk.queryWithEvId("");
//        // 根据文件hash查询
//        zxlsdk.queryWithEvHash("");
//        // 根据交易hash查询
//        zxlsdk.queryWithTxHash("");
//
//        // ---------------
//
//
//    }
//
//}
