package reco.frame.demo.entity;

import java.util.List;

/**
 * Created by UPC on 2016/12/13.
 */
public class MainPageModel {

    /**
     * name : indexpage
     * main_title : [{"title":"首页","style":"001","pageinfo":{"block_img":[{"block_location_below":"","block_location_right":"","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"19","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":2},{"block_location_below":"11","block_location_right":"21","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":3},{"block_location_below":"11","block_location_right":"22","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":3},{"block_location_below":"11","block_location_right":"29","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":3}]}},{"title":"旅行咨询","style":"002","pageinfo":{"block_img":[{"block_location_below":"","block_location_right":"","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"19","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":2},{"block_location_below":"11","block_location_right":"21","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":3},{"block_location_below":"11","block_location_right":"22","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":3},{"block_location_below":"11","block_location_right":"29","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":3}]}},{"title":"目的地","style":"003","pageinfo":{"block_img":[{"block_location_below":"","block_location_right":"","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"19","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":2},{"block_location_below":"11","block_location_right":"21","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":3},{"block_location_below":"11","block_location_right":"22","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":3},{"block_location_below":"11","block_location_right":"29","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":3}]}},{"title":"美食酒店","style":"004","pageinfo":{"block_img":[{"block_location_below":"","block_location_right":"","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"19","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":2},{"block_location_below":"11","block_location_right":"21","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":3},{"block_location_below":"11","block_location_right":"22","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":3},{"block_location_below":"11","block_location_right":"29","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":3}]}},{"title":"风格途趣","style":"005","pageinfo":{"block_img":[{"block_location_below":"","block_location_right":"","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"19","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":2},{"block_location_below":"11","block_location_right":"21","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":3},{"block_location_below":"11","block_location_right":"22","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":3},{"block_location_below":"11","block_location_right":"29","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":3}]}},{"title":"随身锦囊","style":"006","pageinfo":{"block_img":[{"block_location_below":"11","block_location_right":"11","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"11","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":1},{"block_location_below":"11","block_location_right":"11","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"11","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":1},{"block_location_below":"11","block_location_right":"11","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":1}]}},{"title":"专题","style":"007","pageinfo":{"block_img":[{"block_location_below":"","block_location_right":"","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"19","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":2},{"block_location_below":"11","block_location_right":"21","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":3},{"block_location_below":"11","block_location_right":"22","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":3},{"block_location_below":"11","block_location_right":"29","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":3}]}},{"title":"个人设置","style":"008","pageinfo":{"block_img":[{"block_location_below":"","block_location_right":"","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"19","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":2},{"block_location_below":"11","block_location_right":"21","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":3},{"block_location_below":"11","block_location_right":"22","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":3},{"block_location_below":"11","block_location_right":"29","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":3}]}}]
     */

    private String name;
    /**
     * title : 首页
     * style : 001
     * pageinfo : {"block_img":[{"block_location_below":"","block_location_right":"","block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":"0001","block_type":"banner","block_size":1},{"block_location_below":"11","block_location_right":"19","block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":"0002","block_type":"single","block_size":2},{"block_location_below":"11","block_location_right":"21","block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":"0003","block_type":"banner","block_size":3},{"block_location_below":"11","block_location_right":"22","block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":"0004","block_type":"singer","block_size":3},{"block_location_below":"11","block_location_right":"29","block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":"0005","block_type":"singer","block_size":3}]}
     */

    private List<IndexInfoModel> main_title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IndexInfoModel> getMain_title() {
        return main_title;
    }

    public void setMain_title(List<IndexInfoModel> main_title) {
        this.main_title = main_title;
    }

}