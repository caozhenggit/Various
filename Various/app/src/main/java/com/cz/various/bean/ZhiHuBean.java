package com.cz.various.bean;

import java.util.List;

/**
 * @author caozheng
 * @date 2017/12/12
 *
 * describe:
 */

public class ZhiHuBean {

    /**
     * date : 20171114
     * stories : [{"images":["https://pic4.zhimg.com/v2-bd1ad32415a59c5c287b75c22449814b.jpg"],"type":0,"id":9656689,"ga_prefix":"111422","title":"小事 · 这样的老婆你值得拥有"},{"title":"快看，我们当中混入了一个特务","ga_prefix":"111421","images":["https://pic2.zhimg.com/v2-ed1be5bb3f6851ab076f98fec95fb991.jpg"],"multipic":true,"type":0,"id":9656880},{"images":["https://pic1.zhimg.com/v2-4c7adef61e9918938a904cf51086bd74.jpg"],"type":0,"id":9656750,"ga_prefix":"111420","title":"满足这些要求，让微信聊天记录帮你上法庭「讨个说法」"},{"images":["https://pic1.zhimg.com/v2-c768ca31a5345ee237cc69f3f0bfb6e8.jpg"],"type":0,"id":9656896,"ga_prefix":"111419","title":"手术后不能吃牛、羊、鱼等「发物」，这是真的吗？"},{"images":["https://pic1.zhimg.com/v2-5dcf3b0d4adb863ab8394e15c4088448.jpg"],"type":0,"id":9656618,"ga_prefix":"111418","title":"开一家不就完了，为什么有些公司偏要开那么多子公司？"},{"images":["https://pic4.zhimg.com/v2-b56913dba903fb437d307d8180f95eef.jpg"],"type":0,"id":9656875,"ga_prefix":"111417","title":"意大利无缘 2018 年俄罗斯世界杯，最大的问题就是「穷」"},{"images":["https://pic4.zhimg.com/v2-fa71739b3b16b2d346e6ab1bb76a5157.jpg"],"type":0,"id":9655899,"ga_prefix":"111416","title":"快别盯着我了，你越瞅，我发挥越失常"},{"images":["https://pic2.zhimg.com/v2-9e2065eb6b33f24d829efc185e2dac1d.jpg"],"type":0,"id":9656783,"ga_prefix":"111415","title":"比起凶手，为什么大家更不肯放过刘鑫？"},{"images":["https://pic2.zhimg.com/v2-208737f6311a9bfc3e3a74bd7e424c05.jpg"],"type":0,"id":9656630,"ga_prefix":"111414","title":"进医院第一件事就是量血压，电视剧这么演，有道理吗？"},{"images":["https://pic4.zhimg.com/v2-3a77b644adca22a2dc38331956abbf17.jpg"],"type":0,"id":9656772,"ga_prefix":"111413","title":"不买会员，只看 5 秒就可以点击「跳过广告」，好蠢啊\u2026\u2026"},{"images":["https://pic4.zhimg.com/v2-a2829c5cdba190914bc807cd085c83d3.jpg"],"type":0,"id":9656651,"ga_prefix":"111412","title":"大误 · 化学专业的人，这么厉害的吗"},{"title":"《口袋妖怪》这么成功，任天堂为什么不给马里奥和塞尔达出动画？","ga_prefix":"111411","images":["https://pic2.zhimg.com/v2-6af99fd2d662fbc6b7b2ae9d3367ac75.jpg"],"multipic":true,"type":0,"id":9656802},{"images":["https://pic4.zhimg.com/v2-4e483dbdbecdaae555a590245e042ebb.jpg"],"type":0,"id":9656466,"ga_prefix":"111410","title":"为了尽早把快递送到你的手里，它们顶个小托盘，不知疲惫地穿梭"},{"images":["https://pic4.zhimg.com/v2-8a6043c4ce919ed832d39b3807b3c08f.jpg"],"type":0,"id":9656721,"ga_prefix":"111409","title":"这婚不想结了，送出的彩礼还能再要回来吗？"},{"images":["https://pic4.zhimg.com/v2-b00fa318b6106c3d415b7f062b97a487.jpg"],"type":0,"id":9656574,"ga_prefix":"111408","title":"银行转账是直接划走我的钱，还是确认对方后才划走？"},{"images":["https://pic2.zhimg.com/v2-1d6297c95c379f1f34935a22614c1055.jpg"],"type":0,"id":9656667,"ga_prefix":"111407","title":"中国的古典园林这么大，却没有供人晒太阳的大草坪\u2026\u2026"},{"images":["https://pic3.zhimg.com/v2-81432cdb5b4fe52619fbcd7ba5f4872a.jpg"],"type":0,"id":9656730,"ga_prefix":"111407","title":"- 江母发起签名请愿是干预司法吗？\r\n- 完全合法，她能做的也只有这些了"},{"images":["https://pic1.zhimg.com/v2-4d77afb2516bf5d6011b063853649f78.jpg"],"type":0,"id":9656354,"ga_prefix":"111407","title":"中年马东之「异想」：穿越智慧与快乐的狭窄缝隙"},{"images":["https://pic4.zhimg.com/v2-a42e8a2a71faed32a56328354753d633.jpg"],"type":0,"id":9656423,"ga_prefix":"111406","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-bd1ad32415a59c5c287b75c22449814b.jpg"]
         * type : 0
         * id : 9656689
         * ga_prefix : 111422
         * title : 小事 · 这样的老婆你值得拥有
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
