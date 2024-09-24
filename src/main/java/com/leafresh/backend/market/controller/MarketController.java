package com.leafresh.backend.market.controller;

import com.leafresh.backend.market.model.dto.MarketDTO;
import com.leafresh.backend.market.service.MarketServiceImpl;
import com.leafresh.backend.oauth.security.CurrentUser;
import com.leafresh.backend.oauth.security.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/market")
public class MarketController {
    private MarketServiceImpl marketServiceImpl;

    @Autowired
    public MarketController(MarketServiceImpl marketServiceImpl) {
        this.marketServiceImpl = marketServiceImpl;
    }

    @GetMapping
    public List<MarketDTO> getAllMarkets() {
        return marketServiceImpl.allMarketList();
    }

    @PostMapping("/addpost")
    public ResponseEntity<?> create(@Valid @RequestBody MarketDTO marketDTO, @CurrentUser UserPrincipal userPrincipal){
        System.out.println("컨트롤러호출");
        System.out.println(marketDTO);

        MarketDTO createdDTO = marketServiceImpl.createPost(marketDTO, userPrincipal);

        if (createdDTO != null) { // 게시글이 잘 저장되었으면
            return ResponseEntity.ok(createdDTO);
        } else {
            return ResponseEntity.status(500).body("다시 시도해주세요");
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        if(id <= 0 || id == null) {
            response.put("error", "다시 시도해주세요.");
            return ResponseEntity.status(400).body(response);
        }

        MarketDTO findDTO = marketServiceImpl.detailPost(id);

        if (findDTO != null) {
            response.put("post", findDTO);
            return ResponseEntity.ok(response);
        }else {
            response.put("error", "게시글 조회 실패. 다시 시도해주세요.");
            return ResponseEntity.status(400).body(response);
        }
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<?> modify (
            @Valid @RequestBody MarketDTO marketDTO,
            @PathVariable Integer id,
            @CurrentUser UserPrincipal userPrincipal){

        System.out.println("수정컨트롤핸들러");
        System.out.println(marketDTO);


        if(id <= 0 || id == null) {
            return ResponseEntity.status(500).body("수정할 게시글이 없습니다.");
        }

        if (marketDTO == null) {
            return ResponseEntity.status(500).body("수정하려는 부분을 다시 작성해주세요.");
        }

        MarketDTO modifyDTO = marketServiceImpl.modifyPost(marketDTO, userPrincipal, id);

        if (modifyDTO != null) { // 게시글이 잘 저장되었으면
            return ResponseEntity.ok(modifyDTO);
        } else {
            return ResponseEntity.status(500).body("다시 시도해주세요");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@CurrentUser UserPrincipal userPrincipal, @PathVariable Integer id){
        int result = marketServiceImpl.deletePost(userPrincipal, id);

        if (result == 0) { // 데이터가 잘 삭제되었으면
            return ResponseEntity.status(200).body("게시글 삭제가 완료되었습니다.");
        } else {
            return ResponseEntity.status(500).body("다시 시도해주세요");
        }
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateStatus (@RequestBody Map<String, Boolean> requestBody, @PathVariable Integer id) {
        Boolean marketStatus = requestBody.get("status");

        try {
            marketServiceImpl.updateMarketStatus(id, marketStatus);
            Map<String, String> response = new HashMap<>();
            response.put("message", "상태가 업데이트되었습니다.");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "게시글을 찾을 수 없습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "서버 오류가 발생했습니다."));
        }
    }

    @GetMapping("/sales-count")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getSalesCount(@RequestParam String nickname) {
        Long saleCount = marketServiceImpl.countSales(nickname);
        return ResponseEntity.ok(saleCount);
    }

}
