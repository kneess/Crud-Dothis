package com.anibal.dothis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anibal.dothis.model.Tasklist;

@Repository
public interface TasklistRepository extends JpaRepository<Tasklist, Long>{
	Tasklist findTasklistById(Long id);
	Tasklist findTasklistByTasklistName(String name);
	List<Tasklist> findAllByOrderByTasklistNameAsc();
}
