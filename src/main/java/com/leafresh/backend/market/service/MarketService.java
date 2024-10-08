package com.leafresh.backend.market.service;

import com.leafresh.backend.market.model.dto.MarketDTO;
import com.leafresh.backend.market.model.entity.MarketEntity;
import com.leafresh.backend.market.model.entity.VisibleScope;
import com.leafresh.backend.market.repository.MarketRepository;
import com.leafresh.backend.common.exception.ResourceNotFoundException;
import com.leafresh.backend.oauth.model.User;
import com.leafresh.backend.oauth.security.CurrentUser;
import com.leafresh.backend.oauth.security.UserPrincipal;
import com.leafresh.backend.oauth.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MarketService implements MarketServiceImpl {
    private final MarketRepository marketRepository;
    private final UserServiceImpl userServiceImpl; // UserRepo를 직접적으로 사용하지 않아서 결합을 느슨히 함

    // println 로그 부분 수정필요

    @Autowired
    public MarketService(MarketRepository marketRepository, UserServiceImpl userServiceImpl) {
        this.marketRepository = marketRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public List<MarketDTO> allMarketList() {
        List<MarketEntity> entities = marketRepository.findAll();
        List<MarketDTO> marketList = new ArrayList<>();

        if (entities.size() > 0) { // 게시글이 한 개라도 있는지 확인
            for (MarketEntity entity : entities) {
                MarketDTO dto = new MarketDTO();
                dto.setMarketId(entity.getMarketId());
                dto.setMarketCategory(entity.getMarketCategory());
                dto.setMarketTitle(entity.getMarketTitle());
                dto.setMarketContent(entity.getMarketContent());
                dto.setMarketImage(entity.getMarketImage());
                dto.setMarketStatus(entity.isMarketStatus());
                dto.setMarketCreatedAt(entity.getMarketCreatedAt());
                dto.setMarketVisibleScope(entity.getMarketVisibleScope());
                dto.setUserId(entity.getUserId());
                marketList.add(dto);
            }
            return marketList;
        }
        return new ArrayList<>(); // 게시글이 없을때 빈 리스트 반환..? 애매하다
    }

    @Override
    public List<MarketDTO> myMarketList(String userNickname) {
        Optional<User> users = userServiceImpl.findByUserNickname(userNickname); // usernickname으로 정보조회함

        if (users.isPresent()) { // 유저가 있으면
            Integer userId = users.get().getUserId(); // 원예일지 주인의 id를 가져옴
            List<MarketEntity> marketEntities = marketRepository.findAllByUserId(userId);
            List<MarketDTO> marketDTOList = new ArrayList<>();
            if (marketEntities.size() > 0) {
                for (MarketEntity entity : marketEntities) {
                    MarketDTO dto = new MarketDTO();
                    dto.setMarketId(entity.getMarketId());
                    dto.setMarketCategory(entity.getMarketCategory());
                    dto.setMarketTitle(entity.getMarketTitle());
                    dto.setMarketContent(entity.getMarketContent());
                    dto.setMarketImage(entity.getMarketImage());
                    dto.setMarketStatus(entity.isMarketStatus());
                    dto.setMarketCreatedAt(entity.getMarketCreatedAt());
                    dto.setMarketVisibleScope(entity.getMarketVisibleScope());
                    dto.setUserId(entity.getUserId());
                    marketDTOList.add(dto);
                }
                return marketDTOList;
            } else {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public MarketDTO createPost(MarketDTO marketDTO, @CurrentUser UserPrincipal userPrincipal) {
        Integer principalUserId = userPrincipal.getUserId(); // 인증된 유저의 고유한 id 값
        Optional<User> user = userServiceImpl.findById(principalUserId); // id값을 기준으로 user 객체를 꺼내옴

        if (user.isPresent()) { // 유저가 존재하면
            MarketEntity entity = new MarketEntity.Builder()
                    .marketCategory(marketDTO.getMarketCategory())
                    .marketTitle(marketDTO.getMarketTitle())
                    .marketContent(marketDTO.getMarketContent())
                    .marketImage(marketDTO.getMarketImage())
                    .marketStatus(true) // 등록되었을때는 무조건 분양중인 초기상태로 시작
                    .marketVisibleScope(marketDTO.getMarketVisibleScope())
                    .userId(principalUserId) // 이메일을 엔티티에 담아줌
                    .build();
            MarketEntity savedEntity = marketRepository.save(entity);

            if (savedEntity != null) { // 저장이 잘 되었는지 확인
                MarketDTO savedDTO = new MarketDTO(); // dto에 다시 전달해줌
                savedDTO.setMarketId(savedEntity.getMarketId());
                savedDTO.setMarketCategory(savedEntity.getMarketCategory());
                savedDTO.setMarketTitle(savedEntity.getMarketTitle());
                savedDTO.setMarketContent(savedEntity.getMarketContent());
                savedDTO.setMarketImage(savedEntity.getMarketImage());
                savedDTO.setMarketCreatedAt(savedEntity.getMarketCreatedAt());
                savedDTO.setMarketStatus(savedEntity.isMarketStatus());
                savedDTO.setMarketVisibleScope(savedEntity.getMarketVisibleScope());
                savedDTO.setUserId(savedEntity.getUserId());

                return savedDTO;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public MarketDTO detailPost(Integer id) {
        Optional<MarketEntity> findEntity = marketRepository.findById(id); // id값으로 조회
        MarketDTO detailDTO = new MarketDTO();

        if (findEntity.isPresent()) { // 조회된 값이 있으면 dto에 넣어서 반환
            detailDTO.setMarketId(findEntity.get().getMarketId());
            detailDTO.setMarketCategory(findEntity.get().getMarketCategory());
            detailDTO.setMarketTitle(findEntity.get().getMarketTitle());
            detailDTO.setMarketContent(findEntity.get().getMarketContent());
            detailDTO.setMarketImage(findEntity.get().getMarketImage());
            detailDTO.setMarketStatus(findEntity.get().isMarketStatus());
            detailDTO.setMarketVisibleScope(findEntity.get().getMarketVisibleScope());
            detailDTO.setMarketCreatedAt(findEntity.get().getMarketCreatedAt());
            detailDTO.setUserId(findEntity.get().getUserId());
        }
        return detailDTO;
    }

    @Override
    @Transactional
    public MarketDTO modifyPost(MarketDTO marketDTO, @CurrentUser UserPrincipal userPrincipal, Integer id) {
        Integer userId = userPrincipal.getUserId(); // 현재 인증된 유저의 아이디값을 가져옴
        Optional<User> user = userServiceImpl.findById(userId); // user id값 기준으로 유저 엔터티 조회
        Optional<MarketEntity> marketEntity = marketRepository.findById(id); // url id값 기준으로 게시글 조회

        if (user.isPresent()) { // 유저가 존재하면
            if (marketEntity.isPresent()) { // 게시글이 존재하면
                MarketEntity market = marketEntity.get();
                market.setMarketCategory(marketDTO.getMarketCategory());
                market.setMarketTitle(marketDTO.getMarketTitle());
                market.setMarketContent(marketDTO.getMarketContent());
                market.setMarketImage(marketDTO.getMarketImage());
                market.setMarketVisibleScope(marketDTO.getMarketVisibleScope());
                market.setUserId(userId);

                MarketEntity savedEntity =  marketRepository.save(market);

                if (savedEntity != null) {
                    MarketDTO savedDTO = new MarketDTO();
                    savedDTO.setMarketId(savedEntity.getMarketId());
                    savedDTO.setMarketCategory(savedEntity.getMarketCategory());
                    savedDTO.setMarketTitle(savedEntity.getMarketTitle());
                    savedDTO.setMarketContent(savedEntity.getMarketContent());
                    savedDTO.setMarketImage(savedEntity.getMarketImage());
                    savedDTO.setMarketCreatedAt(savedEntity.getMarketCreatedAt());
                    savedDTO.setMarketStatus(savedEntity.isMarketStatus());
                    savedDTO.setMarketVisibleScope(savedEntity.getMarketVisibleScope());
                    savedDTO.setUserId(savedEntity.getUserId());

                    return savedDTO;
                } else {
                    return null;
                }
            } else { // 수정할 게시글이 존재하지 않으면
                return null;
            }
        } else { // 로그인 한 사용자가 없으면
            return null;
        }
    }

    @Override
    @Transactional
    public int deletePost(@CurrentUser UserPrincipal userPrincipal, @PathVariable Integer id){
        Integer userId = userPrincipal.getUserId(); // 로그인한 사용자의 userId를 가져옴
        Optional<User> user = userServiceImpl.findById(userId); // 유저정보 가져옴
        Optional<MarketEntity> market = marketRepository.findById(id); // 게시글 데이터 가져옴

        int result = 0;

        if (user.isPresent()) { // 유저가 존재하면
            if (market.isPresent()) { // 게시글이 존재하면
                MarketEntity marketEntity = market.get(); // 게시글 엔티티 가져오기
                marketEntity.setMarketVisibleScope(VisibleScope.MARKET_DELETE); // 공개범위를 MARKET_DELETE로 수정
                marketRepository.save(marketEntity);
                return result;
            } else { // 삭제하려는 게시글이 존재하지 않으면
                result = 1;
                return result;
            }
        } else { // 로그인 한 사용자가 없으면
            result = 1;
            return result;
        }
    }

    @Override
    @Transactional
    public int updateMarketStatus(Integer id, Boolean status) { // 분양중/분양완료 상태
        Optional<MarketEntity> marketEntity = marketRepository.findById(id);
        int result = 0;

        if (marketEntity.isPresent()) { // 게시글이 존재하면
            MarketEntity market = marketEntity.get();
            market.setMarketStatus(status); // status를 업데이트해줌
            marketRepository.save(market);
            return result;
        } else { // 게시글이 존재하지 않으면
            result = 1;
            return result;
        }
    }

    @Override
    @Transactional
    public Long countSales(String nickname) {
        User user = userServiceImpl.findByUserNickname(nickname)
                .orElseThrow(() -> new ResourceNotFoundException("User", "nickname", nickname));

        // 유저 이메일을 기반으로 마켓 글 수 카운트
        return marketRepository.countByUserId(user.getUserId());
    }

}
