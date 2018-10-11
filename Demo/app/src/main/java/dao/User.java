package dao;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private DateBean data;

    public DateBean getDate() {
        return data;
    }

    public void setDate(DateBean data) {
        this.data = data;
    }

    public static class DateBean implements Serializable{
        private List<ComicBean> comics;

        public List<ComicBean> getComic() {
            return comics;
        }

        public void setComic(List<ComicBean> comics) {
            this.comics = comics;
        }

        public static class ComicBean implements Serializable{
            private String cover_image_url;
            private String title;
            private String label_text;
            private String label_color;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getLabel_color() {
                return label_color;
            }

            public void setLabel_color(String label_color) {
                this.label_color = label_color;
            }

            private TopicBean topic;

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLabel_text() {
                return label_text;
            }

            public void setLabel_text(String label_text) {
                this.label_text = label_text;
            }

            public TopicBean getTopic() {
                return topic;
            }

            public void setTopic(TopicBean topic) {
                this.topic = topic;
            }

            public static class TopicBean implements Serializable{
                private String title;
                private UserBean user;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public static class UserBean implements Serializable{
                    private String nickname;

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }
                }
            }
        }
    }
}
