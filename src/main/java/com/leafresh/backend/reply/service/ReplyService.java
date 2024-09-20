package com.leafresh.backend.reply.service;

import com.leafresh.backend.oauth.security.CurrentUser;
import com.leafresh.backend.oauth.security.UserPrincipal;
import com.leafresh.backend.reply.model.dto.ReplyDTO;
import com.leafresh.backend.reply.model.entity.ReplyEntity;
import com.leafresh.backend.reply.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Transactional
    public List<ReplyDTO> allReplyList(Integer id) {
        List<ReplyEntity> replyEntities = replyRepository.findByFeedId(id); // 피드 id 기준으로 뽑아와야함
        List<ReplyDTO> allReplyDTO = new ArrayList<>();

        if (replyEntities != null) {
            for (ReplyEntity replyEntity : replyEntities) {
                ReplyDTO dto = new ReplyDTO();
                dto.setReplyId(replyEntity.getReplyId());
                dto.setFeedId(replyEntity.getFeedId());
                dto.setUserId(replyEntity.getUserId());
                dto.setReplyContent(replyEntity.getReplyContent());
                dto.setReplyUpdatedAt(replyEntity.getReplyUpdatedAt());
                dto.setReplyStatus(replyEntity.isReplyStatus());
                allReplyDTO.add(dto);
            }
        }
        return allReplyDTO;
    }

    @Transactional
    public ReplyDTO createReply(Integer id, ReplyDTO replyDTO, @CurrentUser UserPrincipal currentUser) {
        Integer currentUserId = currentUser.getUserId(); // 인증된 유저 id 가져오기!

        ReplyEntity entity = new ReplyEntity.Builder()
                .feedId(id)
                .userId(currentUserId)
                .replyContent(replyDTO.getReplyContent())
                .replyStatus(true) // 초기에 댓글 등록시에는 true로 세팅
                .build();
        replyRepository.save(entity);

        if (entity != null) {
            ReplyDTO savedDTO = new ReplyDTO();
            savedDTO.setReplyId(entity.getReplyId());
            savedDTO.setFeedId(entity.getFeedId());
            savedDTO.setUserId(entity.getUserId());
            savedDTO.setReplyContent(entity.getReplyContent());
            savedDTO.setReplyCreatedAt(entity.getReplyCreatedAt());
            savedDTO.setReplyUpdatedAt(entity.getReplyUpdatedAt());
            savedDTO.setReplyStatus(entity.isReplyStatus());

            return savedDTO;
        } else {
            return null;
        }
    }

    @Transactional
    public ReplyDTO editReply(Integer replyId, ReplyDTO replyDTO, @CurrentUser UserPrincipal currentUser) {
        Optional<ReplyEntity> replyEntity = replyRepository.findById(replyId); // 리플id 기준으로 엔터티 조회
        if (currentUser != null) { // 현재 인증된 유저가 있고
            if (replyEntity.isPresent()) { // 댓글이 존재하면
                ReplyEntity editEntity = replyEntity.get();
                editEntity.setReplyContent(replyDTO.getReplyContent()); // 수정내용반영
                editEntity.setReplyCreatedAt(replyEntity.get().getReplyCreatedAt()); // 작성일은 그대로 가져옴
                replyRepository.save(editEntity);

                if (editEntity != null) {
                    ReplyDTO dto = new ReplyDTO();
                    dto.setReplyId(replyEntity.get().getReplyId());
                    dto.setFeedId(replyEntity.get().getFeedId());
                    dto.setUserId(replyEntity.get().getUserId());
                    dto.setReplyContent(replyEntity.get().getReplyContent());
                    dto.setReplyCreatedAt(replyEntity.get().getReplyCreatedAt());
                    dto.setReplyUpdatedAt(replyEntity.get().getReplyUpdatedAt());
                    dto.setReplyStatus(replyEntity.get().isReplyStatus());

                    return dto;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } return null;
    }

    @Transactional
    public int deleteReply(Integer replyId, @CurrentUser UserPrincipal currentUser) {
        int result = 0;
        Optional<ReplyEntity> replyEntity = replyRepository.findById(replyId);

        if (currentUser != null) {
            if (replyEntity.isPresent()) {
                ReplyEntity deleteEntity = replyEntity.get();
                deleteEntity.setReplyStatus(false); // 리플상태를 false로 바꿈
                replyRepository.save(deleteEntity);
                return result;
            } else {
                System.out.println("삭제하려는 게시글이 존재하지 않음.");
                result = 1;
                return result;
            }
        } else {
            System.out.println("로그인 한 사용자가 존재하지 않음.");
            result = 1;
            return result;
        }
    }
}
