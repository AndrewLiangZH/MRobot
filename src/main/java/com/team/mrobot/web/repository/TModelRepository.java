package com.team.mrobot.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.team.mrobot.web.domain.TModel;

public interface TModelRepository extends JpaRepository<TModel, Long> {

    /**
     * Query and paging the TModel list by name like
     * @param name
     * @param pageable
     * @return
     */
    Page<TModel> findByNameLike(String name, Pageable pageable);

}
