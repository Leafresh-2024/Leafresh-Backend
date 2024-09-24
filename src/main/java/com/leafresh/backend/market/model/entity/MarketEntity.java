package com.leafresh.backend.market.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "market")
public class MarketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Integer marketId;

    @Column(name = "maket_category", nullable = false)
    private String marketCategory;

    @Column(name = "market_title", nullable = false)
    private String marketTitle;

    @Column(name = "market_content", nullable = false)
    private String marketContent;

    @Column(name = "market_image", nullable = false)
    private String marketImage;

    @Column(name = "market_created_at")
    @CreationTimestamp
    private LocalDateTime marketCreatedAt; // 분양글 등록일자

    @Column(name = "market_updated_at")
    @UpdateTimestamp
    private LocalDateTime marketUpdatedAt; // 분양글 수정일자

    @Column(name = "market_complated_at")
    @UpdateTimestamp
    private LocalDateTime marketComplatedAt; // 분양 완료일자

    @Column(name = "market_status", nullable = false)
    private boolean marketStatus; // 분양중인지 분양완료인지를 구분하는 상태

    @Column(name = "market_visible_scope", nullable = false)
    private VisibleScope marketVisibleScope; // 게시글 공개 범위 (전체공개, 팔로워 공개, 비공개, 삭제된 게시글)

    @Column(name = "user_id")
    private Integer userId; // 작성자 이메일

    public MarketEntity() {
    }

    private MarketEntity(Builder builder) {
        this.marketCategory = builder.marketCategory;
        this.marketTitle = builder.marketTitle;
        this.marketContent = builder.marketContent;
        this.marketImage = builder.marketImage;
        this.marketStatus = builder.marketStatus;
        this.marketVisibleScope = builder.marketVisibleScope;
        this.userId = builder.userId;
    }

    public static class Builder{
        private String marketCategory;
        private String marketTitle;
        private String marketContent;
        private String marketImage;
        private Boolean marketStatus;
        private VisibleScope marketVisibleScope;
        private Integer userId;

        public Builder marketCategory(String marketCategory) {
            this.marketCategory = marketCategory;
            return this;
        }

        public Builder marketTitle(String marketTitle) {
            if (marketTitle.length() > 50) {
                throw new IllegalArgumentException("제목은 50자 미만만 가능합니다.");
            } else {
                this.marketTitle = marketTitle;
            }
            return this;
        }

        public Builder marketContent(String marketContent) {
            if (marketContent.length() > 1000) {
                throw new IllegalArgumentException("내용은 1000자 미만만 가능합니다.");
            } else {
                this.marketContent = marketContent;
            }
            return this;
        }

        public Builder marketImage(String marketImage) {
            this.marketImage = marketImage;
            return this;
        }

        public Builder marketStatus(Boolean marketStatus) {
            this.marketStatus = marketStatus;
            return this;
        }

        public Builder marketVisibleScope(VisibleScope marketVisibleScope) {
            this.marketVisibleScope = marketVisibleScope;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public MarketEntity build(){
            if (marketCategory == null || marketTitle == null || marketContent == null || marketImage == null) {
                throw new IllegalArgumentException("모든 필드를 채워주세요.");
            }
            return new MarketEntity(this);
        }
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public void setMarketCategory(String marketCategory) {
        this.marketCategory = marketCategory;
    }

    public void setMarketTitle(String marketTitle) {
        this.marketTitle = marketTitle;
    }

    public void setMarketContent(String marketContent) {
        this.marketContent = marketContent;
    }

    public void setMarketImage(String marketImage) {
        this.marketImage = marketImage;
    }

    public void setMarketCreatedAt(LocalDateTime marketCreatedAt) {
        this.marketCreatedAt = marketCreatedAt;
    }

    public void setMarketUpdatedAt(LocalDateTime marketUpdatedAt) {
        this.marketUpdatedAt = marketUpdatedAt;
    }

    public void setMarketComplatedAt(LocalDateTime marketComplatedAt) {
        this.marketComplatedAt = marketComplatedAt;
    }

    public void setMarketStatus(boolean marketStatus) {
        this.marketStatus = marketStatus;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public String getMarketCategory() {
        return marketCategory;
    }

    public String getMarketTitle() {
        return marketTitle;
    }

    public String getMarketContent() {
        return marketContent;
    }

    public String getMarketImage() {
        return marketImage;
    }

    public LocalDateTime getMarketCreatedAt() {
        return marketCreatedAt;
    }

    public LocalDateTime getMarketUpdatedAt() {
        return marketUpdatedAt;
    }

    public LocalDateTime getMarketComplatedAt() {
        return marketComplatedAt;
    }

    public boolean isMarketStatus() {
        return marketStatus;
    }

    public VisibleScope getMarketVisibleScope() {
        return marketVisibleScope;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setMarketVisibleScope(VisibleScope marketVisibleScope) {
        this.marketVisibleScope = marketVisibleScope;
    }

    @Override
    public String toString() {
        return "MarketEntity{" +
                "marketId=" + marketId +
                ", marketCategory='" + marketCategory + '\'' +
                ", marketTitle='" + marketTitle + '\'' +
                ", marketContent='" + marketContent + '\'' +
                ", marketImage='" + marketImage + '\'' +
                ", marketCreatedAt=" + marketCreatedAt +
                ", marketUpdatedAt=" + marketUpdatedAt +
                ", marketComplatedAt=" + marketComplatedAt +
                ", marketStatus=" + marketStatus +
                ", marketVisibleScope=" + marketVisibleScope +
                ", userId='" + userId + '\'' +
                '}';
    }
}
