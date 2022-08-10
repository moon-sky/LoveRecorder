package com.moonvsky.testbirthday.service.github;

import java.util.List;

public class Comments {
    private List<CommentBean> comments;

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

    public List<CommentBean> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "comments=" + comments +
                '}';
    }
}
