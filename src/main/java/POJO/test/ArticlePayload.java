package POJO.test;

import java.util.List;

public class ArticlePayload {

    public class Article{

        private String title;
        private String description;
        private String body;
        private List<String> tagList;

        public Article(){}

        public Article(String title, String description, String body, List<String> tagList){
            this.title = title;
            this.description = description;
            this.body = body;
            this.tagList = tagList;
        }

        public List<String> getTagList() {
            return tagList;
        }

        public void setTagList(List<String> tagList) {
            this.tagList = tagList;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    private Article article;

    public ArticlePayload(){}

    public ArticlePayload(Article article){
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
