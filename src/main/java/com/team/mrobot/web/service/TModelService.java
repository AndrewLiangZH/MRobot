package com.team.mrobot.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.team.mrobot.web.domain.TModel;

/**
 * Project: TaaS
 * Author: AndrewLiang
 * Date: 2017/9/23
 * Description: Service of TModel
 */
public interface TModelService {

    /**
     * Add, edit or save TModel
     *
     * @param TModel
     * @return
     */
    TModel saveOrUpdate(TModel TModel);

    /**
     * Remove TModel by id
     *
     * @param id
     */
    void removeModel(Long id);

    /**
     * Get TModel by id
     *
     * @param id
     * @return
     */
    TModel getModelById(Long id);

    /**
     * List and paging TModel by name like
     *
     * @param name
     * @param pageable
     * @return
     */
    Page<TModel> listModelsByNameLike(String name, Pageable pageable);

}
