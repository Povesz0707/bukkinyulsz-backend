package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.ActiveStatusInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.News;
import com.example.bukkinyulszbackend.services.NewsService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_NEWS)
public class NewsController extends BaseController<News> implements ActiveStatusInterface<News> {

    private NewsService service;

    @Autowired
    public void setService(NewsService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<News>> activeList() throws BusinessException {
        List<News> list = this.service.findAllActiveDesc();
        return returnListResponse(list);
    }

    @GetMapping(AppConstant.URI_API_ACTIVE_3)
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseEntity<List<News>> activeTop3() throws BusinessException {
        List<News> list = this.service.findTop3ActiveDesc();
        return returnListResponse(list);
    }


    @Override
    public ResponseEntity<List<News>> list() throws BusinessException {
        final List<News> fileStores = this.service.list();
        return returnListResponse(fileStores);
    }

    @Override
    public ResponseEntity<News> getById(long id) throws BusinessException {
        final News fileStore = this.service.getById(id);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<News> add(News newData) throws BusinessException {
        final News fileStore = this.service.add(newData);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<News> edit(News data) throws BusinessException {
        final News fileStore = this.service.edit(data);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.service.delete(id);
        return returnBooleanResponse(result);
    }
}
