package com.leafresh.backend.market.model.dto;

import com.leafresh.backend.market.model.entity.VisibleScope;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class MarketDTO {
    private Integer marketId;

    @NotBlank(message = "카테고리는 필수입니다.")
    private String marketCategory;

    @NotBlank(message = "제목은 필수입니다.")
    private String marketTitle;

    @NotBlank(message = "내용은 필수입니다.")
    private String marketContent;

    @NotBlank(message = "사진은 필수입니다.")
    private String marketImage;

    private boolean marketStatus;
    private VisibleScope marketVisibleScope; // 게시글 공개 범위
    private LocalDateTime marketCreatedAt;
    private Integer userId;

    public MarketDTO() {
    }

    public MarketDTO(Integer marketId, String marketCategory, String marketTitle, String marketContent, String marketImage, boolean marketStatus, VisibleScope marketVisibleScope, LocalDateTime marketCreatedAt, Integer userId) {
        this.marketId = marketId;
        this.marketCategory = marketCategory;
        this.marketTitle = marketTitle;
        this.marketContent = marketContent;
        this.marketImage = marketImage;
        this.marketStatus = marketStatus;
        this.marketVisibleScope = marketVisibleScope;
        this.marketCreatedAt = marketCreatedAt;
        this.userId = userId;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getMarketCategory() {
        return marketCategory;
    }

    public void setMarketCategory(String marketCategory) {
        this.marketCategory = marketCategory;
    }

    public String getMarketTitle() {
        return marketTitle;
    }

    public void setMarketTitle(String marketTitle) {
        this.marketTitle = marketTitle;
    }

    public String getMarketContent() {
        return marketContent;
    }

    public void setMarketContent(String marketContent) {
        this.marketContent = marketContent;
    }

    public String getMarketImage() {
        return marketImage;
    }

    public void setMarketImage(String marketImage) {
        this.marketImage = marketImage;
    }

    public boolean isMarketStatus() {
        return marketStatus;
    }

    public void setMarketStatus(boolean marketStatus) {
        this.marketStatus = marketStatus;
    }

    public VisibleScope getMarketVisibleScope() {
        return marketVisibleScope;
    }

    public void setMarketVisibleScope(VisibleScope marketVisibleScope) {
        this.marketVisibleScope = marketVisibleScope;
    }

    public LocalDateTime getMarketCreatedAt() {
        return marketCreatedAt;
    }

    public void setMarketCreatedAt(LocalDateTime marketCreatedAt) {
        this.marketCreatedAt = marketCreatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MarketDTO{" +
                "marketId=" + marketId +
                ", marketCategory='" + marketCategory + '\'' +
                ", marketTitle='" + marketTitle + '\'' +
                ", marketContent='" + marketContent + '\'' +
                ", marketImage='" + marketImage + '\'' +
                ", marketStatus=" + marketStatus +
                ", marketVisibleScope=" + marketVisibleScope +
                ", marketCreatedAt=" + marketCreatedAt +
                ", userId='" + userId + '\'' +
                '}';
    }
}
