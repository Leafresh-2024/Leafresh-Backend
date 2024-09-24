package com.leafresh.backend.reply.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Integer replyId;

    @Column(name = "feed_id", nullable = false)
    private Integer feedId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "reply_content", nullable = false)
    private String replyContent;

    @Column(name = "reply_created_at")
    @CreationTimestamp
    private LocalDateTime replyCreatedAt;

    @Column(name = "reply_updated_at")
    @UpdateTimestamp
    private LocalDateTime replyUpdatedAt; // 등록할 때 크리에이트를 여기다 띄워놓고 수정하면 이 날짜 보여줄것

    @Column(name = "reply_deleted_at")
    @UpdateTimestamp
    private LocalDateTime replyDeletedAt;

    @Column(name = "reply_status", nullable = false)
    private boolean replyStatus; // 댓글 삭제되었을때

    public ReplyEntity() {
    }

    public ReplyEntity(Builder builder) {
        this.feedId = builder.feedId;
        this.userId = builder.userId;
        this.replyContent = builder.replyContent;
        this.replyStatus = builder.replyStatus;
    }

    public static class Builder{
        private Integer feedId;
        private Integer userId;
        private String replyContent;
        private boolean replyStatus;

        public Builder feedId(Integer feedId) {
            this.feedId = feedId;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder replyContent(String replyContent) {
            if (replyContent.length() > 100) {
                throw new IllegalArgumentException("댓글 작성은 100자 미만만 가능합니다.");
            } else {
                this.replyContent = replyContent;
                return this;
            }
        }

        public Builder replyStatus(boolean replyStatus) {
            this.replyStatus = replyStatus;
            return this;
        }

        public ReplyEntity build() {
            if (feedId == null) {
                throw new IllegalArgumentException("댓글을 작성할 피드가 없습니다.");
            }
            if (userId == null) {
                throw new IllegalArgumentException("사용자 정보가 없습니다.");
            }
            if (replyContent == null) {
                throw new IllegalArgumentException("댓글 작성은 100자 미만만 가능합니다.");
            }
            return new ReplyEntity(this);
        }
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setReplyCreatedAt(LocalDateTime replyCreatedAt) {
        this.replyCreatedAt = replyCreatedAt;
    }

    public void setReplyUpdatedAt(LocalDateTime replyUpdatedAt) {
        this.replyUpdatedAt = replyUpdatedAt;
    }

    public void setReplyDeletedAt(LocalDateTime replyDeletedAt) {
        this.replyDeletedAt = replyDeletedAt;
    }

    public void setReplyStatus(boolean replyStatus) {
        this.replyStatus = replyStatus;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public LocalDateTime getReplyCreatedAt() {
        return replyCreatedAt;
    }

    public LocalDateTime getReplyUpdatedAt() {
        return replyUpdatedAt;
    }

    public LocalDateTime getReplyDeletedAt() {
        return replyDeletedAt;
    }

    public boolean isReplyStatus() {
        return replyStatus;
    }

    @Override
    public String toString() {
        return "ReplyEntity{" +
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
