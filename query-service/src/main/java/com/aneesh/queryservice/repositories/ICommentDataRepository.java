package com.aneesh.queryservice.repositories;

import com.aneesh.queryservice.entities.CommentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDataRepository extends JpaRepository<CommentData, Long> {
}
