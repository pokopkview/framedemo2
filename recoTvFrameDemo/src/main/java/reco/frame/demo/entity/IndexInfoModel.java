package reco.frame.demo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UPC on 2016/12/9.
 */
public class IndexInfoModel implements Parcelable {


    /**
     * title : 首页
     * style : 001
     * pageinfo : {"block_img":[{"block_location_below":0,"block_location_right":0,"block_name":"亚马逊","block_img_url":"http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg","block_id":10001,"block_type":"banner","block_size":1},{"block_location_below":11,"block_location_right":19,"block_name":"荒地","block_img_url":"http://tupian.qqjay.com/u/2016/0912/1_141620_8.jpg","block_id":10002,"block_type":"single","block_size":2},{"block_location_below":11,"block_location_right":21,"block_name":"汉兰达","block_img_url":"http://pic6.nipic.com/20100421/3568841_232300045931_2.jpg","block_id":10003,"block_type":"banner","block_size":3},{"block_location_below":11,"block_location_right":22,"block_name":"iPhone","block_img_url":"http://travel.taiwan.cn/list/201502/W020150225370474710043.jpg","block_id":10004,"block_type":"singer","block_size":3},{"block_location_below":11,"block_location_right":29,"block_name":"冬雪","block_img_url":"http://imgsrc.baidu.com/forum/w%3D580/sign=84ed02fb7b310a55c424defc87444387/c9bf41a7d933c895a2980073d31373f08302006b.jpg","block_id":10005,"block_type":"singer","block_size":3}]}
     */

    private String title;
    private String style;
    private PageinfoEntity pageinfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public PageinfoEntity getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(PageinfoEntity pageinfo) {
        this.pageinfo = pageinfo;
    }

    public static class PageinfoEntity implements Parcelable {
        /**
         * block_location_below : 0
         * block_location_right : 0
         * block_name : 亚马逊
         * block_img_url : http://img5.pcpop.com/ArticleImages/0X0/3/3071/003071620.jpg
         * block_id : 10001
         * block_type : banner
         * block_size : 1
         */

        private List<BlockImgEntity> block_img;

        public List<BlockImgEntity> getBlock_img() {
            return block_img;
        }

        public void setBlock_img(List<BlockImgEntity> block_img) {
            this.block_img = block_img;
        }

        public static class BlockImgEntity implements Parcelable {
            private int block_width;
            private int block_hight;
            private String block_name;
            private String block_img_url;
            private int block_id;
            private String block_type;
            private int block_size;

            public String getBlock_name() {
                return block_name;
            }

            public void setBlock_name(String block_name) {
                this.block_name = block_name;
            }

            public String getBlock_img_url() {
                return block_img_url;
            }

            public void setBlock_img_url(String block_img_url) {
                this.block_img_url = block_img_url;
            }

            public int getBlock_id() {
                return block_id;
            }

            public void setBlock_id(int block_id) {
                this.block_id = block_id;
            }

            public String getBlock_type() {
                return block_type;
            }

            public void setBlock_type(String block_type) {
                this.block_type = block_type;
            }

            public int getBlock_size() {
                return block_size;
            }

            public void setBlock_size(int block_size) {
                this.block_size = block_size;
            }

            public int getBlock_width() {
                return block_width;
            }

            public void setBlock_width(int block_width) {
                this.block_width = block_width;
            }

            public int getBlock_hight() {
                return block_hight;
            }

            public void setBlock_hight(int block_gight) {
                this.block_hight = block_gight;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.block_width);
                dest.writeInt(this.block_hight);
                dest.writeString(this.block_name);
                dest.writeString(this.block_img_url);
                dest.writeInt(this.block_id);
                dest.writeString(this.block_type);
                dest.writeInt(this.block_size);
            }

            public BlockImgEntity() {
            }

            protected BlockImgEntity(Parcel in) {
                this.block_width = in.readInt();
                this.block_hight = in.readInt();
                this.block_name = in.readString();
                this.block_img_url = in.readString();
                this.block_id = in.readInt();
                this.block_type = in.readString();
                this.block_size = in.readInt();
            }

            public static final Creator<BlockImgEntity> CREATOR = new Creator<BlockImgEntity>() {
                @Override
                public BlockImgEntity createFromParcel(Parcel source) {
                    return new BlockImgEntity(source);
                }

                @Override
                public BlockImgEntity[] newArray(int size) {
                    return new BlockImgEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.block_img);
        }

        public PageinfoEntity() {
        }

        protected PageinfoEntity(Parcel in) {
            this.block_img = new ArrayList<BlockImgEntity>();
            in.readList(this.block_img, BlockImgEntity.class.getClassLoader());
        }

        public static final Creator<PageinfoEntity> CREATOR = new Creator<PageinfoEntity>() {
            @Override
            public PageinfoEntity createFromParcel(Parcel source) {
                return new PageinfoEntity(source);
            }

            @Override
            public PageinfoEntity[] newArray(int size) {
                return new PageinfoEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.style);
        dest.writeParcelable(this.pageinfo, flags);
    }

    public IndexInfoModel() {
    }

    protected IndexInfoModel(Parcel in) {
        this.title = in.readString();
        this.style = in.readString();
        this.pageinfo = in.readParcelable(PageinfoEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<IndexInfoModel> CREATOR = new Parcelable.Creator<IndexInfoModel>() {
        @Override
        public IndexInfoModel createFromParcel(Parcel source) {
            return new IndexInfoModel(source);
        }

        @Override
        public IndexInfoModel[] newArray(int size) {
            return new IndexInfoModel[size];
        }
    };
}
