package com.leafresh.backend.reply.model.dto;

import java.time.LocalDateTime;

public class ReplyDTO {
    private Integer replyId;
    private Integer feedId;
    private Integer userId;
    private String replyContent;
    private LocalDateTime replyCreatedAt;
    private LocalDateTime replyUpdatedAt;
    private LocalDateTime replyDeletedAt;
    private boolean replyStatus; // 댓글이 삭제되었을 때

    public ReplyDTO() {
    }

    public ReplyDTO(Integer replyId, Integer feedId, Integer userId, String replyContent, LocalDateTime replyCreatedAt, LocalDateTime replyUpdatedAt, LocalDateTime replyDeletedAt, boolean replyStatus) {
        this.replyId = replyId;
        this.feedId = feedId;
        this.userId = userId;
        this.replyContent = replyContent;
        this.replyCreatedAt = replyCreatedAt;
        this.replyUpdatedAt = replyUpdatedAt;
        this.replyDeletedAt = replyDeletedAt;
        this.replyStatus = replyStatus;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public LocalDateTime getReplyCreatedAt() {
        return replyCreatedAt;
    }

    public void setReplyCreatedAt(LocalDateTime replyCreatedAt) {
        this.replyCreatedAt = replyCreatedAt;
    }

    public LocalDateTime getReplyUpdatedAt() {
        return replyUpdatedAt;
    }

    public void setReplyUpdatedAt(LocalDateTime replyUpdatedAt) {
        this.replyUpdatedAt = replyUpdatedAt;
    }

    public LocalDateTime getReplyDeletedAt() {
        return replyDeletedAt;
    }

    public void setReplyDeletedAt(LocalDateTime replyDeletedAt) {
        this.replyDeletedAt = replyDeletedAt;
    }

    public boolean isReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(boolean replyStatus) {
        this.replyStatus = replyStatus;
    }

    @Override
    public String toString() {
        return "ReplyDTO{" +
                "replyId=" + replyId +
                ", feedId=" + feedId +
                ", userId=" + userId +
                ", replyContent='" + replyContent + '\'' +
                ", replyCreatedAt=" + replyCreatedAt +
                ", replyUpdatedAt=" + replyUpdatedAt +
                ", replyDeletedAt=" + replyDeletedAt +
                ", replyStatus=" + replyStatus +
                '}';
    }
}
