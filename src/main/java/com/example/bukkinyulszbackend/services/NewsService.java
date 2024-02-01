package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.News;
import com.example.bukkinyulszbackend.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NewsService extends BaseService<News> implements BaseServiceInterface<News>{
        private NewsRepository repository;

        @Autowired
        public void setRepository(NewsRepository repository) {
            this.repository = repository;
        }

        @Override
        public Boolean delete(long id) throws BusinessException {
            this.repository.deleteById(id);
            this.repository.flush();
            return null;
        }

        @Override
        public News add(News data) throws BusinessException {
            News marking = this.repository.save(data);
            this.repository.flush();
            return marking;
        }

        @Override
        public List<News> list() throws BusinessException {
            List<News> all = this.repository.findAll();
            return all;
        }

        @Override
        public News getById(long id) throws BusinessException {
            Optional<News> optionalMarking = this.repository.findById(id);
            return isPresent(optionalMarking);
        }

        @Override
        public News edit(News data) throws BusinessException {
            News marking = getById(data.getId());
            marking.edit(data);
            News saved = this.repository.save(marking);
            this.repository.flush();
            return saved;
        }

        public List<News> findAllActiveDesc() throws BusinessException{
            List<News> list = this.repository.findAllByActiveIsTrueOrderByInsertedDesc();
            return list;
        }
        public List<News> findTop3ActiveDesc() throws BusinessException{
            List<News> list = this.repository.findTop3ByActiveIsTrueOrderByInsertedDesc();
            return list;
        }
}
