package com.leafresh.backend.market.service;

import com.leafresh.backend.market.model.dto.MarketDTO;
import com.leafresh.backend.oauth.security.UserPrincipal;

import java.util.List;

public interface MarketService {
    List<MarketDTO> allMarketList(); // 분양글 전체조회
    MarketDTO createPost(MarketDTO marketDTO, UserPrincipal userPrincipal); // 분양글 등록하기
    MarketDTO detailPost(Integer id); // 분양글 상세보기
    MarketDTO modifyPost(MarketDTO marketDTO, UserPrincipal userPrincipal, Integer id); // 분양글 수정
    int deletePost(UserPrincipal userPrincipal, Integer id); // 분양글 삭제(상태만 변경할예정)
    void updateMarketStatus(Integer id, Boolean status); // 분양글 분양중/분양완료 상태
    Long countSales(String nickname); // 분양글 수 카운트 (userId기준)

}
