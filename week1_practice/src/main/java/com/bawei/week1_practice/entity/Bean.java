package com.bawei.week1_practice.entity;

import java.util.List;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 15:20
 * @description
 */
public class Bean {

    /**
     * result : [{"commodityId":104,"commodityName":"OPPO R17 全网通 8G+128G 美拍补光灯+美容补水仪套餐 全面屏AI智慧美颜双摄拍照手机","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/5/1.jpg","price":3799,"saleNum":0},{"commodityId":125,"commodityName":"Sony/索尼 DSC-KW1 靓咔 自拍神器 数码相机 美颜相机","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/zxj/5/1.jpg","price":799,"saleNum":0},{"commodityId":173,"commodityName":"新番祖 电脑包 13.3/14/15.6英寸苹果笔记本电脑包 女 手提公文包 贝壳 樱花粉-13.3英寸及以下（淑女款）","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/dnb/4/1.jpg","price":119,"saleNum":1},{"commodityId":189,"commodityName":"高尔夫GOLF锦纶双肩包男士个性旅行背包大容量电脑背包D8BV33913J","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/sjb/6/1.jpg","price":179,"saleNum":0},{"commodityId":121,"commodityName":"佳能（Canon）EOS 3000D 入门级数码单反相机18-55mm IS II防抖镜头套装 32G内存卡 备用电池 三脚架摄影包等礼包版","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/zxj/1/1.jpg","price":3099,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 104
         * commodityName : OPPO R17 全网通 8G+128G 美拍补光灯+美容补水仪套餐 全面屏AI智慧美颜双摄拍照手机
         * masterPic : http://172.17.8.100/images/small/commodity/sjsm/sj/5/1.jpg
         * price : 3799
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
