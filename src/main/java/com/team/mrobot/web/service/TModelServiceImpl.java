package com.team.mrobot.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.team.mrobot.web.domain.TModel;
import com.team.mrobot.web.repository.TModelRepository;

/**
 * Project: TaaS
 * Author: AndrewLiang
 * Date: 2017/9/23
 * Description: Implement of TModel Service
 */
@Service
public class TModelServiceImpl implements TModelService {

    @Autowired
    private TModelRepository TModelRepository;

    @Override
    public TModel saveOrUpdate(TModel TModel) {
        return TModelRepository.save(TModel);
    }

    @Override
    public void removeModel(Long id) {
        TModelRepository.delete(id);
    }

    @Override
    public TModel getModelById(Long id) {
        return TModelRepository.findOne(id);
    }

    @Override
    public Page<TModel> listModelsByNameLike(String name, Pageable pageable) {
        name = "%" + name + "%";
        return TModelRepository.findByNameLike(name, pageable);
    }
}
