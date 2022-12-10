package com.aneesh.queryservice.repositories;

import com.aneesh.queryservice.entities.PostData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostDataRepository extends JpaRepository<PostData, Long> {
}
