package top.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.dao.Top10DAO;

@Service
public class MainService {

    @Autowired
    Top10DAO top10DAO;

}
