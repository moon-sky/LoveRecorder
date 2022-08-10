/**
  * Copyright 2022 bejson.com 
  */
package com.moonvsky.testbirthday.service.github;

/**
 * Auto-generated: 2022-08-09 18:29:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class CommentDetail {

    private String url;
    private String html_url;
    private String issue_url;
    private long id;
    private String node_id;
    private User user;
    private String created_at;
    private String upStringd_at;
    private String author_association;
    private String body;
    private Reactions reactions;
    private String performed_via_github_app;
    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setHtml_url(String html_url) {
         this.html_url = html_url;
     }
     public String getHtml_url() {
         return html_url;
     }

    public void setIssue_url(String issue_url) {
         this.issue_url = issue_url;
     }
     public String getIssue_url() {
         return issue_url;
     }

    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setNode_id(String node_id) {
         this.node_id = node_id;
     }
     public String getNode_id() {
         return node_id;
     }

    public void setUser(User user) {
         this.user = user;
     }
     public User getUser() {
         return user;
     }

    public void setCreated_at(String created_at) {
         this.created_at = created_at;
     }
     public String getCreated_at() {
         return created_at;
     }

    public void setUpStringd_at(String upStringd_at) {
         this.upStringd_at = upStringd_at;
     }
     public String getUpStringd_at() {
         return upStringd_at;
     }

    public void setAuthor_association(String author_association) {
         this.author_association = author_association;
     }
     public String getAuthor_association() {
         return author_association;
     }

    public void setBody(String body) {
         this.body = body;
     }
     public String getBody() {
         return body;
     }

    public void setReactions(Reactions reactions) {
         this.reactions = reactions;
     }
     public Reactions getReactions() {
         return reactions;
     }

    public void setPerformed_via_github_app(String performed_via_github_app) {
         this.performed_via_github_app = performed_via_github_app;
     }
     public String getPerformed_via_github_app() {
         return performed_via_github_app;
     }

}