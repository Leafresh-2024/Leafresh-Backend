package com.leafresh.backend.reply.repository;

import com.leafresh.backend.reply.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {
    List<ReplyEntity> findByFeedId(Integer id); // feedId로 댓글 전체조회하는 메서드!
}
