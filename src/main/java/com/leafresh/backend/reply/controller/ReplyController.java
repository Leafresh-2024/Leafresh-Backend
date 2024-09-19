package com.leafresh.backend.reply.controller;

import com.leafresh.backend.oauth.security.CurrentUser;
import com.leafresh.backend.oauth.security.UserPrincipal;
import com.leafresh.backend.reply.model.dto.ReplyDTO;
import com.leafresh.backend.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeds")
public class ReplyController {
    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/{id}/reply") // 피드 댓글 전체 출력
    public List<ReplyDTO> getAllReply(@PathVariable Integer id) {
        return replyService.allReplyList(id); // 피드 id를 넘겨줌
    }

    @PostMapping("/{id}/addreply") // 댓글 등록
    public ResponseEntity<?> create(@PathVariable Integer id, @RequestBody ReplyDTO replyDTO, @CurrentUser UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return ResponseEntity.status(401).body("로그인 정보가 없습니다. 다시 로그인 해 주세요.");
        }

        if (id == null) {
            return ResponseEntity.status(415).body("피드가 존재하지 않습니다.");
        }

        if (replyDTO.getReplyContent() == null || replyDTO.getReplyContent().isEmpty()) {
            return ResponseEntity.status(415).body("댓글을 작성해주세요.");
        }

        ReplyDTO createdDTO = replyService.createReply(id, replyDTO, userPrincipal);
        System.out.println("댓글등록 dto확인 "+createdDTO);

        if (createdDTO != null) {
            return ResponseEntity.ok(createdDTO);
        } else {
            return ResponseEntity.status(500).body("댓글이 등록되지 않았습니다.");
        }
    }

    @PutMapping("/{id}/editreply/{replyId}") // 댓글 수정
    public ResponseEntity<?> edit(@PathVariable Integer id, @PathVariable Integer replyId,
                                  @RequestBody ReplyDTO replyDTO, @CurrentUser UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).body("로그인 정보가 없습니다. 다시 로그인 해 주세요.");
        }

        if (id == null) {
            return ResponseEntity.status(415).body("피드가 존재하지 않습니다.");
        }

        if (replyId == null) {
            return ResponseEntity.status(415).body("댓글이 존재하지 않습니다.");
        }

        if (replyDTO.getReplyContent() == null || replyDTO.getReplyContent().isEmpty()) {
            return ResponseEntity.status(415).body("댓글을 작성해주세요.");
        }
        ReplyDTO editDTO = replyService.editReply(replyId, replyDTO, userPrincipal);

        if (editDTO != null) {
            return ResponseEntity.ok(editDTO);
        } else {
            return ResponseEntity.status(500).body("다시 시도해주세요.");
        }
    }

    @DeleteMapping("/{id}/deletereply/{replyId}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @PathVariable Integer replyId, @CurrentUser UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return ResponseEntity.status(401).body("로그인 정보가 없습니다. 다시 로그인 해 주세요.");
        }

        if (id == null) {
            return ResponseEntity.status(415).body("피드가 존재하지 않습니다.");
        }

        if (replyId == null) {
            return ResponseEntity.status(415).body("댓글이 존재하지 않습니다.");
        }

        int result = replyService.deleteReply(replyId, userPrincipal);

        if (result == 0) {
            return ResponseEntity.status(200).body("댓글 삭제가 완료되었습니다.");
        } else {
            return ResponseEntity.status(500).body("다시 시도해주세요.");
        }
    }

}
